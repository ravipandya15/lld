package com.example.lld.practise.designPatterns.CompositeDesignPattern.ArithmeticExpression;

public class Expression implements ArithmeticExpression {
    ArithmeticExpression leftExpression;
    ArithmeticExpression rightExpression;
    Operation operation;

    public Expression(ArithmeticExpression leftExpression, ArithmeticExpression rightExpression, Operation operation) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operation = operation;
    }


    @Override
    public int evaluate() {
        int result = 0;

        switch (operation) {
            case ADD:
                result = leftExpression.evaluate() + rightExpression.evaluate();
                break;
            case SUBTRACT:
                result = leftExpression.evaluate() - rightExpression.evaluate();
                break;
            case MULTIPLY:
                result = leftExpression.evaluate() * rightExpression.evaluate();
                break;
            case DIVISION:
                result = leftExpression.evaluate() / rightExpression.evaluate();
                break;
        }

        System.out.println("Expression value is: " + result);
        return result;
    }
}
