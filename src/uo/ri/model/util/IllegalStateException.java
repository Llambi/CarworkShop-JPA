package uo.ri.model.util.exception;

public class IllegalStateException extends Exception {
    private static final long serialVersionUID = 4001710687990554589L;

    public IllegalStateException() {
    }

    public IllegalStateException(String message) {
        super(message);
    }

    public IllegalStateException(Throwable cause) {
        super(cause);
    }

    public IllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }

}
