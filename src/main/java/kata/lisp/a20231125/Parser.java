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
        if (token.isNumber()) { // LoD_O.2
            return new NumberAst(token.asNumber()); // LoD_O.2
        }

        if (token.isString()) { // LoD_O.2
            return new StringAst(token.asString()); // LoD_O.2
        }

        if (token.isBoolean()) { // LoD_O.2
            return new BooleanAst(token.asBoolean()); // LoD_O.2
        }

        if (token.isSymbol()) { // LoD_O.2
            return new SymbolAst(token.asSymbol()); // LoD_O.2
        }

        throw new IllegalArgumentException(token.toString()); // LoD_O.2
    }

    public Ast parse(Tokens tokens) {
        Ast ast = null;

        if (tokens.atOpeningBracket()) { // LoD_O.2
            tokens.consumeToken(); // LoD_O.2
            ast = parseSExpression(tokens); // LoD_O.1

            if (tokens.finished()) { // LoD_O.2
                return ast;
            }
            // else ast is first expression of a program
        }

        // only at top level
        return parseProgram(tokens, ast);
    }

    private Ast parseSExpression(Tokens tokens) {
        List<Ast> children = new ArrayList<>();

        while (true) {
            if (tokens.finished()) { // LoD_O.2
                throw new ParsingException("Premature end of tokens while parsing an Expression",
                        new ExpressionAst(children));
            }

            if (tokens.atClosingBracket()) { // LoD_O.2
                // this expression is finished
                tokens.consumeToken(); // LoD_O.2
                break;
            }

            parseSExpression(tokens, children); // LoD_O.1
        }

        return new ExpressionAst(children);
    }

    private void parseSExpression(Tokens tokens, List<Ast> intoChildren) {
        if (tokens.atOpeningBracket()) { // LoD_O.2
            // a new expression started
            parseExpression(tokens, intoChildren); // LoD_O.1
        } else {
            parseValue(tokens, intoChildren); // LoD_O.1
        }
    }

    private void parseValue(Tokens tokens, List<Ast> intoChildren) {
        Token token = tokens.next(); // LoD_O.2
        Ast value = parse(token); // LoD_O.1
        intoChildren.add(value); // LoD_O.2
        tokens.consumeToken(); // LoD_O.2
    }

    private void parseExpression(Tokens tokens, List<Ast> intoChildren) {
        tokens.consumeToken(); // LoD_O.2
        Ast expression = parseSExpression(tokens); // LoD_O.1
        intoChildren.add(expression); // LoD_O.2
    }

    private Ast parseProgram(Tokens tokens, Ast possibleFirstExpression) {
        List<Ast> children = new ArrayList<>();

        if (possibleFirstExpression != null) {
            children.add(possibleFirstExpression); // LoD_O.3
        }

        while (tokens.hasNext()) { // LoD_O.2
            parseSExpression(tokens, children); // LoD_O.1
        }

        return new ProgramAst(children);
    }

}

// LoD review OK
