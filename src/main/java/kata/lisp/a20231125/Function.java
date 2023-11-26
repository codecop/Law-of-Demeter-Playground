package kata.lisp.a20231125;

public interface Function {

    boolean isNamed(String name);

    Class<?> getArgumentType();

    Result execute(Result[] arguments);

}
