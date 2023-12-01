package kata.lisp.a20231125.eval;

import java.util.Objects;

/**
 * The dynamic result of an AST computation of some type.
 */
public class Result {

    private final Object value;
    private final ResultType type;

    public Result(Object value, ResultType type) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(type);
        type.requireValueType(value);
        this.value = value;
        this.type = type;
    }

    public Object value() {
        return this.value;
    }

    public ResultType type() {
        return type;
    }

    @Override
    public String toString() {
        return value + " (" + type + ")";
    }

    public Result causesTypeMismatchAtPosition(int index) {
        String message = "Type mismatch of " + (index + 1) + ". argument: " + "got " + toString();
        return new Result(message, ResultType.ERROR);
    }

}
