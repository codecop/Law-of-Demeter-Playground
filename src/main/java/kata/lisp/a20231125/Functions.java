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
        return null;
    }

    public Result applyFunction(String value, Ast[] arguments) {
        Function function = getFunctionNamed(value);
        if (function == null) {
            return new Result("Unknown symbol " + value, ResultType.ERROR);
        }
        return applyFunction(function, arguments);
    }

    private Result applyFunction(Function function, Ast[] arguments) {
        return function.execute(arguments, this);
    }

//    public Result applyFunction(String value, Results arguments) {
//        Function function = getFunctionNamed(value);
//        if (function == null) {
//            return new Result("Unknown symbol " + value, ResultType.ERROR);
//        }
//
//        Result error = arguments.firstErrorInArguments();
//        if (error != null) {
//            return error;
//        }
//
//        error = arguments.numberMismatchWith(function);
//        if (error != null) {
//            return error;
//        }
//
//        error = arguments.typeMismatchWith(function);
//        if (error != null) {
//            return error;
//        }
//
//        Object[] values = arguments.toValues();
//        return applyFunction(function, values);
//    }

//    private Result applyFunction(Function function, Object[] values) {
//        return function.execute(values);
//    }

}
