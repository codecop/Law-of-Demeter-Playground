package kata.lisp.a20231125;

public abstract class ValueAsts {

}

final class BooleanAst extends SingleValueAst<Boolean> {

    public BooleanAst(Boolean value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visitBoolean(value);
    }

}

final class NumberAst extends SingleValueAst<Integer> {

    public NumberAst(Integer value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visitNumber(value);
    }

}

final class StringAst extends SingleValueAst<String> {

    public StringAst(String value) {
        super(value);
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visitString(value);
    }

}

// TODO features in AST
// * character with 'a
// * double numbers
