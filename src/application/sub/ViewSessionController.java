package application.sub;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import application.database.ChildDatabase;
import application.database.SessionDatabase;
import application.model.Session;
import application.utils.MUtils;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ViewSessionController {
	String infor = "";
	Session s = null;
	@FXML
	private TextArea sessionInfor;
	@FXML
	private Button cancelSession;
	@FXML
	private Button cancelButton;

	// Event Listener on Button[#cancelSession].onAction
	@FXML
	public void onCancelSession(ActionEvent event) {
		if(MUtils.showConfirmMessage("����� ����� ������", "�� ��� ����� �� ����� ��� �����ɿ")){
			SessionDatabase.deleteSession(s.getId());
			ChildDatabase.updateChildDoneSessions(s.getCh().getId(), s.getCh().getDoneSessions() - 1);
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
			MUtils.notification("����� ����", "�� ����� ������ �����\n ��� ������:" + s.getCh().getName());
		}
		
	}
	// Event Listener on Button[#cancelButton].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	@FXML 
	private void initialize()
	{
		sessionInfor.setEditable(false);
	}
	public void loadSessionInfor(int id)
	{
		s = SessionDatabase.selectSession(id);
			infor = "������� ������ :\n"
					+ "��� ����� : " + s.getCh().getName()
					+ "\n����� ������: " + s.getSessionDateString()
					+ "\n��� ��������: " + s.getSpName()
					+ "\n��� �������: " + s.getPartName()
					+ "\n�������: " + s.getCh().getStatus()
					+ "\n��� ������� ���: " + s.getChildDoneSessions()
					+ "\n��� ������� ���: " + (s.getChildDoneSessions()-1)
					+ "\n==================================\n";
			sessionInfor.setText(infor);
	}
}
