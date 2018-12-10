package controlFlow;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import BookAndCheckAppointment.BookAppointment;
import FeedBack.GetFeedback;
import FeedBack.GiveFeedback;
import Registration.ApprovalOfFoodTruck;
import Registration.FoodTruckRegistration;
import Registration.LoginWithVerification;
import Registration.Registration;
import Search.searchByDateAndTime;
import Search.searchByLocation;
import Search.searchByMenu;
import Search.searchByName;
import menu.UpdateTruckMenu;
import menu.foodMenu;
import menu.foodMenuDisplay;

public class MainFlow {
	public static void main(String[] args) throws ClassNotFoundException {
		int Option = 0;
		int RegistrationSuccessFlag = 0;
		int SelectedTruck = 0;
		String FeedbackPermission;
		int returnFromSubMenu=0;

		int ReturnFromApproval = 0;
		Scanner Sc = new Scanner(System.in);
		System.out.println("\t================================ ");
		System.out.println("  \t WelCome To Food Truck System");
		System.out.println("\t================================ ");
		do {
			int CharacterCheckFlag;
			do {
				CharacterCheckFlag = 0;
				System.out.println("\n\t***Select From Following Option***");
				System.out.println("\t1. User Registeration");
				System.out.println("\t2. Login");
				System.out.println("\t0. Exit");
				try {
					Option = Sc.nextInt();
					CharacterCheckFlag = 0;
				} catch (InputMismatchException Ex) {

					System.out.println("Character's Not Allowed");
					CharacterCheckFlag = 1;
				}
			} while (CharacterCheckFlag == 1);
			switch (Option) {
			case 1:
				Registration Register = new Registration();
				try {
					RegistrationSuccessFlag = Register.UserRegistration();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException localSQLException1) {
				}
			case 2:
				LoginWithVerification LoginPage = new LoginWithVerification();
				try {
					LoginPage.Login();
					if (LoginPage.LoginType.equalsIgnoreCase("A")) {
						do {
							System.out.println("\n\tWelcome Admin: " + LoginPage.FirstNameFromDB);
							CharacterCheckFlag = 0;
							do {
								System.out.println("\n\tPress 1 To Start Approvals");
								System.out.println("\n\tPress 0 To Exit");
								String AdminChoise = Sc.next();
								if ((LoginPage.LoginType.equalsIgnoreCase("A")) && (AdminChoise.equals("1"))) {
									ApprovalOfFoodTruck Approve = new ApprovalOfFoodTruck();
									ReturnFromApproval = Approve.Approval();
								} else if (AdminChoise.equals("0")) {
									System.out.println("Visit Again...");
									System.exit(0);
								} else {
									System.out.println("\n\tOther Character not Allowed...");
									CharacterCheckFlag = 1;
								}
							} while (ReturnFromApproval == 1);
						} while (CharacterCheckFlag == 1);
					} else if (LoginPage.LoginType.equalsIgnoreCase("O")) {
						System.out.println("Welcome " + LoginPage.FirstNameFromDB);
						do {
							System.out.println("\n\tSelect From Following Option");
							System.out.println("\n\t1. Food Truck Registration");
							System.out.println("\n\t2. Enter Food Menu ");
							System.out.println("\n\t3. Update Food Menu ");
							System.out.println("\n\t4. See the FeedBack ");
							System.out.println("\n\t0. Exit");

							String TruckOwnerOption = Sc.next();
							if (TruckOwnerOption.equals("1")) {
								FoodTruckRegistration FoodtruckRegistration = new FoodTruckRegistration();
								try {

									FoodtruckRegistration.truckRegistration(LoginPage.NameFromDB);
								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}
							}
							if (TruckOwnerOption.equals("2")) {
								foodMenu EnterFoodMenu = new foodMenu();

								try {

									ReturnFromApproval = EnterFoodMenu.insertfoodMenu();

								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}
							}else if (TruckOwnerOption.equals("3")) {
								UpdateTruckMenu Updatemenu = new UpdateTruckMenu();
								try {

									ReturnFromApproval=Updatemenu.UpdateFoodMenu();
								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}
							}

							else if (TruckOwnerOption.equals("4")) {
								GetFeedback ShowFeedBack = new GetFeedback();
								try {

									ReturnFromApproval = ShowFeedBack.FeedbackData();
								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}
							} else if (TruckOwnerOption.equals("0")) {
								System.out.println("\n\t Visit Again...");
								System.exit(0);
							} else {
								System.out.println("\n\tOther Character not Allowed...\n Select From The Displayed Menu");
								CharacterCheckFlag = 1;
							}
						} while (ReturnFromApproval == 1|| CharacterCheckFlag == 1);

					} else {
						do {
							System.out.println("\n\tWelcome " + LoginPage.FirstNameFromDB);
							System.out.println("\n\tSelect From Following Option");
							System.out.println("\n\t1.Search By Name ");
							System.out.println("\n\t2.Search By Location ");
							System.out.println("\n\t3.Search By Date And Time ");
							System.out.println("\n\t4.Search By Menu ");
							System.out.println("\n\t0.Exit ");
							String CustomerChoise = Sc.next();
							if (CustomerChoise.equals("1")) {
								searchByName SearchFootTruckByName = new searchByName();
								try {

									SelectedTruck = SearchFootTruckByName.searchName();
								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}
							} else if (CustomerChoise.equals("2")) {
								searchByLocation LocationSearch = new searchByLocation();
								try {
									SelectedTruck = LocationSearch.searchLocation();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} else if (CustomerChoise.equals("3")) {
								searchByDateAndTime DateTimeSearch = new searchByDateAndTime();
								try {
									SelectedTruck = DateTimeSearch.searchTime();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} else if (CustomerChoise.equals("4")) {
								searchByMenu MenuSearch = new searchByMenu();
								try {
									SelectedTruck = MenuSearch.searchMenu();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}   else if (CustomerChoise.equals("5")) {
								searchByMenu MenuSearch = new searchByMenu();
								try {
									foodMenuDisplay FoodDisplayMenu = new foodMenuDisplay();
									FoodDisplayMenu.DisplayMenu(SelectedTruck);
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}}
								if(SelectedTruck>0) {
									do {
									
									System.out.println("\n\tSelect From Following ");
									System.out.println("\n\t1.View Menu ");
									System.out.println("\n\t2.Book Appointment ");
									System.out.println("\n\t3.Give Feedback ");
									System.out.println("\n\t0.Exit ");
									String SelectedSubMenu = Sc.next();
								if (SelectedSubMenu.equals("1")) {
									foodMenuDisplay ViewMenu = new foodMenuDisplay();
									try {
										//FeedbackPermission = Sc.next().toUpperCase();
										returnFromSubMenu = ViewMenu.DisplayMenu(SelectedTruck);
										} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
								else if (SelectedSubMenu.equals("2")) {
									BookAppointment BookAppt = new BookAppointment();
									try {
										//FeedbackPermission = Sc.next().toUpperCase();
										BookAppt.BookAppointmentInput(SelectedTruck,LoginPage.NameFromDB);
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}else if (SelectedSubMenu.equals("3")) {
									GiveFeedback FeedbackFromCustomer = new GiveFeedback();
									try {
										ReturnFromApproval = FeedbackFromCustomer.CustomerFeedback(SelectedTruck);
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}else if (SelectedSubMenu.equals("0")) {
									System.out.println("\n\t Visit Again...");
									System.exit(0);

								}
									}while(returnFromSubMenu!=0);
							} 
						else if (CustomerChoise.equals("0")) {
								System.out.println("\n\t Visit Again...");
								System.exit(0);
							}
							else {
								System.out.println("\n\tOther Character not Allowed...");
								CharacterCheckFlag = 1;
							}					
													
							} while (ReturnFromApproval == 1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}//endswitch
		} while (RegistrationSuccessFlag == 1);//Strat with Registration
	}
}
