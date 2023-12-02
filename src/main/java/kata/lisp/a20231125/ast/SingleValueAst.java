package kata.lisp.a20231125.ast;

import java.util.Objects;

/**
 * A node in the syntax tree with a single value.
 */
public abstract class SingleValueAst<T> implements Ast {

    private final T value;

    public SingleValueAst(T value) {
        Objects.requireNonNull(value); // ignore
        this.value = value;
    }

    protected T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        // if (other == null || this.getClass() != other.getClass()) { // LoD_O.1, LoD_Cs.1
        if (!this.getClass().isInstance(other)) { // LoD_O.4
            return false;
        }

        // Not PMD conform - argue that a cast is a different operation?
        // SingleValueAst<?> that = (SingleValueAst<?>) other;
        // return this.value.equals(that.getValue()); // LoD_O.4, LoD_O.2

        // PMD conform code, but uses LoD_C
        @SuppressWarnings("unchecked")
        SingleValueAst<T> that = this.getClass().cast(other); // LoD_O.4
        return this.value.equals(that.value); // LoD_O.4, LoD_Cs.1
    }

    @Override
    public int hashCode() {
        return value.hashCode(); // LoD_O.4
    }

    @Override
    public String toString() {
        return "Ast(" + value.toString() + ")"; // LoD_O.4
    }

}

// LoD review OK
