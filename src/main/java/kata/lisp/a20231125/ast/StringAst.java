package kata.lisp.a20231125.ast;

public final class StringAst extends SingleValueAst<String> {

    public StringAst(String value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visitString(value);
    }

}
