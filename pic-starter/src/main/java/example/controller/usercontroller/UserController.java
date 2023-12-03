package example.controller.usercontroller;

import com.baomidou.mybatisplus.extension.api.R;
import example.entity.database.User;
import example.service.userservice.UserService;
import example.service.userservice.impl.VerifyUserIdCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.R_Code;
import response.ReBody;

import java.awt.print.Book;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private VerifyUserIdCard verifyUserIdCard;

    @GetMapping
    public ReBody getAll(){
        return  new ReBody(R_Code.R_Ok, userService.list());
    }

    @PostMapping
    public ReBody save(@RequestBody User user){
        return  new ReBody(R_Code.R_Ok,userService.save(user));
    }

    @PutMapping
    public ReBody update(@RequestBody User user){
        return new ReBody(R_Code.R_Ok,userService.updateById(user));
    }

    @DeleteMapping("{userId}")
    public ReBody delete(@PathVariable String userId){
        return new ReBody(verifyUserIdCard.verifyIdCard(userId).getRCode(),userService.removeById(userId));
    }

    @GetMapping("{userId}")
    public ReBody getById(@PathVariable String userId){
        return new ReBody(verifyUserIdCard.verifyIdCard(userId).getRCode(), userService.getById(userId));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public ReBody getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return new ReBody(R_Code.R_Ok,userService.getPage(currentPage,pageSize));
    }
}
