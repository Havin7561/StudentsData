package studentexcel.JsonDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReadDb {
private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[]args) throws SQLException {
		
	
		System.out.println("1.Search for Student by Name");
		System.out.println("2.Search for Student by Admission No");
		System.out.println("Enter 1 or 2:");
		int choice = Integer.parseInt(sc.nextLine());
		
		switch(choice) {
		
		case 1:
			System.out.println("By Name");
			name();
			break;
		case 2:
			System.out.println("By AdmissionNo");
			admissionNo();
			break;
			default:
				break;
		}
	}
	private static Connection connection() throws SQLException {
		String password = System.getProperty("database.password");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata","root",password);
		
	}
	
	
	private static void name() throws SQLException {
		System.out.println("Enter Name:");
		String c = sc.nextLine();
		String sql = "select * from student where name='"+c+"' " ;  
	    selectRecord(sql);
	}
	private static void admissionNo() throws SQLException {
		System.out.println("Enter AdmissionNo:");
		int d = sc.nextInt();
		String sql = "select * from student where admissionno= "+d;
		selectRecord(sql);
	}
	public static void selectRecord(String query)throws SQLException {
		
		
		JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
		Connection con=connection();
		Statement stmt = con.createStatement();
		System.out.println(query);
		ResultSet rs = stmt.executeQuery(query);
		
		
		while(rs.next()){
			JSONObject details = new JSONObject();
			details.put("name", rs.getString("name"));	
			details.put("admissionno", rs.getInt("admissionno"));    
			details.put("physics",  rs.getDouble("physics"));
			details.put("chemistry",  rs.getDouble("chemistry"));
			details.put("maths",  rs.getDouble("maths"));
			array.add(details);
		
		}
		jsonObject.put("stdata", array);
		System.out.println(jsonObject.toJSONString());
		
		}
	

}
