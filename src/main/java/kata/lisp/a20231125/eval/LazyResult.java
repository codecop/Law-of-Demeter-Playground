package kata.lisp.a20231125.eval;

public interface LazyResult {

    Result get();

    Result asSymbol();

    LazyResult[] asList();

}
