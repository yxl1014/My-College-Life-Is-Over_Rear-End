package org.example.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.model.dao.UserMapper;
import org.example.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */

@Service("UserMapper")
public class UserMapperImpl implements UserMapper {
    private SqlSession sqlSession;
    public void RoleMapperImpl(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }

    @Override
    public void insert(User user){sqlSession.insert("AddUser",user);
    }
    @Override
    public void delete(User user) {
        sqlSession.delete("DeleteUserById", user);
    }
    @Override
    public void update(User user) {
        sqlSession.update("UpdateUser", user);
    }

    @Override
    public User selectOne(Integer userId) {
        return sqlSession.selectOne("GetUserById", userId);
    }

    @Override
    public List<User> selectAll() {
        return sqlSession.selectList("SelectAll");
    }
}
