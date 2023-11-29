package kata.lisp.a20231125;

public class Functions {

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
    public Result execute(Results arguments) {
        int sum = 0;
        for (int i = 0; i < arguments.size(); i++) {
            sum += (Integer) arguments.get(i).value();
        }
        return new Result(sum, ResultType.NUMBER);
    }

}

class RaiseError implements Function {

    private final String message;

    public RaiseError(String message) {
        this.message = message;
    }

    @Override
    public boolean isNamed(String name) {
        throw new IllegalStateException();
    }

    @Override
    public ResultType getArgumentType(int i) {
        return ResultType.ANY;
    }

    @Override
    public Result execute(Results arguments) {
        return new Result(message, ResultType.ERROR);
    }

}
