package kata.lisp.a20231125;

public class NumberAst extends AbstractAst<Integer> {

    public NumberAst(Integer value) {
        super(value);
    }

    @Override
    public Result eval() {
        return new Result(value, Result.Type.NUMBER);
    }
}
