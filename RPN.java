package com.company;
import java.util.Scanner;
import java.util.Stack;

public class RPN {
    static private boolean IsDelimeter(char c)
    {
        if (( " =".indexOf(c) != -1))
            return true;
        return false;
    }
    static private boolean IsOperator(char с)
    {
        if (("+-/*^()".indexOf(с) != -1))
            return true;
        return false;
    }
    static private byte GetPriority(char s)
    {
        switch (s)
        {
            case '(': return 0;
            case ')': return 1;
            case '+': return 2;
            case '-': return 3;
            case '*': return 4;
            case '/': return 5;
            case '^': return 6;
            default: return 7;
        }
    }
    static public double Calculate(String input)
    {
        String output = GetExpression(input);
        double result = Counting(output);
        return result;
    }
    static private String GetExpression(String input)
    {
        String output = "";
        Stack operStack = new Stack();

        for (int i = 0; i < input.length(); i++)
        {



            if (Character.isDigit(input.charAt(i)))
            {

                while (!IsDelimeter(input.charAt(i)) && !IsOperator(input.charAt(i)))
                {
                    output += input.charAt(i);
                    i++;

                    if (i == input.length()) break;
                }

                output += " ";
                i--;
            }


            if (IsOperator(input.charAt(i)))
            {
                if (input.charAt(i) == '(')
                    operStack.push(input.charAt(i));
                else if (input.charAt(i) == ')')
                {

                    char s = (char) operStack.pop();

                    while (s != '(')
                    {
                        output += String.valueOf(s) + ' ';
                        s = (char) operStack.pop();
                    }
                }
                else
                {
                    if (operStack.size() > 0)
                        if (GetPriority(input.charAt(i)) <= GetPriority((Character) operStack.peek()))
                            output += String.valueOf(operStack.pop()) + " ";

                    operStack.push(input.charAt(i));

                }
            }
        }


        while (operStack.size() > 0)
            output += operStack.pop() + " ";

        return output;
    }
    static private double Counting(String input)
    {
        double result = 0;
        Stack temp = new Stack();

        for (int i = 0; i < input.length(); i++)
        {

            if (Character.isDigit(input.charAt(i)))
            {
                String a = "";

                while (!IsDelimeter(input.charAt(i)) && !IsOperator(input.charAt(i)))
                {
                    a += input.charAt(i);
                    i++;
                    if (i == input.length()) break;
                }
                temp.push(Double.parseDouble(a));
                i--;
            }
            else if (IsOperator(input.charAt(i)))
            {

                double a = (double) temp.pop();
                double b = (double) temp.pop();

                switch (input.charAt(i))
                {
                    case '+': result = b + a; break;
                    case '-': result = b - a; break;
                    case '*': result = b * a; break;
                    case '/': result = b / a; break;
                    case '^': result = Math.pow(Double.parseDouble(String.valueOf(b)), (Double.parseDouble(String.valueOf(a)))); break;
                }
                temp.push(result);
            }
        }
        return (double) temp.peek();
    }


}



