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
    // TODO LIST

    public static final ResultType SYMBOL = new ResultType("F", String.class);
    public static final ResultType ERROR = new ResultType("E", String.class);
    public static final ResultType UNDEFINED = new ResultType("U", String.class);

    private final String name;
    private final Class<?> type;

    private ResultType(String name, Class<?> value) {
        Objects.requireNonNull(name); // ignore
        Objects.requireNonNull(value); // ignore
        this.name = name;
        this.type = value;
    }

    public void requireProperType(Object value) {
        if (!type.isInstance(value)) { // LoD_O.4
            throw new IllegalArgumentException(value + " and " + toString() + " don't match");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ResultType)) {
            return false;
        }
        ResultType that = (ResultType) other;
        return name.equals(that.name) && type == that.type; // LoD_O.4, LoD_Cs.1
    }

    @Override
    public int hashCode() {
        return name.hashCode() ^ type.hashCode(); // LoD_O.4  
    }

    @Override
    public String toString() {
        return "ResultType " + name + " of " + type; // LoD_O.4 implicit toString
    }

}

// LoD review OK
