package com.sharing.expense.ExpenseSharing.ExpenseSevices;
import com.sharing.expense.ExpenseSharing.DummyClasses.LoginAttributes;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.User;
import com.sharing.expense.ExpenseSharing.ExpenseRepository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

//All the logic related to login and registration is here.

@Service
public class UserService {


    @Autowired
    UserRepository userJPA ;

    @Autowired
    ExpenseRepository expenseJPA ;

    @Autowired
    private HttpSession session ;


    public String userRegistration(Map<String,Object> post) {
            String username = (String) post.get("username");
            String email = (String) post.get("email");
            String phoneNo = (String) post.get("phoneno") ;
            String password = (String) post.get("password");

            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setPhoneNo(phoneNo);
            user.setPassword(password);

            userJPA.save(user);

            return "OK";

    }

    public String userLogin(LoginAttributes loginattributes) {
        String email = loginattributes.getEmail() ;
        String password = loginattributes.getPassword() ;
        User user = userJPA.findByEmail(email) ;
        if(user == null) return "Login Unsuccessful";

        if(password.equals(user.getPassword())){
            session.setAttribute("username" , user.getName());
            return "Login Successful";
        }
        return "Login Unsuccessful";
    }

    boolean ValidUserOrNot(String username){
        if(username == null) return false;
        return true ;
    }

    public List<User> getAlluser() {
        return userJPA.findAll() ;
    }
}
