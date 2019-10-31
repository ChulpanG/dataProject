
package fxmlControllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab1.ProjectForms.News;

public class AddNewsController implements Initializable{
    @FXML
    private TextField newsName;
    @FXML
    private TextField newsCreator;
    @FXML
    private TextArea newsText;
    @FXML
    private DatePicker newsDate;
    @FXML
    private Button cancel;
    @FXML
    Button edit;
    @FXML
    Button add;
    
    private Stage dialogStage;
    private News news;
    private boolean okClicked = false;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    void addButton(ActionEvent action) throws SQLException, IOException{
        News news = new News();
        if (newsName.getText() == null | newsText.getText() == null | newsCreator.getText() == null | newsDate.getValue() == null){
            
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка добавления");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля");
            
            alert.showAndWait();
        } else {
            String name = newsName.getText();
            String text = newsText.getText();
            String creator = newsCreator.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy",Locale.US);
            String date = (newsDate.getValue()).format(formatter);
            News addNewsContent = new News(0,name,text,creator,date);
            News.addNews(addNewsContent);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Новость успешно добавлена");

            alert.showAndWait();
            
            Stage stage = new Stage();
            stage.setTitle("Главная");
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/mainPage.fxml"));
        
            Scene scene = new Scene(root);
        
            stage.setScene(scene);
            stage.show(); 
            Stage stage1 = (Stage) add.getScene().getWindow();
            stage1.close();
            
        }
        
    }
    private int id;
    public void setID(int id){
    this.id = id;
    }
    public int getID(){
        return id;
    }
    @FXML
    public void editButton (ActionEvent action) throws SQLException, IOException, FileNotFoundException, ClassNotFoundException{
        //MainPageController main = new MainPageController();
        
        int id = getID();
        System.out.println("id = "+id);
        String name = newsName.getText();
        String text = newsText.getText();
        String creator = newsCreator.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy",Locale.US);
        String date = (newsDate.getValue()).format(formatter);
        
        News news = new News();
        
        if (news.newsValidNull(name, text, creator, date)){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка добавления");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля");
            
            alert.showAndWait();
        } else {
            News addNewsContent = new News(id,name,text,creator,date);
            News.editNews(addNewsContent);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Новость успешно изменена");

            alert.showAndWait();
            
            Stage stage = new Stage();
            stage.setTitle("Главная");
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/mainPage.fxml"));
        
            Scene scene = new Scene(root);
        
            stage.setScene(scene);
            stage.show(); 
            Stage stage1 = (Stage) edit.getScene().getWindow();
            stage1.close();
        }
        
    }

    public void SetNews(News news){
        
    newsName.setText(news.getNewsName());
    newsText.setText(news.getNewsText());
    newsCreator.setText(news.getNewsCreator());
       
    String dateValue = news.getNewsDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy",Locale.US);
    newsDate.setValue(LocalDate.parse(dateValue,formatter));
       
    }
    public int getNewsID(News news){
    int newsID;
    return newsID = news.getNewsID();
    }
    @FXML
    public void cancelButton(ActionEvent action) throws IOException{
          
    Stage stage = new Stage();
    stage.setTitle("Главная");
    Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/mainPage.fxml"));
        
    Scene scene = new Scene(root);
       
    stage.setScene(scene);
    stage.show();
    Stage stage1 = (Stage) cancel.getScene().getWindow();
    stage1.close();
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
