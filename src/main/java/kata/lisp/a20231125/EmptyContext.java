package kata.lisp.a20231125;

import java.util.HashMap;
import java.util.Map;

public class EmptyContext implements Context {

    private final Map<String, Function> functions = new HashMap<>();

    @Override
    public void addFunctionNamed(Function function) {
        functions.put(function.getName(), function);
    }

    @Override
    public Function getFunctionNamed(String name) {
        return functions.get(name);
    }

}
