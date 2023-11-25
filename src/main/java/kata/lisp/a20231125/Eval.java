package kata.lisp.a20231125;

public class Eval {

    public Result eval(Ast ast) {
        if (ast instanceof NumberAst) {
            return new Result(((NumberAst) ast).value, Result.Type.NUMBER);
        }
        return null;
    }

}
