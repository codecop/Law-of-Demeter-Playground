package kata.lisp.a20231125.eval;

interface TypeOfArguments {
    boolean matches(int i, ResultType parameterType);
}

class AllSameTypeOfArguments implements TypeOfArguments {

    private final ResultType requiredType;

    public AllSameTypeOfArguments(ResultType requiredType) {
        this.requiredType = requiredType;
    }

    @Override
    public boolean matches(int i, ResultType parameterType) {
        return requiredType == parameterType;
    }

}