import javafx.application.Application;

import java.io.File;
import java.io.IOException;

public class Nurse {
	private String nurseName = "";
	private int nurseID = 0;
	public File nurseInfoFile;
	public File nurseHistoryFile;
	public File nurseSentMessagesFile;
	public File nurseRecievedMessagesFile;
	
	public Nurse(String nurseName, int nurseID) {
		this.nurseName = nurseName;
		this.nurseID = nurseID;
		nurseInfoFile = new File("src/" + nurseID + "_NurseInfo.txt");
		nurseHistoryFile = new File("src/" + nurseID + "_NurseHistory.txt");
		nurseSentMessagesFile = new File("src/" + nurseID + "_SentMessages.txt");
		nurseRecievedMessagesFile = new File("src/" + nurseID + "_RecievedMessages.txt");
		try {
			nurseInfoFile.createNewFile();
			nurseHistoryFile.createNewFile();
			nurseSentMessagesFile.createNewFile();
			nurseRecievedMessagesFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNurseName() {
		return nurseName;
	}
	
	public int getNurseID() {
		return nurseID;
	}
	
	public void setNurseName(String patientName) {
		this.nurseName = patientName;
	}
	
	public void setNurseID(int patientID) {
		this.nurseID = patientID;
	}
	
}
