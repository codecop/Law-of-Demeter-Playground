package kata.lisp.a20231125;

public class Context {

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

        Result error = arguments.error();
        if (error != null) {
            return error;
        }
        error = arguments.typeMismatchWith(function);
        if (error != null) {
            return error;
        }

        return function.execute(arguments); // NOPMD 
        // PMD says object not created locally
        // technically true, but a Map is like a local object with many objects, 
        // so like many local objects, not? Changed it to an array, which is more
        // low level for LoD, still not created locally. True, it comes in via a set.
        // TODO PMD rule: allow a Map or Array of elements as my own fields.
    }

}
