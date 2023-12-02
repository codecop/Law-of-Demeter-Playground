package kata.lisp.a20231125.eval;

import java.util.Objects;

/**
 * The dynamic result of an AST computation of some type.
 */
public class Result {

    private final Object value;
    private final ResultType type;

    public static Result error(String message) {
        return new Result(message, ResultType.ERROR);
    }

    public Result(Object value, ResultType type) {
        Objects.requireNonNull(value); // ignore
        Objects.requireNonNull(type); // ignore
        type.requireProperType(value); // LoD_O.2
        this.value = value;
        this.type = type;
    }

    // TODO type the result with generics
    public Object value() {
        return this.value;
    }

    public ResultType type() {
        return type;
    }

    public boolean isError() {
        return type == ResultType.ERROR;
    }

    @Override
    public String toString() {
        return value + " (" + type + ")"; // LoD_O.4, LoD_O.4
    }

}
