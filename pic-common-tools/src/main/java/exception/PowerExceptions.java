package exception;

/**
 * @Author: eensh
 * @CreateDate: 2023/12/20
 */

import log.LogEnum;

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
    public static class PowerIdIsNullException extends Exception {
        public PowerIdIsNullException() {
            super(LogEnum.POWER_Id_Is_Null.getMsg());
            System.out.println(LogEnum.POWER_Id_Is_Null.getMsg());
        }
    }



}