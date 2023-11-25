package kata.lisp.a20231125;

public interface Function {

    String getName();

    Class<?> getArgumentType();

    Result execute(Result[] arguments);

}
