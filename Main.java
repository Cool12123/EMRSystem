
import javafx.application.Application;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.DatePicker;


//Name: Nosakhare Odaro     ASU ID: 1222992667
//Description: GUI for food and drink ordering system

public class Project3 extends Application {
	int patientIDForPatientView = 0;
  int doctorOrNurseIDView = 0;
	String doctorOrNurse = "";
	GridPane gridPane2 = new GridPane();
	BorderPane mainPane = new BorderPane();                      //Nurse view pane
	Patient[] patientsIDList = new Patient[10];                  //Patients ID List (use Patients class)
	Doctor[] doctorsIDList = new Doctor[10];                     //Doctors ID List (use Patients class)
	Nurse[] nursesIDList = new Nurse[10];                        //Nurses ID List (use Patients class)
	
	int[] iDList1  = {11111, 10001, 20002, 30003, 40004, 50005, 60006, 70007, 80008, 90009}; //Assumption that Patient ID is known (according to the professor)
	int[] iDList2  = {111111, 110001, 120002, 130003, 140004, 150005, 160006, 170007, 180008, 190009}; //Assumption that Doctor ID is known (according to the professor)
	int[] iDList3  = {211111, 210001, 220002, 230003, 240004, 250005, 260006, 270007, 280008, 90009}; //Assumption that Nurse ID is known (according to the professor)
	
	String[] passwordList1  = {"", "", "", "", "", "", "", "", "", "", }; //Assumption that Patient password is unknown (according to the professor)
	String[] passwordList2  = {"abc11", "abc22", "abc33", "abc44", "abc55", "abc66", "abc77", "abc88", "abc99", "abc10", }; //Assumption that Doctor password is known (according to the professor)
	String[] passwordList3  = {"abc111", "abc221", "abc331", "abc441", "abc551", "abc661", "abc771", "abc881", "abc991", "abc101", }; //Assumption that Nurse Password is known (according to the professor)
	
