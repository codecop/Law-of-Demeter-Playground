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
            return parseExpression(tokens);
        }
        // only at top level
        return parseProgram(tokens);
    }

    private Ast parseExpression(Tokens tokens) {
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
                children.add(parseExpression(tokens));
            } else {
                children.add(parse(token));
                tokens.consumeToken();
            }
        }
        SymbolAst symbol = (SymbolAst) (children.get(0));
        List<Ast> arguments = children.subList(1, children.size());
        return new ExpressionAst(symbol, arguments);
    }

    private Ast parseProgram(Tokens tokens) {
        List<Ast> children = new ArrayList<>();
        while (tokens.hasNext()) {
            Token token = tokens.next();
            if (tokens.atOpeningBracket()) {
                // a new expression started
                tokens.consumeToken();
                children.add(parseExpression(tokens));
            } else {
                children.add(parse(token));
                tokens.consumeToken();
            }
        }

        return new ProgramAst(children);
    }

}
