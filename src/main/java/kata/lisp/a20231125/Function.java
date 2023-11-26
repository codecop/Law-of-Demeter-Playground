package kata.lisp.a20231125;

public interface Function {

    boolean isNamed(String name);

    Class<?> getArgumentType(int i);

    Result execute(Result[] arguments);

}
