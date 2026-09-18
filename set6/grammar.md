expression  -> comma ;
comma       -> conditional ( "," conditional )* ;
conditional -> equality ( "?" expression ":" conditional )? ;
equality   -> comparison ( ( "!=" | "==" ) comparison )* ;
comparison -> term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
term       -> factor ( ( "-" | "+" ) factor )* ;
factor     -> unary ( ( "/" | "*" ) unary )* ;
unary      -> ( "!" | "-" ) unary
            | primary ;
primary    -> NUMBER | STRING | "true" | "false" | "nil"
            | "(" expression ")" ;

For this one, I placed the comma rule above the other expression rules because
the comma operator has the lowest precedence. The repeated part of the rule
also makes a series such as `a, b, c` group from the left as `(a, b), c`, which
matches how it works in C.

For the ternary operator the expression between the ? and : can use any precedence level because that part goes back through the full expression rule.
The last part uses conditional again so the operator groups from the right.
For example a ? b : c ? d : e is treated as a ? b : (c ? d : e).

For this part I had the parser report the missing left side and then keep reading the right side at the operator's normal precedence. The + operator
reads through a factor. The * and / operators read through a unary expression. I did it this way so one bad operator would not throw off the rest of the
expression or cause extra errors. I did not treat a leading - as a mistake because Lox already uses it for unary negation like -5.
