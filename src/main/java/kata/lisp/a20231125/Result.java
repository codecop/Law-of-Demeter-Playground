package kata.lisp.a20231125;

import java.util.Objects;

/**
 * The result of an AST computation.
 */
public class Result {

    private final Object value;
    private final ResultType type;

    public Result(Object value, ResultType type) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(type);
        type.requireType(value);
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

}

/**
 * An enumeration of result types.
 */
class ResultType {

    static final ResultType ANY = new ResultType("A", Objects.class);
    static final ResultType NUMBER = new ResultType("N", Integer.class);
    static final ResultType BOOLEAN = new ResultType("B", Boolean.class);
    static final ResultType STRING = new ResultType("s", String.class);

    static final ResultType SYMBOL = new ResultType("F", String.class);
    static final ResultType ERROR = new ResultType("E", String.class);

    private final String name;
    private final Class<?> type;

    private ResultType(String name, Class<?> value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
        this.name = name;
        this.type = value;
    }

    public void requireType(Object value) {
        if (!type.isInstance(value)) {
            throw new IllegalArgumentException(value + " and " + toString() + " don't match");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ResultType)) {
            return false;
        }
        ResultType that = (ResultType) other;
        return Objects.equals(name, that.name) && //
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type.getName());
    }

    @Override
    public String toString() {
        return "ResultType " + name + " of " + type;
    }

}
