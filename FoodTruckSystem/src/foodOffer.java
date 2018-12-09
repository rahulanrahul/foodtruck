import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class foodOffer {

	int TruckId;
	String FoodType;
	String Day;
	String TimeSlot;
	String Offer;
	String FromDate;
	String ToDate;
	String choice;
	String FoodTypeChoice, DayChoice;

	public void foodOffer() throws ClassNotFoundException, SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_truck_schema?useSSL=false",
				"root", "lenovopc.3");
		Statement stmt = con.createStatement();
		boolean Flag, IsNotNumeric, Flag2, Flag3;
		PreparedStatement preparedStmt;
		Scanner Sc = new Scanner(System.in);
		System.out.println("Enter the Truck Id");
		TruckId = Sc.nextInt();
		do {
			System.out.println("Enter the Food Type");
			System.out.println("Press 1 for German ");
			System.out.println("Press 2 for Indian");
			System.out.println("Press 3 for Chineese");
			System.out.println("Press 4 for Continental");
			FoodTypeChoice = Sc.next();
			Flag2 = false;
			switch (FoodTypeChoice) {
			case "1":
				FoodType = "German";
				break;
			case "2":
				FoodType = "Indian";
				break;
			case "3":
				FoodType = "Chineese";
				break;
			case "4":
				FoodType = "Continental";
				break;
			default:
				System.out.println("Wrong Entry");
				System.out.println("Please give a correct input...!!");
				Flag2 = true;
				break;
			}
		} while (Flag2);

		do {
			System.out.println("Enter the Day");
			System.out.println("Press 1 for Monday ");
			System.out.println("Press 2 for Tuesday");
			System.out.println("Press 3 for Wednesday");
			System.out.println("Press 4 for Thursday");
			System.out.println("Press 5 for Friday");
			System.out.println("Press 6 for Saturday");
			System.out.println("Press 7 for Sunday");
			DayChoice = Sc.next();
			Flag3 = false;
			switch (DayChoice) {
			case "1":
				Day = "Monday";
				break;
			case "2":
				Day = "Tuesday";
				break;
			case "3":
				Day = "Wednesday";
				break;
			case "4":
				Day = "Thursday";
				break;
			case "5":
				Day = "Friday";
				break;
			case "6":
				Day = "Saturday";
				break;
			case "7":
				Day = "Sunday";
				break;
			default:
				System.out.println("Wrong Entry");
				System.out.println("Please give a correct input...!!");
				Flag3 = true;
				break;
			}
		} while (Flag3);
		do {
			Flag = false;
			System.out.println("Choose the time slot..");
			System.out.println("Press 1 for 6-10");
			System.out.println("Press 2 for 12-15");
			System.out.println("Press 3 for 18-22");
			choice = Sc.next();
			switch (choice) {
			case "1":
				TimeSlot = "6-10";
				break;
			case "2":
				TimeSlot = "12-15";
				break;
			case "3":
				TimeSlot = "18-22";
				break;
			default: {
				System.out.println("Wrong Entry..");
				System.out.println("Please give a correct input...!!");
				Flag = true;
				break;
			}
			}
		} while (Flag);

		do {
			Flag = false;
			try {
				System.out.println("Enter the offer for the dish");
				Offer = Sc.next();
				float Pr = Float.parseFloat(Offer);
			} catch (NumberFormatException ex) {
				System.out.println("Wrong Entry...");
				System.out.println("Please enter again..");
				Flag = true;
			}
		} while (Flag);
		boolean IsNotValid = false;
		do {
			System.out.println("Please enter From Date:(YYYY-MM-DD)");
			FromDate = Sc.next();
			IsNotValid = false;
			if (FromDate.contains(" ")) {
				IsNotValid = true;
				System.out.println("No spaces allowed. Please try again!!!");
				continue;
			}
			DateFormat FromDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			FromDateFormat.setLenient(false);
			try {
				FromDateFormat.parse(FromDate);
			} catch (ParseException ex) {
				System.out.println("Date " + FromDate + " is not valid");
				IsNotValid = true;
			}
		} while (IsNotValid);
		do {
			System.out.println("Please enter To Date:(YYYY-MM-DD)");
			ToDate = Sc.next();
			IsNotValid = false;
			if (ToDate.contains(" ")) {
				IsNotValid = true;
				System.out.println("No spaces allowed. Please try again!!!");
				continue;
			}
			try {
				SimpleDateFormat ToDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				ToDateFormat.setLenient(false);
				ToDateFormat.parse(FromDate);
				Date FromDateParse = ToDateFormat.parse(FromDate);
				Date ToDateParse = ToDateFormat.parse(ToDate);
				if (FromDateParse.after(ToDateParse)) {
					System.out.println("The ToDate must be greater than FromDate");
					IsNotValid = true;
				}
			} catch (ParseException ex) {
				System.out.println("Date " + ToDate + " is not valid");
				IsNotValid = true;
			}
		} while (IsNotValid);


		String Query = "INSERT INTO truck_offer_tab (`TruckId`,`FoodType`,`Day`,`TimeSlot`,`Offer`,`FromDate`,`ToDate`) VALUES (?,?,?,?,?,?,?)";
		preparedStmt = con.prepareStatement(Query);
		preparedStmt.setInt(1, TruckId);
		preparedStmt.setString(2, FoodType);
		preparedStmt.setString(3, Day);
		preparedStmt.setString(4, TimeSlot);
		preparedStmt.setString(5, Offer);
		preparedStmt.setString(6, FromDate);
		preparedStmt.setString(7, ToDate);
		preparedStmt.execute();
		con.close();

	}

}
