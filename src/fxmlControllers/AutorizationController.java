
package fxmlControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab1.ProjectForms.AutoData;

public class AutorizationController implements Initializable{
   
    @FXML
    private Label label1; 
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    public TextField textField1 ;
    @FXML
    private PasswordField password1;
    @FXML
    private Button enter2;
    @FXML
    private Button krest1;
    @FXML
    private Button krest2;
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        
        String login = textField1.getText();
        String password = password1.getText();
        AutoData autoData = null;
        if(autoData.search(login,password)){
        Stage stage = new Stage();
        stage.setTitle("Главная");
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/mainPage.fxml"));
        //root.setStyle("-fx-background-image: url('/shop/grad.jpg'); "+"-fx-background-size:auto;");
        //Shop a1=new Shop(l);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) enter2.getScene().getWindow();
        stage1.close();
        //NewClass.setGOV(l);
        }
        else {
            label3.setText("incorrect login or password");
        }
    }
    @FXML
    // Кнопка "Отмена"
    private void cleanButtonAction1(ActionEvent event) {
        textField1.setText("");
    }
    @FXML
    private void cleanButtonAction2(ActionEvent event) {
        password1.setText("");
    }
    
    @FXML
   private void h(ActionEvent event) throws IOException{
       Stage stage = new Stage();
        stage.setTitle("Регистрация");
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/registrat1.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) enter2.getScene().getWindow();
        stage1.close();
        
        
    } 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
}
    

