package kata.lisp.a20231125.ast;

public final class StringAst extends SingleValueAst<String> {

    public StringAst(String value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        String value = getValue(); // LoD_O.1 or LoD_Cw.6
        visitor.visitString(value); // LoD_O.2
    }

}

// LoD review OK
