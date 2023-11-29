package kata.lisp.a20231125;

public class ValueAsts {

}

class BooleanAst extends SingleValueAst<Boolean> {

    public BooleanAst(Boolean value) {
        super(value);
    }

    @Override
    protected ResultType getValueType() {
        return Result.Type.BOOLEAN;
    }

}

class NumberAst extends SingleValueAst<Integer> {

    public NumberAst(Integer value) {
        super(value);
    }

    @Override
    protected ResultType getValueType() {
        return Result.Type.NUMBER;
    }

}

class StringAst extends SingleValueAst<String> {

    public StringAst(String value) {
        super(value);
    }

    @Override
    protected ResultType getValueType() {
        return Result.Type.STRING;
    }

}

// TODO features in AST
// * character with 'a
// * double numbers
