package kata.lisp.a20231125;

import java.util.function.Function;

public class Result {

    static class Type {
        static final Class<Integer> NUMBER = Integer.class;
        static final Class<Boolean> BOOLEAN = Boolean.class;
        static final Class<String> STRING = String.class;
        static final Class<?> SYMBOL = Function.class;
        static final Class<Exception> ERROR = Exception.class;
    }

    private final Object value;
    private final Class<?> type;

    public Result(Object value, Class<?> type) {
        this.value = value;
        this.type = type;
    }

    public Object value() {
        return this.value;
    }

    public Class<?> type() {
        return type;
    }

    @Override
    public String toString() {
        return value + " (" + type + ")";
    }

}
