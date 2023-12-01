package kata.lisp.a20231125;

import kata.lisp.a20231125.eval.Function;
import kata.lisp.a20231125.eval.LazyResult;
import kata.lisp.a20231125.eval.LazyResults;
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
    public Result apply(LazyResults arguments, Variables variables) {
        variables.push();
        setVariables(arguments.asList(0), variables);
        Result result = arguments.evalArgument(1);
        variables.pop();
        return result;
    }

    private void setVariables(LazyResult[] listOfPairs, Variables variables) {
        for (LazyResult r : listOfPairs) {
            LazyResult[] pair = r.asList();
            Result symbol = pair[0].asSymbol();
            Result value = pair[1].get();
            setVariable(variables, symbol, value);
        }
    }

    private void setVariable(Variables variables, Result symbol, Result value) {
        variables.add((String) symbol.value(), value);
    }

}
