package kata.lisp.a20231125;

public class BooleanAst extends AbstractAst<Boolean> {

    public BooleanAst(Boolean value) {
        super(value);
    }

    @Override
    public Result eval() {
        return new Result(value, Result.Type.BOOLEAN);
    }

}
