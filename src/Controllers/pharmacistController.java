package Controllers;

import Model.DATABASE;
import Model.MEDICINE;
import Model.USER;
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

public class pharmacistController {
    public TextField searchMedicineID;
    public TextField searchMedicineBrand;
    public TextField searchMedicineName;
    public ComboBox searchMedicineType;
    public TextField searchMedicinePrice;
    public Button SearchMedicineButton;
    @FXML
    private TableView<MEDICINE> pharmacistMedicineTable;
    private ObservableList<MEDICINE> observableList;
    private int pharmacistPharmacyID;
    public ImageView maleDefaultUserProfile;
    public ImageView femaleDefaultUserProfile;
    public TextField modifyMedicineID;
    public TextField modifyMedicineBrand;
    public TextField modifyMedicineName;
    public ComboBox modifyMedicineType;
    public TextField modifyMedicinePrice;
    public DatePicker modifyMedicineExpiryDate;
    public TextArea modifymedicineDescription;

    public Button addMedicineButton;
    public Button updateMedicineButton;
    public Button clearMedicineButton;
    public Button deleteMedicineButton;
    public Spinner modifyMedicineQuantity;
    private int USERID;

    public GridPane profilePicturePart;
    public GridPane manageProfile;
    public GridPane searchbar;
    public ImageView closeManageProfile;
    public TextField fName;
    public TextField mName;
    public TextField lName;
    public ComboBox sex;
    public TextField phoneNum;
    public TextField address;
    public TextField manageusername;
    public PasswordField managePassword;
    public TextField managetextField;
    public ImageView manageeye;
    public ImageView manageeyeSlash;

    public Button savechangesManageProfile;

    public Button ManageProfileButton;
    public ImageView unlockProfile;
    public ImageView lockProfile;
    public DatePicker dateofBirth;
    public BorderPane forPharmacistOnly;
    public ImageView manageProfileImagePane;


    public void initialize() {

        pharmacistPharmacyID = DATABASE.searchPharmacistsPharmacyid(USER.getUserId());


        if (USER.getUserSex().equals("Male")) {
            maleDefaultUserProfile.setVisible(true);
            femaleDefaultUserProfile.setVisible(false);
        } else if (USER.getUserSex().equals("Female")) {
            maleDefaultUserProfile.setVisible(false);
            femaleDefaultUserProfile.setVisible(true);
        }

        TableViewDisplay();
    }