	ReportErrorMessage messageReceptionistView = new ReportErrorMessage("Data items are missing");
	ReportErrorMessage testingLimitMessage = new ReportErrorMessage("Testing limit of 10 patients");
	ReportErrorMessage errorMessageTechView = new ReportErrorMessage("Error: Data items are missing");
    ReportErrorMessage patientViewNoDataMessage = new ReportErrorMessage("No data is available yet");
    PatientIDReportMessage errorMessagePatientID = new PatientIDReportMessage("Error:  Wrong patient ID");
    
	
	@Override
	public void start(Stage primaryStage) {
		
		for (int textFileIndex = 0; textFileIndex < patientsIDList.length; ++textFileIndex) { //Initialize all actors of system
			patientsIDList[textFileIndex] = new Patient("", iDList1[textFileIndex]);
			doctorsIDList[textFileIndex] = new Doctor("", iDList2[textFileIndex]);
			nursesIDList[textFileIndex] = new Nurse("", iDList3[textFileIndex]);
			System.out.println(textFileIndex);
	    }
		
		loginView();
		
		try {
			Scene scene = new Scene(gridPane2, 900, 700);   //Scene of specific size
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	EventHandler<ActionEvent> eventLogin = new EventHandler<ActionEvent>() {       //event for clicking Create Account button
        public void handle(ActionEvent event) {
        	recursiveLoginChecker();
        }
	};
    
    EventHandler<ActionEvent> eventCreatePatientAccount = new EventHandler<ActionEvent>() {       //event for clicking Create Account button
        public void handle(ActionEvent event) {
        	recursivePasswordChecker();
        }
	};
	
	public void loginView() {
		gridPane2.getChildren().clear();

		Label loginViewlabel = new Label("Login View");
		Label userIDLabel = new Label("User ID: ");
		Label passwordLabel = new Label("Password:");


		loginViewlabel.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 35;");
		userIDLabel.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");
		passwordLabel.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");


		TextField userIDField = new TextField();
		TextField passwordField = new TextField();
		userIDField.setMinSize(350, 50);
		passwordField.setMinSize(350, 50);
		userIDField.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");
		passwordField.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");

		Button loginButton1 = new Button("Login");
		Button createAccountButton1 = new Button("Create an Account");
		loginButton1.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25; -fx-background-color: #005A9C; -fx-font-fill: #FFFFFF;");
		createAccountButton1.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25; -fx-background-color: #005A9C; -fx-font-fill: #FFFFFF;");
		loginButton1.setMinSize(150, 50);
		createAccountButton1.setMinSize(150, 50);

		gridPane2.add(loginViewlabel, 3, 1, 4, 1);
		gridPane2.add(userIDLabel, 1, 6, 2, 1);
		gridPane2.add(passwordLabel, 1, 8, 2, 1);
		gridPane2.add(userIDField, 3, 6, 3, 1);
		gridPane2.add(passwordField, 3, 8, 3, 1);
		gridPane2.add(loginButton1, 3, 11, 2, 1);
		gridPane2.add(createAccountButton1, 3, 13, 4, 1);
		gridPane2.setHgap(83);
		gridPane2.setVgap(20);	
		
		createAccountButton1.setOnAction(eventCreatePatientAccount);
		loginButton1.setOnAction(eventLogin);

	}
	
	public void doctorView() {																 //Doctors View
		gridPane2.getChildren().clear();
		
		doctorOrNurse = "Doctor";
		//Text
		
		Text welcome1 = new Text("Phoenix Pediatric Office");
		Text welcome2 = new Text("\tDoctor View");
		Text info = new Text("Patient Info");
		Text history = new Text("Patient History");
		
		welcome1.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");
		welcome2.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");
		info.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 20;");
		history.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 20;");

		//TextField
		TextField fName = new TextField("FN:");
		fName.setMaxSize(120, 50);
		TextField lName = new TextField("LN:");
		lName.setMaxSize(120, 50);

		//DatePicker
		DatePicker date = new DatePicker();
		date.setMaxSize(120, 50);

		//Button
		Button find = new Button("Find Patient");
		Button Phamacy = new Button("Send to Phamacy");
		Button update = new Button("Update Visit");
		Button logout = new Button("logout");
		Button messaging = new Button("Messaging System");


		//TextArea
		TextArea textArea1 = new TextArea();
		textArea1.setPrefHeight(600);
		textArea1.setPrefWidth(400);

		TextArea textArea2 = new TextArea();
		textArea2.setPrefHeight(175);
		textArea2.setPrefWidth(400);
		textArea2.setText("Enter Physical Test Findings:");

		TextArea textArea3 = new TextArea();
		textArea3.setPrefHeight(175);
		textArea3.setPrefWidth(400);
		textArea3.setText("Enter Prescription Information:");

		TextArea textArea4 = new TextArea();
		textArea4.setPrefHeight(175);
		textArea4.setPrefWidth(400);
		textArea4.setText("Visit summary:");
		

		//layout
		gridPane2.setAlignment(Pos.BASELINE_LEFT);
		gridPane2.setVgap(12);
		gridPane2.setHgap(7);
		gridPane2.setPadding(new Insets(10,10,10,10));

		gridPane2.add(welcome1, 20, 0,2,1);
		gridPane2.add(welcome2, 20, 1,2,1);

		gridPane2.add(info, 12, 2,4,1);
		gridPane2.add(history, 30, 2,1,1);

		gridPane2.add(fName, 2, 3,9,1);
		gridPane2.add(lName, 11, 3,10,1);
		gridPane2.add(textArea1, 29, 3,5,8);
		gridPane2.add(date, 19, 3,10,1);
		gridPane2.add(find, 11, 4,10,1);

		gridPane2.add(textArea2, 2, 5,20,1);
		gridPane2.add(textArea3, 2, 7,20,1);
		gridPane2.add(Phamacy, 11, 8,10,1);

		gridPane2.add(textArea4, 2, 9,20,1);
		gridPane2.add(update, 2, 11,18,6);
		gridPane2.add(logout, 21, 11,9,6);
		gridPane2.add(messaging, 30, 11,10,6);

		EventHandler<ActionEvent> eventBackToLogin = new EventHandler<ActionEvent>() {       //event for clicking logout button
			public void handle(ActionEvent event) {	
				loginView();                                    							 //Back to login view
			}
		};
		
		EventHandler<ActionEvent> eventFindPatientHistory = new EventHandler<ActionEvent>() {   //event for clicking find patient button
			public void handle(ActionEvent event) {	
				messagingSystemView();                                    					 //Back to login view
			}
		};
		
		EventHandler<ActionEvent> eventUpdateVisit = new EventHandler<ActionEvent>() {   //event for clicking update visit button
			public void handle(ActionEvent event) {	
				recursiveRecordingPatientsInfo();
			}
		};
		
		EventHandler<ActionEvent> eventMessagingSystem = new EventHandler<ActionEvent>() {   //event for clicking messaging system button
			public void handle(ActionEvent event) {	
				messagingSystemView();                                    					
			}
		};
		
		logout.setOnAction(eventBackToLogin);
		messaging.setOnAction(eventMessagingSystem);
		update.setOnAction(eventUpdateVisit);

	}
	
	public void patientView() throws IOException { 															 //Patient View
		gridPane2.getChildren().clear();
		
		//define Labels for title and text fields
		Label lbl_logo = new Label("Phoenix Pediatric Office");
		lbl_logo.setFont(new Font("Arial", 24));
		
		Label lbl_welcome = new Label("Welcome ");
		lbl_welcome.setFont(new Font("Arial", 24));
		
		Label lbl_fname = new Label("First Name: ");
		lbl_fname.setFont(new Font("Arial", 14));
		
		Label lbl_lname = new Label("Last Name: ");
		lbl_lname.setFont(new Font("Arial", 14));
		
		Label lbl_rcpt = new Label("Email Recepient: ");
		lbl_rcpt.setFont(new Font("Arial", 14));
		
		Label lbl_phone = new Label("Contact: ");
		lbl_phone.setFont(new Font("Arial", 14));
		
		//define text areas for  user input
		TextArea txt_fname = new TextArea();
		TextArea txt_lname = new TextArea();
		TextArea txt_rcpt = new TextArea();
		TextArea txt_phone = new TextArea();
		TextArea txt_summary = new TextArea();
		TextArea txt_replies = new TextArea();
		TextArea txt_send = new TextArea();
		
		txt_fname.setText("Type Here");
		txt_lname.setText("Type Here");
		txt_rcpt.setText("ID here");
		txt_phone.setText("(XXX)XXX-XXXX");
		txt_summary.setText("Visit Summary");
		txt_replies.setText("Replies");
		txt_send.setText("Send Message");
		
		File patientFile2;
		Scanner scanFile2;
		
		try {
			patientFile2 = new File("src/" + patientIDForPatientView + "_PatientInfo.txt");
			if (("").compareTo(new String(Files.readAllBytes(patientFile2.toPath()))) != 0) {
				txt_summary.setText(new String(Files.readAllBytes(patientFile2.toPath())));
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			patientFile2 = new File("src/" + patientIDForPatientView + "_PatientRecievedMessages.txt");
			if (("").compareTo(new String(Files.readAllBytes(patientFile2.toPath()))) != 0) {
				txt_replies.setText(new String(Files.readAllBytes(patientFile2.toPath())));
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//define buttons for saving patient info and returning back to main view
		Button btn_sendMessage = new Button("Message Rcpt.");
		Button btn_logOut = new Button("Log Out");
		
		//GRID
		
		//set gaps
		gridPane2.setHgap(20);
		gridPane2.setVgap(20);
		gridPane2.setPadding(new Insets(10,10,10,10));
		
		//set minimum size and align with center of page
		gridPane2.setAlignment(Pos.TOP_CENTER);
		
		//3 columns, each with 1/3 of the page size
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		
		column1.setPercentWidth(25);
		column2.setPercentWidth(35);
		column3.setPercentWidth(40);
		
		gridPane2.getColumnConstraints().add(column1);
		gridPane2.getColumnConstraints().add(column2);
		gridPane2.getColumnConstraints().add(column3);
		

		//Add title to grid pane and center
		gridPane2.add(lbl_logo, 1, 0, 1, 1);

		gridPane2.add(lbl_welcome, 0, 1, 3, 1);
		GridPane.setHalignment(lbl_welcome, HPos.CENTER);
		GridPane.setHalignment(lbl_logo, HPos.CENTER);

		//add labels and associated text fields
		gridPane2.add(lbl_fname, 0, 2);
		gridPane2.add(lbl_lname, 0, 3);
		gridPane2.add(lbl_rcpt, 0, 4);
		gridPane2.add(lbl_phone, 0, 5);

		gridPane2.add(txt_fname, 0, 2);
		gridPane2.add(txt_lname, 0, 3);
		gridPane2.add(txt_rcpt, 0, 4);
		gridPane2.add(txt_phone, 0, 5);

		//fix height of text fields
		txt_fname.setMaxHeight(25);
		txt_lname.setMaxHeight(25);
		txt_rcpt.setMaxHeight(25);
		txt_phone.setMaxHeight(25);

		txt_fname.setMaxWidth(125);
		txt_lname.setMaxWidth(125);
		txt_rcpt.setMaxWidth(125);
		txt_phone.setMaxWidth(125);

		GridPane.setHalignment(txt_fname, HPos.RIGHT);
		GridPane.setHalignment(txt_lname, HPos.RIGHT);
		GridPane.setHalignment(txt_rcpt, HPos.RIGHT);
		GridPane.setHalignment(txt_phone, HPos.RIGHT);

		gridPane2.add(txt_summary, 1, 2, 1, 6);

		gridPane2.add(txt_send, 2, 2, 2, 3);
		gridPane2.add(txt_replies, 2, 5, 2, 3);


		//add buttons
		gridPane2.add(btn_sendMessage, 0, 6);
		GridPane.setHalignment(btn_sendMessage, HPos.CENTER);
		gridPane2.add(btn_logOut, 0, 7);
		GridPane.setHalignment(btn_logOut, HPos.CENTER);

		//change horizontal size of buttons
		btn_logOut.setMinWidth(125);
		btn_sendMessage.setMinWidth(125);

		//change vertical size of buttons
		btn_logOut.setMinHeight(50);
		btn_sendMessage.setMinHeight(50);
					

		EventHandler<ActionEvent> eventBackToLogin = new EventHandler<ActionEvent>() {       //event for clicking logout button
			public void handle(ActionEvent event) {	
				gridPane2.getColumnConstraints().removeAll(column1, column2, column3);
				gridPane2.setPadding(new Insets(0,0,0,0));
				loginView();                                    							 //Back to login view
			}
		};
		
		EventHandler<ActionEvent> eventMessage = new EventHandler<ActionEvent>() {       //event for clicking message button
			public void handle(ActionEvent event) {	
				FileWriter writer;
        		try {
        			
        			writer = new FileWriter("src/" + ((TextArea)gridPane2.getChildren().get(8)).getText() + "_RecievedMessages.txt");
					writer.write(patientIDForPatientView + "\n" + ((TextArea)gridPane2.getChildren().get(11)).getText() + "\n");
					writer.flush();
					writer.close();
				} 
        		catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		String filePathName1 = "src/" + patientIDForPatientView + "_PatientInfo.txt";        //Assumption that 10 patients
		File patientFile1 = new File(filePathName1);
		Scanner scanFile1;

		try {
			scanFile1 = new Scanner(patientFile1);
			lbl_welcome.setText("Welcome " + scanFile1.nextLine() + " " + scanFile1.nextLine());
			scanFile1.close();  //Scanner is closed
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		btn_logOut.setOnAction(eventBackToLogin);
		btn_sendMessage.setOnAction(eventMessage);

	}
	
		public void nurseView() { 															     //Patients View
		gridPane2.getChildren().clear();
		doctorOrNurse = "Nurse";
		
        mainPane.setPadding(new Insets(20));

        Text titleText1 = new Text("Nurse View\n");
        titleText1.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        mainPane.setTop(titleText1);
        mainPane.setAlignment(titleText1, Pos.CENTER);

        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(32);
        formPane.setPadding(new Insets(10));

        Label iDLabel = new Label("Patient ID:");
        iDLabel.setStyle("-fx-font-size: 12pt;");
        TextField iDField = new TextField();
        iDField.setStyle("-fx-font-size: 9pt;");
        iDField.setPrefWidth(100);
        formPane.add(iDLabel, 0, 0);
        formPane.add(iDField, 1, 0);

        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-size: 12pt;");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-font-size: 9pt;");
        nameField.setPrefWidth(100);
        formPane.add(nameLabel, 0, 1);
        formPane.add(nameField, 1, 1);

        Label dateOfBirthLabel = new Label("Date of Birth:");
        dateOfBirthLabel.setStyle("-fx-font-size: 12pt;");
        TextField dateOfBirthField = new TextField();
        dateOfBirthField.setStyle("-fx-font-size: 9pt;");
        formPane.add(dateOfBirthLabel, 0, 2);
        formPane.add(dateOfBirthField, 1, 2);

        Label weightLabel = new Label("Weight:");
        weightLabel.setStyle("-fx-font-size: 12pt;");
        TextField weightField = new TextField();
        weightField.setStyle("-fx-font-size: 9pt;");
        formPane.add(weightLabel, 0, 3);
        formPane.add(weightField, 1, 3);

        Label heightLabel = new Label("Height:");
        heightLabel.setStyle("-fx-font-size: 12pt;");
        TextField heightField = new TextField();
        heightField.setStyle("-fx-font-size: 9pt;");
        formPane.add(heightLabel, 0, 4);
        formPane.add(heightField, 1, 4);

        Label bodyTempLabel = new Label("Body Temperature:");
        bodyTempLabel.setStyle("-fx-font-size: 12pt;");
        TextField bodyTempField = new TextField();
        bodyTempField.setStyle("-fx-font-size: 9pt;");
        formPane.add(bodyTempLabel, 0, 5);
        formPane.add(bodyTempField, 1, 5);

        Label bpLabel = new Label("Blood Pressure:");
        bpLabel.setStyle("-fx-font-size: 12pt;");
        TextField bpField = new TextField();
        bpField.setStyle("-fx-font-size: 9pt;");
        formPane.add(bpLabel, 0, 6);
        formPane.add(bpField, 1, 6);

        Label over12Label = new Label("Patient is over 12:");
        over12Label.setStyle("-fx-font-size: 12pt;");
        RadioButton yesRadioButton = new RadioButton("Yes");
        RadioButton noRadioButton = new RadioButton("No");
        ToggleGroup over12Group = new ToggleGroup();
        yesRadioButton.setToggleGroup(over12Group);
        noRadioButton.setToggleGroup(over12Group);
        formPane.add(over12Label, 0, 7);
        formPane.add(yesRadioButton, 1, 7);
        formPane.add(noRadioButton, 2, 7);

        Label otherLabel = new Label("Other health information:");
        otherLabel.setStyle("-fx-font-size: 12pt;");
        TextField otherField = new TextField();
        otherField.setStyle("-fx-font-size: 9pt;");
        formPane.add(otherLabel, 0, 8);
        formPane.add(otherField, 1, 8);

        mainPane.setLeft(formPane);

        TextArea historyTextArea = new TextArea();
        historyTextArea.setPrefColumnCount(35);
        historyTextArea.setPrefRowCount(12);
        historyTextArea.setText("Patient's History");
        mainPane.setRight(historyTextArea);

        Button findPatientButton = new Button("Find Patient");
        Button newPatientButton = new Button("New Patient");
        Button updateVisitButton = new Button("Update Visit");
        Button logoutButton = new Button("Logout");
        Button messagingButton = new Button("Messsaging System");

        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(50, 0, 0, 0));
        buttonBox.getChildren().addAll(findPatientButton, newPatientButton, updateVisitButton, logoutButton, messagingButton);
        mainPane.setBottom(buttonBox);
        mainPane.setAlignment(buttonBox, Pos.CENTER);
        
        gridPane2.add(mainPane, 0, 0);

		EventHandler<ActionEvent> eventBackToLogin = new EventHandler<ActionEvent>() {       //event for clicking logout button
			public void handle(ActionEvent event) {	
				loginView();                                    							 //Back to login view
			}
		};
		
		EventHandler<ActionEvent> eventMessagingSystem = new EventHandler<ActionEvent>() {   //event for clicking messaging system button
			public void handle(ActionEvent event) {	
				messagingSystemView();                                    					 //Back to login view
			}
		};
		
		logoutButton.setOnAction(eventBackToLogin);
		messagingButton.setOnAction(eventMessagingSystem);
      
		updateVisitButton.setOnAction(e -> {
      if (new File("src/" + iDField.getText() + "_PatientInfo.txt").isFile()) {
        File patientFile2 = new File("src/" + iDField.getText() + "_PatientInfo.txt");
        try {
          BufferedWriter writer = new BufferedWriter(new FileWriter(patientFile2));
          
          //separate entered patient info with semicolons
          writer.write(nameField.getText() + "\n" + dateOfBirthField.getText() + "\n" + 
                 weightField.getText() + "\n" + heightField.getText() + "\n" + 
                 bodyTempField.getText() + "\n" + bpField.getText() + "\n" + 
                 otherField.getText() + "\n");
          writer.close();
          
        } catch (IOException e2) {
          e2.printStackTrace();
        };
      }
    });
		
		findPatientButton.setOnAction(e -> 
		{
			
			try {
				
				if (new File("src/" + iDField.getText() + "_PatientInfo.txt").isFile()) {
					
					File patientFile2 = new File("src/" + iDField.getText() + "_PatientInfo.txt");
					
					historyTextArea.clear();
					
					Scanner scan = new Scanner(patientFile2);
					
					while (scan.hasNextLine()) {
						historyTextArea.appendText(scan.nextLine() + "\n");
					}
					
					scan.close();
					}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
	
		newPatientButton.setOnAction(e ->
		{
			//might not need since patient id is assumed to be known for demo
		});

	}
	
	public void messagingSystemView() { 													 //Messaging System View
		gridPane2.getChildren().clear();

		//Text
		Text welcome1 = new Text("Phoenix Pediatric Office");
		Text welcome2 = new Text("\tWelcome  " + doctorOrNurse);
		Text inbox = new Text("Inbox");
		Text select = new Text("Selected Message");
		welcome1.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");
		welcome2.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");
		inbox.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 20;");
		select.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 20;");

		//TextArea
		TextArea textArea1 = new TextArea();
		textArea1.setPrefHeight(370);
		textArea1.setPrefWidth(340);

    File patientFile2;
		Scanner scanFile2;
    
    try {
			patientFile2 = new File("src/" + doctorOrNurseIDView + "_RecievedMessages.txt");
			if (("").compareTo(new String(Files.readAllBytes(patientFile2.toPath()))) != 0) {
				textArea1.setText(new String(Files.readAllBytes(patientFile2.toPath())));
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    catch (IOException e) {
			e.printStackTrace();
		}

		TextArea textArea2 = new TextArea();
		textArea2.setPrefHeight(370);
		textArea2.setPrefWidth(340);

		//button
		Button urgent = new Button("Urgent Call");
		Button newMessage = new Button("New Message");
		Button reply = new Button("Reply");
		Button view = new Button(doctorOrNurse +" view");


		gridPane2.setAlignment(Pos.BASELINE_LEFT);
		gridPane2.setVgap(10);
		gridPane2.setHgap(10);
		gridPane2.setPadding(new Insets(10,10,10,10));

		gridPane2.add(welcome1, 16, 0,2,1);
		gridPane2.add(welcome2, 16, 1,2,1);
		gridPane2.add(inbox, 5, 2,2,1);
		gridPane2.add(select, 20, 2,2,1);
		gridPane2.add(textArea1, 2, 4,15,1);
		gridPane2.add(textArea2, 20, 4,10,1);

		gridPane2.add(urgent, 20, 6,10,1);
		gridPane2.add(newMessage, 2, 6,10,1);
		gridPane2.add(reply, 29, 6,10,1);
		gridPane2.add(view, 29, 12,10,1);

		EventHandler<ActionEvent> eventDoctorsView = new EventHandler<ActionEvent>() {       //event for back to Doctors View or Patients View
			public void handle(ActionEvent event) {	
				gridPane2.setPadding(new Insets(0,0,0,0));
				if (doctorOrNurse.compareTo("Doctor") == 0) {
					doctorView();                                    					     //Back to Doctor view
				}
				else {
					nurseView();                                    					     //Back to Nurse view
				}
			}
		};

    newMessage.setOnAction(event -> {

      FileWriter writer;

        		try {
        			writer = new FileWriter("src/" + ((TextArea)gridPane2.getChildren().get(5)).getText().substring(0, ((TextArea)gridPane2.getChildren().get(5)).getText().indexOf("\n")) + "_PatientRecievedMessages.txt");
					writer.write(((TextArea)gridPane2.getChildren().get(5)).getText().substring(((TextArea)gridPane2.getChildren().get(5)).getText().indexOf("\n")) + "\n");
					writer.flush();
					writer.close();
				} 
        		catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    });
    
		view.setOnAction(eventDoctorsView);

	}
	
	public void recursiveRecordingPatientInfo() {
		int textFileIndex = 0;
    	String filePathName = "";
    	
    	if (((TextField)gridPane2.getChildren().get(7)).getText().compareTo("") == 0) {
			gridPane2.add(messageReceptionistView.warningMessage, 3, 15, 4, 1);      //Data missing message
			recursiveRecordingPatientInfo();
		}
    	else if (((TextField)gridPane2.getChildren().get(8)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 3, 15, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((TextField)gridPane2.getChildren().get(9)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 3, 15, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((TextField)gridPane2.getChildren().get(10)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 3, 15, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((TextField)gridPane2.getChildren().get(11)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 3, 15, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((TextField)gridPane2.getChildren().get(12)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 3, 15, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	
    	for (textFileIndex = 0; textFileIndex < patientsIDList.length; ++textFileIndex) {   //Associate Patient Name to first free ID
    		if (patientsIDList[textFileIndex].getPatientName().compareTo("") == 0) {                 //If Patient Name not associated yet
    			patientsIDList[textFileIndex].setPatientName(((TextField)gridPane2.getChildren().get(7)) + " " + ((TextField)gridPane2.getChildren().get(8))); //Patient Name is now associated with the ID
    			break;
    		}
    		if (patientsIDList[textFileIndex].getPatientName().compareTo(((TextField)gridPane2.getChildren().get(7)).getText() + " " + ((TextField)gridPane2.getChildren().get(8)).getText()) == 0) { //If Patient Name associated ID already
    			break;
    		}
    	}

    	if (textFileIndex != patientsIDList.length) {                                       //Patient list not full to limit
    		if (gridPane2.getChildren().contains(testingLimitMessage.warningMessage)) {
        		gridPane2.getChildren().remove(testingLimitMessage.warningMessage);           //Remove Patient ID Error
        	}

				if (gridPane2.getChildren().contains(messageReceptionistView.warningMessage)) {
					gridPane2.getChildren().remove(messageReceptionistView.warningMessage);           //Remove Patient ID Error
				}
		
    	
    	}
    	else {
    		gridPane2.add(testingLimitMessage.warningMessage, 3, 16, 4, 1);        //Testing limit of 10 patients
    		recursiveRecordingPatientInfo();
    	}
	}

	public void techViewFileWriting() {
		int textFileIndex = 0;
    	String filePathName = "";
        for (textFileIndex = 0; textFileIndex < patientsIDList.length; ++textFileIndex) {   //Look for id
    		try { //See if input Patient ID is  a number
    			int isNum = Integer.parseInt(((TextField)gridPane2.getChildren().get(8)).getText());
    		} 
    		catch(Exception e) {
    			gridPane2.add(errorMessagePatientID.warningMessage, 5, 9, 5, 1);            //Error message
        		techViewFileWriting();
    		}
    		if (patientsIDList[textFileIndex].getPatientID() == Integer.parseInt(((TextField)gridPane2.getChildren().get(8)).getText())) {     //If Patient ID found then break
    			break;
    		}
        }
		
		if (textFileIndex != patientsIDList.length) {                                       //Patient ID found
        
        	if (gridPane2.getChildren().contains(errorMessagePatientID.warningMessage)) {
        		gridPane2.getChildren().remove(errorMessagePatientID.warningMessage);           //Remove Patient ID Error
        	}
        
				
				if (((TextField)gridPane2.getChildren().get(8)).getText().compareTo("") == 0) {
            		gridPane2.add(errorMessageTechView.warningMessage, 5, 11, 5, 1);            //Error message
            		techViewFileWriting();
				}
            	else if (((TextField)gridPane2.getChildren().get(9)).getText().compareTo("") == 0) {
            		gridPane2.add(errorMessageTechView.warningMessage, 5, 11, 5, 1);            //Error message
            		techViewFileWriting();
            	}
            	else if (((TextField)gridPane2.getChildren().get(10)).getText().compareTo("") == 0) {
            		gridPane2.add(errorMessageTechView.warningMessage, 5, 11, 5, 1);            //Error message
            		techViewFileWriting();
            	}
            	else if (((TextField)gridPane2.getChildren().get(11)).getText().compareTo("") == 0) {
            		gridPane2.add(errorMessageTechView.warningMessage, 5, 11, 5, 1);            //Error message
            		techViewFileWriting();
            	}
            	else if (((TextField)gridPane2.getChildren().get(12)).getText().compareTo("") == 0) {
            		gridPane2.add(errorMessageTechView.warningMessage, 5, 11, 5, 1);            //Error message
            		techViewFileWriting();
            	}
            	else if (((TextField)gridPane2.getChildren().get(13)).getText().compareTo("") == 0) {
            		gridPane2.add(errorMessageTechView.warningMessage, 5, 11, 5, 1);            //Error message
            		techViewFileWriting();
            	}
            	else if (((TextField)gridPane2.getChildren().get(14)).getText().compareTo("") == 0) {
            		gridPane2.add(errorMessageTechView.warningMessage, 5, 11, 5, 1);            //Error message
            		techViewFileWriting();
            	}
            	else {
            		if (gridPane2.getChildren().contains(errorMessageTechView.warningMessage)) {
                		gridPane2.getChildren().remove(errorMessageTechView.warningMessage);           //Remove Patient ID Error
                	}
            		
	
		
        }
    }
		else {
			gridPane2.add(errorMessageTechView.warningMessage, 5, 11, 5, 1);            //Error message
    		techViewFileWriting();
		}
	}
	
	public void recursiveLoginChecker() {
		try { //See if input is  a number
			int isNum = Integer.parseInt(((TextField)gridPane2.getChildren().get(3)).getText());
		} 
		catch(Exception e) {
			gridPane2.add(errorMessagePatientID.warningMessage, 3, 15, 4, 1); //Error Message
			recursivePasswordChecker();
		}
		
		if (((TextField)gridPane2.getChildren().get(3)).getText().compareTo("") != 0 && ((TextField)gridPane2.getChildren().get(4)).getText().compareTo("") != 0) {         // USer Id and password both entered
			int actorIndex;
			for (actorIndex = 0; actorIndex < patientsIDList.length; ++actorIndex) {
				if (Integer.parseInt(((TextField)gridPane2.getChildren().get(3)).getText()) == iDList1[actorIndex]) {  //If Patient User ID is found
					if (gridPane2.getChildren().contains(errorMessagePatientID.warningMessage)) {
			    		gridPane2.getChildren().remove(errorMessagePatientID.warningMessage);                          //Remove Patient User ID Error
					}
					if (passwordList1[actorIndex].compareTo(((TextField)gridPane2.getChildren().get(4)).getText()) == 0) { //If password is correct
						patientIDForPatientView = Integer.parseInt(((TextField)gridPane2.getChildren().get(3)).getText());
						try {
							patientView();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} //Returns to Patient View
					}
				}
				if (Integer.parseInt(((TextField)gridPane2.getChildren().get(3)).getText()) == iDList2[actorIndex]) {  //If Doctor User ID is found
					if (gridPane2.getChildren().contains(errorMessagePatientID.warningMessage)) {
			    		gridPane2.getChildren().remove(errorMessagePatientID.warningMessage);           //Remove Patient User ID Error
					}
					if (passwordList2[actorIndex].compareTo(((TextField)gridPane2.getChildren().get(4)).getText()) == 0) { //If password is correct
						doctorOrNurseIDView = iDList2[actorIndex];
            doctorView(); //Returns to Doctors View
					}
				}
				if (Integer.parseInt(((TextField)gridPane2.getChildren().get(3)).getText()) == iDList3[actorIndex]) {  //If Nurse User ID is found
					if (gridPane2.getChildren().contains(errorMessagePatientID.warningMessage)) {
			    		gridPane2.getChildren().remove(errorMessagePatientID.warningMessage);           //Remove Patient User ID Error
					}
					if (passwordList3[actorIndex].compareTo(((TextField)gridPane2.getChildren().get(4)).getText()) == 0) { //If password is correct
						doctorOrNurseIDView = iDList3[actorIndex];
            nurseView(); //Returns to Nurses View
					}
				}
			}
			if (actorIndex == patientsIDList.length) {
				gridPane2.add(errorMessagePatientID.warningMessage, 3, 15, 4, 1); //Error Message
				recursivePasswordChecker();
			}
		}
		else {
			gridPane2.add(errorMessagePatientID.warningMessage, 3, 15, 4, 1); //Error Message
			recursivePasswordChecker();
		}
		
	}
	
	public void recursivePasswordChecker() {                                                     //For creating an account
		try { //See if input is  a number
			int isNum = Integer.parseInt(((TextField)gridPane2.getChildren().get(3)).getText());
		} 
		catch(Exception e) {
			gridPane2.add(errorMessagePatientID.warningMessage, 3, 15, 4, 1); //Error Message
			recursivePasswordChecker();
		}
		
		if (((TextField)gridPane2.getChildren().get(3)).getText().compareTo("") != 0 && ((TextField)gridPane2.getChildren().get(4)).getText().compareTo("") != 0) {         // USer Id and password both entered
			int patientIndex;
			for (patientIndex = 0; patientIndex < patientsIDList.length; ++patientIndex) {
				if (Integer.parseInt(((TextField)gridPane2.getChildren().get(3)).getText()) == iDList1[patientIndex]) {  //If Patient User ID is found
					if (gridPane2.getChildren().contains(errorMessagePatientID.warningMessage)) {
			    		gridPane2.getChildren().remove(errorMessagePatientID.warningMessage);           //Remove Patient User ID Error
					}
					Label accountCreateSuccessLabel = new Label("Patient Account Created with UserID: " + iDList1[patientIndex]);
					accountCreateSuccessLabel.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 25;");
					gridPane2.add(accountCreateSuccessLabel, 3, 15, 4, 1); //Account creation success message
					passwordList1[patientIndex] = ((TextField)gridPane2.getChildren().get(4)).getText();
					break;
				}
			}
			if (patientIndex == patientsIDList.length) {
				gridPane2.add(errorMessagePatientID.warningMessage, 3, 15, 4, 1); //Error Message
				recursivePasswordChecker();
			}
		}
		else {
			gridPane2.add(errorMessagePatientID.warningMessage, 3, 15, 4, 1); //Error Message
			recursivePasswordChecker();
		}
		
	}
	
	public void recursiveRecordingPatientsInfo() {
		int textFileIndex = 0;
    	String filePathName = "";
    	
    	if (((TextField)gridPane2.getChildren().get(4)).getText().compareTo("") == 0) {
			gridPane2.add(messageReceptionistView.warningMessage, 12, 13, 4, 1);      //Data missing message
			recursiveRecordingPatientInfo();
		}
    	else if (((TextField)gridPane2.getChildren().get(5)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 12, 13, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((TextArea)gridPane2.getChildren().get(6)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 12, 13, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((DatePicker)gridPane2.getChildren().get(7)).getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 12, 13, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((TextArea)gridPane2.getChildren().get(9)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 12, 13, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((TextArea)gridPane2.getChildren().get(10)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 12, 13, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	else if (((TextArea)gridPane2.getChildren().get(12)).getText().compareTo("") == 0) {
    		gridPane2.add(messageReceptionistView.warningMessage, 12, 13, 4, 1);      //Data missing message
    		recursiveRecordingPatientInfo();
    	}
    	
    	
    	for (textFileIndex = 0; textFileIndex < patientsIDList.length; ++textFileIndex) {   //Associate Patient Name to first free ID
    		filePathName = "src/" + iDList1[textFileIndex] + "_PatientInfo.txt";                 //Assumption that 10 patients
    		
    		if (patientsIDList[textFileIndex].getPatientName().compareTo("") == 0) {                 //If Patient Name not associated yet
    			patientsIDList[textFileIndex].setPatientName(((TextField)gridPane2.getChildren().get(4)) + " " + ((TextField)gridPane2.getChildren().get(5))); //Patient Name is now associated with the ID
    			break;
    		}
    		if (patientsIDList[textFileIndex].getPatientName().compareTo(((TextField)gridPane2.getChildren().get(4)).getText() + " " + ((TextField)gridPane2.getChildren().get(5)).getText()) == 0) { //If Patient Name associated ID already
    			break;
    		}
    	}

    	if (textFileIndex != patientsIDList.length) {                                       //Patient list not full to limit
    		FileWriter writer;
    		if (gridPane2.getChildren().contains(testingLimitMessage.warningMessage)) {
        		gridPane2.getChildren().remove(testingLimitMessage.warningMessage);           //Remove Patient ID Error
        	}
			try {
				if (gridPane2.getChildren().contains(messageReceptionistView.warningMessage)) {
					gridPane2.getChildren().remove(messageReceptionistView.warningMessage);           //Remove Patient ID Error
				}
				
				File file1 = new File(filePathName);
				writer = new FileWriter(filePathName);
				writer.write(((TextField)gridPane2.getChildren().get(4)).getText() + "\n");
				writer.write(((TextField)gridPane2.getChildren().get(5)).getText() + "\n");
				writer.write(((TextArea)gridPane2.getChildren().get(6)).getText() + "\n");
				writer.write(((DatePicker)gridPane2.getChildren().get(7)).getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n");
				writer.write(((TextArea)gridPane2.getChildren().get(9)).getText() + "\n");
				writer.write(((TextArea)gridPane2.getChildren().get(10)).getText() + "\n");
				writer.write(((TextArea)gridPane2.getChildren().get(12)).getText() + "\n");
				writer.flush(); //flush buffer
				writer.close(); //close file

			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
		
    	
    	}
    	else {
    		gridPane2.add(new Label("Not Updated") , 12, 11, 4, 1);                //Testing limit of 10 patients
    		recursiveRecordingPatientInfo();
    	}
	}

	
	public static void main(String[] args) {
		launch(args);                                                              //launching application
	}
}