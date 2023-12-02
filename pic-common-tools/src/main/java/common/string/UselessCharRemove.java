package common.string;

/**
 * @description: 整理字符串
 * @author: HammerRay
 * @date: 2023/12/1 上午12:29
 */
public class UselessCharRemove {
    /**
     * description: 前端传来的json数据 "" 也会当做字符传到java里面，所有要去除才能得到纯的比如 电话号码
     * @paramType [java.lang.String]
     * @param string:
     * @returnType: java.lang.String
     * @author: GodHammer
     * @date: 2023-12-02 下午11:50
     */
    public static String remove1(String string){
        if (string.startsWith("\"") && string.endsWith("\"")) {
            string = string.substring(1, string.length() - 1);
        }
        return string;
    }
}
