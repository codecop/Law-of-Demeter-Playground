package kata.lisp.a20231125;

import kata.lisp.a20231125.ast.Ast;

public class Eval {

    public Result eval(Ast ast) {
        Functions context = prepareContext();
        // (1) return ast.eval(context);
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
