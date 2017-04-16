package ro.sci.h11.db;

/**
 * Booking Data Base exception class.
 *
 * @author Emanuel Pruker
 */
public class BookingsDbException extends Throwable {
    public BookingsDbException(String s, Exception e) {
        super(s, e);
    }

    public BookingsDbException(String s) {
        super(s);
    }
}
