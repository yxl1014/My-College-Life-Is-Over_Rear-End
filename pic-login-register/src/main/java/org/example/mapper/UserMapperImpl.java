//package org.example.mapper;
//
//import org.example.entity.database.User;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * User类的增删改查接口实现
// * @author: HammerRay
// * @date: 2023/11/4 下午6:36
// */
//@Service
//public class UserMapperImpl implements UserMapper {
//    @Override
//    public User insert(User user) {
//
//        return null;
//    }
//
//    @Override
//    public int delete(User user) {
//        return 0;
//    }
//
//    @Override
//    public User update(User user) {
//        return null;
//    }
//
//    @Override
//
//    public User selectOne(User user,byte flag) {
//        switch (flag){
//            case 1:
//            case 2:
//            case 3:
//            default:throw new RuntimeException("从数据库查询信息出错，没有配对选项");
//        }
//
//    }
//
//    @Override
//    public List<User> selectAll() {
//        return null;
//    }
//}
