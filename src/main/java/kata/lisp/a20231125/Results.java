package kata.lisp.a20231125;

public class Results {

    private final Result[] arguments;

    public Results(Result[] arguments) {
        this.arguments = arguments;
    }

    public Result error() {
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].type() == ResultType.ERROR) {
                return arguments[i];
            }
        }
        return null;
    }

    public Result typeMismatchWith(Function function) {
        for (int i = 0; i < arguments.length; i++) {
            ResultType expectedType = function.getArgumentType(i);
            if (expectedType != ResultType.ANY && expectedType != arguments[i].type()) {
                String message = "Type mismatch of " + (i + 1) + ". argument: " + //
                        "expected " + expectedType + ", got " + arguments[i];
                return new Result(message, ResultType.ERROR);
            }
        }
        return null;
    }

    public Result get(int i) {
        return arguments[i];
    }

    public int size() {
        return arguments.length;
    }

}
