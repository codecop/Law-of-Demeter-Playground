package kata.lisp.a20231125;

import kata.lisp.a20231125.eval.Function;
import kata.lisp.a20231125.eval.LazyResult;
import kata.lisp.a20231125.eval.Result;
import kata.lisp.a20231125.eval.Variables;

class Let extends Function {

    public Let() {
        super("let");
    }

    @Override
    public boolean matchesArgumentNumber(int parameterCount) {
        return parameterCount == 2; // variables and expression
    }

    @Override
    public Result apply(LazyResult[] arguments, Variables variables) {
        variables.push();
        setVariables(arguments[0], variables);
        Result result = evalEpression(arguments[1]);
        variables.pop();
        return result;
    }

    private void setVariables(LazyResult localVariables, Variables variables) {
        LazyResult[] listOfPairs = localVariables.asList();
        for (LazyResult r : listOfPairs) {
            LazyResult[] pair = r.asList();
            Result symbol = pair[0].get();
            Result value = pair[1].get();
            setVariable(variables, symbol, value);
        }
    }

    private void setVariable(Variables variables, Result symbol, Result value) {
        variables.add((String) symbol.value(), value);
    }

    private Result evalEpression(LazyResult expression) {
        return expression.get();
    }

}
