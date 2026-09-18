package com.craftinginterpreters.lox;

public class RpnPrinter implements Expr.Visitor<String> {
  String print(Expr expression) {
    return expression.accept(this);
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr) {
    return print(expr.left)
        + " "
        + print(expr.right)
        + " "
        + expr.operator.lexeme;
  }
@Override
public String visitConditionalExpr(Expr.Conditional expr) {
  return print(expr.condition)
      + " "
      + print(expr.thenBranch)
      + " "
      + print(expr.elseBranch)
      + " ?:";
}
  @Override
  public String visitGroupingExpr(Expr.Grouping expr) {
    return print(expr.expression);
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr) {
    if (expr.value == null) {
      return "nil";
    }

    return expr.value.toString();
  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr) {
    return print(expr.right) + " " + expr.operator.lexeme;
  }

  public static void main(String[] args) {
    Token plus = new Token(TokenType.PLUS, "+", null, 1);
    Token minus = new Token(TokenType.MINUS, "-", null, 1);
    Token star = new Token(TokenType.STAR, "*", null, 1);

    Expr expression = new Expr.Binary(
        new Expr.Grouping(
            new Expr.Binary(
                new Expr.Literal(1),
                plus,
                new Expr.Literal(2))),
        star,
        new Expr.Grouping(
            new Expr.Binary(
                new Expr.Literal(4),
                minus,
                new Expr.Literal(3))));

    System.out.println(new RpnPrinter().print(expression));
  }
}