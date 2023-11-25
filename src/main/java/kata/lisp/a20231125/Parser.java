package kata.lisp.a20231125;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public Ast parse(String token) {
        if (token.matches("0|-?[1-9]\\d*")) {
            int value = Integer.parseInt(token);
            return new NumberAst(value);
        }
        if (token.matches("\"[^\"]*\"")) {
            String value = token.substring(1, token.length() - 1);
            return new StringAst(value);
        }
        if (token.matches("#f|#t")) {
            boolean value = token.equals("#t");
            return new BooleanAst(value);
        }
        if (token.matches("[+*!=?a-zA-Z][+*!=?a-zA-Z0-9]*(-[+*!=?a-zA-Z0-9]*)*")) {
            return new SymbolAst(token);
        }
        return null;
    }

    public Ast parse(Tokens tokens) {
        if (tokens.isList()) {
            List<Ast> children = new ArrayList<>();
            for (String token : tokens.insideBrackets()) {
                children.add(parse(token));
            }
            return new ListAst(children);
        }
        return null;
    }

}
