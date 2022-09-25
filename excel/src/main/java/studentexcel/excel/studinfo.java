package studentexcel.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class studinfo {
	
static ArrayList<StudentDetails> studentList= new ArrayList<>();
	
	public static void main(String[]args) throws IOException {
		
		FileInputStream file = null;
		XSSFWorkbook workbook = null;
		
		try {
			
			String excelPath="C:\\Users\\Havin\\eclipse-workspace\\excel\\Students.xlsx";
			  
            // Reading file from local directory
            
            
            file=  new FileInputStream( new File(excelPath));
  
            // Create Workbook instance holding reference to.xlsx file
            workbook = new XSSFWorkbook(file);
  
            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            
            
            
            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            
            while (rowIterator.hasNext()) {
            	
            	
                Row row = rowIterator.next();
              
                // For each row, iterate through all the columns
                 
                Iterator<Cell> cellIterator = row.cellIterator();
                
                StudentDetails std = new StudentDetails();
                
                while (cellIterator.hasNext()) {
  
                    Cell nextcell = cellIterator.next();
                
                    int columnIndex = nextcell.getColumnIndex();
                    
                    switch (columnIndex) {
  
                    case 0:
                        System.out.print(
                        		nextcell.getStringCellValue()+ "\t");
                            
                        std.setName(nextcell.getStringCellValue());
                        break;
                        
                    case 1:
                        System.out.print(
                        		nextcell.getNumericCellValue()+ "\t");
                            
                        std.setAdmissionNo((int)nextcell.getNumericCellValue());
                        break;
                        
                    case 2:
                        System.out.print(
                        		nextcell.getNumericCellValue()+ "\t");
                       
                      std.setPhysics((int)nextcell.getNumericCellValue());
                      break;
                        
                    case 3:
                        System.out.print(
                        		nextcell.getNumericCellValue()+ "\t");
                            
                        std.setChemistry((int)nextcell.getNumericCellValue());
                        
                        break;
                        
                    case 4:
                        System.out.print(
                        		nextcell.getNumericCellValue()+ "\t");
                            
                        std.setMaths((int)nextcell.getNumericCellValue());
                        
                      break;
                      default:
                    	  break;
                   
                    }
                    
                }
                std.setResult(std.getPhysics(),std.getChemistry(),std.getMaths());
                studentList.add(std) ;
  
                System.out.println("");
            }
            
            
        
		}
		finally {
        	if(file != null) {
        		try {
        			file.close();
        		} catch (Exception e) {
        			  
        	           
                    e.printStackTrace();
                }
        	}
        	if (workbook != null) {
                workbook.close();
            }
        }
  
       
		name();
    }
	public static void name() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter Name :" );
		String name = in.nextLine();
        
        for (StudentDetails st : studentList) {       	
         if(st.getName().equals(name) ) {
        	 System.out.println("name:"  + name);
        	 printStudentDetails(st);
         }
        	 
        }
        
}
	private static void printStudentDetails(StudentDetails st) {
        System.out.println("Name =" +st.getName() );
        System.out.println("AdmissionNO =" + st.getAdmissionNo() );
        System.out.println("Percentage =" +st.getPercentage() );
        System.out.println("Physics " );
        System.out.println("PhysicsMark =" + st.getPhysics() );
        System.out.println("Grade =" +st.getPhysicsGrade() );
        System.out.println("Gradepoint =" +st.getPhysicsGradepoint() );
        System.out.println("Chemistry " );
        System.out.println("ChemistryMark =" + st.getChemistry() );
        System.out.println("Grade =" +st.getChemistryGrade() );
        System.out.println("Gradepoint =" +st.getChemistryGradepoint() );
        System.out.println("Maths " );
        System.out.println("MathsMark =" + st.getMaths() );
        System.out.println("Grade =" +st.getMathsGrade() );
        System.out.println("Gradepoint =" +st.getMathsGradepoint() );
        
        }
	

}
