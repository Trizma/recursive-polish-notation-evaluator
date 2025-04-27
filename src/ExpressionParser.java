// Name: Jaya Clement
// Date: 4/17/2025
// Class: CS &240
// Lab 2
// Purpose: Calculate expressions using polish notation
// I really enjoyed this assignment but im not sure if I fully understood the instructions or why this assignment was so hard for me
// I got there in the end after about 10-15 hours of trying different things and fixing bugs.
// I would appreciate it if you could look over my code to see if I made it harder for myself.

/* Questions
* 1. My structure is somewhat similar except there is no need to worry about operator priority
*
* 2. I could manage my tokens with a hash map or linked list
*
* 3. Needing to store 2 operands before reaching an operator is different.
*
*  4. Perhaps after everyone has turned in a lab we could access a completed better version of the lab to look over to see what we could have done differently.
*
*  Its not really a question but I feel like my brain learn best when we follow a problem though the code and see exactly what happens to it,
* and I would appreciate it if we could do that more in class.
* */



import java.util.Scanner;

// Entry point: REPL for evaluating arithmetic expressions
public class ExpressionParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression or type 'exit' to quit.");

        while (true) {
            System.out.print("expr> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

           try {
                Tokenizer tokenizer = new Tokenizer(input); // Tokenize the input
                Parser parser = new Parser(tokenizer);     // Parse and evaluate
                int result = parser.parseExpr();           // Start from the highest-level rule
                System.out.println("Result: " + result);
           } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
           }
        }

        scanner.close();
    }
}