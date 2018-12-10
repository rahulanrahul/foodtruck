package updateoffer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateOffer {

	
	
	public static void main(String[] args){
		 int truckId;
	     String foodType;
	     String day;
	     String timeSlot;
		 String offer;
		 String from;
		 String to;
		
		String choice ;
		try{
				Scanner scanner = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_truck_schema","root","desertstrom47007");
				PreparedStatement preparedStmt;
				Statement stmt = con.createStatement();
				System.out.println("Please provide TruckId :" );
				truckId = scanner.nextInt();
				String q = "Select * from truck_offer_tab where TruckId ='"+ truckId+"'";
				preparedStmt = con.prepareStatement(q);	
				ResultSet rs = preparedStmt.executeQuery(q);
				while(rs.next()){
					System.out.println("Truck ID: " +truckId+ "\n"+
										"Food Type: " +rs.getString("foodtype") + "\n"+
											"Day: " +rs.getString("day") + "\n"+
											"Timeslot: " +rs.getString("timeslot") + "\n"+
											"Offer: " +rs.getString("offer") + "\n"+
											"From Date: " +rs.getString("fromdate") + "\n"+
											"To Date: " +rs.getString("todate") );
}
				boolean isloopneeded;
				do{
					System.out.println("MENU" + "\n" +
														"1. Delete" + "\n" +
														"2. Change Food Type" + "\n" +
														"3. Change Day" + "\n" +
														"4. Change Timeslot" + "\n" +
														"5. Change Offer" + "\n" +
														"6. Change FromDate" + "\n" +
														"7. Change ToDate" + "\n" +
														"0. Exit");
				choice = scanner.next();
			
				isloopneeded = false;
				switch(choice){
					case "1": {
						
						preparedStmt = con.prepareStatement("Delete * from truck_offer_tab where TruckId ='"+ truckId+"'");	
						preparedStmt.executeUpdate();					
						break;
					}
					case "2": {
					
						System.out.println("Please provide Food Type: " );
						foodType = scanner.next();
						preparedStmt = con.prepareStatement("update truck_offer_tab set foodtype ='" + foodType + "' where TruckId ='"+truckId+"'");	
						preparedStmt.executeUpdate();					
						break;
					}
					case "3": {
					
						System.out.println("Please provide Day: " );
						day = scanner.next();
						preparedStmt = con.prepareStatement("update truck_offer_tab set day ='" + day + "' where TruckId ='"+truckId+"'");	
						preparedStmt.executeUpdate();					
						break;
					}
					case "4": {
						
						System.out.println("Please provide Timeslot : " );
						timeSlot = scanner.next();
						preparedStmt = con.prepareStatement("update truck_offer_tab set timeslot ='" + timeSlot + "' where TruckId ='"+truckId+"'");	
						preparedStmt.executeUpdate();					
						break;
					}
					case "5": {
						
						System.out.println("Please provide offer : " );
						offer = scanner.next();
						preparedStmt = con.prepareStatement("update truck_offer_tab set offer ='" + offer + "' where TruckId ='"+truckId+"'");	
						preparedStmt.executeUpdate();					
						break;
					}
					case "6": {
						
						System.out.println("Please provide From Date: " );
						from = scanner.next();
						preparedStmt = con.prepareStatement("update truck_offer_tab set fromdate ='" + from + "' where TruckId ='"+truckId+"'");	
						preparedStmt.executeUpdate();					
						break;
					}
					case "7": {
					
						System.out.println("Please provide To Day: " );
						to = scanner.next();
						preparedStmt = con.prepareStatement("update truck_offer_tab set todate ='" + to + "' where TruckId ='"+truckId+"'");	
						preparedStmt.executeUpdate();					
						break;
					}
					case "0":{
						System.out.println("Bye");
						break;
					}
					default:{
						isloopneeded = true;
						System.out.println("Wrong choice");
						break;
					}					
				}
				} while(isloopneeded);
		}
		catch (Exception e){
      		System.err.println("Got an exception! \n" + e);
		}
	}
}

