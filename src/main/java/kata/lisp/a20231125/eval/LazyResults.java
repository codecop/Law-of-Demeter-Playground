package kata.lisp.a20231125.eval;

public class LazyResults {

    private final LazyResult[] arguments;

    public LazyResults(LazyResult[] arguments) {
        this.arguments = arguments;
    }

    public Result evalArgument(int i) {
        return arguments[i].get(); // LoD_O.4
    }

    public Result asSymbol(int i) {
        return arguments[i].asSymbol(); // LoD_O.4
    }

    public LazyResult[] asList(int i) {
        return arguments[i].asList(); // LoD_O.4
    }

    public Results evalArguments() {
        Result[] results = new Result[size()]; // LoD_O.1
        for (int i = 0; i < size(); i++) { // LoD_O.1
            results[i] = evalArgument(i); // LoD_O.1
        }
        return new Results(results);
    }

    private int size() {
        return arguments.length; // LoD_O.1
    }

}

// LoD review OK
