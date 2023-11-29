package kata.lisp.a20231125.eval;

import java.util.Objects;

/**
 * An enumeration of result types.
 */
public class ResultType {

    public static final ResultType NUMBER = new ResultType("N", Integer.class);
    public static final ResultType BOOLEAN = new ResultType("B", Boolean.class);
    public static final ResultType STRING = new ResultType("S", String.class);
    public static final ResultType FLOAT = new ResultType("F", Double.class);

    // public static final ResultType SYMBOL = new ResultType("F", String.class);
    public static final ResultType ERROR = new ResultType("E", String.class);

    private final String name;
    private final Class<?> type;

    private ResultType(String name, Class<?> value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
        this.name = name;
        this.type = value;
    }

    public void requireValueType(Object value) {
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
        return Objects.equals(name, that.name) && type == that.type;
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