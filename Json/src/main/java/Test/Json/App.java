package Test.Json;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	 //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        //Creating a json array
        JSONArray array = new JSONArray();
        ResultSet rs = RetrieveData();
        //Inserting ResutlSet data into the json object
        while(rs.next()) {
           JSONObject record = new JSONObject();
           //Inserting key-value pairs into the json object
           record.put("ID", rs.getInt("ID"));
           record.put("First_Name", rs.getString("First_Name"));
           record.put("Last_Name", rs.getString("Last_Name"));
           record.put("Date_Of_Birth", rs.getDate("Date_Of_Birth"));
           record.put("Place_Of_Birth", rs.getString("Place_Of_Birth"));
           record.put("Country", rs.getString("Country"));
           array.add(record);
        }
        for (Object object : array) {
			System.out.println(object);
		}
        jsonObject.put("Players_data", array);
        try {
           FileWriter file = new FileWriter("E:/output.json");
           file.write(jsonObject.toJSONString());
           file.close();
        } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }
        System.out.println("JSON file created......");
    }
    
    public static ResultSet RetrieveData() throws Exception {
        //Registering the Driver
    	Class.forName("com.mysql.jdbc.Driver");  
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost:3306/players";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        System.out.println("Connection established......");
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Retrieving the records
        ResultSet rs = stmt.executeQuery("Select * from MyPlayers");
        return rs;
     }
}
