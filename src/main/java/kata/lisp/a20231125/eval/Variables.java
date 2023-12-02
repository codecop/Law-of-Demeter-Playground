package kata.lisp.a20231125.eval;

import java.util.HashMap;
import java.util.Map;

public class Variables {

    private final Map<String, Result> values;
    private Variables variablesStack;

    public Variables() {
        this(null, new HashMap<>());
    }

    public Variables(Variables priority, Map<String, Result> previous) {
        this.variablesStack = priority;
        this.values = new HashMap<>(previous);
    }

    public void add(String name, Result value) {
        values.put(name, value);
    }

    public Result get(String variableName) {
        Result result = values.get(variableName);
        if (result == null) {
            return new Result("Unknown variable symbol " + variableName, ResultType.ERROR);
        }
        return result;
    }

    public void push() {
        variablesStack = new Variables(variablesStack, values);
    }

    public void pop() {
        if (variablesStack != null) {
            values.clear();
            values.putAll(variablesStack.values);
        }
        variablesStack = null;
    }

}
