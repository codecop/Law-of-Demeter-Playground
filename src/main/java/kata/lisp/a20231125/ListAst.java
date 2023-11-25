package kata.lisp.a20231125;

import java.util.List;

public class ListAst extends AbstractAst<List<Ast>> {

    public ListAst(List<Ast> values) {
        super(values);
    }

    @Override
    public Result eval(Context context) {
        SymbolAst symbol = (SymbolAst) value.get(0);
        if (symbol.value.equals("+")) { //
            List<Ast> arguments = value.subList(1, value.size());
            return arguments.stream().map(a -> a.eval(context)).reduce(this::add).get(); //
        }
        throw new IllegalStateException();
    }

    public Result add(Result a, Result b) {
        Integer ai = (Integer) (a.value());
        Integer bi = (Integer) (b.value());
        return new Result(ai + bi, Result.Type.NUMBER);
    }

}
