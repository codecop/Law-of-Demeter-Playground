package kata.lisp.a20231125.eval;

class Let extends Function {

    public Let() {
        super("let", new ExactNumberOfArguments(2)); // variables and expression
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        variables.push();
        Result error = setVariables(arguments.asList(0), variables);
        if (error != null) {
            return error;
        }
        Result result = arguments.evalArgument(1);
        variables.pop();
        return result;
    }

    private Result setVariables(LazyResult[] listOfPairs, Variables variables) {
        for (LazyResult r : listOfPairs) {
            LazyResult[] pair = r.asList();
            Result symbol = pair[0].asSymbol();
            // TODO LoD
            // if (symbol.isError()) {
            //    return symbol;
            // }
            Result value = pair[1].get();
            setVariable(variables, symbol, value);
        }
        return null;
    }

    private void setVariable(Variables variables, Result symbol, Result value) {
        variables.add((String) symbol.value(), value);
    }

}
