package kata.lisp.a20231125;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.eval.BasicFunctions;
import kata.lisp.a20231125.eval.Functions;
import kata.lisp.a20231125.eval.Result;

public class Eval {

    public Result eval(Ast ast) {
        Functions functions = prepareContextFunctions();
        Variables variables = new Variables();
        return evalUsing(ast, functions, variables);
    }

    /* for tests*/ Result evalUsing(Ast ast, Functions functions, Variables variables) {
        EvalVisitor visitor = new EvalVisitor(functions, variables);
        return visitor.eval(ast);
    }

    private Functions prepareContextFunctions() {
        Functions functions = new Functions();
        BasicFunctions.addTo(functions);
        functions.addFunctionNamed(new Let());
        return functions;
    }

}
