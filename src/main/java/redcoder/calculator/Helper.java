package redcoder.calculator;

import java.util.Stack;

public class Helper {

    public static long calculate(String expr) {
        Stack<Long> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();
        char[] chars = expr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (isDigit(ch)) {
                try {
                    StringBuilder ss = new StringBuilder();
                    ss.append(ch);
                    for (int j = i + 1; j < chars.length; j++) {
                        char c = chars[j];
                        if (isDigit(c)) {
                            ss.append(c);
                            i++;
                        } else {
                            break;
                        }
                    }
                    operand.push(Long.parseLong(ss.toString()));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid expression");
                }
            } else if (isOperator(ch)) {
                if (operator.isEmpty()) {
                    operator.push(ch);
                } else {
                    char lastOp = operator.pop();
                    if ((ch == '*' || ch == '/') && (lastOp == '+' || lastOp == '-')) {
                        operator.push(lastOp);
                        operator.push(ch);
                    } else {
                        long y = operand.pop();
                        long x = operand.pop();
                        long result = compute(x, y, lastOp);
                        operand.push(result);
                        operator.push(ch);
                    }
                }
            }
        }

        if (operand.size() != operator.size() + 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        for (int i = 0, len = operator.size(); i < len; i++) {
            char op = operator.pop();
            long y = operand.pop();
            long x = operand.pop();
            long result = compute(x, y, op);
            operand.push(result);
        }
        return operand.pop();
    }

    private static boolean isDigit(char ch) {
        return ch >= 48 && ch <= 57;
    }

    private static boolean isOperator(char ch) {
        // 43: +, 45: -, 42: *, 47: /
        return ch == 42 || ch == 43 || ch == 45 || ch == 47;
    }

    /**
     * 比较两个操作符的大小，'*' = '/' > '+' = '-'
     *
     * @param op1 操作符1
     * @param op2 操作符2
     * @return 当op1大于op2时返回1，当op1等于op2时返回0，当op1小于op2时返回-1
     */
    private static int compareOperator(char op1, char op2) {
        if ((op1 == 42 || op1 == 47) && (op2 == 43 || op2 == 45)) {
            return 1;
        } else if ((op1 == 43 || op1 == 45) && (op2 == 42 || op2 == 47)) {
            return -1;
        } else {
            return 0;
        }
    }

    private static long compute(long x, long y, char operator) {
        long result;
        switch (operator) {
            case '+':
                result = x + y;
                break;
            case '-':
                result = x - y;
                break;
            case '*':
                result = x * y;
                break;
            case '/':
                result = x / y;
                break;
            default:
                throw new IllegalArgumentException("Illegal character, the '" + operator + "' is not operator");
        }
        return result;
    }
}
