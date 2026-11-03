package org.example.javaspringboot_beadando_eloadas;

public class TestFailureException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public TestFailureException(String message) {
        super("FAILURE:"+message);
    }
    public TestFailureException(Exception e) {
        super(e);
    }
}