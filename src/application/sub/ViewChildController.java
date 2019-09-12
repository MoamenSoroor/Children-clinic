package application.sub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import java.io.IOException;

import application.database.BillDatabase;
import application.database.ChildDatabase;
import application.database.SessionDatabase;
import application.model.Bill;
import application.model.Child;
import application.model.Session;
import application.utils.MUtils;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewChildController {
	
	int childId;
	Child ch = null;
	String infor = "";
	@FXML
	private TextArea childInfor;
	@FXML
	private ImageView photo;
	@FXML
	private Button cancelButton;
	@FXML
	private Button editButton;
	
	@FXML
	private Button cancelLastBill;
	@FXML
	private Button cancelLastSession;
	
	@FXML
	public void onCancelLastBill(ActionEvent event) {
		if(MUtils.showConfirmMessage("����� ����� ������", "�� ��� ����� �� ����� ��� ������ �����ɿ")){
			Bill bill = BillDatabase.selectLastBillOfChild(childId);
			BillDatabase.deleteLastBillOfChild(childId);
			ChildDatabase.updateChildPaidUpMoneyMinus(bill);
			MUtils.notification("����� ������", "�� ����� �������� �����\n ��� ������:" + bill.getChild().getName());
		}
	}
	@FXML
	public void onCancelLastSession(ActionEvent event) {
		if(MUtils.showConfirmMessage("����� ����� ������", "�� ��� ����� �� ����� ��� ���� �����ɿ")){
			Session s = SessionDatabase.selectLastSessionOfChild(childId);
			SessionDatabase.deleteLastSessionOfChild(childId);
			ChildDatabase.updateChildDoneSessions(childId, ch.getDoneSessions() - 1);
			MUtils.notification("����� ����", "�� ����� ������ �����\n ��� ������:" + s.getCh().getName());
		}
	}
	
	

	// Event Listener on Button[#cancelButton].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button[#editButton].onAction
	@FXML
	public void onEdit(ActionEvent event) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/sub/AddChild.fxml"));
			Parent root = loader.<ScrollPane>load();
			AddChildController add = loader.getController();
			add.setEditMode(childId);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			//stage.setAlwaysOnTop(true);
			//stage.setResizable(false);
			stage.showAndWait();
			} catch (IOException e) { e.printStackTrace(); }
		loadChildInfor(childId);
	}
	@FXML
	private void initialize()
	{
		childInfor.setEditable(false);
	}
	

	public void loadChildInfor(int id)
	{
		childId = id;
		ch = ChildDatabase.selectChild(id);
		infor = "������� ����� ��������: \n��� �����: " + ch.getName() + "\n����� �������: " + ch.getBirthDate()
			+ "\n����: " + ch.getAge() + "\n�������: "+ ch.getStatus()
			+ "\n�����: " + ch.getGenderString() + "\n��� ������� �����: " + ch.getP1().getName()
			+ "\n��� ������� ������: " + ch.getP2().getName()
			+ "\n==================================\n"
			+ "������� ������ ������ �����:\n"
			+ "��� ������� ������: " + ch.getNumberOfSessions()
			+ "\n��� ������� ���� ���: " + ch.getDoneSessions()
			+ "\n��� ������: " + ch.getOneSessionPrice()
			+ "\n������ �������: " + ch.getPaidUpMoney()
			+ "\n������ ������: " + ch.getRestMoney()
			+ "\n==================================\n";
		if(ch.getP1().getId() != 0)
		{
			infor += "������� ������� �����:\n"
					+ "��� ������� �����: " + ch.getP1().getName()
					+ "\n������ ������: " + ch.getP1().getGroundPhone()
					+ "\n������ �����: " + ch.getP1().getPhone1()
					+ "\n������ ������: " + ch.getP1().getPhone2()
					+ "\n��������: " + ch.getP1().getAddress()
					+ "\n==================================\n";
		}
		if(ch.getP2().getId() != 0)
		{
			infor += "������� ������� ������:\n"
					+ "��� ������� ������: " + ch.getP2().getName()
					+ "\n������ �����: " + ch.getP2().getPhone1()
					+ "\n������ ������: " + ch.getP2().getPhone2()
					+ "\n==================================\n";
		}
		childInfor.setText(infor);
		
		photo.setImage(new Image("file:" + ch.getPhoto().getAbsolutePath()));
	}


}


/*
 * 
 * public void loadPartnerInfor(int id)
	{
		Partner p1 = PartnerDatabase.selectPartner(id);
		ObservableList<Child> childs = ChildDatabase.selectChildOfPartner(id);
			infor = "������� ������� �����:\n"
					+ "��� ������� �����: " + p1.getName()
					+ "\n������ ������: " + p1.getGroundPhone()
					+ "\n������ �����: " + p1.getPhone1()
					+ "\n������ ������: " + p1.getPhone2()
					+ "\n��������: " + p1.getAddress()
					+ "\n==================================\n";
		partnerInfor.setText(infor);
	}
 */
















