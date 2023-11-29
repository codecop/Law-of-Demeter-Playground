package kata.lisp.a20231125.ast;

public final class NumberAst extends SingleValueAst<Integer> {

    public NumberAst(Integer value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visitNumber(value);
    }

}
