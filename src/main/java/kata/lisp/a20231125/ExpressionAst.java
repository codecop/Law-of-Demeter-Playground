package kata.lisp.a20231125;

import java.util.List;
import java.util.Objects;

public class ExpressionAst implements Ast {

    private final Ast first;
    private final List<Ast> remaining;

    public ExpressionAst(Ast first, List<Ast> arguments) {
        Objects.requireNonNull(first);
        this.first = first;
        this.remaining = arguments;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        ExpressionAst that = (ExpressionAst) other;
        return this.first.equals(that.first) && this.remaining.equals(that.remaining);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first) ^ Objects.hash(remaining);
    }

    @Override
    public String toString() {
        return "Ast(" + first + ": " + remaining + ")";
    }

    @Override
    public Result eval(Context context) {
        SymbolAst symbol = (SymbolAst) first;
        Ast[] arguments = remaining.toArray(new Ast[remaining.size()]);
        return symbol.evalFunction(arguments, context); // NOPMD - "symbol" is false positive to be foreign
    }

}
