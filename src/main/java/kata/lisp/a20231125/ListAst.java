package kata.lisp.a20231125;

import java.util.List;

public class ListAst extends SingleValueAst<List<Ast>> {

    public ListAst(List<Ast> values) {
        super(values);
    }

    @Override
    public Result eval(Context context) {
        SymbolAst symbol = getSymbol();
        Function function = symbol.getFunction(context); //x
        List<Ast> arguments = value.subList(1, value.size());
        Result[] rs = new Result[arguments.size()]; //x
        for (int i = 0; i < arguments.size(); i++) {
            rs[i] = arguments.get(i).eval(context); //x
        }
        return function.execute(rs); //x
    }

    private SymbolAst getSymbol() {
        return (SymbolAst) value.get(0);
    }

}
