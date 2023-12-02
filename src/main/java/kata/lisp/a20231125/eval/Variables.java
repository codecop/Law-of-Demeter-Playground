package kata.lisp.a20231125.eval;

import java.util.HashMap;
import java.util.Map;

public class Variables {

    private final Map<String, Result> values;
    private Variables variablesStack;

    public Variables() {
        this(null, new HashMap<>());
    }

    public Variables(Variables variablesStack, Map<String, Result> previous) {
        this.variablesStack = variablesStack;
        this.values = new HashMap<>(previous);
    }

    public void add(String variableName, Result value) {
        values.put(variableName, value); // LoD_O.4
    }

    public Result get(String variableName) {
        Result result = values.get(variableName); // LoD_O.4
        if (result == null) {
            return Result.error("Unknown variable symbol " + variableName); // named constructor
        }
        return result;
    }

    public void push() {
        variablesStack = new Variables(variablesStack, values);
    }

    public void pop() {
        if (variablesStack != null) {
            values.clear(); // LoD_O.4
            Map<String, Result> previousValues = variablesStack.values; // LoD_O.4
            values.putAll(previousValues); // LoD_O.4
        }
        variablesStack = null;
    }

}

// LoD review OK
