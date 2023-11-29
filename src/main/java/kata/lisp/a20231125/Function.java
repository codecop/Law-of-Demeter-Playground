package kata.lisp.a20231125;

public interface Function {

    boolean isNamed(String name);

    boolean matchesArgumentType(int i, ResultType type);

    Result execute(Object[] arguments);

}
