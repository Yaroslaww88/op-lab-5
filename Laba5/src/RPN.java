//import java.util.ArrayList;
//import java.util.Stack;
//
//public class RPN {
//    private static Stack<Token> values = new Stack<>();
//    private static Stack<Token> operations = new Stack<>();
//    private static Boolean mayUnary = true;
//
//    public static ArrayList<Token> getRPN(ArrayList<Token> tokens) throws Exception {
//        String temp = "";
//
//        for (Token token : tokens) {
//            //-(-(1+1))
//            // OPEN BRACKET
//            if (token.getType() == Token.OPERATION_OPEN_BRACKET) {
//                operations.push(token);
//                mayUnary = true;
//            }
//            else if (token.getType() == Token.OPERATION_CLOSE_BRACKET) {
//                while (operations.peek().getType() != Token.OPERATION_OPEN_BRACKET) {
//                    Token topOperator = operations.peek();
//                    operations.pop();
//
//                    /**
//                     * Fixed
//                     */
//                    if ((topOperator.getType() == Token.OPERATION_SUBTRACT && mayUnary)
//                            || token.getType() == Token.OPERATION_UNARY_MINUS) {
//                        Token topValue = values.peek();
//                        values.pop();
//                        if (topValue.getType() == Token.VALUE_CONSTANT)
//                            values.push(new ConstantToken(topValue.getValue() * (-1)));
//                        else
//                            throw new Exception("Unknown Token type: " + topValue.getType());
//                        continue;
//                    }
//
//                    Double op1 = stack.top();
//                    stack.pop();
//                    Double op2 = stack.top();
//                    stack.pop();
//
//                    //System.out.println("Op1 op2 " + op2 + " " + op1);
//
//                    if (ch == '+')
//                        stack.push(op2 + op1);
//                    if (ch == '-')
//                        stack.push(op2 - op1);
//                    if (ch == '*')
//                        stack.push(op2 * op1);
//                    if (ch == '/')
//                        stack.push(op2 / op1);
//                    if (ch == '^')
//                        stack.push(Math.pow(op2, op1));
//                }
//
//                mayUnary = false;
//                op.pop();
//            }
//            else if (getToken(buff.charAt(i)) > 0) {
//                char cur = buff.charAt(i);
//                boolean f = false;
//                if (mayUnary && (i == 0 ||
//                        (buff.charAt(i - 1) == '(' || getToken(buff.charAt(i - 1)) > 0))) {
//                    f = true;
//                    if (cur == '-')
//                        cur = '=';
//                    else
//                        cur = 'W';
//                }
//                //System.out.println("dasdas " + i + " " + f + " " + op.getSize());
//
//                while (op.getSize() > 0 && (getToken(op.peek()) >= getToken(cur))) {
//                    char ch = op.peek();
//                    op.pop();
//                    //System.out.println("popped " + ch);
//
//                    /**
//                     * Fixed
//                     */
//                    if ((ch == '-' && mayUnary) || ch == '=') {
//                        //System.out.println("Work- " + i);
//                        if (stack.getSize() == 0) {
//                            op.push('=');
//                            break;
//                        }
//                        Double op1 = stack.top();
//                        stack.pop();
//                        stack.push(-op1);
//                        continue;
//                    }
//
//                    if ((ch == 'V' && mayUnary)  || ch == 'W') {
//                        //System.out.println("WorkV " + i);
//                        if (stack.getSize() == 0) {
//                            op.push('W');
//                            break;
//                        }
//                        Double op1 = stack.top();
//                        stack.pop();
//                        stack.push(Math.sqrt(op1));
//                        continue;
//                    }
//
//                    Double op1 = stack.top();
//                    stack.pop();
//                    Double op2 = stack.top();
//                    stack.pop();
//                    f = false;
//
//                    //System.out.println("Op1 op2 " + op2 + " " + op1);
//
//                    if (ch == '+')
//                        stack.push(op2 + op1);
//                    if (ch == '-')
//                        stack.push(op2 - op1);
//                    if (ch == '*')
//                        stack.push(op2 * op1);
//                    if (ch == '/')
//                        stack.push(op2 / op1);
//                    if (ch == '^')
//                        stack.push(Math.pow(op2, op1));
//                }
//
//                if (!f) {
//                    op.push(cur);
//                } else {
//                    op.push(cur);
//                }
//                mayUnary = true;
//            }
//            else {
//                String operand = "";
//                int j = i;
//                while (j < buff.length() && Character.isDigit(buff.charAt(j))) {
//                    //System.out.println("work " + j);
//                    operand += buff.charAt(j);
//                    j++;
//                }
//                j--;
//                //if (operand.length() == 0)
//                //   continue;
//                i = j;
//                // System.out.println("Operand: " + operand + "  " + i);
//                stack.push(Double.valueOf(operand));
//                //System.out.println(stack.getSize());
//                /*if (getToken(operand.charAt(0)) == 0) {
//                    stack.push(Double.valueOf(operand));
//                } else {
//                    stack.push();
//                }*/
//
//                mayUnary = false;
//
//            }
//
//        }
//
//        //System.out.println(op.getSize());
//
//        while (op.getSize() > 0) {
//            char ch = op.peek();
//            op.pop();
//            //System.out.println("ch " + ch);
//            if (ch == '=') {
//                Double op1 = stack.top();
//                stack.pop();
//                stack.push(-op1);
//                continue;
//            }
//            if (ch == 'W') {
//                Double op1 = stack.top();
//                stack.pop();
//                stack.push(Math.sqrt(op1));
//                continue;
//            }
//            Double op1 = stack.top();
//            stack.pop();
//            Double op2 = stack.top();
//            stack.pop();
//
//            //System.out.println("Op1 op2 11 " + op2 + " " + op1);
//
//            if (ch == '+')
//                stack.push(op2 + op1);
//            if (ch == '-')
//                stack.push(op2 - op1);
//            if (ch == '*')
//                stack.push(op2 * op1);
//            if (ch == '/')
//                stack.push(op2 / op1);
//            if (ch == '^')
//                stack.push(Math.pow(op2, op1));
//        }
//
//        return stack.peek();
//
//
//
//
///*
//                if (priority == 0 && i != buff.length() - 1) {
//                temp += buff.charAt(i);
//                continue;
//            } else {
//                if (i == buff.length() - 1 && priority == 0)
//                    temp += buff.charAt(i);
//                RPN += " " + temp;
//                if (mayUnary && temp.length() > 0 && getToken(buff.charAt(i)) == 0) {
//                    System.out.println(i);
//                    RPN += " =";
//                    mayUnary = false;
//                }
//                temp = "";
//            }
//            if (buff.charAt(i) == '-' && (i == 0 || (i > 0 && getToken(buff.charAt(i-1)) >= 1))) {
//                mayUnary = true;
//                //temp += buff.charAt(i);
//                continue;
//            }
//            if (priority == 1) {
//                stack.push('(');
//            }
//            if (priority > 1){
//                while (!stack.isEmpty()) {
//                    if (getToken(stack.peek()) >= priority) RPN += " " + stack.pop();
//                    else break;
//                }
//                stack.push(buff.charAt(i));
//            }
//            if (priority==-1) {
//                while (getToken(stack.top())!=1) {
//                    RPN += " " + stack.pop();
//                }
//                if (mayUnary) {
//                    RPN += " =";
//                    mayUnary = false;
//                }
//                stack.pop();
//            }
//        }
//        while (!stack.isEmpty()) {
//            RPN += " " + stack.pop();
//        }*/
//    }
//
//    public double getInfix() {
//        MyStack<Double> stack = new MyStack<>();
//        String temp = "";
//
//        RPN += " ";
//
//        for (int i=0; i<RPN.length(); i++) {
//            char ch = RPN.charAt(i);
//
//            if (ch == ' ') {
//                if (!temp.equals("") && !temp.equals("="))
//                    stack.push(Double.valueOf(temp));
//                temp = "";
//                continue;
//            }
//
//            //|| (ch == '-' && (i < RPN.length() - 1) && RPN.charAt(i+1) != ' ')
//            if (getToken(ch) == 0 && ch != '=') {
//                temp += ch;
//            } else {
//                if (ch == '=') {
//                    Double op1 = stack.top();
//                    stack.pop();
//                    stack.push(op1 * (-1));
//                    ///System.out.println("sss: " + stack.peek());
//                } else {
//                    Double op1 = stack.top();
//                    stack.pop();
//                    Double op2 = stack.top();
//                    stack.pop();
//
//                    if (ch == '+')
//                        stack.push(op2 + op1);
//                    if (ch == '-')
//                        stack.push(op2 - op1);
//                    if (ch == '*')
//                        stack.push(op2 * op1);
//                    if (ch == '/')
//                        stack.push(op2 / op1);
//                    if (ch == '^')
//                        stack.push(Math.pow(op2, op1));
//                }
//            }
//        }
//
//        return stack.top();
//    }
//
//    private static int getToken(char next) {
//        if (next == '*' || next=='/')
//            return 2;
//        else if (next=='+' || next=='-')
//            return 1;
//        /*else if (next=='(')
//            return 1;
//        else if (next==')')
//            return -1;*/
//        else if (next=='^')
//            return 3;
//        else if (next=='=' || next=='V' || next == 'W')
//            return 4;
//        else return 0;
//    }
//}
