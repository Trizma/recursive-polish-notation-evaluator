// The parser evaluates an arithmetic expression using recursive descent
public class Parser {
    private Tokenizer tokenizer;
    private Token currentToken; // The current token we're looking at
    public int result = 0;

    Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.currentToken = tokenizer.nextToken(); // Initialize by reading the first token
    }

    // Consume the current token if it matches the expected type
    private void eat(TokenType expected) {
        if (currentToken.type == expected) {
            currentToken = tokenizer.nextToken();
        } else {
            throw new RuntimeException("Expected " + expected + " but got " + currentToken.type);
        }
    }

    // Parse expression for any operator
    int parseExpr() {
        int operand = parseFactor(); // get first operand
        if (operand != 0 && currentToken.type == TokenType.NUMBER) { //if you already have the first operand get the 2nd and store it in result
            result = parseFactor();
        }

        while (currentToken.type == TokenType.MUL || currentToken.type == TokenType.DIV || currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            TokenType op = currentToken.type;
            eat(op);

            if (op == TokenType.MUL) {
                result *= operand;
                if (currentToken.type != TokenType.EOF) parseExpr();
            } else if (op == TokenType.DIV) {
                result /= operand;
            } else if (op == TokenType.PLUS) {
                result += operand;
                if (currentToken.type != TokenType.EOF) parseExpr();
            } else if (op == TokenType.MINUS) {
                result -= operand;
            } else {
                return result;
            }
        }
        return result;
    }

    // Parse numbers
    int parseFactor() {
        if (currentToken.type == TokenType.NUMBER) {
            int operand = Integer.parseInt(currentToken.text);
            eat(TokenType.NUMBER);
            return operand;
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken.type); // Error handling
        }
    }
}