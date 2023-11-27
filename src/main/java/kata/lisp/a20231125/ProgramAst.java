package kata.lisp.a20231125;

import java.util.List;

public class ProgramAst extends MultipleValueAst {

    public ProgramAst(List<Ast> expressions) {
        super(expressions);
    }

    @Override
    public String toString() {
        return "Program" + super.toString();
    }

    @Override
    public Result eval(Context context) {
        Result lastResult = new Result("Empty Program", Result.Type.ERROR);
        for (Ast ast : asArray()) {
            lastResult = ast.eval(context);
        }
        return lastResult;
    }

}
