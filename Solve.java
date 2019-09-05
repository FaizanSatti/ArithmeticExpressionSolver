/*

@Author: Faizan Satti
@Date: 05-09019

A program to evaluate a given expression

*/
import java.util.Scanner;


import java.util.Stack;

public class Solve
{
    public static int evaluate(String expression)                     //it checks the given expression and tokenize it on basis of spaces
    {
        String str ="";

        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);

            if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                str+=" "+ c +" ";
            }
            else
                str+=""+c;
        }
        char[] tokens = str.toCharArray();                                      // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<Integer>();

        Stack<Character> ops = new Stack<Character>();                         // Stack for Operators: 'ops' 

        for (int i = 0; i < tokens.length; i++)
        {
                                                                             // Current token is a number, push it to stack for numbers 
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();                       // There may be more than one digits in number 
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
            }
                                                                               // Current token is an opening brace, push it to 'ops' 
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
                                                                               // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
                                                                              // Current token is an operator
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/')
            {
                                                                
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                                                                              // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
                                                                              // Entire expression has been parsed at this point, apply remaining 
                                                                              // ops to remaining value
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }
                                                                                // Returns true if 'op2' has higher or same precedence as 'op1', 
                                                                                // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if (op1 == '*' && op2 == '+')
            return false;
        if ((op1 == '*' || op2 == '-'))
            return false;
        if (op1 == '/' && op2 == '+')
            return false;
        if (op1 == '/' && op2 == '-')
            return false;
        else
            return true;
    }


    public static int applyOp(char op, int b, int a)                         // A utility method to apply an operator 'op' on operands 'a'  
    // and 'b'. Return the result. 
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    public String aTest(String test){
        return "aaa";
    }

    public static void main(String[] args)                                      //Main method to run above functionality
    {

        String expression1= "1+21/3+3+9/3";
        String expression2= "10-5*(4/2)+22";
        String expression3= "50+5-4/2*(20-2)*2";

        System.out.println("||*******************************************************************||");
        System.out.println("Your expression no 1 is: "+expression1);
        System.out.println("Result of expression 1 is: "+Solve.evaluate(expression1));
        System.out.println("||*******************************************************************||");

        System.out.println("Your expression is no 2: "+expression2);
        System.out.println("Result of expression 2 is: "+Solve.evaluate(expression2));
        System.out.println("||*******************************************************************||");

        System.out.println("Your expression no 3 is: "+expression3);
        System.out.println("Result of expression 3 is: "+Solve.evaluate(expression3));
        System.out.println("||*******************************************************************||");
    }
}
