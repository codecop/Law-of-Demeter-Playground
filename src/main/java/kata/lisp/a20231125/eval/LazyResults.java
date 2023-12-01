package kata.lisp.a20231125.eval;

public class LazyResults {

    private final LazyResult[] arguments;

    public LazyResults(LazyResult[] arguments) {
        this.arguments = arguments;
    }

    public Result evalArgument(int i) {
        return arguments[i].get();
    }

    public Result asSymbol(int i) {
        return arguments[i].asSymbol();
    }
    
    public LazyResult[] asList(int i) {
        return arguments[i].asList();
    }
    
    public Results evalArguments() {
        Result[] results = new Result[size()];
        for (int i = 0; i < size(); i++) {
            results[i] = arguments[i].get();
        }
        return new Results(results);
    }

    public int size() {
        return arguments.length;
    }

}
