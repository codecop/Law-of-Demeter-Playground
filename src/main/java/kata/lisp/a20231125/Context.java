package kata.lisp.a20231125;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private final Map<String, Function> functions = new HashMap<>();

    public void addFunctionNamed(Function function) {
        functions.put(function.getName(), function);
    }

    public Function getFunctionNamed(String name) {
        return functions.get(name);
    }

}
