package kata.lisp.a20231125;

import java.util.ArrayList;
import java.util.List;

import kata.lisp.a20231125.ast.Ast;
import kata.lisp.a20231125.ast.BooleanAst;
import kata.lisp.a20231125.ast.ExpressionAst;
import kata.lisp.a20231125.ast.NumberAst;
import kata.lisp.a20231125.ast.ProgramAst;
import kata.lisp.a20231125.ast.StringAst;
import kata.lisp.a20231125.ast.SymbolAst;
import kata.lisp.a20231125.token.Token;
import kata.lisp.a20231125.token.Tokens;

public class Parser {

    public Ast parse(Token token) {
        // TODO LoD isNumber is called on received object
        // but I do not want to add AST to token. What to do?
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
        Ast ast = null;
        if (tokens.atOpeningBracket()) {
            tokens.consumeToken();
            ast = parseExpression(tokens);
            if (tokens.finished()) {
                return ast;
            }
            // else ast is first expression of a program
        }
        // only at top level
        return parseProgram(tokens, ast);
    }

    private Ast parseExpression(Tokens tokens) {
        List<Ast> children = new ArrayList<>();
        while (true) {
            if (tokens.finished()) {
                throw new ParsingException("Premature end of tokens while parsing an Expression",
                        new ExpressionAst(children));
            }
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
        return new ExpressionAst(children);
    }

    private Ast parseProgram(Tokens tokens, Ast possibleFirstExpression) {
        List<Ast> children = new ArrayList<>();
        if (possibleFirstExpression != null) {
            children.add(possibleFirstExpression);
        }
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
