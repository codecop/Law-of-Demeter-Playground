package kata.lisp.a20231125;

public interface Function {

    boolean isNamed(String name);

    ResultType getArgumentType(int i);

    Result execute(Object[] arguments);

}
