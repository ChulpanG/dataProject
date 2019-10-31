package fxmlControllers;

import lab1.ProjectForms.Registration;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import javax.swing.BorderFactory;
import lab1.ProjectForms.AutoData;

public class RegistrationController implements Initializable {
    
    @FXML
    private Label label1; 
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private TextField loginreg ;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private PasswordField passwordreg;
    @FXML
    private PasswordField passwordreg2;
    @FXML
    private Label error;
    @FXML
    private Button okbutton;
    @FXML
    private Button nazad;
    
    @FXML
    void reg(ActionEvent event) throws IOException, SQLException{
        String l= loginreg.getText();
        String p= passwordreg.getText();
        String p1=passwordreg2.getText();
        String e = email.getText();
        String n = name.getText();
        String s = surname.getText();
        Registration a = new Registration();
        boolean log=false;
        boolean pass=false;
        boolean search=false;
        
        if(a.ValidString(l)==false){
            error.setText("Используйте только символы A-Z и 0-9") ;  
            loginreg.setStyle("-fx-border-color: #d00000;"+"-fx-border-width: 2;");
        }
        else{
            error.setText("Все окей бро");
            log=true;
            loginreg.setStyle("-fx-border-color: transparent;"+"-fx-border-width: 0;");
        }
        
        if (p.equals(p1)){
            pass=true;
            passwordreg.setStyle("-fx-border-color: transparent;"+"-fx-border-width: 0;");
            passwordreg2.setStyle("-fx-border-color: transparent;"+"-fx-border-width: 0;");
        }
         else
        {
            error.setText("Пароли не совпадают");
            passwordreg.setStyle("-fx-border-color: #d00000;"+"-fx-border-width: 2;");
            passwordreg2.setStyle("-fx-border-color: #d00000;"+"-fx-border-width: 2;");
        }
        
        if(a.resgistrationValidNull(l,p)){
            log=false;
            pass=false;
            error.setText("Заполните все поля");
            loginreg.setStyle("-fx-border-color: #d00000;"+"-fx-border-width: 2;");
            passwordreg.setStyle("-fx-border-color: #d00000;"+"-fx-border-width: 2;");
            passwordreg2.setStyle("-fx-border-color: #d00000;"+"-fx-border-width: 2;");
        }
        if(AutoData.searchForRegistration(l)){
        search = false;  
        error.setText("Такой пользователь уже существует");
        } else { search = true; }
        if(log&pass&search){
            
           AutoData autoData = new AutoData(l,p,e,n,s);
           autoData.add(autoData);
            
           Stage stage = new Stage();
           stage.setTitle("Авторизация");
           Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/Autorization.fxml"));
       
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
           Stage stage1 = (Stage) okbutton.getScene().getWindow();
           stage1.close();
        }   
    }  
    
     
@FXML
   void Back (ActionEvent evenet) throws IOException{
        
        Stage stage = new Stage();
        stage.setTitle("Вход");
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/Autorization.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show(); 
        Stage stage1 = (Stage) nazad.getScene().getWindow();
        stage1.close();
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}