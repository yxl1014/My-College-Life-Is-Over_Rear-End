package org.commons.common.uuid;

import java.util.Date;
import java.util.UUID;

/**
 * @description: UUID 自定义生成类
 * @author zy_
 */

public class UuidGenerator {

    public static void main(String[] args) {
        //这个是以毫秒为单位的时间
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        UUID uuid = getCustomUuid(timestamp);
        System.out.println("UUID:"+uuid);
        System.out.println("DateTIME:"+extractTimeFromUuid(uuid));

    }

    /**
     * @decription: 包含当前timestamp的UUID生成方法
     * @author: GodHammer
     * @date: 2023-10-28 下午7:54
     * @version: v1.0
     */
    public static String getCustomUuid() {
        return getCustomUuid(System.currentTimeMillis()).toString();
    }

    /**
    * @decription: 包含当前timestamp的UUID生成方法
    * @author: GodHammer
    * @date: 2023-10-28 下午7:54
    * @version: v1.0
    */
    public static UUID getCustomUuid(long timestamp) {  //UUID一般是128位的
        long mostSigBits = UUID.randomUUID().getMostSignificantBits();
        long leastSigBits = UUID.randomUUID().getLeastSignificantBits();

        //64位  8个字节  16个16进制数  将前64位清空  即左为前0x0000_0000_0000_0000_0000_0000_0000_0000L
        mostSigBits &= 0x0000_0000_0000_0000L;

        //将前64位置位版本标记位
        mostSigBits |= timestamp;

        return new UUID(mostSigBits, leastSigBits);
    }

    /**
    * @decription: 从UUID中抽取时间
    * @author: GodHammer
    * @date: 2023-10-28 下午8:02
    * @version: v1.0
    */
    public static Date extractTimeFromUuid(UUID uuid){
        Date date;
        long most64 = uuid.getMostSignificantBits();
        date = new Date(most64);
        return date;
    }


}
