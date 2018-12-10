package FoodTruckSystem.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.result.ResultSetImpl;

public class CheckAppointment {
	public String Input;
	boolean InputMismatch;
	boolean BulkOrder;
	boolean ReplyMismatch;
	boolean Mismatch;
	public String sql;
	public String FromDate;
	public java.util.Date FromDateD;
	public String ToDate;
	public String Reply;

	public String CustomerId = "1";
	public int BookId;
	public String TimeSlot;
	public int TruckId = 1;
	public String Location;
	public String DateValue;
	public String Date;
	public int BulkOrderId;
	public String NoOfPerson;
	public String Dish;
	
	public void Appointmentdata () throws ClassNotFoundException, SQLException {
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata","root","Shams*97");
			java.sql.Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM customer_appointment_tab where TruckId ='"+TruckId+"'");
			System.out.println("   CustomerId   BookId TimeSlot TruckId  Location  Date  BulkOrderId  NoOfPerson  Dish");
			while(((ResultSetImpl) rs).next()) {
			System.out.println((" \t" + ((ResultSetImpl) rs).getString(1))+"\t "+((ResultSetImpl) rs).getString(2)+"     "+((ResultSetImpl) rs).getString(3)+"  \t "+((ResultSetImpl) rs).getInt(4)+"      \t "+((ResultSetImpl) rs).getString(5)+"\t"+((ResultSetImpl) rs).getString(6)+"\t"+((ResultSetImpl) rs).getInt(7)+"\t"+((ResultSetImpl) rs).getInt(8)+"\t"+((ResultSetImpl) rs).getString(9));
			}	
			
}
}