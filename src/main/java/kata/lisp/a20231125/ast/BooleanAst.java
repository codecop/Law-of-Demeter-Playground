package kata.lisp.a20231125.ast;

public final class BooleanAst extends SingleValueAst<Boolean> {

    public BooleanAst(Boolean value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visitBoolean(value);
    }

}
