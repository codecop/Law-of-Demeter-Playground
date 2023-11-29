package kata.lisp.a20231125;

public class BasicFunctions {

}

class Add implements Function {

    @Override
    public boolean isNamed(String name) {
        return "+".equals(name);
    }

    @Override
    public ResultType getArgumentType(int i) {
        return ResultType.NUMBER;
    }

    @Override
    public Result execute(Object[] arguments) {
        int sum = 0;
        for (int i = 0; i < arguments.length; i++) {
            sum += (Integer) arguments[i];
        }
        return new Result(sum, ResultType.NUMBER);
    }

}
