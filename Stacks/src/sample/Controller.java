package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
    Stack stack = new Stack();
    ExpressionEvaluator exp = new ExpressionEvaluator();
    @FXML
    private TextArea push;
    @FXML
    private TextArea Pop;
    @FXML
    private TextArea Peek;
    @FXML
    private TextArea IsEmpty;
    @FXML
    private TextArea size;
    @FXML
    private TextArea St;
    @FXML
    private TextArea Expression;
    @FXML
    private TextArea Value;
    @FXML
    private TextArea Postfix;
    public void pressButton(ActionEvent press){
        String s = push.getText();
        stack.push(s);
        push.clear();
        St.clear();
        St.appendText(stack.printStack(stack));
    }
    public void pressButton2(ActionEvent press){
        String s =(String) stack.pop();
        Pop.clear();
        Pop.appendText(s);
        St.clear();
        St.appendText(stack.printStack(stack));
    }
    public void pressButton3(ActionEvent press){
        String s =(String) stack.peek();
        Peek.clear();
        Peek.appendText(s);
    }
    public void pressButton4(ActionEvent press){
        IsEmpty.clear();
        if (stack.isEmpty()) IsEmpty.appendText("True");
        else IsEmpty.appendText("False");
    }
    public void pressButton5(ActionEvent press){
        size.clear();
        String X = String.valueOf((char) (stack.size()+48));
        size.appendText(X);
    }
    public void pressButton6(ActionEvent press){
        Postfix.clear();
        Value.clear();
        String x = exp.infixToPostfix(Expression.getText());
        Postfix.appendText(x);
        String X = String.valueOf(exp.evaluate(x));
        Value.appendText(X);
    }
}
