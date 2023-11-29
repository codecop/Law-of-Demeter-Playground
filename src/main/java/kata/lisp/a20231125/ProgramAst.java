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
    public void accept(AstVisitor visitor) {
        visitor.visitProgram(getChildren());
    }

    @Override
    public String toString() {
        return "Program" + super.toString();
    }

}
