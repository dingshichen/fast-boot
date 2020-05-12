package cn.dsc.doc.exception;

/**
 * 系统异常
 *
 * @author dingshichen
 * @version 1.0
 * @since 1.0
 */
public class ApiException extends Exception {

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

}
