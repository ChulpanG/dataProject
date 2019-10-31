
package lab1.ProjectForms;

import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static lab1.ProjectForms.MainPageClass.viewNewsList;

public class Registration{
    public String login;
    public String password;
    
    public boolean registrationValidString(String login){
        this.login=login;
        String n = ".*[0-9].*";
        String a = ".*[A-Z].*";
        return login.matches(n) && login.matches(a);
    }
    //proverka na nol
    public boolean resgistrationValidNull(String login, String password){
        this.login=login;
        this.password=password;
        Integer s;
        boolean finish=false;
        s = 0;
        if ("".equals(login)){
            s=1;
        }
        if ("".equals(password)){
            s=2;
        }
        if("".equals(login)&&"".equals(password)){
            s=3;
        }
        if(s!=0){
            finish=true;
        }
        return finish;
    }
    //proverka a-z 0-9
    public boolean ValidString(String login){
        this.login=login;
        boolean valid = false;
        char[] a = login.toCharArray();

        for (char c: a){
            valid = ((c >= 'a') && (c <= 'z')) || 
                ((c >= 'A') && (c <= 'Z')) || 
                ((c >= '0') && (c <= '9'));  
        }

    return valid;
    }
    
}

   

    