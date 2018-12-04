import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchByDateTime {
	String Date;
	String Time;
	private boolean flag;
	Scanner Sc = new Scanner(System.in);
	public static void main(String[] args) 
	{
		
		try {
			SearchByDateTime SDT =  new SearchByDateTime();
			SDT.searchTime();
			} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
		 
	}

	

	private void searchTime() 
	{
		
		do {
		System.out.println("Please Enter Date:");
		
	    System.out.println("Format Date:YYYY-MM-DD");
	    String regex = "\\d{4}-\\d{2}-\\d{2}";
	    
	    	Date = Sc.nextLine();   	
	       	Pattern pattern = Pattern.compile(regex);
	    	Matcher matcher = pattern.matcher(Date);
	        if(matcher.matches()== false)
	        {
	        	System.out.print("\nPlease Enter Correcr Format : YYYY-MM-DD");
	        		flag = false;
	        }else
	        {
	        	flag = true;
	        }
	        }while(flag != true);
	    
	    System.out.println("\nPlease Select From Above Options");
	    System.out.println("1. From 8 to 12");
	    System.out.println("2. From 12 to 16");
	    System.out.println("3. From 17 to 22");
	    int Option = Sc.nextInt();
	    if(Option == 1)
	    {
	    	Time = "8-12";
	    }
	    else if(Option == 2)
	    {
	    	Time = "12-16";
	    }
	    else
	    {
	    	Time = "17-22";
	    }
   
	    Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_truck_schema","root","Abhi08338@");
			Statement Stmt = con.createStatement();
	        
	    	String sql = "SELECT truck_owner_tab.TruckName, food_truck_tab.Location1,food_truck_tab.Location2,food_truck_tab.Location3, \r\n" + 
	    			"food_truck_tab.FormDate, food_truck_tab.ToDate, food_truck_tab.TimeSlot1 , food_truck_tab.TimeSlot2 , \r\n" + 
	    			"food_truck_tab.TimeSlot3,food_truck_tab.Days \r\n" + 
	    			"FROM  food_truck_schema.food_truck_tab \r\n" + 
	    			"INNER JOIN food_truck_schema.truck_owner_tab \r\n" + 
	    			"ON food_truck_tab.TruckId = truck_owner_tab.TruckId\r\n" + 
	    			"where(((TimeSlot1 = '8-12')||(TimeSlot2 = '12-16')||(TimeSlot3 = '17-22'))\r\n" + 
	    			"AND(CAST('"+Date+"' AS DATETIME)BETWEEN(CAST(FormDate AS DATETIME) )AND(CAST(ToDate AS DATETIME))));";
	    	ResultSet  resultSet = Stmt.executeQuery(sql);
	    	System.out.println( "TruckName"  +"\t"+ "FormDate" + "\t"+ "ToDate"+ "\t"+"Location1"+"\t"+"Location2" +"\t"+"Location3" + "\t"+ "TimeSlot1"+"\t"+"TimeSlot2"+"\t"+"TimeSlot3");
	    	 while(resultSet.next()) 
		     { 
		        System.out.println(resultSet.getString("TruckName")+ "\t"+resultSet.getString("FormDate")+ "\t"+resultSet.getString("ToDate")+ "\t"+ resultSet.getString("Location1")+ "\t" +resultSet.getString("Location2")+ "\t"+ resultSet.getString("Location3")+ "\t"+(resultSet.getString("TimeSlot1")+ "\t"+resultSet.getString("TimeSlot2")+ "\t"+resultSet.getString("TimeSlot3")));
		     }
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}


}
