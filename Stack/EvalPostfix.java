/**
 * EvalPostfix.java
 * @author Tyler Gerritsen
 * @version 3-5-2017
 */
public class EvalPostfix {
	private SuperOutput out;
	private ObjectStack stack;
	private String postfix;
	private int length;
	private int index;
	private int result;

	/**
	 * default constructor
	 */
	public EvalPostfix() {
		
	}
	
	/**
	 * three-argument constructor accepting output object, stack of objects,
	 * and postfix expression
	 * @param out SuperOutput object for console/csis.txt output 
	 * @param stack ObjectStack instance for stack operations
	 * @param postfix postfix expression represented by a String object
	 */
	public EvalPostfix(SuperOutput out, ObjectStack stack, String postfix) {
		this.out = out;
		this.stack = stack;
		this.postfix = postfix;
		length = this.postfix.length();
	}
	
	/**
	 * evaluates a postfix expression using a switch-case statement and an ObjectStack
	 * instance.
	 */
	public void evaluate() {
		int op1, op2;
		
		while (index < length) {
			char c = postfix.charAt(index);
			int i = (int)(c - 48);
			
			switch (c) {
			case '^':
				op1 = (int)stack.pop();
				op2 = (int)stack.pop();
				stack.push((int)Math.pow(op2, op1));
				break;
			case '*': 
				op1 = (int)stack.pop();
				op2 = (int)stack.pop();
				stack.push(op1 * op2);
				break;
			case '/': 
				op1 = (int)stack.pop();
				op2 = (int)stack.pop();
				stack.push(op2 / op1);
				break;
			case '+': 
				op1 = (int)stack.pop();
				op2 = (int)stack.pop();
				stack.push(op1 + op2);
				break;
			case '-': 
				op1 = (int)stack.pop();
				op2 = (int)stack.pop();
				stack.push(op2 - op1);
				break;
			default :
				stack.push(i);
				break;
			}
			index++;
		}
		result = (int)(stack.pop());
		out.output("Value: " + result + "%n%n");
	}

}
