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
        List<Ast> children = new ArrayList<>();
        if (tokens.atStartingBracket()) {
            tokens.consumeToken();
            parseChildren(tokens, children);
        }
        return new ListAst(children);
    }

    private void parseChildren(Tokens tokens, List<Ast> children) {
        while (true) {
            Token token = tokens.next();
            if (tokens.atClosingBracket()) {
                tokens.consumeToken();
                break;
            }
            children.add(parse(token));
            tokens.consumeToken();
        }
    }

}
