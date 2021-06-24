package com.study.structure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) throws Exception {
        // 1. 将expression 转换为list
        List<String> list = toInFixExpressionList("(3+4)*5-6");
        List<String> suffixExpressionList = toLastFixExpressionList(list);
        for (String str : suffixExpressionList) {
            System.out.print(str + " ");
        }

        String suffixExpression = "3 4 + 5 * 6 -";
//        List<String> suffixExpressionList = getStringList(suffixExpression);
        int calculation = calculation(suffixExpressionList);
        System.out.printf("表达式：%s 的结果为： %d", suffixExpression, calculation);
    }

    public static List<String> toInFixExpressionList(String expression) {
        ArrayList<String> list = new ArrayList<String>();
        char ch;
        StringBuilder str;
        for (int i = 0; i < expression.length(); i++) {
            ch = expression.charAt(i);
            if (ch < 48 || ch > 57) {
                list.add("" + ch);
            } else {
                str = new StringBuilder();
                while (i < expression.length() && expression.charAt(i) >= 48 && expression.charAt(i) <= 57) {
                    str.append(expression.charAt(i));
                    i ++;
                }
                list.add(str.toString());
                i --;
            }
        }

        return list;
    }

    public static List<String> toLastFixExpressionList(List<String> inFixExpression) throws Exception {
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = new ArrayList<>();
        for (String str : inFixExpression) {
            if (str.matches("\\d")) {
                list.add(str);
            } else if (str.equals("(")) {
                stack.add(str);
            } else if (str.equals(")")) {
                while (!stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                stack.pop();
            } else {
                while (stack.size() != 0 &&
                        Operation.isOperation(stack.peek()) &&
                        Operation.getValue(stack.peek()) >= Operation.getValue(str)) {
                    list.add(stack.pop());
                }
                stack.push(str);
            }
        }

        while (stack.size() > 0) {
            list.add(stack.pop());
        }

        return list;

    }

    public static List<String> getStringList(String expression) {
        String[] arr = expression.split(" ");
        return new ArrayList<String>(Arrays.asList(arr));
    }

    public static int calculation(List<String> expressionList) throws Exception {
        Stack<Integer> stack = new Stack<Integer>();
        for (String str : expressionList) {

            if(str.matches("\\d")) {
                stack.push(Integer.parseInt(str));
            } else {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();

                int result = 0;
                if ("-".equals(str)) {
                    result = num2 - num1;
                } else if ("+".equals(str)) {
                    result = num2 + num1;
                } else if ("*".equals(str)) {
                    result = num2 * num1;
                } else if ("/".equals(str)) {
                    result = num2 / num1;
                } else {
                    throw new Exception("not support this operator");
                }

                stack.push(result);
            }
        }

        return stack.pop();

    }



}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;


    public static boolean isOperation(String operation) {
        return  (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/"));
    }
    public static int getValue(String operation) throws Exception {
        int result;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                throw new Exception("not support this operation: " + operation);
        }
        return result;
    }
}