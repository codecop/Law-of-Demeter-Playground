package kata.lisp.a20231125;

public class ValueAsts {

}

final class BooleanAst extends SingleValueAst<Boolean> {

    public BooleanAst(Boolean value) {
        super(value);
    }

    @Override
    protected ResultType getValueType() {
        return ResultType.BOOLEAN;
    }

    @Override
    public void accept(EvalVisitor visitor) {
        visitor.visitBoolean(value);
    }

}

final class NumberAst extends SingleValueAst<Integer> {

    public NumberAst(Integer value) {
        super(value);
    }

    @Override
    protected ResultType getValueType() {
        return ResultType.NUMBER;
    }

    @Override
    public void accept(EvalVisitor visitor) {
        visitor.visitNumber(value);
    }

}

final class StringAst extends SingleValueAst<String> {

    public StringAst(String value) {
        super(value);
    }

    @Override
    protected ResultType getValueType() {
        return ResultType.STRING;
    }

    @Override
    public void accept(EvalVisitor visitor) {
        visitor.visitString(value);
    }

}

// TODO features in AST
// * character with 'a
// * double numbers
