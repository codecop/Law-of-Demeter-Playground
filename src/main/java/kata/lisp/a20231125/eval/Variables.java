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

    public Result add(LazyResult[] pair) {
        if (pair.length != 2) {
            return Result.error("Too few/many arguments to variable declaration" + // LoD_O.1
                    ", expected " + 2 + ", got " + pair.length); // LoD_O.4
        }
        Result symbol = pair[0].asSymbol(); // LoD_O.2
        Result value = pair[1].get(); // LoD_O.2
        return add(symbol, value); // LoD_O.1
    }

    public Result add(Result symbol, Result value) {
        if (symbol.isError()) { // LoD_O.2
            return symbol;
        }
        String symbolName = (String) symbol.value(); // LoD_O.2
        add(symbolName, value); // LoD_O.1

        return null;
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
