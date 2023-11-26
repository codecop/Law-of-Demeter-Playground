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

    public Function getFunctionNamed(String name) {
        for (int i = 0; i < functions.length; i++) {
            if (functions[i].isNamed(name)) {
                return functions[i];
            }
        }
        throw new FunctionLookupError(name);
    }

    public Result applyFunction(String value, Result[] arguments) {
        Function function = getFunctionNamed(value);
        return function.execute(arguments); // NOPMD 
        // PMD says object not created locally
        // technically true, but a Map is like a local object with many objects, 
        // so like many local objects, not? Changed it to an array, which is more
        // low level for LoD, still not created locally. True, it comes in via a set.
    }

}

class FunctionLookupError extends RuntimeException {

    public FunctionLookupError(String name) {
        super(name);
    }

}
