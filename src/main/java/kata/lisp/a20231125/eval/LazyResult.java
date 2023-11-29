package kata.lisp.a20231125.eval;

public interface LazyResult {

    Result eval();

    LazyResult[] asList();
}
