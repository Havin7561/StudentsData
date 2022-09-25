package studentexcel.JsonDb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.sql.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDb {
	
	public static void  main(String[] args) throws SQLException, IOException {
		FileInputStream file = null;
		XSSFWorkbook workbook = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
		
		//Database connection
		String password = System.getProperty("database.password");

	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata","root",password);
		String sql="insert into student  values (?,?,?,?,?)";	
		
		 stmt=con.prepareStatement(sql);
		
		
			String excelPath="C:\\Users\\Havin\\eclipse-workspace\\excel\\Students.xlsx";
			
			  
            // Reading file from local directory
             file = new FileInputStream( new File(excelPath));
  
            // Create Workbook instance holding reference to .xlsx file
            
             workbook = new XSSFWorkbook(file);
            
            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            
    		
            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            
            while (rowIterator.hasNext()) {
            	
                Row row = rowIterator.next();
                
                Iterator<Cell> cellIterator = row.cellIterator();
                String name= "" ;
            	int admissionno=0;
            	double physics=0;
            	double maths=0;
            	double chemistry=0;
                
                while (cellIterator.hasNext()) {
  
                    Cell nextcell = cellIterator.next();
                  
                    int columnIndex = nextcell.getColumnIndex();
                    
                    switch (columnIndex) {
  
                    case 0:
                     name = nextcell.getStringCellValue();
                       
                        break;
                        
                    case 1:
                    	 admissionno = (int) nextcell.getNumericCellValue();
                    	 
                        break;
                        
                    case 2:
                        physics = nextcell.getNumericCellValue();
                    	
                      break;
                        
                    case 3:
                    	 chemistry = nextcell.getNumericCellValue();
                        break;
                        
                    case 4:
                    	 maths = nextcell.getNumericCellValue();
                      break;
                      default:
                      break;
                      
                    }
        			
                }
                stmt.setString(1,name);
                stmt.setInt(2,admissionno);
                stmt.setDouble(3,physics);
            	stmt.setDouble(4,chemistry);                  
            	stmt.setDouble(5,maths);
                stmt.execute();
              
            }
		} catch (Exception e) {}
		
		finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
                if (workbook != null) {
                    try {
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (con != null) {
                    con.close();
                if(stmt!=null) {
                	try{
                    stmt.close();
                	}catch(Exception excpt){
                    
                }
                }
     
            }
 
        
		
            
    		System.out.println("Values Inserted Successfully");
	
}

	

}

}
