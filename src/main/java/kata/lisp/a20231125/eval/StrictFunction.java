package kata.lisp.a20231125.eval;

public abstract class StrictFunction extends Function {

    public StrictFunction(String name, NumberOfArguments numberOfArguments, TypeOfArguments typeOfArguments) {
        super(name, numberOfArguments, typeOfArguments);
    }

    @Override
    protected Result apply(LazyResults lazyArguments, Variables variables) {
        return lazyArguments.eval(this); // LoD_O.2
    }

    protected abstract Result apply(Object[] arguments);

}

// LoD review OK
