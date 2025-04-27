// Tokenizer reads a raw string and produces a sequence of tokens
public class Tokenizer {
    private String input;
    public int pos = 0;

    Tokenizer(String input) {
        this.input = input; // uses whitespace to space operands and operators
        pos = input.length()-1; // start pos at the last should be number in the input
    };

    // Produce the next token from the input string
    Token nextToken() {
        if (pos < 0) return new Token(TokenType.EOF, ""); // End of input at -1

        char current = input.charAt(pos); // get last char

        while (current == ' ') { //if there is a white space at this point we can skip it as we only use it to know when to stop in the multi digit loop
            pos--; current = input.charAt(pos);
        }

        // Read a multi-digit number
        if (Character.isDigit(current)) {
            int start = pos;
            while (pos >= 0 && Character.isDigit(input.charAt(pos))) {
                pos--;
            }
            String number = (input.substring(pos+1, start+1));
            return new Token(TokenType.NUMBER, input.substring(pos+1, start+1));
        }

        // Recognize operators and parentheses
        switch (current) {
            case '+': pos--; return new Token(TokenType.PLUS, "+");
            case '-': pos--; return new Token(TokenType.MINUS, "-");
            case '*': pos--; return new Token(TokenType.MUL, "*");
            case '/': pos--; return new Token(TokenType.DIV, "/");
            default:
                throw new RuntimeException("Unknown character: " + current); // Catch any unexpected character
        }
    }
}