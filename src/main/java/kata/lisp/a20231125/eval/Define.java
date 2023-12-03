package kata.lisp.a20231125.eval;

class Define extends Function {

    public Define() {
        super("define", new ExactNumberOfArguments(2), null);
    }

    @Override
    protected Result apply(LazyResults arguments, Variables variables) {
        Result symbol = arguments.asSymbol(0); // LoD_O.2
        Result value = arguments.evalArgument(1); // LoD_O.2

        Result added = variables.add(symbol, value); // LoD_O.2
        if (added != null) {
            return added;

        }

        return new Result("<undefined>", ResultType.UNDEFINED);
    }

}

// LoD review OK
