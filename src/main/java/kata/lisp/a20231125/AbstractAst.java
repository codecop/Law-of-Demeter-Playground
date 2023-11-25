package kata.lisp.a20231125;

import java.util.Objects;

public abstract class AbstractAst<T> implements Ast {

    protected final T value;

    public AbstractAst(T value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        AbstractAst<T> that = this.getClass().cast(other);
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

    @Override
    public Result eval() {
        return new Result(value, value.getClass());
    }
    
}
