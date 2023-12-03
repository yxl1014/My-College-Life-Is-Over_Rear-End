package example.service.userservice.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import example.entity.database.User;
import example.mapper.UserDao;
import example.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public IPage<Book> getPage(int currentPage, int pageSiza) {
        IPage page = new Page(currentPage, pageSiza);
        userDao.selectPage(page,null);
        return page;
    }
}
