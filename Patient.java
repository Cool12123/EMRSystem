
import javafx.application.Application;

import java.io.File;
import java.io.IOException;

public class Patient {
	private String patientName = "";
	private int patientID = 0;
	public File patientInfoFile;
	public File patientHistoryFile;
	public File patientSentMessagesFile;
	public File patientRecievedMessagesFile;
	
	public Patient(String patientName, int patientID) {
		this.patientName = patientName;
		this.patientID = patientID;
		patientInfoFile = new File("src/" + patientID + "_PatientInfo.txt");
		patientHistoryFile = new File("src/" + patientID + "_PatientHistory.txt");
		patientSentMessagesFile = new File("src/" + patientID + "_PatientSentMessages.txt");
		patientRecievedMessagesFile = new File("src/" + patientID + "_PatientRecievedMessages.txt");
		try {
			patientInfoFile.createNewFile();
			patientHistoryFile.createNewFile();
			patientSentMessagesFile.createNewFile();
			patientRecievedMessagesFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	public int getPatientID() {
		return patientID;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	
}