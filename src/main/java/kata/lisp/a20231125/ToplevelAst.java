package kata.lisp.a20231125;

import java.util.List;
import java.util.Objects;

public class ToplevelAst implements Ast {

    private final List<Ast> remaining;

    public ToplevelAst(List<Ast> arguments) {
        this.remaining = arguments;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        ToplevelAst that = (ToplevelAst) other;
        return this.remaining.equals(that.remaining);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remaining);
    }

    @Override
    public String toString() {
        return "Ast(" + remaining + ")";
    }

    @Override
    public Result eval(Context context) {
        Ast[] arguments = remaining.toArray(new Ast[remaining.size()]);
        Result lastResult = new Result("Empty Ast", Result.Type.ERROR);
        for (Ast argument : arguments) {
            lastResult = argument.eval(context);
        }
        return lastResult;
    }

}
