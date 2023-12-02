package kata.lisp.a20231125.ast;

public final class BooleanAst extends SingleValueAst<Boolean> {

    public BooleanAst(Boolean value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        Boolean value = getValue(); // LoD_O.1 or LoD_Cw.6
        visitor.visitBoolean(value); // LoD_O.2 
    }

}

// LoD review OK
