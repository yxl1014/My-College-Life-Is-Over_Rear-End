package org.commons.common.uuid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;


/**
 * 随机生成Uuid的组件
 * 这里使用JDK自带的UUID生成
 * 之后感兴趣的可以读一下源码自己写一个
 * @author yxl17
 * @Package : org.example.common.uuid
 * @Create on : 2023/11/12 13:18
 **/


@Component
public class SystemUuidComp {
    /**
     * 很垃圾 慎用 没有办法反解析回去了 只能用再一些没有含义的UUID上 BY YXL
     * 或者你可以自己给他拼上 -
     * 他的格式是这样的: FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF
     * @return UUID
     */
    public String genUuid(){
        UUID uuid = UUID.randomUUID();
        return StringUtils.remove(uuid.toString(), '-');
    }

    /**
     * @param username 用户名
     * @return 带-的uuid 可以通过uuid反解出来username
     */
    public String genUserUuidByUserName(String username){
        UUID uuid = UUID.nameUUIDFromBytes(username.getBytes(StandardCharsets.UTF_8));
        return uuid.toString();
    }
}
