package edu.curso.bibliotecafx;

public class LivrariaException extends Exception {

    public LivrariaException(String message){
        super(message);
    }

    public LivrariaException() {
        super();
    }

    public LivrariaException( Throwable t ) {
        super(t);
    }
}
