/**
 * InfixToPostfix.java
 * @author Tyler Gerritsen
 * @version 3-5-2017
 */
public class InfixToPostfix {
	private SuperOutput out;
	private ObjectStack stack;
	
	private String[] infix;
	private char buf[];
	private StringBuilder postfix = new StringBuilder("");
	
	private int length;
	private int postfixLength;
	private int infixIndex;
	private int postfixIndex;
	
	/**
	 * default constructor; completely useless for this lab, but I make a habit
	 * to always include a default if and when one is not auto-generated
	 * by the compiler.
	 */
	public InfixToPostfix() {
		
	}
	
	/**
	 * constructor which takes SuperOutput, ObjectStack, and String[] arguments. 
	 * @param out used for terminal and csis.txt output
	 * @param stack ObjectStack instance for stack operations
	 * @param tokens array of Strings representing the infix expression
	 */
	public InfixToPostfix(SuperOutput out, ObjectStack stack, String[] tokens) {
		this.out = out;
		this.stack = stack;
		this.infix = tokens;
		length = tokens.length;
		postfixLength = length;
		infixIndex = 0;
		postfixIndex = 0;
		buf = new char[length];
	}
	
	/**
	 * getter for the postfix result
	 * @return String object containing the postfix expression
	 */
	public String getPostfix() {
		return postfix.toString();
	}
	
	/**
	 * this is the priority method, as recommended in the book
	 * @param op the operator to be tested
	 * @return value representing the priority of the argument. higher priority
	 * returns higher values
	 */
	private int priority(char op) {
		switch (op) {
			case '^': return 3;
			case '*': 
			case '/': return 2;
			case '+':
			case '-': return 1;
			default : return 0;
		}
	}
	
	/**
	 * moves the elements from buf over to the postfix StringBuilder instance, the
	 * reason being that the buf char array is not properly sized at the end of the 
	 * conversion
	 */
	private void buildPostfix() {
		postfix.insert(0, buf[0]);
		for (int i = 1; i < postfixLength; i++) {
			postfix.append(buf[i]);
		}
	}
	
	/**
	 * this method uses a switch-case statement to call several private methods
	 * which convert the infix expression to postfix
	 */
	public void toPostfix() {
		while (infixIndex != length) {
			switch (infix[infixIndex].charAt(0)) {
				case '^': 
					infixOperator('^');
					break;
				case '*': 
					infixOperator('*');
					break;
				case '/': 
					infixOperator('/');
					break;
				case '+':
					infixOperator('+');
					break;
				case '-': 
					infixOperator('-');
					break;
				case '(':
					infixLeftParenthesis();
					break;
				case ')':
					infixRightParenthesis();
					break;
				default : 
					infixOperand(infix[infixIndex].charAt(0));
					break;
			}
		}
		infixEnd();
		buildPostfix();
		out.output("Postfix conversion: " + new String(buf) + "%n");
	}
	
	/**
	 * when an operator is encountered, this method gets called, which places
	 * previous appropriate operators into the postfix expression
	 * @param c operator char encountered
	 */
	private void infixOperator(char c) {
		while (!stack.isEmpty()) {
			if ((char)stack.top() == '(') {
				break;
			}
			if (priority((char)stack.top()) < priority(c)) {
				break;
			}
			buf[postfixIndex] = (char)stack.pop();
			postfixIndex++;
		}
		infixIndex++;
		stack.push(c);
	}
	
	/**
	 * called when an operand is encountered while scanning through the infix expression
	 * @param c the operand encountered
	 */
	private void infixOperand(char c) {
		buf[postfixIndex] = c;
		postfixIndex++;
		infixIndex++;
	}
	
	/**
	 * called when the program finishes scanning the infix expression to complete the
	 * postfix expression
	 */
	private void infixEnd() {
		while (!stack.isEmpty()) {
			buf[postfixIndex] = (char)stack.pop();
			postfixIndex++;
		}
	}

	/**
	 * called when the program finds a left parenthesis
	 */
	private void infixLeftParenthesis() {
		postfixLength--;
		stack.push('(');
		infixIndex++;
	}
	
	/**
	 * called when the program finds a right parenthesis
	 */
	private void infixRightParenthesis() {
		postfixLength--;
		while ((char)stack.top() != '(') {
			buf[postfixIndex] = (char)stack.pop();
			postfixIndex++;
		}
		stack.pop();
		infixIndex++;
	}
	
}
