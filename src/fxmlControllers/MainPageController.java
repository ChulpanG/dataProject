
package fxmlControllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lab1.ProjectForms.MainPageClass;
import lab1.ProjectForms.News;

public class MainPageController implements Initializable {
    public News selectedNews;
    @FXML
    TableView<News> newsList;
    @FXML
    private Button add;
    @FXML 
    private Button cancel;
    @FXML
    private Button edit;
    @FXML
    private TableColumn<News, Integer> newsID;
    @FXML
    private TableColumn<News, String> newsName;
    @FXML
    private TableColumn<News, String> newsText;
    @FXML
    private TableColumn<News, String> newsCreator;
    @FXML
    private TableColumn<News, String> newsDate;
    
    List list = MainPageClass.viewNewsList();
    ObservableList<News> mainList = FXCollections.observableArrayList(list);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        newsID.setCellValueFactory(new PropertyValueFactory<News, Integer>("newsID"));
        newsName.setCellValueFactory(new PropertyValueFactory<News, String>("newsName"));
        newsText.setCellValueFactory(new PropertyValueFactory<News, String>("newsText"));
        newsCreator.setCellValueFactory(new PropertyValueFactory<News, String>("newsCreator"));
        newsDate.setCellValueFactory(new PropertyValueFactory<News, String>("newsDate"));
        
        newsList.setItems(mainList);
        
    }
    public void showPersonEditDialog(News news) {
    try {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainPageController.class.getResource("/fxmlForms/AddNews.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Изменение новости");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        AddNewsController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.SetNews(news);
        controller.setID(controller.getNewsID(news));
        controller.add.setVisible(false);
        controller.edit.setVisible(true);
   
        dialogStage.showAndWait();
        Stage stage1 = (Stage) cancel.getScene().getWindow();
        stage1.close();
  
        
    } catch (IOException e) {
        e.printStackTrace();
    }
       
    }
    
    @FXML
    public void editNewsButton(ActionEvent action) {
   
    News selectedNews = newsList.getSelectionModel().getSelectedItem();
    if (selectedNews != null) {
        showPersonEditDialog(selectedNews);
        
        int selectedIndex=newsList.getSelectionModel().getSelectedIndex();
        mainList.set(selectedIndex, selectedNews);
        
        
    } else {
        // Ничего не выбрано.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(null);
        alert.setTitle("No Selection");
        alert.setHeaderText("No News Selected");
        alert.setContentText("Please select a news in the table.");

        alert.showAndWait();
    }
    }
    @FXML
    void deleteButton(ActionEvent action){
    News selectedNews = newsList.getSelectionModel().getSelectedItem();
    int selectedIndex=newsList.getSelectionModel().getSelectedIndex();
    newsList.getItems().remove(selectedIndex);
    //mainList.remove(selectedIndex);
    if (selectedNews != null) {
    News.deleteNews(selectedNews);
        }else{
            Alert alert=new Alert(AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Не выбрана новость");
            alert.setContentText("Выберите новость в таблице");
            alert.showAndWait();
            
        }
    }
    @FXML
    void cancelButton(ActionEvent action) throws IOException{
          
        Stage stage = new Stage();
        stage.setTitle("Авторизация");
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/Autorization.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show(); 
        Stage stage1 = (Stage) cancel.getScene().getWindow();
        stage1.close();
        
    }
    @FXML
    void addButton(ActionEvent action) throws IOException{
        
        Stage stage = new Stage();
        stage.setTitle("Добавление новости");
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlForms/AddNews.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show(); 
        Stage stage1 = (Stage) cancel.getScene().getWindow();
        stage1.close();
    }
}
