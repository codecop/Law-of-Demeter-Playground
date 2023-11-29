package kata.lisp.a20231125;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.eval.Functions;
import kata.lisp.a20231125.eval.Result;

public class Eval {

    public Result eval(Ast ast) {
        Functions context = prepareContext();
        EvalVisitor visitor = new EvalVisitor(context);
        ast.accept(visitor);
        return visitor.result();
    }

    private Functions prepareContext() {
        Functions context = new Functions();
        BasicFunctions.add(context);
        return context;
    }

}
