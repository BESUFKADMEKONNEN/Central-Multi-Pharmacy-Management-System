package Controllers;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


    public class adminstratorController {



    @FXML
    private GridPane searchbarMedicinePage;
    @FXML
    private GridPane searchbarUserPage;
    @FXML
    private GridPane searchbarPharmacyPage;
    @FXML
    private ImageView femaleDefaultUserProfile;
    @FXML
    private ImageView maleDefaultUserProfile;
    private static int USERID;
    @FXML
    private TextField fName;
    @FXML
    private TextField mName;
    @FXML
    private TextField lName;
    @FXML
    private ComboBox sex;
    @FXML
    private DatePicker dateofBirth;
    @FXML
    private TextField phoneNum;
    @FXML
    private TextField address;
    @FXML
    private TextField manageusername;
    @FXML
    private PasswordField managePassword;
    @FXML
    private TextField managetextField;
    @FXML
    private ImageView manageeye;
    @FXML
    private ImageView manageeyeSlash;

    @FXML
    private Button savechangesManageProfile;
    @FXML
    private ImageView closeManageProfile;
    @FXML
    private ImageView lockProfile;
    @FXML
    private ImageView unlockProfile;
    @FXML
    private GridPane manageProfile;
    @FXML
    private Button ManageProfileButton;
    @FXML
    private GridPane profilePicturePart;
    @FXML
    private ImageView inputmanageeyeSlash;
    @FXML
    private ImageView inputmanageeye;
    @FXML
    private TextField inputmanagetextField;
    @FXML
    private PasswordField inputmanagePassword;
    @FXML
    private TextField inputmanageusername;
    @FXML
    private TextField inputaddress;
    @FXML
    private TextField inputphoneNum;
    @FXML
    private DatePicker inputdateofBirth;
    @FXML
    private ComboBox inputsex;
    @FXML
    private TextField inputfirstName;
    @FXML
    private TextField inputmiddleName;
    @FXML
    private TextField inputlastName;
    @FXML
    private GridPane administratorPharmacistMenu;
    @FXML
    private GridPane administratorusersMenu;
    @FXML
    private BorderPane part2;
    @FXML
    private MenuItem adminMedicinePage;
    @FXML
    private MenuItem adminUserPage;
    @FXML
    private MenuItem adminPharmacyPage;

    @FXML
    private TextField inputPharmacyName;
    @FXML
    private TextField inputOwnerName;
    @FXML
    private ComboBox inputphonenumber;
    @FXML
    private TextField inputPharmacyphoneNum;
    @FXML
    private TextField inputPharmacyaddress;
    @FXML
    private Button deletePharmacy;
    @FXML
    private Button clearPharmacy;
    @FXML
    private Button updatePharmacy;
    @FXML
    private Button addPharmacy;
    @FXML
    private GridPane administratorPharmacyModifyMenu;
    @FXML
    private TextField medicineID;
    @FXML
    private TextField medicineBrand;
    @FXML
    private TextField medicineName;
    @FXML
    private ComboBox medicineType;
    @FXML
    private TextField medicinePrice;

    @FXML
    private BorderPane forAdministratorOnly;
    @FXML
    private TableView<MEDICINEPHARMACY> administratorMedicineTable;
    @FXML
    private ObservableList<MEDICINEPHARMACY> administratormedicineObservableList;
    @FXML
    private TableView<SOLDMEDICINE> soldMedicineTableview;
    @FXML
    private ObservableList<SOLDMEDICINE> soldmedicineObservableList;
    @FXML
    private TableView<PHARMACY> administratorPharmacyTable;
    @FXML
    private GridPane administratorPharmacymodifyPart;
    @FXML
    private ComboBox inputPosition;

    private ObservableList<PHARMACY> pharmacyTableObservableList;
    @FXML
    private TableView<USERTABLE> administratorUserTable;
    @FXML
    private ObservableList<USERTABLE> USERTABLEObservableList;
    @FXML
    private TextField searchUseruserNameSE;
    @FXML
    private TextField UserAdressSE;
    @FXML
    private TextField userUserNameSE;
    @FXML
    private TextField PhonenumberSE;
    @FXML
    private TextField UserFNameSE;
    @FXML
    private TextField UserIDSE;
    @FXML
    private TextField medicineIDSE;
    @FXML
    private TextField medicineBrandSE;
    @FXML
    private TextField medicineNameSE;
    @FXML
    private ComboBox medicineTypeSE;
    @FXML
    private TextField medicinePriceSE;
    @FXML
    private Button searcheMedicine;
    @FXML
    private TextField pharmacyIDSE;
    @FXML
    private TextField pharmacyNameSE;
    @FXML
    private TextField pharmacyAdressSE;
    @FXML
    private TextField pharmacyphonenumberSE;
    @FXML
    private TextField pharmacyOwnerNameSE;
    @FXML
    private TextField pharmacyIDSE0;


    public void initialize() {
        if (USER.getUserSex().equals("Male")) {
            maleDefaultUserProfile.setVisible(true);
            femaleDefaultUserProfile.setVisible(false);
        } else if (USER.getUserSex().equals("Female")) {
            maleDefaultUserProfile.setVisible(false);
            femaleDefaultUserProfile.setVisible(true);
        }
        TableMedicinePharmacyViewDisplay();
        TableSoldMedicinePharmacyViewDisplay();
        TablePharmacyViewDisplay();
        TableUserViewDisplay();
    }

    public void TableUserViewDisplay() {
        TableColumn<PHARMACY, Integer> pharmacyId = new TableColumn<>("User ID");
        pharmacyId.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<PHARMACY, String> userPosition = new TableColumn<>("User Authority");
        userPosition.setCellValueFactory(new PropertyValueFactory<>("userPosition"));

        TableColumn<PHARMACY, String> userFirstName = new TableColumn<>("User First Name");
        userFirstName.setCellValueFactory(new PropertyValueFactory<>("userFirstName"));

        TableColumn<PHARMACY, String> userMiddleName = new TableColumn<>("User Middle Name");
        userMiddleName.setCellValueFactory(new PropertyValueFactory<>("userMiddleName"));

        TableColumn<PHARMACY, String> userLastName = new TableColumn<>("User Last tName");
        userLastName.setCellValueFactory(new PropertyValueFactory<>("userLastName"));

        TableColumn<PHARMACY, String> userSex = new TableColumn<>("Sex");
        userSex.setCellValueFactory(new PropertyValueFactory<>("userSex"));

        TableColumn<PHARMACY, String> userDateOfBirth = new TableColumn<>("Birth-Date");
        userDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("userDateOfBirth"));

        TableColumn<PHARMACY, String> userPhoneNumber = new TableColumn<>("Phone number");
        userPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("userPhoneNumber"));

        TableColumn<PHARMACY, String> userAdress = new TableColumn<>("User Adress");
        userAdress.setCellValueFactory(new PropertyValueFactory<>("userAdress"));


        USERTABLEObservableList = FXCollections.observableArrayList();

        getUserTableView();

        administratorUserTable.setItems(USERTABLEObservableList);

    }

    public void getUserTableView() {
        Connection conn = DATABASE.getConnection();
        String input = "SELECT * From users";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(input);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                USERTABLE user = new USERTABLE(rs.getInt("User_id"), rs.getString("User_postion")
                        , rs.getString("First_Name"), rs.getString("Middle_Name"),
                        rs.getString("Last_Name"), rs.getString("Sex"),
                        rs.getString("Birth_Date"), rs.getString("PhoneNumber"),
                        rs.getString("User_Adress"), rs.getString("User_password"));


                USERTABLEObservableList.add(user);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "get value  for Pharmacy Medicine", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                //System.out.println("get medicinepharmacy table");
                //System.out.println(e.getMessage());
            }
        }
    }

    public void TablePharmacyViewDisplay() {
        TableColumn<PHARMACY, Integer> pharmacyId = new TableColumn<>("Pharmacy ID");
        pharmacyId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));

        TableColumn<PHARMACY, String> pharmacyName = new TableColumn<>("Pharmacy Name");
        pharmacyName.setCellValueFactory(new PropertyValueFactory<>("pharmacyName"));

        TableColumn<PHARMACY, String> pharmacyOwnerName = new TableColumn<>("Pharmacy Owner-name");
        pharmacyOwnerName.setCellValueFactory(new PropertyValueFactory<>("pharmacyOwnerName"));

        TableColumn<PHARMACY, String> pharmacyAdress = new TableColumn<>("Pharmacy Adress");
        pharmacyAdress.setCellValueFactory(new PropertyValueFactory<>("pharmacyAdress"));

        TableColumn<PHARMACY, String> pharmacyPhoneNumber = new TableColumn<>("Phone number");
        pharmacyPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("pharmacyPhoneNumber"));

        pharmacyTableObservableList = FXCollections.observableArrayList();
        getPharmacyTableView();

        administratorPharmacyTable.setItems(pharmacyTableObservableList);
    }

    public void getPharmacyTableView() {
        Connection conn = DATABASE.getConnection();
        String input = "SELECT * From pharmacy";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(input);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PHARMACY phy = new PHARMACY(rs.getInt("Pharmacy_id"), rs.getString("Pharmacy_name")
                        , rs.getString("Pharmacy_ownername"), rs.getInt("Pharmacist_id"),
                        rs.getString("Pharmacy_Adress"), rs.getString("PhoneNumber"));

                pharmacyTableObservableList.add(phy);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "get value  for Pharmacy Medicine", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                //System.out.println("get medicinepharmacy table");
                //System.out.println(e.getMessage());
            }
        }
    }

    private void TableSoldMedicinePharmacyViewDisplay() {
        TableColumn<MEDICINEPHARMACY, Integer> medicineId = new TableColumn<>("Sold Medicine ID");
        medicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));

        TableColumn<MEDICINEPHARMACY, String> medicineName = new TableColumn<>("Sold Medicine Name");
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));


        TableColumn<MEDICINEPHARMACY, Float> medicinePrice = new TableColumn<>("Sold Medicine Price");
        medicinePrice.setCellValueFactory(new PropertyValueFactory<>("medicinePrice"));


        TableColumn<MEDICINEPHARMACY, Integer> userId = new TableColumn<>("Buyer User ID");
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<MEDICINEPHARMACY, String> userName = new TableColumn<>("Buyer User Name");
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));


        TableColumn<MEDICINEPHARMACY, Integer> pharmacyId = new TableColumn<>("Pharmacy Id");
        pharmacyId.setCellValueFactory(new PropertyValueFactory<>("pharmacyId"));


        soldmedicineObservableList = FXCollections.observableArrayList();

        getSoldMedicineFromMEdicineTableForAdminstratorMedicineTable();

        soldMedicineTableview.setItems(soldmedicineObservableList);


    }

    @FXML
    private void getSoldMedicineFromMEdicineTableForAdminstratorMedicineTable() {

        Connection conn = DATABASE.getConnection();
        String input = "SELECT * From soldDrugs";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(input);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SOLDMEDICINE med = new SOLDMEDICINE(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                        rs.getString("Medicine_Brand"), rs.getString("Medicine_Type"), rs.getInt("Medicine_Quantity"),
                        rs.getFloat("Medicine_price"), rs.getString("Medicine_Expiry_date"),
                        rs.getString("Medicine_Description"), rs.getInt("Pharmacy_id"), rs.getString("Pharmacy_name")
                        , rs.getString("Pharmacy_ownername"), rs.getString("Pharmacy_Adress"), rs.getInt("Buyer_id"),
                        rs.getString("Buyer_Name"), rs.getString("Buyer_Adress"));

                soldmedicineObservableList.add(med);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "get value  for Pharmacy Medicine", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                //System.out.println("get medicinepharmacy table");
                //System.out.println(e.getMessage());
            }
        }


    }

    private void TableMedicinePharmacyViewDisplay() {
        TableColumn<MEDICINEPHARMACY, Integer> medicineId = new TableColumn<>("Medicine ID");
        medicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));

        TableColumn<MEDICINEPHARMACY, String> medicineName = new TableColumn<>("Medicine Name");
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));


        TableColumn<MEDICINEPHARMACY, Float> medicinePrice = new TableColumn<>("Medicine Price");
        medicinePrice.setCellValueFactory(new PropertyValueFactory<>("medicinePrice"));


        TableColumn<MEDICINEPHARMACY, Integer> pharmacyId = new TableColumn<>("Pharmacy ID");
        medicineId.setCellValueFactory(new PropertyValueFactory<>("pharmacyId"));

        TableColumn<MEDICINEPHARMACY, String> pharmacyName = new TableColumn<>("Pharmacy Name");
        medicineName.setCellValueFactory(new PropertyValueFactory<>("pharmacyName"));


        TableColumn<MEDICINEPHARMACY, String> pharmacyAddress = new TableColumn<>("Pharmacy Address");
        medicinePrice.setCellValueFactory(new PropertyValueFactory<>("pharmacyAddress"));


        administratorMedicineTable.getColumns().addAll(medicineId, medicineName, medicinePrice, pharmacyId, pharmacyName, pharmacyAddress);

        administratormedicineObservableList = FXCollections.observableArrayList();

        getMedicineFromMEdicineTableForAdminstratorMedicineTable();

        administratorMedicineTable.setItems(administratormedicineObservableList);


    }

    @FXML
    private void getMedicineFromMEdicineTableForAdminstratorMedicineTable() {

        Connection conn = DATABASE.getConnection();
        String input = "SELECT Medicines.Medicine_id, Medicines.Medicine_Name, Medicines.Medicine_price," +
                " pharmacy.Pharmacy_id, pharmacy.Pharmacy_name, pharmacy.Pharmacy_Adress FROM Medicines " +
                "JOIN pharmacy " +
                "ON Medicines.Pharmacy_id = pharmacy.Pharmacy_id";
        Statement stm = null;
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(input);

            while (rs.next()) {
                MEDICINEPHARMACY med = new MEDICINEPHARMACY(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                        rs.getFloat("Medicine_price"), rs.getInt("Pharmacy_id"), rs.getString("Pharmacy_name"),
                        rs.getString("Pharmacy_Adress"));

                administratormedicineObservableList.add(med);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "get value  for Pharmacy Medicine", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                //System.out.println("get medicinepharmacy table");
                //System.out.println(e.getMessage());
            }
        }


    }


    public void closeManageProfile(MouseEvent e) {
        if (administratorusersMenu.isVisible()) {
            searchbarUserPage.setVisible(true);
            searchbarPharmacyPage.setVisible(false);
            searchbarMedicinePage.setVisible(false);
        }
        if (administratorPharmacyModifyMenu.isVisible()) {
            searchbarUserPage.setVisible(false);
            searchbarPharmacyPage.setVisible(true);
            searchbarMedicinePage.setVisible(false);
        }
        if (administratorPharmacistMenu.isVisible()) {
            searchbarUserPage.setVisible(false);
            searchbarPharmacyPage.setVisible(false);
            searchbarMedicinePage.setVisible(true);
        }

        manageProfile.setVisible(false);
        fName.setDisable(true);
        mName.setDisable(true);
        lName.setDisable(true);
        sex.setDisable(true);
        dateofBirth.setDisable(true);
        address.setDisable(true);
        manageusername.setDisable(true);
        managePassword.setDisable(true);
        phoneNum.setDisable(true);
        savechangesManageProfile.setDisable(true);
        manageeyeSlash.setDisable(true);

    }

    public void managePassword(MouseEvent e) {
        if (e.getSource() == manageeyeSlash) {
            manageeye.setVisible(true);
            manageeyeSlash.setVisible(false);

            managePassword.setVisible(false);
            managetextField.setVisible(true);
            managetextField.setText(managePassword.getText());
        }
        if (e.getSource() == manageeye) {
            manageeye.setVisible(false);
            manageeyeSlash.setVisible(true);

            managetextField.setVisible(false);
            managePassword.setVisible(true);
            managePassword.setText(managetextField.getText());
        }
    }

    public void lockProfile(MouseEvent e) {
        if (e.getSource() == lockProfile) {
            lockProfile.setVisible(false);
            unlockProfile.setVisible(true);

            fName.setDisable(true);
            mName.setDisable(true);
            lName.setDisable(true);
            sex.setDisable(true);
            dateofBirth.setDisable(true);
            address.setDisable(true);
            manageusername.setDisable(true);
            managePassword.setDisable(true);
            phoneNum.setDisable(true);
            savechangesManageProfile.setDisable(true);
            manageeyeSlash.setDisable(true);
        }
        if (e.getSource() == unlockProfile) {
            lockProfile.setVisible(true);
            unlockProfile.setVisible(false);

            //System.out.println("unlock");
            fName.setDisable(false);
            mName.setDisable(false);
            lName.setDisable(false);
            sex.setDisable(false);
            dateofBirth.setDisable(false);
            address.setDisable(false);
            manageusername.setDisable(false);
            phoneNum.setDisable(false);
            managePassword.setDisable(false);
            savechangesManageProfile.setDisable(false);
            manageeyeSlash.setDisable(false);
        }

    }

    public void inputManagePassword(MouseEvent e) {
        if (e.getSource() == inputmanageeyeSlash) {
            inputmanageeye.setVisible(true);
            inputmanageeyeSlash.setVisible(false);

            inputmanagePassword.setVisible(false);
            inputmanagetextField.setVisible(true);
            inputmanagetextField.setText(inputmanagePassword.getText());
        }
        if (e.getSource() == inputmanageeye) {
            inputmanageeye.setVisible(false);
            inputmanageeyeSlash.setVisible(true);

            inputmanagetextField.setVisible(false);
            inputmanagePassword.setVisible(true);
            inputmanagePassword.setText(inputmanagetextField.getText());
        }
    }

    public void switchPage2(ActionEvent e) {

        if (e.getSource() == adminPharmacyPage) {
            administratorPharmacyModifyMenu.setVisible(true);
            part2.setCenter(administratorPharmacyModifyMenu);
            administratorPharmacistMenu.setVisible(false);
            administratorusersMenu.setVisible(false);

        }
        if (e.getSource() == adminUserPage) {
            administratorusersMenu.setVisible(true);
            part2.setCenter(administratorusersMenu);
            administratorPharmacistMenu.setVisible(false);
            administratorPharmacyModifyMenu.setVisible(false);

        }
        if (e.getSource() == adminMedicinePage) {
            administratorPharmacistMenu.setVisible(true);
            part2.setCenter(administratorPharmacistMenu);
            administratorusersMenu.setVisible(false);
            administratorPharmacyModifyMenu.setVisible(false);
            administratormedicineObservableList.clear();


            TableMedicinePharmacyViewDisplay();
            TableSoldMedicinePharmacyViewDisplay();
        }

        manageProfile.setVisible(false);
        fName.setDisable(true);
        mName.setDisable(true);
        lName.setDisable(true);
        sex.setDisable(true);
        dateofBirth.setDisable(true);
        address.setDisable(true);
        manageusername.setDisable(true);
        managePassword.setDisable(true);
        phoneNum.setDisable(true);
        savechangesManageProfile.setDisable(true);
        manageeyeSlash.setDisable(true);

        if (administratorusersMenu.isVisible()) {
            searchbarUserPage.setVisible(true);
            searchbarPharmacyPage.setVisible(false);
            searchbarMedicinePage.setVisible(false);
        }
        if (administratorPharmacyModifyMenu.isVisible()) {
            searchbarUserPage.setVisible(false);
            searchbarPharmacyPage.setVisible(true);
            searchbarMedicinePage.setVisible(false);
        }
        if (administratorPharmacistMenu.isVisible()) {
            searchbarUserPage.setVisible(false);
            searchbarPharmacyPage.setVisible(false);
            searchbarMedicinePage.setVisible(true);
        }
    }

    public void logout(MouseEvent event) throws IOException {
        forAdministratorOnly.setVisible(false);
        Stage stage = new Stage();
        stage.setWidth(forAdministratorOnly.getWidth());
        stage.setHeight(forAdministratorOnly.getHeight());
        Parent root = FXMLLoader.load(getClass().getResource("./../View/loginPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void manageProfile(ActionEvent e) {

        manageProfile.setVisible(true);
        searchbarUserPage.setVisible(false);
        searchbarMedicinePage.setVisible(false);
        searchbarPharmacyPage.setVisible(false);


        USERID = USER.getUserId();
        fName.setText(USER.getUserFirstName());
        mName.setText(USER.getUserMiddleName());
        lName.setText(USER.getUserLastName());
        sex.setValue(USER.getUserSex());
        dateofBirth.setValue(USER.getUserDateOfBirth());
        phoneNum.setText(USER.getUserPhoneNumber());
        address.setText(USER.getUserAdress());
        manageusername.setText(USER.getUserUserName());
        managePassword.setText(USER.getUserPassword());


        if (managetextField == null)
            managetextField.setText(managePassword.getText());


    }


    public void savechanges(ActionEvent event) {

        DATABASE.UpdateuserInfo(USERID, fName.getText(), mName.getText(), lName.getText(), String.valueOf(sex.getSelectionModel().getSelectedItem()),
                String.valueOf(dateofBirth.getValue()), phoneNum.getText(), address.getText(), manageusername.getText()
                , managePassword.getText(),"administrator");

        if (String.valueOf(sex.getSelectionModel().getSelectedItem()).equals("Male")) {
            maleDefaultUserProfile.setVisible(true);
            femaleDefaultUserProfile.setVisible(false);
        }
        if (String.valueOf(sex.getSelectionModel().getSelectedItem()).equals("Female")) {
            maleDefaultUserProfile.setVisible(false);
            femaleDefaultUserProfile.setVisible(true);
        }
        USERTABLEObservableList.clear();
        TableUserViewDisplay();
    }


    public void resetValues(ActionEvent event) {
        if (administratorPharmacistMenu.isVisible()) {
            medicineIDSE.setText("");
            medicineNameSE.setText("");
            medicinePriceSE.setText("");
            pharmacyIDSE0.setText("");
            pharmacyNameSE.setText("");
            pharmacyAdressSE.setText("");
            administratormedicineObservableList.clear();
            TableMedicinePharmacyViewDisplay();
            soldmedicineObservableList.clear();
            TableSoldMedicinePharmacyViewDisplay();
        }
        if (administratorPharmacyTable.isVisible()) {
            UserIDSE.setText("");
            UserAdressSE.setText("");
            UserFNameSE.setText("");
            PhonenumberSE.setText("");
            userUserNameSE.setText("");

            USERTABLEObservableList.clear();
            TableUserViewDisplay();
        }
        if (administratorPharmacymodifyPart.isVisible()) {
            pharmacyIDSE.setText("");
            pharmacyNameSE.setText("");
            pharmacyphonenumberSE.setText("");
            pharmacyAdressSE.setText("");
            pharmacyOwnerNameSE.setText("");

            pharmacyTableObservableList.clear();
            TablePharmacyViewDisplay();
        }
    }

    public void getSelectedPharmacyRowValue(MouseEvent event) {
        PHARMACY pha = administratorPharmacyTable.getSelectionModel().getSelectedItem();
        if (pha != null) {
            inputPharmacyName.setText(pha.getPharmacyName());
            inputOwnerName.setText(pha.getPharmacyOwnerName());
            inputPharmacyaddress.setText(pha.getPharmacyAdress());
            inputPharmacyphoneNum.setText(pha.getPharmacyPhoneNumber());
        }
    }

    public void searchMedicine(ActionEvent event) {
        List<String> parameter = new ArrayList<>();
        StringBuilder input1 = new StringBuilder("SELECT m.Medicine_id, m.Medicine_Name, m.Medicine_price,p.Pharmacy_id," +
                " p.Pharmacy_name, p.Pharmacy_Adress FROM Medicines m" +
                " JOIN pharmacy p ON m.Pharmacy_id = p.Pharmacy_id where 1=1 ");
        StringBuilder input2 = new StringBuilder("Select s.* From soldDrugs s where 1=1 ");
        if (medicineIDSE != null && !medicineIDSE.getText().trim().isBlank()) {
            input1.append("AND m.Medicine_id=? ");
            input2.append("AND s.Medicine_id=? ");
            parameter.add(medicineIDSE.getText().trim());
        }
        if (medicineNameSE != null && !medicineNameSE.getText().trim().isBlank()) {
            input1.append("AND m.Medicine_Name=? ");
            input2.append("AND s.Medicine_Name=? ");
            parameter.add(medicineNameSE.getText().trim());
        }
        if (pharmacyIDSE0 != null && !pharmacyIDSE0.getText().trim().isBlank()) {
            input1.append("AND p.Pharmacy_id=? ");
            input2.append("AND s.Pharmacy_id=? ");
            parameter.add(pharmacyIDSE0.getText().trim());
        }
        //
        if (medicinePriceSE != null && !medicinePriceSE.getText().trim().isEmpty()) {
            input1.append("AND m.Medicine_price<=? ");
            input2.append("AND s.Medicine_price<=? ");
            parameter.add(String.valueOf(medicinePriceSE.getText().trim()));
        }
        SearchMedicineTableView(parameter, input1);
        SearchSoldMedicineTableView(parameter, input2);

    }

    private void SearchSoldMedicineTableView(List<String> parameter, StringBuilder input2) {

        int id = -1;
        Connection conn = DATABASE.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(input2.toString());
            for (int i = 0; i < parameter.size(); i++) {
                ps.setString(i + 1, parameter.get(i));
            }

            ////System.out.println("Medicine Searched Successfully ");


            ResultSet rs = ps.executeQuery();

            //                id = rs.getInt(1);

            ////System.out.println("SearchID: " + id);
            SOLDMEDICINE med = null;
            soldmedicineObservableList.clear();
            while (rs.next()) {
                id = rs.getInt("Medicine_id");

                med = new SOLDMEDICINE(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                        rs.getString("Medicine_Brand"), rs.getString("Medicine_Type"), rs.getInt("Medicine_Quantity"),
                        rs.getFloat("Medicine_price"), rs.getString("Medicine_Expiry_date"),
                        rs.getString("Medicine_Description"), rs.getInt("Pharmacy_id"), rs.getString("Pharmacy_name"),
                        rs.getString("Pharmacy_ownername"), rs.getString("Pharmacy_Adress"),
                        rs.getInt("Buyer_id"), rs.getString("Buyer_Name"), rs.getString("Buyer_Adress"));

                soldmedicineObservableList.add(med);


            }
            if (med != null)
                if (med.getMedicineId() == 0 && med.getMedicineName().trim().isEmpty() && med.getMedicineBrand().trim().isEmpty() &&
                        String.valueOf(medicineType.getSelectionModel().getSelectedItem()).trim().isEmpty() && med.getMedicinePrice() == 0.0
                        && med.getMedicineExpirydate().isEmpty() && med.getMedicineDescription().isEmpty()
                        && med.getMedicineQuantity() == 1 && med.getPharmacyId() == 0 && med.getPharmacyName().trim().isEmpty() &&
                        med.getPharmacyOwnerName().trim().isEmpty() && med.getPharmacyAdress().trim().isEmpty() &&
                        med.getUserName().trim().isEmpty() && med.getMedicineId() == 0 && med.getUserAdress().trim().isEmpty()) {

                    soldmedicineObservableList.clear();
                    TableSoldMedicinePharmacyViewDisplay();

                    ////System.out.println("type:" + medicineType.getSelectionModel().getSelectedItem().toString() + ":");
                }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Sold adminstrator Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Sold adminstrator Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
            ////System.out.println("Medicine Sold adminstrator connection closed");
        }

    }

    private void SearchMedicineTableView(List<String> parameter, StringBuilder input) {
        int id = -1;
        Connection conn = DATABASE.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(input.toString());
            for (int i = 0; i < parameter.size(); i++) {
                ps.setString(i + 1, parameter.get(i));
            }

            //System.out.println("Medicine Searched Successfully ");


            ResultSet rs = ps.executeQuery();

            //                id = rs.getInt(1);

            //System.out.println("SearchID: " + id);
            MEDICINEPHARMACY med = null;
            administratormedicineObservableList.clear();
            while (rs.next()) {
                id = rs.getInt("Medicine_id");

                med = new MEDICINEPHARMACY(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                        rs.getFloat("Medicine_price"), rs.getInt("Pharmacy_id"),
                        rs.getString("Pharmacy_name"), rs.getString("Pharmacy_Adress"));

                administratormedicineObservableList.add(med);

            }
            if (med != null)
                if (med.getMedicineId() == 0 && med.getMedicineName().trim().isEmpty() && med.getMedicinePrice() == 0.0 && med.getPharmacyId() == 0 &&
                        med.getPharmacyName().isEmpty() && med.getPharmacyAddress().trim().isEmpty()) {
                    administratormedicineObservableList.clear();
                    TableMedicinePharmacyViewDisplay();
                }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
            //System.out.println("Medicine deleting connection closed");
        }


    }

    public void searchUsers(ActionEvent event) {
        List<String> parameter = new ArrayList<>();
        StringBuilder input = new StringBuilder("SELECT * From users where 1=1 ");
        if (UserIDSE != null && !UserIDSE.getText().trim().isEmpty()) {
            input.append("AND User_id=? ");
            parameter.add(UserIDSE.getText().trim());
        }
        if (UserFNameSE != null && !UserFNameSE.getText().trim().isEmpty()) {
            input.append("AND First_Name=? ");
            parameter.add(UserFNameSE.getText().trim());
        }
        if (PhonenumberSE != null && !PhonenumberSE.getText().trim().isEmpty()) {
            input.append("AND PhoneNumber=? ");
            parameter.add(PhonenumberSE.getText().trim());
        }
        if (UserAdressSE != null && !UserAdressSE.getText().trim().isEmpty()) {
            input.append("AND User_Adress=? ");
            parameter.add(String.valueOf(UserAdressSE.getText().trim()));
        }
        if (userUserNameSE != null && !userUserNameSE.getText().trim().isEmpty()) {
            input.append("AND User_name=? ");
            parameter.add(String.valueOf(userUserNameSE.getText().trim()));
        }
        SearchUserTableView(parameter, input);

    }

    private void SearchUserTableView(List<String> parameter, StringBuilder input) {
        int id = -1;
        Connection conn = DATABASE.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(input.toString());
            for (int i = 0; i < parameter.size(); i++) {
                ps.setString(i + 1, parameter.get(i));
            }


            ResultSet rs = ps.executeQuery();

            //                id = rs.getInt(1);

            USERTABLE user = null;
            USERTABLEObservableList.clear();
            while (rs.next()) {

                user = new USERTABLE(rs.getInt("User_id"), rs.getString("User_postion"),
                        rs.getString("First_Name"), rs.getString("Middle_Name"),
                        rs.getString("Last_Name"), rs.getString("Sex"),
                        rs.getString("Birth_Date"), rs.getString("PhoneNumber"),
                        rs.getString("User_Adress"), rs.getString("User_name"));

                USERTABLEObservableList.add(user);

            }
            if (user != null)
                if (user.getUserId() == 0 && user.getUserFirstName().trim().isBlank() && user.getUserMiddleName().trim().isBlank() &&
                        user.getUserLastName().trim().isBlank() && user.getUserSex().trim().isBlank() && user.getUserDateOfBirth().trim().isEmpty() &&
                        user.getUserPhoneNumber().trim().isBlank() && user.getUserAdress().trim().isEmpty() && user.getUserUserName().isEmpty()) {
                    USERTABLEObservableList.clear();
                    TableUserViewDisplay();
                }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void searchPharmacy(ActionEvent event) {
        List<String> parameter = new ArrayList<>();
        StringBuilder input = new StringBuilder("SELECT * From pharmacy where 1=1 ");
        if (pharmacyIDSE != null && !pharmacyIDSE.getText().isBlank()) {
            input.append("AND Pharmacy_id=? ");
            parameter.add(pharmacyIDSE.getText().trim());
        }
        if (pharmacyNameSE != null && !pharmacyNameSE.getText().isBlank()) {
            input.append("AND Pharmacy_name=? ");
            parameter.add(pharmacyNameSE.getText().trim());
        }
        if (pharmacyOwnerNameSE != null && !pharmacyOwnerNameSE.getText().isBlank()) {
            input.append("AND Pharmacy_ownername=? ");
            parameter.add(pharmacyOwnerNameSE.getText().trim());
        }
        if (pharmacyphonenumberSE != null && !pharmacyphonenumberSE.getText().isBlank()) {
            input.append("AND PhoneNumber=? ");
            parameter.add(String.valueOf(pharmacyphonenumberSE.getText().trim()));
        }
        if (pharmacyAdressSE != null && !pharmacyAdressSE.getText().isBlank()) {
            input.append("AND Pharmacy_Adress=? ");
            parameter.add(String.valueOf(pharmacyAdressSE.getText().trim()));
        }
        SearchPharmacyTableView(parameter, input);
    }

    public void searchUser(ActionEvent event) {
        List<String> parameter = new ArrayList<>();
        StringBuilder input = new StringBuilder("SELECT * From users where 1=1 ");
        if (UserIDSE != null && !UserIDSE.getText().isBlank()) {
            input.append("AND User_id=? ");
            parameter.add(UserIDSE.getText().trim());
        }
        if (UserFNameSE != null && !UserFNameSE.getText().isBlank()) {
            input.append("AND First_Name=? ");
            parameter.add(UserFNameSE.getText().trim());
        }
        if (PhonenumberSE != null && !PhonenumberSE.getText().isBlank()) {
            input.append("AND PhoneNumber=? ");
            parameter.add(PhonenumberSE.getText().trim());
        }
        if (UserAdressSE != null && !UserAdressSE.getText().isBlank()) {
            input.append("AND User_Adress=? ");
            parameter.add(UserAdressSE.getText().trim());
        }
        if (userUserNameSE != null && !userUserNameSE.getText().isBlank()) {
            input.append("AND User_name=? ");
            parameter.add(userUserNameSE.getText().trim());
        }
        SearchPharmacyForTableView(parameter, input);
    }

    private void SearchPharmacyForTableView(List<String> parameter, StringBuilder input) {
        int id = -1;
        Connection conn = DATABASE.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(input.toString());
            for (int i = 0; i < parameter.size(); i++) {
                ps.setString(i + 1, parameter.get(i));
            }


            ResultSet rs = ps.executeQuery();


            USERTABLE user = null;
            USERTABLEObservableList.clear();
            while (rs.next()) {

                user = new USERTABLE(rs.getInt("User_id"), rs.getString("User_postion"), rs.getString("First_Name"),
                        rs.getString("Middle_Name"), rs.getString("Last_Name"), rs.getString("Sex"),
                        rs.getString("Birth_Date"), rs.getString("PhoneNumber"), rs.getString("User_Adress"),
                        rs.getString("User_name"));

                USERTABLEObservableList.add(user);

            }
            if (user != null)
                if (user.getUserId() == 0 && user.getUserFirstName().trim().isBlank() && user.getUserPosition().trim().isBlank() && user.getUserMiddleName().trim().isEmpty()
                        && user.getUserLastName().trim().isBlank() && user.getUserSex().trim().isEmpty() && user.getUserDateOfBirth().trim().isEmpty() && user.getUserPhoneNumber().trim().isEmpty()
                        && user.getUserUserName().trim().isEmpty() && user.getUserAdress().trim().isEmpty()) {
                    USERTABLEObservableList.clear();
                    TableUserViewDisplay();
                }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "User Table Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "User TableDATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void SearchPharmacyTableView(List<String> parameter, StringBuilder input) {
        int id = -1;
        Connection conn = DATABASE.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(input.toString());
            for (int i = 0; i < parameter.size(); i++) {
                ps.setString(i + 1, parameter.get(i));
            }


            ResultSet rs = ps.executeQuery();

            //                id = rs.getInt(1);

            PHARMACY pha = null;
            pharmacyTableObservableList.clear();
            while (rs.next()) {

                pha = new PHARMACY(rs.getInt("Pharmacy_id"), rs.getString("Pharmacy_name"),
                        rs.getString("Pharmacy_ownername"), rs.getInt("Pharmacist_id"),
                        rs.getString("Pharmacy_Adress"), rs.getString("PhoneNumber"));

                pharmacyTableObservableList.add(pha);

            }
            if (pha != null)
                if (pha.getPharmacyId() == 0 && pha.getPharmacyName().trim().isBlank() && pha.getPharmacyOwnerName().trim().isBlank() &&
                        pha.getPharmacistId() == 0 && pha.getPharmacyAdress().trim().isBlank() && pha.getPharmacyPhoneNumber().trim().isEmpty()) {
                    pharmacyTableObservableList.clear();
                    TablePharmacyViewDisplay();
                }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void addPharmacy(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adding Pharmacy");
        alert.setHeaderText("Are you sure you want to Add this Pharmacy?");
        alert.setContentText("Pharmacy-Name: " + inputPharmacyName.getText());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int phid = DATABASE.adminRegisteringPharmacyInDatabase(inputPharmacyName.getText(), inputOwnerName.getText(),
                    -1, inputPharmacyaddress.getText(), inputPharmacyphoneNum.getText());

            PHARMACY pha = new PHARMACY(phid, inputPharmacyName.getText(), inputOwnerName.getText(), -1, inputPharmacyaddress.getText(), inputPharmacyphoneNum.getText());
            pharmacyTableObservableList.add(pha);
            administratorPharmacyTable.refresh();
            clearPharmacy(e);
        }
    }

    public void updatePharmacy(ActionEvent e) {
        PHARMACY pha = administratorPharmacyTable.getSelectionModel().getSelectedItem();
        if (pha != null) {
            int phaid = pha.getPharmacyId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Updating Pharmacy Information");
            alert.setHeaderText("Are you sure you want to Update this pharmacies information ?");
            alert.setContentText("Pharmacy-ID: " + String.valueOf(pha.getPharmacyId()) + "\n" + "Pharmacy-Name: " + pha.getPharmacyName());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                DATABASE.updaingPharmacyInfoInDatabase(phaid, inputPharmacyName.getText(), inputOwnerName.getText(), pha.getPharmacistId(),
                        inputPharmacyaddress.getText(), inputPharmacyphoneNum.getText());

                pharmacyTableObservableList.removeIf(pharmacy -> pharmacy.getPharmacyId() == phaid);


                PHARMACY phar = new PHARMACY(phaid, inputPharmacyName.getText(), inputOwnerName.getText(), -1, inputPharmacyaddress.getText(), inputPharmacyphoneNum.getText());
                pharmacyTableObservableList.add(phar);
                administratorPharmacyTable.refresh();
                clearPharmacy(e);
            }
        }
    }

    public void deletePharmacy(ActionEvent e) {
        PHARMACY pha = administratorPharmacyTable.getSelectionModel().getSelectedItem();
        if (pha != null) {
            int phaid = pha.getPharmacyId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting Pharmacy Information");
            alert.setHeaderText("Are you sure you want to Delete This Pharmacies From The System ?");
            alert.setContentText("Pharmacy-ID: " + String.valueOf(pha.getPharmacyId()) + "\n" + "Pharmacy-Name: " + pha.getPharmacyName());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                DATABASE.DeletingPharmacyInDatabase(phaid);
                pharmacyTableObservableList.removeIf(pharmacy -> pharmacy.getPharmacyId() == phaid);


                administratorPharmacyTable.refresh();
                clearPharmacy(e);
            }
        }


    }

    public void clearPharmacy(ActionEvent e) {
        inputPharmacyName.setText("");
        inputPharmacyaddress.setText("");
        inputOwnerName.setText("");
        inputPharmacyphoneNum.setText("");
    }

    public void getSelectedUserRowValue(MouseEvent event) {
        USERTABLE user = administratorUserTable.getSelectionModel().getSelectedItem();
        if (user != null) {
            inputfirstName.setText(user.getUserFirstName());
            inputmiddleName.setText(user.getUserMiddleName());
            inputlastName.setText(user.getUserLastName());
            inputsex.setValue(user.getUserSex());
            inputdateofBirth.setValue(LocalDate.parse(user.getUserDateOfBirth()));
            inputphoneNum.setText(user.getUserPhoneNumber());
            inputaddress.setText(user.getUserAdress());
            inputmanageusername.setText(user.getUserUserName());
            inputPosition.setValue(user.getUserPosition());
        }
    }

    public void addUser(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adding User");
        alert.setHeaderText("Are you sure you want to Add this User?");
        alert.setContentText("User-Name: " + inputfirstName.getText());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (inputmanagePassword.getText().isBlank())
                inputmanagePassword.setText(inputmanagetextField.getText());
            try {
                int userid = DATABASE.registeringUserInDatabase(inputPosition.getSelectionModel().getSelectedItem().toString(), inputfirstName.getText(), inputmiddleName.getText(), inputlastName.getText(), inputsex.getSelectionModel().getSelectedItem().toString()
                        , inputdateofBirth.getValue().toString(), inputphoneNum.getText(), inputaddress.getText(), inputmanageusername.getText(), inputmanagePassword.getText());

                USERTABLE user = new USERTABLE(userid, inputPosition.getSelectionModel().getSelectedItem().toString(), inputfirstName.getText(), inputmiddleName.getText(), inputlastName.getText(), inputsex.getSelectionModel().getSelectedItem().toString()
                        , inputdateofBirth.getValue().toString(), inputphoneNum.getAccessibleHelp(), inputaddress.getText(), inputmanageusername.getText());

                USERTABLEObservableList.add(user);
                administratorUserTable.refresh();
                clearUser(e);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public void updateUser(ActionEvent e) {
        USERTABLE user = administratorUserTable.getSelectionModel().getSelectedItem();
        if (user != null) {
            int userid = user.getUserId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Updating User Information");
            alert.setHeaderText("Are you sure you want to Update this User information ?");
            alert.setContentText("User-ID: " + userid + "\n" + "User-Name: " + user.getUserFirstName());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                DATABASE.UpdateuserInfo(userid, inputfirstName.getText(), inputmiddleName.getText(), inputlastName.getText(),
                                                inputsex.getSelectionModel().getSelectedItem().toString(), inputdateofBirth.getValue().toString(),
                                                inputphoneNum.getText(), inputaddress.getText(), inputmanageusername.getText(),
                                                inputmanagePassword.getText(), inputPosition.getSelectionModel().getSelectedItem().toString());

                USERTABLEObservableList.clear();
                TableUserViewDisplay();
                clearUser(e);

            }}

            }

    public void clearUser (ActionEvent e){
                inputfirstName.setText("");
                inputmiddleName.setText("");
                inputlastName.setText("");
                inputsex.getSelectionModel().clearSelection();
                inputdateofBirth.setValue(null);
                inputphoneNum.setText("");
                inputaddress.setText("");
                inputmanageusername.setText("");
                inputPosition.getSelectionModel().clearSelection();
            }

            public void deleteUser (ActionEvent e){
                USERTABLE user = administratorUserTable.getSelectionModel().getSelectedItem();
                if (user != null) {
                    int userid = user.getUserId();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Deleting User Information");
                    alert.setHeaderText("Are you sure you want to Delete this User information ?");
                    alert.setContentText("User-ID: " + userid + "\n" + "User-Name: " + user.getUserFirstName());

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {

                        DATABASE.DeleteUser(userid);

                        USERTABLEObservableList.clear();
                        TableUserViewDisplay();
                        clearUser(e);
                    }}
            }
        }