    private void TableViewDisplay() {
        TableColumn<MEDICINE, Integer> medicineId = new TableColumn<>("Medicine ID");
        medicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));

        TableColumn<MEDICINE, String> medicineName = new TableColumn<>("Medicine Name");
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));


        TableColumn<MEDICINE, String> medicineBrand = new TableColumn<>("Medicine Brand");
        medicineBrand.setCellValueFactory(new PropertyValueFactory<>("medicineBrand"));


        TableColumn<MEDICINE, String> medicineType = new TableColumn<>("Medicine Type");
        medicineType.setCellValueFactory(new PropertyValueFactory<>("medicineType"));


        TableColumn<MEDICINE, String> medicineQuantity = new TableColumn<>("Medicine Quantity");
        medicineQuantity.setCellValueFactory(new PropertyValueFactory<>("medicineQuantity"));


        TableColumn<MEDICINE, String> medicinePrice = new TableColumn<>("Medicine Price");
        medicinePrice.setCellValueFactory(new PropertyValueFactory<>("medicinePrice"));


        TableColumn<MEDICINE, String> medicineExpirydate = new TableColumn<>("Medicine_Expiry_date");
        medicineExpirydate.setCellValueFactory(new PropertyValueFactory<>("medicineExpirydate"));


        TableColumn<MEDICINE, String> medicineDescription = new TableColumn<>("Medicine Description");
        medicineDescription.setCellValueFactory(new PropertyValueFactory<>("medicineDescription"));

        pharmacistMedicineTable.getColumns().addAll(medicineId, medicineName, medicineBrand, medicineType, medicineQuantity,
                medicinePrice, medicineExpirydate, medicineDescription);

        observableList = FXCollections.observableArrayList();

        getMedicineFromMEdicineTableForPharmacyMedicineTable();

        pharmacistMedicineTable.setItems(observableList);


    }

    @FXML
    private void getMedicineFromMEdicineTableForPharmacyMedicineTable() {

        Connection conn = DATABASE.getConnection();
        String input = "SELECT * FROM Medicines where Pharmacy_id=?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(input);
            ps.setInt(1, pharmacistPharmacyID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MEDICINE med = new MEDICINE(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                        rs.getString("Medicine_Brand"), rs.getString("Medicine_Type"), rs.getInt("Medicine_Quantity"),
                        rs.getFloat("Medicine_price"), rs.getString("Medicine_Expiry_date"),
                        rs.getString("Medicine_Description"),rs.getInt("Pharmacy_id"));


                observableList.add(med);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "get value  for Pharmacy Medicine", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                //System.out.println("get medicine table");
                //System.out.println(e.getMessage());
            }
        }


    }

    public void closeManageProfile(MouseEvent e) {
        manageProfile.setVisible(false);
        searchbar.setVisible(true);

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

    public void logout(MouseEvent event) throws IOException {
        forPharmacistOnly.setVisible(false);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("./../View/loginPage.fxml"));
        stage.setWidth(forPharmacistOnly.getWidth());
        stage.setHeight(forPharmacistOnly.getHeight());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void manageProfile(ActionEvent e) {

        manageProfile.setVisible(true);
        searchbar.setVisible(false);
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
                , managePassword.getText(),"pharmacist");

        if (String.valueOf(sex.getSelectionModel().getSelectedItem()).equals("Male")) {
            maleDefaultUserProfile.setVisible(true);
            femaleDefaultUserProfile.setVisible(false);
        } else if (USER.getUserSex().equals("Female")) {
            maleDefaultUserProfile.setVisible(false);
            femaleDefaultUserProfile.setVisible(true);
        }
    }

    public void clearValues(ActionEvent event) {
        modifyMedicineBrand.setText("");
        modifyMedicineName.setText("");
        modifyMedicineType.getSelectionModel().clearSelection();
        modifyMedicinePrice.setText("");
        modifyMedicineExpiryDate.setValue(null);
        modifyMedicineQuantity.getValueFactory().setValue(1);
        modifymedicineDescription.setText("");
        observableList.clear();
        TableViewDisplay();
    }



    public void addmedicine(ActionEvent e) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adding Medicine");
        alert.setHeaderText("Are you sure you want to Add this medicine?");
        alert.setContentText("Medicine-Name: " + modifyMedicineName.getText());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int medid = DATABASE.registeringMedicineInDatabase(modifyMedicineName.getText(), modifyMedicineBrand.getText(), String.valueOf(modifyMedicineType.getSelectionModel().getSelectedItem()),
                    Integer.valueOf(modifyMedicineQuantity.getValue().toString()), Float.parseFloat(modifyMedicinePrice.getText()), String.valueOf(modifyMedicineExpiryDate.getValue()), modifymedicineDescription.getText(),pharmacistPharmacyID);


            MEDICINE med = new MEDICINE(medid, modifyMedicineName.getText(), modifyMedicineBrand.getText(), String.valueOf(modifyMedicineType.getSelectionModel().getSelectedItem()),
                    Integer.valueOf(modifyMedicineQuantity.getValue().toString()), Float.parseFloat(modifyMedicinePrice.getText()), String.valueOf(modifyMedicineExpiryDate.getValue()), modifymedicineDescription.getText(),pharmacistPharmacyID);
            observableList.add(med);
            pharmacistMedicineTable.refresh();
            clearValues(e);
        }


    }

    public void updateMedicine(ActionEvent e) {
        MEDICINE med = pharmacistMedicineTable.getSelectionModel().getSelectedItem();
        if (med != null) {
            int medid = med.getMedicineId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Updating Medicine");
            alert.setHeaderText("Are you sure you want to Update this medicine?");
            alert.setContentText("Medicine-ID: " + med.getMedicineId() + "\n" + "Medicine-Name: " + med.getMedicineName());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                DATABASE.updaingMedicineInDatabase(modifyMedicineName.getText(), modifyMedicineBrand.getText(), String.valueOf(modifyMedicineType.getSelectionModel().getSelectedItem()),
                        Integer.valueOf(modifyMedicineQuantity.getValue().toString()), Float.parseFloat(modifyMedicinePrice.getText()), String.valueOf(modifyMedicineExpiryDate.getValue()),
                        modifymedicineDescription.getText(),  pharmacistPharmacyID, medid);

                observableList.removeIf(medicine -> medicine.getMedicineId() == medid);


                MEDICINE medicine = new MEDICINE(medid, modifyMedicineName.getText(), modifyMedicineBrand.getText(), String.valueOf(modifyMedicineType.getSelectionModel().getSelectedItem()),
                        Integer.valueOf(modifyMedicineQuantity.getValue().toString()), Float.parseFloat(modifyMedicinePrice.getText()), String.valueOf(modifyMedicineExpiryDate.getValue()), modifymedicineDescription.getText(),pharmacistPharmacyID);
                observableList.add(medicine);
                pharmacistMedicineTable.refresh();

                clearValues(e);

            }
        }
    }

    public void deleteMedicine(ActionEvent e) {

        MEDICINE med = pharmacistMedicineTable.getSelectionModel().getSelectedItem();
        if (med != null) {
            int medid = med.getMedicineId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting Medicine");
            alert.setHeaderText("Are you sure you want to permanently delete this medicine?");
            alert.setContentText("Medicine-ID: " + med.getMedicineId() + "\n" + "Medicine-Name: " + med.getMedicineName());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DATABASE.DeletingMedicineInDatabase(medid);
               TableViewDisplay();
                clearValues(e);
            }

        }
    }


    public void getSelectedRowValue(MouseEvent event) {
        MEDICINE med = pharmacistMedicineTable.getSelectionModel().getSelectedItem();
        if (med != null) {
            modifyMedicineBrand.setText(med.getMedicineBrand());
            modifyMedicineName.setText(med.getMedicineName());
            modifyMedicineType.setValue(med.getMedicineType());
            modifyMedicinePrice.setText(String.valueOf(med.getMedicinePrice()));
            modifyMedicineExpiryDate.setValue(LocalDate.parse(med.getMedicineExpirydate()));
            modifymedicineDescription.setText(med.getMedicineDescription());
            modifyMedicineQuantity.getValueFactory().setValue(med.getMedicineQuantity());
        }
    }


    public void searchMedicine(ActionEvent event) {
        List<String> parameter = new ArrayList<>();
        StringBuilder input = new StringBuilder("Select * From Medicines where 1=1 ");
        if (searchMedicineID != null && !searchMedicineID.getText().trim().isBlank()) {
            input.append("AND Medicine_id=? ");
            parameter.add(searchMedicineID.getText().trim());
        }
        if (searchMedicineName != null && !searchMedicineName.getText().trim().isBlank()) {
            input.append("AND Medicine_Name=? ");
            parameter.add(searchMedicineName.getText().trim());
        }
        if (searchMedicineBrand != null && !searchMedicineBrand.getText().trim().isBlank()) {
            input.append("AND Medicine_Brand=? ");
            parameter.add(searchMedicineBrand.getText().trim());
        }
        if (String.valueOf(searchMedicineType.getSelectionModel().getSelectedItem()).trim() != null && searchMedicineType.getSelectionModel().getSelectedItem() != null) {
            input.append("AND Medicine_Type=? ");
            parameter.add(String.valueOf(searchMedicineType.getSelectionModel().getSelectedItem()));
        }
        if (searchMedicinePrice != null && !searchMedicinePrice.getText().isEmpty()) {
            input.append("AND Medicine_price<=? ");
            parameter.add(searchMedicinePrice.getText().trim());
        }
        SearchMedicineTableView(parameter, input);


    }

    private void SearchMedicineTableView(List<String> parameter, StringBuilder input) {
//        int id = -1;
        String lastQuery=" AND Pharmacy_id=?";
        input.append(lastQuery);
        Connection conn = DATABASE.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(input.toString());
            for (int i = 0; i < parameter.size(); i++) {
                ps.setString(i+1, parameter.get(i));
            }
            ps.setInt(parameter.size()+1,pharmacistPharmacyID);

            //System.out.println("Medicine Searched Successfully ");


            ResultSet rs = ps.executeQuery();

//                id = rs.getInt(1);


            MEDICINE med =null;
            observableList.clear();
            while (rs.next()) {
//                    id = rs.getInt("Medicine_id");

                     med = new MEDICINE(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                            rs.getString("Medicine_Brand"), rs.getString("Medicine_Type"), rs.getInt("Medicine_Quantity"),
                            rs.getFloat("Medicine_price"), rs.getString("Medicine_Expiry_date"),
                            rs.getString("Medicine_Description"),rs.getInt("Pharmacy_id"));
                //System.out.println("besu "+rs.getInt("Pharmacy_id"));
                     observableList.add(med);

                if (med.getMedicineId()==-1&&med.getMedicineName().trim().isEmpty()&&med.getMedicineBrand().trim().isEmpty()&&
                        String.valueOf(modifyMedicineType.getSelectionModel().getSelectedItem()).trim().isEmpty() &&med.getMedicinePrice()==0.0 &&med.getMedicineExpirydate().isEmpty()&&med.getMedicineDescription().isEmpty()
                        &&med.getMedicineQuantity()==1){
                    observableList.clear();
                    TableViewDisplay();
                    //System.out.println("type:"+modifyMedicineType.getSelectionModel().getSelectedItem().toString()+":");
                }
//return id;
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
//return id;

        }














}





/*Model.DATABASE.registeringMedicineInDatabase(modifyMedicineName.getText(),modifyMedicineBrand.getText(),String.valueOf(modifyMedicineType.getSelectionModel().getSelectedItem()),
        (Integer) modifyMedicineQuantity.getValue(),Float.parseFloat(modifyMedicinePrice.getText()),String.valueOf(modifyMedicineExpiryDate.getValue()),modifymedicineDescription.getText(),Model.USER.getUserId(), Model.USER.getUserFirstName(), Model.USER.getUserMiddleName(),
        Model.USER.getUserAdress(),Model.USER.getUserId(), Model.USER.getUserFirstName(), Model.USER.getUserAdress());
        }*/