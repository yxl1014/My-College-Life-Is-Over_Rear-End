package org.commons.exception;

/**
 * @Author: eensh
 * @CreateDate: 2023/12/20
 */

import org.commons.log.LogEnum;

public class PowerExceptions {

    public static class EmptyPowerException extends Exception {
        public EmptyPowerException() {
            super(LogEnum.POWER_EMPTY.getMsg());
            System.out.println(LogEnum.POWER_EMPTY.getMsg());
        }
    }

    public static class PowerExistsException extends Exception {
        public PowerExistsException() {
            super(LogEnum.POWER_EXISTS.getMsg());
            System.out.println(LogEnum.POWER_EXISTS.getMsg());
        }
    }

    public static class PowerIsNullException extends Exception {
        public PowerIsNullException() {
            super(LogEnum.POWER_Is_Null.getMsg());
            System.out.println(LogEnum.POWER_Is_Null.getMsg());
        }
    }

    public static class PowerNoExistsException extends Exception {
        public PowerNoExistsException() {
            super(LogEnum.POWER_NO_Exists.getMsg());
            System.out.println(LogEnum.POWER_NO_Exists.getMsg());
        }
    }


}