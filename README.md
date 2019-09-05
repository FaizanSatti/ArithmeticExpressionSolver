# ArithmeticExpressionSolver
A program for solving arithmetic expressions on the basis of DMAS rule. 

public static int evaluate(String expression)                     
it checks the given expression and tokenize it on basis of spaces

Stack<Integer> values = new Stack<Integer>();
Stack for numbers: 'values'

Stack<Character> ops = new Stack<Character>();
Stack for Operators: 'ops' 

public static boolean hasPrecedence(char op1, char op2)
Returns true if 'op2' has higher or same precedence as 'op1', 
otherwise returns false. 
   
public static int applyOp(char op, int b, int a)

A utility method to apply an operator 'op' on operands 'a'  
and 'b'. Return the result. 

public static void main(String[] args)
Driver method to test above methods 
