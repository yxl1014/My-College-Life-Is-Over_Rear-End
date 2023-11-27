package org.example.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.model.dao.RoleMapper;
import org.example.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */
@Service("RoleMapper")
public class RoleMapperImpl implements RoleMapper {
    private SqlSession sqlSession;
    public void RoleMapperImpl(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }

        @Override
        public void insert(Role role){
        sqlSession.insert("AddRole",role);
         }
         @Override
        public void delete(Role role) {
        sqlSession.delete("DeleteRoleById", role);
        }
        @Override
        public void update(Role role) {
            sqlSession.update("UpdateRole", role);
        }

        @Override
        public Role selectOne(Integer roleId) {
            return sqlSession.selectOne("GetRoleById", roleId);
        }

    @Override
    public List<Role> selectAll() {
        return sqlSession.selectList("SelectAll");
    }
}
