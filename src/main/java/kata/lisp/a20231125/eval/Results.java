package kata.lisp.a20231125.eval;

public class Results {

    private final Result[] arguments;

    public Results(Result[] arguments) {
        this.arguments = arguments;
    }

    public Result firstErrorInArguments() {
        for (int i = 0; i < size(); i++) {
            if (arguments[i].type() == ResultType.ERROR) {
                return arguments[i];
            }
        }
        return null;
    }

    public Result typeMismatchWith(StrictFunction function) {
        for (int i = 0; i < size(); i++) {
            Result argument = arguments[i];
            if (!function.matchesArgumentType(i, argument.type())) {
                return argument.causesTypeMismatchAtPosition(i);
            }
        }
        return null;
    }

    public Object[] toValues() {
        Object[] values = new Object[size()];
        for (int i = 0; i < size(); i++) {
            values[i] = arguments[i].value();
        }
        return values;
    }

    public int size() {
        return arguments.length;
    }

}
