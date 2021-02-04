package fr.syrows.staffmodlib.exceptions;

public class StaffModException extends RuntimeException {

    public StaffModException(String message) {
        super(message);
    }

    public StaffModException(String message, Throwable cause) {
        super(message, cause);
    }
}
