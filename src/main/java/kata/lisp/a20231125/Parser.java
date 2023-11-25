package kata.lisp.a20231125;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public Ast parse(Token token) {
        if (token.isNumber()) {
            return new NumberAst(token.asNumber());
        }
        if (token.isString()) {
            return new StringAst(token.asString());
        }
        if (token.isBoolean()) {
            return new BooleanAst(token.asBoolean());
        }
        if (token.isSymbol()) {
            return new SymbolAst(token.asSymbol());
        }
        return null;
    }

    public Ast parse(Tokens tokens) {
        return parse(tokens, 0);
    }

    private Ast parse(Tokens tokens, int position) {
        if (tokens.startsWithBracket(position)) {
            List<Ast> children = new ArrayList<>();
            for (Token token : tokens.tokensInsideBrackets(position)) {
                children.add(parse(token));
            }
            return new ListAst(children);
        }
        return null;
    }

}
