package kata.lisp.a20231125;

public class Functions {

}

class Add implements Function {

    @Override
    public boolean isNamed(String name) {
        return "+".equals(name);
    }

    @Override
    public Class<?> getArgumentType(int i) {
        return Integer.class;
    }

    @Override
    public Result execute(Result[] arguments) {
        int sum = 0;
        for (Result argument : arguments) {
            sum += (Integer) argument.value();
        }
        return new Result(sum, Result.Type.NUMBER);
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
    public Class<?> getArgumentType(int i) {
        return null;
    }

    @Override
    public Result execute(Result[] arguments) {
        return new Result(message, Result.Type.ERROR);
    }

}
