package application.sub;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import application.database.BillDatabase;
import application.database.ChildDatabase;
import application.model.Bill;
import application.model.Child;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ViewBillController {
	String infor = "";
	Bill bill = null;
	@FXML
	private TextArea billInfor;
	@FXML
	private Button cancelButton;
	// Event Listener on Button[#cancelButton].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	@FXML 
	private void initialize()
	{
		billInfor.setEditable(false);
	}
	public void loadBillInfor(int id)
	{
		bill = BillDatabase.selectBill(id);
		Child ch = ChildDatabase.selectChild(bill.getChild().getId());
			infor = "������� �������� �������� :\n"
					+ "==================================\n"
					+ "��� ����� : " + bill.getChild().getName()
					+ "\n��� �������: " + bill.getPartName()
					+ "\n��� �����: " + bill.getDay()
					+ "\n����� �����: " + bill.getPayDateString()
					+ "\n����� ��������: " + bill.getExpiredDateString()
					+ "\n�����: " + bill.getDuration()

					+ "\n\n������� �������� ������� :\n"
					+ "==================================\n"
					+ "\n������ �������: " + bill.getNewPayment()
					+ "\n������ ������ �����: " + bill.getOldRestMoney()
					+ "\n������ ����� ������: " + (bill.getOldRestMoney() + bill.getNewPayment())
					+ "\n��� ������� �������: " + (bill.getChild().getRestSessions())
					+ "\n��� ������� ������: " + ((bill.getOldRestMoney() + bill.getNewPayment())/bill.getOldOneSessionPrice())
					+ "\n\n������ ����� �����: " + ch.getPaidUpMoney()
					+ "\n������ ������ �����: " + ch.getRestMoney()
					+ "\n��� ������� �����: " + ch.getNumberOfSessions()
					+ "\n��� ������� ������� �����: " + ch.getRestSessions()
					+ "\n==================================\n";
					
			billInfor.setText(infor);
	}
}
