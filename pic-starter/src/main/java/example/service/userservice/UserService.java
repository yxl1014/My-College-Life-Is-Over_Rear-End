package example.service.userservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import example.entity.database.User;

import java.awt.print.Book;

public interface UserService extends IService<User> {
    IPage<Book> getPage(int currentPage, int pageSiza);
}
