package exception;

/**
 * 操作失败 异常
 * @author By-Lin
 */
public class OperationalException extends RuntimeException {
    public OperationalException() {
    }

    public OperationalException(String message) {
        super(message);
    }
}
