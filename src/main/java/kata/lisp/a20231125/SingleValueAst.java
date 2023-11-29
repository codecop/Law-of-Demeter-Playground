package kata.lisp.a20231125;

import java.util.Objects;

/**
 * A node in the syntax tree with a single value.
 */
public abstract class SingleValueAst<T> implements Ast {

    protected final T value;

    public SingleValueAst(T value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    @Override
    public Result eval(Functions context) {
        return new Result(value, getValueType());
    }

    protected abstract ResultType getValueType();

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        SingleValueAst<T> that = this.getClass().cast(other);
        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Ast(" + value + ")";
    }

}
