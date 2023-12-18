package org.mysql.util;

import exception.FormatException;
import org.mysql.domain.Announcement;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author yxl17
 * @Package : org.mysql.util
 * @Create on : 2023/12/17 13:10
 **/

@Component
public class MysqlCommonUtil {

    /**
     * 将java对象的字段名转换成mysql的列名
     *
     * @param fieldName java类的字段名
     * @return mysql的列名
     */
    public String javaFieldName2MysqlColumnsName(String fieldName) throws FormatException {
        StringBuilder mysqlName = new StringBuilder();
        int len = fieldName.length();
        StringBuilder temp = new StringBuilder();
        for (int index = 0; index < len; index++) {
            char s = fieldName.charAt(index);
            int i = checkLetterUpperOrLower(s);
            switch (i) {
                // 大写的话 将之前的给加到结果当中
                case 0: {
                    if (temp.length() == 0) {
                        continue;
                    }
                    mysqlName.append(temp.append("_"));
                    temp = new StringBuilder();
                    temp.append(Character.toLowerCase(s));
                    break;
                }
                case 1: {
                    temp.append(Character.toLowerCase(s));
                    if (index + 1 == len) {
                        mysqlName.append(temp);
                        break;
                    }
                    break;
                }
                case -1:
                default:
                    throw new FormatException("javaFieldName2MysqlColumnsName");
            }
        }
        return mysqlName.toString();
    }

    /**
     * 判断字母是大写还是小写  大写返回0  小写返回1 不是字母则返回-1
     *
     * @param letter 字母
     * @return
     */
    public int checkLetterUpperOrLower(char letter) {
        if (Character.isUpperCase(letter)) {
            return 0;
        }
        if (Character.isLowerCase(letter)) {
            return 1;
        }
        return -1;
    }

    public static void main(String[] args) throws FormatException {
        MysqlCommonUtil mysqlCommonUtil = new MysqlCommonUtil();
        Class<Announcement> clz = Announcement.class;
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + "--->" + mysqlCommonUtil.javaFieldName2MysqlColumnsName(field.getName()));
        }
    }

}
