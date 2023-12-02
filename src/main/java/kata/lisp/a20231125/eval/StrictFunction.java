package kata.lisp.a20231125.eval;

public abstract class StrictFunction extends Function {

    public StrictFunction(String name, NumberOfArguments numberOfArguments, TypeOfArguments typeOfArguments) {
        super(name, numberOfArguments, typeOfArguments);
    }

    @Override
    public Result apply(LazyResults lazyArguments, Variables variables) {
        return lazyArguments.eval(this); // LoD_O.2
    }

    public abstract Result apply(Object[] arguments);

}

// LoD review OK
