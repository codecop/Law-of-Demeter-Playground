package kata.lisp.a20231125.eval;

class Define extends Function {

    public Define() {
        super("define", new ExactNumberOfArguments(2), null);
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        Result symbol = arguments.asSymbol(0); // LoD_O.2
        Result value = arguments.evalArgument(1); // LoD_O.2
        return apply(symbol, value, variables); // LoD_O.1
    }

    private Result apply(Result symbol, Result value, Variables variables) {
        if (symbol.isError()) { // LoD Violation? - cheat by using another method
            return symbol;
        }
        String symbolName = (String) symbol.value(); // LoD Violation? - cheat by using another method
        variables.add(symbolName, value); // LoD_O.2

        return new Result("<undefined>", ResultType.UNDEFINED);
    }

}

// LoD review Violation?
