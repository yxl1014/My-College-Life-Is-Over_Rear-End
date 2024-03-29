package org.commons.exception;

import org.commons.log.LogEnum;

/**
 * @author yxl17
 * @Package : exception
 * @Create on : 2023/12/17 13:19
 **/
public class FormatException extends Exception {

    public FormatException() {
        super(LogEnum.EXCEPTION_FORMAT_ERROR.getMsg());
    }

    public FormatException(String message) {
        super(LogEnum.EXCEPTION_FORMAT_ERROR.getMsg() + "——" + message);
    }
}
