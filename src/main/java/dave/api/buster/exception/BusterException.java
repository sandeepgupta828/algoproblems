package dave.api.buster.exception;

public class BusterException extends RuntimeException{
    private int errorCode = -1;

    public BusterException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusterException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BusterException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusterException(String message) {
        super(message);
    }
}
