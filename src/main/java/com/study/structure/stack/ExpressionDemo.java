package com.study.structure.stack;

public class ExpressionDemo {
    public static void main(String[] args) throws Exception {
        String expression = "340+(2*6)-20";


    }


    // 判断是否是符号
    public static boolean isOperator(int data) {
        return data == '+' || data == '-' || data == '*' || data == '/';
    }


    public static int priority(int data) throws Exception {
        int flag = 0;
        switch (data) {
            case '-' :
            case '+':
                flag = 1;
                break;
            case '*' :
            case '/':
                flag = 2;
                break;
            default:
                throw new Exception("not support this operator");
        }
        return flag;
    }

    public static int calculation(int num1, int num2, char operator) throws Exception {
        int result = 0;
        switch (operator) {
            case '-' :
                result = num2 - num1;
                break;
            case '+':
                result = num2 + num1;
                break;
            case '*' :
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                throw new Exception("not support this operator");
        }
        return result;
    }

    public static int expressionCal(String expression) throws Exception {
        ArrayStack numberStack = new ArrayStack(10);
        ArrayStack operatorStack = new ArrayStack(10);

        int index = 0;
        int num1;
        int num2;
        char operator;
        int result;
        char ch;
        String keepNum = "";

        int length = expression.length();
        while (true) {
            if (index == length) {
                break;
            }
            ch = expression.charAt(index);
            if (isOperator(ch)) {
                if(operatorStack.isEmpty()) {
                    operatorStack.push(ch);
                } else {
                    if (priority(ch) <= priority(operatorStack.peep())) {
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        operator = (char) operatorStack.pop();
                        result = (char)calculation(num1, num2, operator);

                        numberStack.push(result);
                        operatorStack.push(ch);
                    } else {
                        operatorStack.push(ch);
                    }
                }
                index ++;
            } else {
                while (true) {
                    if (index == length) {
                        break;
                    }
                    ch = expression.charAt(index);
                    if (isOperator(ch)) {
                        break;
                    }
                    keepNum += ch;
                    index ++;
                }
                numberStack.push(Integer.parseInt(keepNum));
                keepNum = "";
            }
        }

        while(true) {
            if (operatorStack.isEmpty()) {
                break;
            }
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            operator = (char) operatorStack.pop();
            result = calculation(num1, num2, operator);
            numberStack.push(result);
        }

        System.out.printf("表达式 %s 的结果为：%d", expression, numberStack.pop());
        return numberStack.pop();

    }


}


class ArrayStack {
    private int maxSize;
    private int[] arr;
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize-1;
    }

    public void push(int data) {
        if (isFull()) {
            System.out.println("数组已经满了");
        } else {
            top ++;
            arr[top] = data;
        }
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("数组已经空...");
        } else {
            int data = arr[top];
            top --;
            return data;
        }
    }

    public int peep() {
        return arr[top];
    }
}