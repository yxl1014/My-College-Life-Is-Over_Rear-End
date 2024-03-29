package org.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 用户登录进来ThreadLocal当中存的类 在系统中可以随时获取
 * @author yxl17
 * @Package : org.commons.domain
 * @Create on : 2023/12/24 17:15
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginCommonData {

    /**
     * 用户的UID
     */
    private String userId;
    /**
     * 用户的随机校验码 也可一说是版本
     */
    private int version;
    /**
     * 时间戳
     */
    private long timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginCommonData that = (LoginCommonData) o;
        return version == that.version && timestamp == that.timestamp && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, version, timestamp);
    }
}
