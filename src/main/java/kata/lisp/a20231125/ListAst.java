package kata.lisp.a20231125;

import java.util.Arrays;
import java.util.List;

public class ListAst implements Ast {

    private final List<Ast> children;

    public ListAst(List<Ast> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ListAst)) {
            return false;
        }
        ListAst that = (ListAst) other;
        return this.children.equals(that.children);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(children.toArray());
    }

    @Override
    public String toString() {
        return "Ast(" + children + ")";
    }

}
