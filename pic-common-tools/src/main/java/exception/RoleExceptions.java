package exception;

/**
 * @Author: eensh
 * @CreateDate: 2023/12/21
 */

import log.LogEnum;

public class RoleExceptions {
    public static class EmptyRoleException extends Exception {
        public EmptyRoleException() {
            super(LogEnum.ROLE_EMPTY.getMsg());
            System.out.println(LogEnum.ROLE_EMPTY.getMsg());
        }
    }

    public static class RoleExistsException extends Exception {
        public RoleExistsException() {
            super(LogEnum.ROLE_EXISTS.getMsg());
            System.out.println(LogEnum.ROLE_EXISTS.getMsg());
        }
    }

    public static class RoleIsNullException extends Exception {
        public RoleIsNullException() {
            super(LogEnum.ROLE_Is_Null.getMsg());
            System.out.println(LogEnum.ROLE_Is_Null.getMsg());
        }
    }

    public static class RoleNoExistsException extends Exception {
        public RoleNoExistsException() {
            super(LogEnum.ROLE_NO_Exists.getMsg());
            System.out.println(LogEnum.ROLE_NO_Exists.getMsg());
        }
    }

    public static class RoleAndUserEmptyException extends Exception {
        public RoleAndUserEmptyException() {
            super(LogEnum.ROLE_USER_EMPTY.getMsg());
            System.out.println(LogEnum.ROLE_USER_EMPTY.getMsg());
        }
    }

    public static class RoleAndPowerEmptyException extends Exception {
        public RoleAndPowerEmptyException() {
            super(LogEnum.ROLE_POWER_EMPTY.getMsg());
            System.out.println(LogEnum.ROLE_POWER_EMPTY.getMsg());
        }
    }
    public static class RoleHasPowerException extends Exception {
        public RoleHasPowerException() {
            super(LogEnum.ROLE_HAS_POWER.getMsg());
            System.out.println(LogEnum.ROLE_HAS_POWER.getMsg());
        }
    }

    public static class RoleNoPowerException extends Exception {
        public RoleNoPowerException() {
            super(LogEnum.ROLE_NO_POWER.getMsg());
            System.out.println(LogEnum.ROLE_NO_POWER.getMsg());
        }
    }
}
