import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
//import com.mysql.cj.jdbc.result.ResultSetImpl;

public class searchByName {
	public String TruckName; 
	
	public void searchName() throws ClassNotFoundException, SQLException
	{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_truck_schema","root","123456789");
		Statement stmt = con.createStatement();
		Scanner Sc = new Scanner(System.in);
		System.out.println("Please enter your desired Food Truck Name to Search1");
		TruckName = Sc.nextLine();
	        
		try {
		stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("Select truck_owner_tab.TruckName, "+ 
        		"food_truck_tab.FromDate, food_truck_tab.ToDate, food_truck_tab.Days, food_truck_tab.TimeSlot1, "+ 
        		"food_truck_tab.Location1, food_truck_tab.TimeSlot2, food_truck_tab.Location2, "+ 
        		"food_truck_tab.TimeSlot3, food_truck_tab.Location3 "+ 
        		"From truck_owner_tab "+ 
        		"inner join food_truck_tab on truck_owner_tab.TruckId = food_truck_tab.TruckId "+ 
        		"where TruckName = '"+TruckName+"'");
		int nCol = result.getMetaData().getColumnCount();
		int count = 0;
		List<String[]> table = new ArrayList<>();
		while( result.next()) {
			int size = nCol+1;
		    String[] row = new String[size];
		    for( int iCol = 0; iCol <= nCol; iCol++ ){	
		    		if(iCol == 0) {
		    		row[iCol] = Integer.toString(++count);
		    		continue;
		    		}
		            Object obj = result.getObject( iCol );
		            row[iCol] = (obj == null) ?null:obj.toString();
		    }
		    table.add( row );
		}
		// print result
		for( String[] row: table ){
		    for( String s: row ){
		        System.out.print( s+" " );
		    }
		    System.out.println();
		}           
	        }
	            catch (SQLException e){
	                 System.out.println(e.getMessage());
	             }
	}
}
