
package lab1.ProjectForms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import static lab1.ProjectForms.AutoData.getDBConnection;

public class News{
    int newsID;
    String newsName;
    String newsText;
    String newsCreator;
    String newsDate; //news format is dd/mm/yyyy
    public News(int newsID,String newsName, String newsText, String newsCreator, String newsDate){
        
        this.newsID = newsID;
        this.newsName = newsName;
        this.newsText = newsText;
        this.newsCreator = newsCreator;
        this.newsDate = newsDate;
        
    }
    public News(){ 
    }
    public int getNewsID(){
        return newsID;
    }
    public void setNewsID(int newsID){
        this.newsID = newsID;
    }
    public String getNewsName(){
        return newsName;
    }
    public void setNewsName(String newsName){
        this.newsName = newsName;
    }
    public String getNewsText(){
        return newsText;
    }
    public void setNewsText(String nesText){
        this.newsText = newsText;
    }
    public String getNewsCreator(){
        return newsCreator;
    }
    public void setNewsCreator(String newsCreator){
        this.newsCreator = newsCreator;
    }
    public String getNewsDate(){
        return newsDate;
    }
    public void setNewsDate(String newsDate){
        this.newsDate = newsDate;
    }
    public boolean newsValidNull(String newsName, String newsText, String newsCreator, String newsDate){
        this.newsName = newsName;
        this.newsText = newsText;
        this.newsCreator = newsCreator;
        this.newsDate = newsDate;
        Integer s;
        boolean finish=false;
        s = 0;
        if ("".equals(newsName)){
            s = 1;
        }
        if ("".equals(newsText)){
            s = 1;
        }
        if ("".equals(newsCreator)){
            s = 1;
        }
        if ("".equals(newsDate)){
            s = 1;
        }
        if("".equals(newsName)&&"".equals(newsText)&&"".equals(newsCreator)&&"".equals(newsDate)){
            s = 1;
        }
        if(s!=0){
            finish = true;
        }
        return finish;
    }
    public static void addNews(News bit) throws SQLException{
        boolean res=false;
        int index;
        String newsName = new String();
        String newsText = new String();
        String newsCreator = new String();
        String newsDate = new String();         
        
        String newLine = "INSERT INTO NEWS (newsid, newsname, newstext, newscreator, newsdate) VALUES (?,?,?,?,?);";
        Connection con = getDBConnection();
        Statement stmt = con.createStatement();
        
        newsName = bit.getNewsName();
        newsText = bit.getNewsText();
        newsCreator = bit.getNewsCreator();
        newsDate = bit.getNewsDate();
        
        ResultSet rsIndex = stmt.executeQuery("SELECT * FROM public.NEWS;");
        int indexSearch = 0;
        
            while(rsIndex.next()){
                indexSearch = rsIndex.getInt("newsid");
            }
            
        System.out.println("indexSearch:" + indexSearch);
        index = (int) ((Math.random()*(Integer.MAX_VALUE - indexSearch)) + indexSearch);
        
        PreparedStatement pstmt = con.prepareStatement(newLine);
        
        pstmt.setInt(1, index);
        pstmt.setString(2, newsName);
        pstmt.setString(3, newsText);
        pstmt.setString(4, newsCreator);
        pstmt.setString(5, newsDate);
        
        pstmt.executeUpdate();
    }
    
    public static void deleteNews(News news){
    
    int newsID = news.getNewsID();
    String deleteSQL = "DELETE FROM NEWS WHERE"+" newsid="+newsID+";";
    try {
	Connection dbConnection = getDBConnection();
	Statement statement = dbConnection.createStatement();
	ResultSet rs = statement.executeQuery(deleteSQL);

	while (rs.next()) {
		
	}
    } catch (SQLException e) {
	System.out.println(e.getMessage());
    }
    }
    
    public static void editNews (News b) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{
    
    int newsID = b.getNewsID();
    String newsName = b.getNewsName();
    String newsText = b.getNewsText();
    String newsCreator = b.getNewsCreator();
    String newsDate = b.getNewsDate();
    String updateSQL = "UPDATE NEWS SET newsname='"+newsName+"',newstext='"+newsText+"',newscreator='"
                                +newsCreator+"',newsdate='"+newsDate+"' WHERE"+" newsid="+newsID+";";
    try {
	Connection dbConnection = getDBConnection();
	Statement statement = dbConnection.createStatement();
	ResultSet rs = statement.executeQuery(updateSQL);

	while (rs.next()) {
		
	}
    } catch (SQLException e) {
	System.out.println(e.getMessage());
}
}
}
