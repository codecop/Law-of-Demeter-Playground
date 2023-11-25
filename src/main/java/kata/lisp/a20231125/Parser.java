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
        throw new IllegalArgumentException(token.toString());
    }

    public Ast parse(Tokens tokens) {
        if (tokens.atOpeningBracket()) {
            tokens.consumeToken();
            return parseChildren(tokens);
        }
        throw new IllegalArgumentException(tokens.toString());
    }

    private Ast parseChildren(Tokens tokens) {
        List<Ast> children = new ArrayList<>();
        while (true) {
            Token token = tokens.next();
            if (tokens.atClosingBracket()) {
                // this expression is finished
                tokens.consumeToken();
                break;
            }
            if (tokens.atOpeningBracket()) {
                // a new expression started
                tokens.consumeToken();
                children.add(parseChildren(tokens));
            } else {
                children.add(parse(token));
                tokens.consumeToken();
            }
        }
        return new ListAst(children);
    }

}
