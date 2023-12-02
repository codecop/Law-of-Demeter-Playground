package kata.lisp.a20231125.eval;

class Let extends Function {

    public Let() {
        super("let", new ExactNumberOfArguments(2), null); // variables and expression
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        variables.push(); // LoD_O.2

        LazyResult[] listOfPairs = arguments.asList(0); // LoD_O.2
        Result result = apply(listOfPairs, arguments, variables); // LoD_O.1

        variables.pop(); // LoD_O.2
        return result;
    }

    private Result apply(LazyResult[] listOfPairs, LazyResults otherArguments, Variables variables) {
        for (LazyResult pair : listOfPairs) {

            LazyResult[] declaration = pair.asList(); // LoD_O.2
            Result added = variables.add(declaration); // LoD_O.2
            if (added != null) { // This was a violation but changing it to null fixed it. Workaround...
                return added;
            }
        }

        return otherArguments.evalArgument(1); // LoD_O.2
    }

}

// LoD review OK
