package kata.lisp.a20231125;

public class Functions {

}

class Add implements Function {

    @Override
    public boolean isNamed(String name) {
        return "+".equals(name);
    }

    @Override
    public Class<?> getArgumentType() {
        return Integer.class;
    }

    @Override
    public Result execute(Result[] arguments) {
        int s = 0;
        for (Result r : arguments) {
            s += (Integer) r.value();
        }
        return new Result(s, Result.Type.NUMBER);
    }

}
