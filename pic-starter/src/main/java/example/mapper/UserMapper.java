package example.mapper;


import org.apache.ibatis.annotations.Mapper;
import example.entity.database.User;

import java.util.List;

/**
* @decription: User类的增删改查接口
* @author: GodHammer
* @date: 2023-11-04 下午6:20
* @version: v1.0
*/

@Mapper

public interface UserMapper {
    /**
     * 插入一条新的用户信息记录
     * @paramType: User
     * @param user 整个一个新的用户实例对象
     * @returnType: User
     * @author: GodHammer
     * @date: 2023-11-04 下午11:09
     * @version: v1.0
     */
    int insert (User user);
    /**
     * 根据id删除一条用户信息记录
     * @paramType: User
     * @param user 只用用户id封装的用户对象
     * @returnType: int
     * @author: GodHammer
     * @date: 2023-11-04 下午11:09
     * @version: v1.0
     */
    int  delete (User user);
    /**
     * 用新的用户信息，替换掉旧的
     * @paramType: User
     * @param user 更新完的用户实例对象
     * @returnType: User
     * @author: GodHammer
     * @date: 2023-11-04 下午11:08
     * @version: v1.0
     */
    int update (User user);

    /**
     * 通过 用户名 或 电话号 或 邮箱 查询一个学生的信息
     * @paramType: User
     * @param user 只有一个id组成的user
     * @returnType:
     * @author: GodHammer
     * @date: 2023-11-04 下午9:20
     * @version: v1.0
     */
    User selectOne(User user);

    /**
     * 查找所有学生的个人信息，一条一条的  fetch data by rule id
     * @paramType:
     * @returnType:
     * @author: GodHammer
     * @date: 2023-11-04 下午9:18
     * @version: v1.0
     */
    List<User> selectAll();
}
