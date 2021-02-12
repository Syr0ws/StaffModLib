package fr.syrows.staffmodlib.common.exceptions;

public class StaffModException extends RuntimeException {

    public StaffModException(String message) {
        super(message);
    }

    public StaffModException(String message, Throwable cause) {
        super(message, cause);
    }
}
