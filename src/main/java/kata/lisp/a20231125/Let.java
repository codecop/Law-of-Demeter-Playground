package kata.lisp.a20231125;

import kata.lisp.a20231125.eval.Function;
import kata.lisp.a20231125.eval.LazyResult;
import kata.lisp.a20231125.eval.Result;

class Let extends Function {

    public Let() {
        super("let");
    }

    @Override
    public boolean matchesArgumentNumber(int parameterCount) {
        return parameterCount == 2; // variables and expression
    }

    @Override
    public Result apply(LazyResult[] arguments) {
        LazyResult variables = arguments[0]; // this is a list of pairs
        LazyResult[] declarations = variables.asList();
        for (LazyResult r : declarations) {
            LazyResult[] pair = r.asList();
            Result symbol = pair[0].eval();
            Result value = pair[1].eval();
            // TODO set context
        }

        LazyResult expression = arguments[1];
        return expression.eval();
    }

}
