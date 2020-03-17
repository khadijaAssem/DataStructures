package sample;
/**
 * Evaluates mathematical expressions
 * @author Khadija Assem
 */
public class ExpressionEvaluator implements IExpressionEvaluator {
    /**
     * Finds the Postfix expression from infix expression
     * @param expression -in infix-
     * @return String with the postfix expression
     */
    @Override
    public String infixToPostfix(String expression) {
        int u=0;
        char[] exp = expression.toCharArray();
        try {
            while (u < expression.length()) {
                if (exp[u] == '*' || exp[u] == '+' || exp[u] == '/')
                    if (u == (expression.length() - 1) || u == 0 || !((Character.isLetterOrDigit(exp[u - 1]) || exp[u - 1] == ')') && (Character.isLetterOrDigit(exp[u + 1]) || exp[u + 1] == '(') || (exp[u + 1] == '-' && Character.isLetterOrDigit(exp[u + 2]))))
                        throw new ArithmeticException();
                u++;
            }
        } catch (Exception e) { System.out.println("Invalid Expression"); return null;}
        if (!CheckValidation(expression)){System.out.println("Missing prantatheis"); return null;}
        Stack stack = new Stack();
        String perfix="";
        for (int i=0;i<exp.length;i++){
            if ((exp[i]=='-'&&i==0)||(i!=0&&exp[i]=='-'&&(exp[i-1]!=')'&&(!Character.isLetterOrDigit(exp[i-1]))))){
                perfix+=exp[i++];
                perfix+=exp[i++];
                perfix+=" ";
            }
            while (i<exp.length&&Character.isLetterOrDigit(exp[i]))
                perfix += exp[i++];
            perfix+=" ";
            if (i==exp.length) {
                while (!stack.isEmpty()) {
                    perfix += stack.pop();
                    perfix += " ";
                }
                return perfix;
            }
            if(exp[i]=='*'||exp[i]=='-'||exp[i]=='+'||exp[i]=='/') {
                while (!stack.isEmpty()&&Precision(exp[i])<=Precision((char)stack.peek())) {
                    perfix += stack.pop();
                    perfix += " ";
                }
                stack.push(exp[i]);
            }
            else if (exp[i]=='(')
                stack.push('(');
            else if (exp[i]==')'){
                while (!stack.isEmpty()&&(char)stack.peek()!='(') {
                    perfix += stack.pop();
                    perfix+=" ";
                }
                if (!stack.isEmpty()&&(char)stack.peek() != '(')
                    return "Invalid Expression";
                else
                    stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            perfix += stack.pop();
            perfix += " ";
        }
        return perfix;
    }
    /**
     * Evaluates a postfix mathematical expression
     * @param expression -in postfix-
     * @return the value of the expression
     */
    @Override
    public int evaluate(String expression) {
        Stack s = new Stack();
        Stack eval = new Stack();
        char[] exp = expression.toCharArray();
        int i=0;
        while (i<exp.length) {
            if ((i==0&&exp[i]=='-'&&Character.isLetterOrDigit(exp[i+1]))||(i!=0&&exp[i]=='-'&&Character.isLetterOrDigit(exp[i+1]))){
                i++;
                while (Character.isLetterOrDigit(exp[i]) && exp[i] != ' ')
                    s.push(exp[i++]);
                if (!s.isEmpty()) {
                    int j = 0, y = 1, size = s.size();
                    float x = 0;
                    while (j < size) {
                        j++;
                        x += ((char) s.pop() - 48) * y;
                        y *= 10;
                    }
                    x*=-1;
                    eval.push(x);
                }
            }
            else {
                while (Character.isLetterOrDigit(exp[i]) && exp[i] != ' ')
                    s.push(exp[i++]);
                if (!s.isEmpty()) {
                    int j = 0, y = 1, size = s.size();
                    float x = 0;
                    while (j < size) {
                        j++;
                        x += ((char) s.pop() - 48) * y;
                        y *= 10;
                    }
                    eval.push(x);
                } else if (exp[i] == '*' || exp[i] == '-' || exp[i] == '+' || exp[i] == '/') {
                    float Y = (float) eval.pop();
                    float X = (float) eval.pop();
                    if (exp[i]=='/'&& Y==0){ System.out.println("Cannot divide on zero"); throw new ArithmeticException();}
                    float result = result(X, Y, exp[i]);
                    eval.push(result);
                    i++;
                } else i++;
            }
        }
        float ans =  (float)eval.pop();
        return (int)ans;
    }
    /**
     * gives the precision of an operator
     * @param ch : the operator
     * @return an integer which increases as the Precision increase
     */
    static int Precision(char ch) {
        switch (ch) {
            case '+': case '-':
                return 1;
            case '*': case '/':
                return 2;
        }
        return -1;
    }
    /**
     * check the prantatheis validation
     * @param expression
     * @return true if expression is valid and false if not
     */
    static boolean CheckValidation(String expression){
        Stack S = new Stack();
        char[] exp = expression.toCharArray();
        for (int i=0;i<exp.length;i++)
            if (exp[i]=='(') S.push('(');
            else if (exp[i]==')'){
                if (S.isEmpty())
                    return false;
                else S.pop();
            }
        if (S.isEmpty())
            return true;
        return false;
    }
    /**
     * @param X : first operand
     * @param Y : second operand
     * @param op : operator
     * @return the result of X op Y
     */
    static float result(float X,float Y,char op){
        switch (op){
            case '+':return X+Y;
            case '-':return X-Y;
            case '*':return X*Y;
            case '/':return X/Y;
        }
        return 0;
    }
}
