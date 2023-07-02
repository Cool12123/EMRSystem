
package application;

//import javafx.application.Application;
import java.io.File;
import java.io.IOException;

public class Doctor {
	private String doctorName = "";
	private int doctorID = 0;
	public File doctorInfoFile;
	public File doctorHistoryFile;
	public File doctorSentMessagesFile;
	public File doctorRecievedMessagesFile;
	
	public Doctor(String doctorName, int doctorID) {
		this.doctorName = doctorName;
		this.doctorID = doctorID;
		doctorInfoFile = new File("src/" + doctorID + "_DoctorInfo.txt");
		doctorHistoryFile = new File("src/" + doctorID + "_DoctorHistory.txt");
		doctorSentMessagesFile = new File("src/" + doctorID + "_DoctorSentMessages.txt");
		doctorRecievedMessagesFile = new File("src/" + doctorID + "_DoctorRecievedMessages.txt");
		try {
			doctorInfoFile.createNewFile();
			doctorHistoryFile.createNewFile();
			doctorSentMessagesFile.createNewFile();
			doctorRecievedMessagesFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getDoctorsName() {
		return doctorName;
	}
	
	public int getDoctorsID() {
		return doctorID;
	}
	
	public void setDoctorsName(String patientName) {
		this.doctorName = patientName;
	}
	
	public void setDoctorsID(int patientID) {
		this.doctorID = patientID;
	}
	
}