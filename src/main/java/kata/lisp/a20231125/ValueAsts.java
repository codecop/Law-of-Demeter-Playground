package kata.lisp.a20231125;

public class ValueAsts {

}

class BooleanAst extends SingleValueAst<Boolean> {

    public BooleanAst(Boolean value) {
        super(value);
    }

}

class NumberAst extends SingleValueAst<Integer> {

    public NumberAst(Integer value) {
        super(value);
    }

}

class StringAst extends SingleValueAst<String> {

    public StringAst(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "Ast(\"" + value + "\")";
    }

}
