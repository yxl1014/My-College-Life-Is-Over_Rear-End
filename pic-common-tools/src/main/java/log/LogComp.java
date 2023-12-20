package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author yxl17
 * @Package : org.example.log
 * @Create on : 2023/11/11 20:25
 **/
public class LogComp {
    public static Logger getLogger(Class<?> clz) {
        return LogManager.getLogger(clz);
    }

    public static LogMessage buildData(LogType logType, LogEnum logEnum) {
        return new LogMessage(logType, logEnum);
    }

    public static LogMessage buildData(LogType logType) {
        return new LogMessage(logType);
    }

    public static LogMessage buildPowerExists() { return new LogMessage(LogType.POWER, LogEnum.POWER_EXISTS);
    }
    public static LogMessage buildPowerEmpty() {
        return new LogMessage(LogType.POWER, LogEnum.POWER_EMPTY);
    }

    public static LogMessage buildPowerIdIsNull() {
        return new LogMessage(LogType.POWER, LogEnum.POWER_Id_Is_Null);
    }



    public static class LogMessage {
        private final StringBuilder logMsg;

        private LogMessage(LogType logType, LogEnum logEnum) {
            logMsg = new StringBuilder();
            logMsg.append("LogType -> ").append(logType.getName()).append(" -- ").append(logEnum.getType()).append(" -- ").append(logEnum.getMsg());
        }

        private LogMessage(LogType logType) {
            logMsg = new StringBuilder();
            logMsg.append("LogType -> ").append(logType.getName());
        }

        public LogMessage build(LogEnum logEnum) {
            logMsg.append(" -- ").append(logEnum.getType()).append(" -- ").append(logEnum.getMsg());
            return this;
        }

        public LogMessage build(String key, String value) {
            logMsg.append(" -- ").append(key).append(" -> ").append(value);
            return this;
        }



        public String log() {
            return logMsg.toString();
        }
    }
}
