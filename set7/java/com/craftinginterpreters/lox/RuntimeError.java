package com.craftinginterpreters.lox;

class RuntimeError extends RuntimeException {
    private static final long serialVersionUID = 1L;
    final Token token;
    
}
