package kata.lisp.a20231125.eval;

/**
 * First class collections for results.
 */
public class Results {

    private final Result[] arguments;

    public Results(Result[] arguments) {
        this.arguments = arguments;
    }

    public Result eval(StrictFunction function) {
        Result error = firstError(); // LoD_O.1
        if (error != null) {
            return error;
        }

        error = typeMismatchWith(function); // LoD_O.1
        if (error != null) {
            return error;
        }

        Object[] values = values(); // LoD_O.1
        return function.apply(values); // LoD_O.2
    }

    public Result firstError() {
        for (int i = 0; i < size(); i++) { // LoD_O.1
            if (arguments[i].isError()) { // LoD_O.4
                return arguments[i];
            }
        }
        return null;
    }

    public Result typeMismatchWith(StrictFunction function) {
        for (int i = 0; i < size(); i++) { // LoD_O.1
            ResultType type = arguments[i].type(); // LoD_O.4
            if (!function.matchesArgumentType(i, type)) { // LoD_O.2
                return Result.error(function.errorMatchingArgumentType(i, type)); // LoD_O.2
            }
        }
        return null;
    }

    public Object[] values() {
        Object[] values = new Object[size()];
        for (int i = 0; i < size(); i++) { // LoD_O.1
            values[i] = arguments[i].value(); // LoD_O.4
        }
        return values;
    }

    private int size() {
        return arguments.length; // LoD_O.4
    }

}

// LoD review OK
