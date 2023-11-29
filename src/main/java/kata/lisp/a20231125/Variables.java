package kata.lisp.a20231125;

import java.util.HashMap;
import java.util.Map;

import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.ResultType;

public class Variables {

    private final Map<String, Result> values = new HashMap<>();

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

}
