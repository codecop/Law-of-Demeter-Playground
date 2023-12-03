package kata.lisp.a20231125.eval;

import java.util.HashMap;
import java.util.Map;

public class Functions {

    private final Map<String, Function> functions = new HashMap<>();

    public void addFunctionNamed(Function function) {
        String name = function.getName(); // LoD_O.2
        functions.put(name, function); // LoD_O.4
    }

    public Result applyFunction(String functionName, LazyResults results, Variables variables) {
        Function function = getFunctionNamed(functionName); // LoD_O.1
        if (function == null) {
            return Result.error("Unknown function symbol " + functionName);
        }
        return function.applyFunction(results, variables); // LoD_O.4 NOPMD if I say all functions are my part
    }

    private Function getFunctionNamed(String functionName) {
        return functions.get(functionName); // LoD_O.4
    }

}

// LoD review OK (if I consider all elements of Map to be "mine")
