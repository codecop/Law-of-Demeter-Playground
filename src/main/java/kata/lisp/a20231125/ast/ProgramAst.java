package kata.lisp.a20231125.ast;

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
        Ast[] children = getChildren(); // LoD_O.1 or LoD_Cw.6
        visitor.visitProgram(children); // LoD_O.2
    }

    @Override
    public String toString() {
        return "Program" + super.toString(); // LoD_Cw.6
    }

}

// LoD review OK
