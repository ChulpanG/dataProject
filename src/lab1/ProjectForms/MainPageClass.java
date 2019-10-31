
package lab1.ProjectForms;

import lab1.ProjectForms.AutoData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainPageClass {
    
    public static List viewNewsList() {
        
        List<News> listNews = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM public.NEWS";
            
            Connection con = AutoData.getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            
            
            while (resultSet.next()) {

                int newsID = resultSet.getInt("newsid");
                String newsName = resultSet.getString("newsname");
                String newsText = resultSet.getString("newstext");
                String newsCreator = resultSet.getString("newscreator");
                String newsDate = resultSet.getString("newsdate");
                
                News news = new News(newsID,newsName,newsText,newsCreator,newsDate);
                listNews.add(news);
    
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainPageClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listNews;
    }
    
}
