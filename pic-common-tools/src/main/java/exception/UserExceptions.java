package exception;

import log.LogEnum;

/**
 * @Author: eensh
 * @CreateDate: 2023/12/22
 */
public class UserExceptions {
    public static class EmptyUserException extends Exception {
        public EmptyUserException() {
            super(LogEnum.USER_EMPTY.getMsg());
            System.out.println(LogEnum.USER_EMPTY.getMsg());
        }
    }

    public static class UserExistsException extends Exception {
        public UserExistsException() {
            super(LogEnum.USER_EXISTS.getMsg());
            System.out.println(LogEnum.USER_EXISTS.getMsg());
        }
    }

    public static class UserIsNullException extends Exception {
        public UserIsNullException() {
            super(LogEnum.USER_Is_Null.getMsg());
            System.out.println(LogEnum.USER_Is_Null.getMsg());
        }
    }

    public static class UserNoExistsException extends Exception {
        public UserNoExistsException() {
            super(LogEnum.USER_NO_Exists.getMsg());
            System.out.println(LogEnum.USER_NO_Exists.getMsg());
        }
    }
    public static class UserHasRoleException extends Exception {
        public UserHasRoleException() {
            super(LogEnum.USER_HAS_ROLE.getMsg());
            System.out.println(LogEnum.USER_HAS_ROLE.getMsg());
        }
    }
    public static class UserNoRoleException extends Exception {
        public UserNoRoleException() {
            super(LogEnum.USER_NO_ROLE.getMsg());
            System.out.println(LogEnum.USER_NO_ROLE.getMsg());
        }
    }
}
