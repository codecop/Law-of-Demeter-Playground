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
        setVariables(arguments[0]);
        Result result = evalEpression(arguments[1]);
        // pop context
        return result;
    }

    private void setVariables(LazyResult variables) {
        LazyResult[] listOfPairs = variables.asList();
        for (LazyResult r : listOfPairs) {
            LazyResult[] pair = r.asList();
            Result symbol = pair[0].get();
            Result value = pair[1].get();
            // TODO set context
        }
    }

    private Result evalEpression(LazyResult expression) {
        return expression.get();
    }

}
