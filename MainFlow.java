package controlFlow;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import BookAndCheckAppointment.BookAppointment;
import BookAndCheckAppointment.CheckAppointment;
import FeedBack.GetFeedback;
import FeedBack.GiveFeedback;
import Offers.AddOffer;
import Offers.ViewOffer;
import Registration.ApprovalOfFoodTruck;
import Registration.FoodTruckRegistration;
import Registration.LoginWithVerification;
import Registration.Registration;
import Search.searchByDateAndTime;
import Search.searchByLocation;
import Search.searchByMenu;
import Search.searchByName;
import Updates.UpdateTruckSchedule;
import menu.UpdateTruckMenu;
import menu.foodMenu;
import menu.foodMenuDisplay;

public class MainFlow {
	public static void main(String[] args) throws ClassNotFoundException {
		int Option = 0;
		int RegistrationSuccessFlag = 0;
		int SelectedTruck = 0;
		String FeedbackPermission;
		int returnFromSubMenu = 0;

		int ReturnFromApproval = 0;
		Scanner Sc = new Scanner(System.in);
		System.out.println("================================ ");
		System.out.println("WelCome To Food Truck System");
		System.out.println("================================ ");
		do {
			boolean CharacterCheckFlag = false;
			do {
				CharacterCheckFlag = false;
				System.out.println("***Select From Following Option***");
				System.out.println("1. User Registeration");
				System.out.println("2. Login");
				System.out.println("0. Exit");
				try {
					CharacterCheckFlag = false;
					Option = Sc.nextInt();
					
				} catch (InputMismatchException Ex) {

					System.out.println("Character's Not Allowed");
					CharacterCheckFlag = true;
				}
			} while (CharacterCheckFlag);
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
							System.out.println("Welcome Admin: " + LoginPage.FirstNameFromDB);
							CharacterCheckFlag = false;
							do {
								System.out.println("Press 1 To Start Approvals");
								System.out.println("Press 0 To Exit");
								String AdminChoise = Sc.next();
								if ((LoginPage.LoginType.equalsIgnoreCase("A")) && (AdminChoise.equals("1"))) {
									ApprovalOfFoodTruck Approve = new ApprovalOfFoodTruck();
									ReturnFromApproval = Approve.Approval();
								} else if (AdminChoise.equals("0")) {
									System.out.println("Visit Again...");
									System.exit(0);
								} else {
									System.out.println("Other Character not Allowed...");
									CharacterCheckFlag = true;
								}
							} while (ReturnFromApproval == 1);
						} while (CharacterCheckFlag);
					} else if (LoginPage.LoginType.equalsIgnoreCase("O")) {
						System.out.println("Welcome " + LoginPage.FirstNameFromDB);
						do {
							System.out.println("Select From Following Option");
							System.out.println("1. Food Truck Registration");
							System.out.println("2. Enter Food Menu ");
							System.out.println("3. Update Food Menu ");
							System.out.println("4. View the Appointment  ");
							System.out.println("5. Add Food Offers  ");
							System.out.println("6. View the FeedBack ");
							System.out.println("7. Update Schedule ");
							System.out.println("0. Exit");

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
							} else if (TruckOwnerOption.equals("3")) {
								UpdateTruckMenu Updatemenu = new UpdateTruckMenu();
								try {

									ReturnFromApproval = Updatemenu.UpdateFoodMenu();
								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}
							} else if (TruckOwnerOption.equals("4")) {
								CheckAppointment ViewAppt = new CheckAppointment();
								try {

									ReturnFromApproval = ViewAppt.ViewAppointmentdata();
								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}
							}

							else if (TruckOwnerOption.equals("5")) {
								AddOffer AddFoodOffers = new AddOffer();
								try {
									ReturnFromApproval = AddFoodOffers.foodOffer();

								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}

							} else if (TruckOwnerOption.equals("6")) {
								GetFeedback ShowFeedBack = new GetFeedback();
								try {

									ReturnFromApproval = ShowFeedBack.FeedbackData();
								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}

							} else if (TruckOwnerOption.equals("7")) {
								UpdateTruckSchedule UpdateSchedule = new UpdateTruckSchedule();
								try {

									ReturnFromApproval = UpdateSchedule.UpdateSchedule();
								} catch (ClassNotFoundException e) {

									e.printStackTrace();
								}

							} else if (TruckOwnerOption.equals("0")) {
								System.out.println("Visit Again...");
								System.exit(0);
							} else {
								System.out.println("Other Character not Allowed...\n Select From The Displayed Menu");
								CharacterCheckFlag = true;
							}
						} while (ReturnFromApproval == 1 || CharacterCheckFlag == true);

					} else {
						do {
							System.out.println("Welcome " + LoginPage.FirstNameFromDB);
							System.out.println("Select From Following Option");
							System.out.println("1.Search By Name ");
							System.out.println("2.Search By Location ");
							System.out.println("3.Search By Date And Time ");
							System.out.println("4.Search By Menu ");
							System.out.println("0.Exit ");
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

							} else if (CustomerChoise.equals("5")) {
								searchByMenu MenuSearch = new searchByMenu();
								try {
									foodMenuDisplay FoodDisplayMenu = new foodMenuDisplay();
									FoodDisplayMenu.DisplayMenu(SelectedTruck);
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (SelectedTruck > 0) {
								do {

									System.out.println("Select From Following ");
									System.out.println("1.View Menu ");
									System.out.println("2.Book Appointment ");
									System.out.println("3.View Offer ");
									System.out.println("4.Give Feedback ");
									System.out.println("0.Exit ");
									String SelectedSubMenu = Sc.next();
									if (SelectedSubMenu.equals("1")) {
										foodMenuDisplay ViewMenu = new foodMenuDisplay();
										try {
											// FeedbackPermission = Sc.next().toUpperCase();
											returnFromSubMenu = ViewMenu.DisplayMenu(SelectedTruck);
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else if (SelectedSubMenu.equals("2")) {
										BookAppointment BookAppt = new BookAppointment();
										try {
											// FeedbackPermission = Sc.next().toUpperCase();
											BookAppt.BookAppointmentInput(SelectedTruck, LoginPage.NameFromDB);
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else if (SelectedSubMenu.equals("3")) {
										ViewOffer FoodOffers = new ViewOffer();
										try {
											ReturnFromApproval = FoodOffers.foodOfferDisplay(SelectedTruck);
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else if (SelectedSubMenu.equals("4")) {
										GiveFeedback FeedbackFromCustomer = new GiveFeedback();
										try {
											ReturnFromApproval = FeedbackFromCustomer.CustomerFeedback(SelectedTruck);
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else if (SelectedSubMenu.equals("0")) {
										System.out.println("Visit Again...");
										System.exit(0);

									}
								} while (returnFromSubMenu != 0);
							} else if (CustomerChoise.equals("0")) {
								System.out.println("Visit Again...");
								System.exit(0);
							} else {
								System.out.println("Other Character not Allowed...");
								CharacterCheckFlag = true;
							}

						} while (ReturnFromApproval == 1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}// endswitch
		} while (RegistrationSuccessFlag == 1);// Strat with Registration
	}
}
