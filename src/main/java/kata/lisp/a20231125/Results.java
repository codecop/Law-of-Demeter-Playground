package kata.lisp.a20231125;

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

    public Result numberMismatchWith(Function function) {
        if (!function.matchesArgumentNumber(size())) {
            String message = "Too many arguments to " + function.toString() + ", got " + size();
            return new Result(message, ResultType.ERROR);
        }
        return null;
    }

    public Result typeMismatchWith(Function function) {
        for (int i = 0; i < size(); i++) {
            if (!function.matchesArgumentType(i, arguments[i].type())) {
                String message = "Type mismatch of " + (i + 1) + ". argument: " + "got " + arguments[i];
                return new Result(message, ResultType.ERROR);
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
