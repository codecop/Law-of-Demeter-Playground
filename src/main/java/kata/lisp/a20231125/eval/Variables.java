package kata.lisp.a20231125.eval;

import java.util.HashMap;
import java.util.Map;

public class Variables {

    private final Map<String, Result> values;
    private Variables priority;

    public Variables() {
        this(null, new HashMap<>());
    }

    public Variables(Variables priority, Map<String, Result> previous) {
        this.priority = priority;
        this.values = new HashMap<>(previous);
    }

    public void add(String name, Result value) {
        values.put(name, value);
    }

    public Result get(String variableName) {
        Result result = values.get(variableName);
        if (result == null) {
            return new Result("Unknown symbol " + variableName, ResultType.ERROR);
        }
        return result;
    }

    public void push() {
        priority = new Variables(priority, values);
    }

    public void pop() {
        if (priority != null) {
            values.clear();
            values.putAll(priority.values);
        }
        priority = null;
    }

}
