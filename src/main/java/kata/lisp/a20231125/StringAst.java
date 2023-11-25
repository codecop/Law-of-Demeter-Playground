package kata.lisp.a20231125;

public class StringAst extends AbstractAst<String> {

    public StringAst(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "Ast(\"" + value + "\")";
    }

}
