package kata.lisp.a20231125;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.eval.BasicFunctions;
import kata.lisp.a20231125.eval.Functions;
import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.Variables;

public class Eval {

    public Result eval(Ast ast) {
        Functions functions = prepareContextFunctions(); // LoD_O.1
        Variables variables = new Variables();
        return evalUsing(ast, functions, variables); // LoD_O.1
    }

    /* for tests*/ Result evalUsing(Ast ast, Functions functions, Variables variables) {
        EvalVisitor visitor = new EvalVisitor(functions, variables);
        return visitor.eval(ast); // LoD_O.3
    }

    private Functions prepareContextFunctions() {
        Functions functions = new Functions();
        BasicFunctions.addTo(functions);
        return functions;
    }

}

// LoD review OK
