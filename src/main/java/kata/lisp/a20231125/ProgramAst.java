package kata.lisp.a20231125;

import java.util.List;

/**
 * A top level node of an expression, containing a list of expressions.
 */
public class ProgramAst extends MultipleValueAst {

    public ProgramAst(List<Ast> expressions) {
        super(expressions);
    }

    @Override
    public Result eval(Context context) {
        Result lastResult = new Result("Empty Program", Result.Type.ERROR);
        for (Ast ast : getChildren()) {
            lastResult = ast.eval(context);
        }
        return lastResult;
    }

    @Override
    public String toString() {
        return "Program" + super.toString();
    }

}
