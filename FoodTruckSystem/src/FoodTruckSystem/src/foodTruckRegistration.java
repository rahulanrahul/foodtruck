import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//import com.mysql.cj.jdbc.result.ResultSetImpl;

public class foodTruckRegistration {
	public String FromDate;
	public String ToDate;
	public int DaysOption;
	public String Days;
	public int TimeOption;
	public String TimeSlot;
	public String TimeSlot1;
	public String TimeSlot2;
	public String TimeSlot3;
	public String Location;
	public String Location1;
	public String Location2;
	public String Location3;
	public int KeyEntry;
	
	public void truckRegistration() throws ClassNotFoundException, SQLException
	{	
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_truck_schema","root","123456789");
		Statement stmt = con.createStatement();
		PreparedStatement preparedStmt;
		Scanner Sc = new Scanner(System.in);
		do
		{
		System.out.println("Please enter From Date:(YYYY-MM-DD)");
		FromDate = Sc.next();
		System.out.println("Please enter To Date:(YYYY-MM-DD)");
		ToDate = Sc.next();
		System.out.println("Please enter your Days of operation");
		System.out.println("Please enter 1 for Sunday");
		System.out.println("Please enter 2 for Monday");
		System.out.println("Please enter 3 for Tuesday");
		System.out.println("Please enter 4 for Wednesday");
		System.out.println("Please enter 5 for Thursday");
		System.out.println("Please enter 6 for Friday");
		System.out.println("Please enter 7 for Saturday");
		DaysOption = Sc.nextInt();
		switch (DaysOption){
		case 1: 
			Days = "Sunday";
			break;
		case 2:
			Days = "Monday";
			break;
		case 3:
			Days = "Tuesday";
			break;
		case 4:
			Days = "Wednesday";
			break;
		case 5:
			Days = "Thursday";
			break;
		case 6:
			Days = "Friday";
			break;
		case 7:
			Days = "Saturday";	
		}
		do
		{
			System.out.println("Please enter your desired timeslot:");
			System.out.println("Please enter 1 for slot 06:00 to 10:00");
			System.out.println("Please enter 2 for slot 12:00 to 16:00");
			System.out.println("Please enter 3 for slot 18:00 to 22:00");	
			TimeOption = Sc.nextInt();
			System.out.println("Please enter your desired location for this timeslot:");
			Location = Sc.next();
			switch (TimeOption){
			case 1: 
				TimeSlot = "06:00 to 10:00";
				break;
			case 2:
				TimeSlot = "12:00 to 16:00";
				break;
			case 3:
				TimeSlot = "18:00 to 22:00";
				break;
			}
			if(TimeSlot == TimeSlot1 || TimeSlot == TimeSlot2)
			{
				System.out.println("Time Slot already blocked");
				continue;
			}
			if(TimeSlot1 == null)
			{
				TimeSlot1 = TimeSlot;
				TimeSlot = "";
				Location1 = Location;
				Location = "";
			}
			else if(TimeSlot2 == null)
			{
				TimeSlot2 = TimeSlot;
				TimeSlot = "";
			    Location2 = Location;
			    Location = "";
			}
			else
			{
				TimeSlot3 = TimeSlot;
				TimeSlot = "";
				Location3 = Location;
				Location = "";
				break;
			}
			System.out.println("Please enter 1 for more slots. "
					+ "Press any other number to continue");
			KeyEntry = Sc.nextInt();
		}while(KeyEntry == 1);
		String Query = 
		"INSERT INTO food_truck_tab "
		+ "(`FromDate`, `ToDate`, `Days`, `TimeSlot1`, `TimeSlot2`, `TimeSlot3`, "
		+ "`Location1`, `Location2`, `Location3`) "
		+ "VALUES (?,?,?,?,?,?,?,?,?)";
		preparedStmt = con.prepareStatement(Query);
		preparedStmt.setString (1,FromDate );
		preparedStmt.setString (2,ToDate );
		preparedStmt.setString (3,Days );
		preparedStmt.setString (4,TimeSlot1 );
		preparedStmt.setString (5,TimeSlot2 );
		preparedStmt.setString (6,TimeSlot3 );
		preparedStmt.setString (7,Location1 );
		preparedStmt.setString (8,Location2 );
		preparedStmt.setString (9,Location3 );
		try {
		preparedStmt.execute();
		}catch(SQLException ex){
			System.out.println("Invalid date format. Please press 1 to re-enter data. "
					+ "Press any other key to exit");
			KeyEntry = Sc.nextInt();
			if(KeyEntry != 1)
			   System.exit(0);
		}
		System.out.println("Please press 1 to add another entry. "
				+ "Press any other key to exit");
		KeyEntry = Sc.nextInt();
		TimeSlot1 = "";
		TimeSlot2 = "";
		TimeSlot3 = "";
		}while(KeyEntry == 1);
	    //con.close();
	}
}