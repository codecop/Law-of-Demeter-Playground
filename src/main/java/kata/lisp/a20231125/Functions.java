package kata.lisp.a20231125;

public class Functions {

    // private final Map<String, Function> functions = new HashMap<>();
    private final Function[] functions = new Function[100];

    public void addFunctionNamed(Function function) {
        for (int i = 0; i < functions.length; i++) {
            if (functions[i] == null) {
                functions[i] = function;
                break;
            }
        }
    }

    private Function getFunctionNamed(String name) {
        for (int i = 0; i < functions.length; i++) {
            if (functions[i] != null && functions[i].isNamed(name)) {
                return functions[i];
            }
        }
        return new RaiseError("Unknown symbol " + name);
    }

    public Result applyFunction(String value, Results arguments) {
        Function function = getFunctionNamed(value);

        Result error = arguments.firstErrorInArguments();
        if (error != null) {
            return error;
        }

        error = arguments.numberMismatchWith(function);
        if (error != null) {
            return error;
        }

        error = arguments.typeMismatchWith(function);
        if (error != null) {
            return error;
        }

        Object[] values = arguments.toValues();
        return applyFunction(function, values);
    }

    private Result applyFunction(Function function, Object[] values) {
        return function.execute(values);
    }

}

class RaiseError implements Function {

    private final String message;

    public RaiseError(String message) {
        this.message = message;
    }

    @Override
    public boolean isNamed(String name) {
        throw new IllegalStateException();
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        // allow everything
        return true;
    }

    @Override
    public boolean matchesArgumentType(int i, ResultType type) {
        // allow everything
        return true;
    }

    @Override
    public Result execute(Object[] arguments) {
        return new Result(message, ResultType.ERROR);
    }

}
