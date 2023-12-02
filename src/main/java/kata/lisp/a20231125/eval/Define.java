package kata.lisp.a20231125.eval;

class Define extends Function {

    public Define() {
        super("define");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count == 2;
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        Result r = arguments.asSymbol(0);
        Result value = arguments.evalArgument(1);
        return apply(r, value, variables);
    }

    private Result apply(Result symbol, Result value, Variables variables) {
        if (symbol.isError()) {
            return symbol;
        }
        String symbolName = (String) symbol.value();
        variables.add(symbolName, value);
        return new Result("<undefined>", ResultType.UNDEFINED);
    }

}