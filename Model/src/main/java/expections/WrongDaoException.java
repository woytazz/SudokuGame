package expections;

import java.io.IOException;

public class WrongDaoException extends IOException {
    public WrongDaoException(Throwable cause) {
        super(cause);
    }
}
