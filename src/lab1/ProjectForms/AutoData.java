
package lab1.ProjectForms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
//selenium ide + chromeDriver
public class AutoData {
    private StringProperty personID;
    private StringProperty login;
    private StringProperty password;
    private StringProperty email;
    private StringProperty name;
    private StringProperty surname;
    public AutoData(String login,String password, String email, String name, String surname){
       // this.personID=new SimpleStringProperty (personID.toString());
        this.login=new SimpleStringProperty(login);
        this.password=new SimpleStringProperty(password);
        this.email=new SimpleStringProperty(email);
        this.name=new SimpleStringProperty(name);
        this.surname=new SimpleStringProperty(surname);
    }
    public String getId() {
        return personID.get();
    }
 
    public void setId(String personID) {
        this.personID.set(personID);
    }
    
    public String getLogin() {
        return login.get();
    }
 
    public void setLogin(String login) {
        this.login.set(login);
    }
    public StringProperty loginProperty() {
        return login;
    }
    public String getPassword() {
        return password.get();
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    public String getEmail() {
        return email.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    public String getName() {
        return name.get();
    }
    
    public void setSurname(String surname) {
        this.surname.set(surname);
    }
    public String getSurname() {
        return surname.get();
    }
    
    public static Connection getDBConnection(){
        Connection dbConnection = null;
        try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}
	try {
                String url = "jdbc:postgresql://localhost:5432/Restoran";
                String loginBD = "postgres";
                String passwordBD = "2121";
		dbConnection = DriverManager.getConnection(url, loginBD,passwordBD);
		return dbConnection;
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	return dbConnection;
    }
    
    public static void testSystems() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Restoran";
            String login = "postgres";
            String password = "2121";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM public.userinfo");
                while (rs.next()) {
                    String str = rs.getString("userid") + ":" + rs.getString("login") + " " + rs.getString("password");
                    System.out.println("Пользователь " + str);
                }
                rs.close();
                stmt.close();
            }
            finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    public static boolean search(String login, String password) throws SQLException{
        boolean res=false;
        Connection con = getDBConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.userinfo");
        while(rs.next()){
            if(rs.getString("login").equals(login) && rs.getString("password").equals(password)){
                res=true;
                break;
            } else {
                res=false;
            }
        }
      return res;
    }
    public static boolean searchForRegistration(String login) throws SQLException{
        boolean res=false;
        Connection con = getDBConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.userinfo");
        while(rs.next()){
            if(rs.getString("login").equals(login)){
                res=true;
            } else {
                res=false;
            }
        }
      return res;
    }
    
    public static void add(AutoData bit) throws SQLException{
        boolean res=false;
        int index;
        String login = new String();
        String password = new String();
        String email = new String();
        String name = new String();         
        String surname = new String();
        String newLine = "INSERT INTO userinfo (userid,login,password,email,name,surname) VALUES (?,?,?,?,?,?);";
        Connection con = getDBConnection();
        Statement stmt = con.createStatement();
        
        login = bit.getLogin();
        password = bit.getPassword();
        email = bit.getEmail();
        name = bit.getName();
        surname = bit.getSurname();   
        
        ResultSet rsIndex = stmt.executeQuery("SELECT * FROM public.userinfo");
        int indexSearch = 0;
        
            while(rsIndex.next()){
                indexSearch = rsIndex.getInt("userid");
            }
        System.out.println("indexSearch:" + indexSearch);
        index = (int) ((Math.random()*(Integer.MAX_VALUE - indexSearch)) + indexSearch);
        System.out.println("index:" + index);
        PreparedStatement pstmt = con.prepareStatement(newLine);
        System.out.println(login);
        pstmt.setInt(1, index);
        pstmt.setString(2, login);
        pstmt.setString(3, password);
        pstmt.setString(4, email);
        pstmt.setString(5, name);
        pstmt.setString(6, surname);
        pstmt.executeUpdate();
        
    }
    
    

            /*try {
            dbConnection = getDBConnection();
	statement = dbConnection.createStatement();

	// выбираем данные с БД
	ResultSet rs = statement.executeQuery(selectTableSQL);

	// И если что то было получено то цикл while сработает   
	while (rs.next()) {
		String userid = rs.getString("USER_ID");
		String username = rs.getString("USERNAME");

		System.out.println("userid : " + userid);
		System.out.println("username : " + username);
	}
} catch (SQLException e) {
	System.out.println(e.getMessage());
}*/

            //PreparedStatement pstmt1 = con.prepareStatement(del);
            //PreparedStatement pstmt = con.prepareStatement(updatesql);
 /*
            pstmt.setInt(1,id);
            pstmt.setString(2,firstname);
            pstmt.setString(3,lastname);
            pstmt.setString(4,secondname);
            pstmt.setString(5,email);
            pstmt.setString(6,phone);
            // update
*/
            //pstmt.executeUpdate();
            
        
    
} 
    

