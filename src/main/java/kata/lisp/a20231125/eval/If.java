package kata.lisp.a20231125.eval;

class If extends Function {

    public If() {
        super("if");
    }

    @Override
    public boolean matchesArgumentNumber(int count) {
        return count == 3;
    }

    @Override
    public Result apply(LazyResults arguments, Variables variables) {
        Result condition = getOfTypeOrError(arguments.evalArgument(0), ResultType.BOOLEAN, 0);
        return evalCond(condition, arguments);
    }

    private Result evalCond(Result condition, LazyResults arguments) {
        if (condition.isError()) {
            return condition;
        }

        if ((Boolean) condition.value()) {
            return arguments.evalArgument(1);
        }

        return arguments.evalArgument(2);
    }

    private Result getOfTypeOrError(Result result, ResultType resultType, int index) {
        if (result.type() != resultType) {
            return result.causesTypeMismatchAtPosition(index);
        }
        return result;
    }

}