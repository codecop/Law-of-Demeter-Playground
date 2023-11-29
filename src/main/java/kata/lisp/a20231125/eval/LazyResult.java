package kata.lisp.a20231125.eval;

public interface LazyResult {

    Result get();

    LazyResult[] asList();
}
