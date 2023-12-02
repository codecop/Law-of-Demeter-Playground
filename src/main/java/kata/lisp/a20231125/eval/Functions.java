package kata.lisp.a20231125.eval;

import java.util.HashMap;
import java.util.Map;

public class Functions {

    private final Map<String, Function> functions = new HashMap<>();

    public void addFunctionNamed(Function function) {
        String name = function.getName(); // LoD_O.2
        functions.put(name, function); // LoD_O.4
    }

    public Function getFunctionNamed(String functionName) {
        return functions.get(functionName); // LoD_O.4
    }

}

// LoD review OK
