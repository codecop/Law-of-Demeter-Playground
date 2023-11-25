package kata.lisp.a20231125;

public interface Context {

    Function getFunctionNamed(String name);

    void addFunctionNamed(Function function);

}
