package kata.lisp.a20231125;

import java.util.List;

/**
 * A top level node of an expression, containing a list of expressions.
 */
public final class ProgramAst extends MultipleValueAst {

    public ProgramAst(List<Ast> expressions) {
        super(expressions);
    }

    @Override
    public Result eval(Functions context) {
        Result lastResult = new Result("Empty Program", ResultType.ERROR);
        for (Ast ast : getChildren()) {
            lastResult = ast.eval(context);
        }
        return lastResult;
    }

    @Override
    public Result accept(EvalVisitor visitor) {
        return visitor.visitProgram(getChildren());
    }
    
    @Override
    public String toString() {
        return "Program" + super.toString();
    }

}
