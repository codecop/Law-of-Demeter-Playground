package kata.lisp.a20231125;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import kata.lisp.a20231125.ast.Ast;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().runFile(args[0]));
    }

    private final Parser parser = new Parser();
    private final Eval eval = new Eval();

    public Result runFile(String fileName) throws IOException {
        String code = Files.readString(Paths.get(fileName));
        return run(code);
    }

    public Result run(String code) {
        Lexer lexer = new Lexer(code);
        Tokens tokens = lexer.tokenise();
        Ast program = parser.parse(tokens);
        return eval.eval(program);
    }
}
