package org.sunhb.beans;

/**
 * @author: SunHB
 * @createTime: 2023/12/23 下午9:51
 * @description:
 */
public class BeanException extends RuntimeException {
    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
