package core.spring.exception;


public class EntitiNotFoundException extends RuntimeException{
    public EntitiNotFoundException() {
    }

    public EntitiNotFoundException(String message) {
        super(message);
    }

    public EntitiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntitiNotFoundException(Throwable cause) {
        super(cause);
    }
}
