package Controllers;

import Model.DATABASE;
import Model.MEDICINE;
import Model.SOLDMEDICINE;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class userController {
    private int PHARMACYID;

    public TableView<MEDICINE> UsersMedicineTable;
    public ObservableList<MEDICINE> observableMedicineList;
    public ImageView femaleDefaultUserProfile;
    public ImageView maleDefaultUserProfile;
    public TableView<SOLDMEDICINE> UsersSoldMedicineTable;
    public ObservableList<SOLDMEDICINE> observableSoldMedicineList;

    private int USERID;
    public Button ManageProfileButton;
    public GridPane manageProfile;
    public ImageView manageeyeSlash;
    public ImageView manageeye;
    public TextField managetextField;

    public TextField medicineID;
    public TextField medicineName;
    public ComboBox medicineType;
    public TextField medicinePrice;
    
    public ImageView closeManageProfile;
 

    public TextField phoneNum;
    public TextField address;
    public TextField username;
    public PasswordField managePassword;
    public ComboBox sex;
    public TextField lName;
    public TextField mName;
    public TextField fName;
    public GridPane profilePicturePart;
    public GridPane searchbar;
    public ScrollPane scrollbar;
    public Button cancelchangesManageProfile;
    public Button savechangesManageProfile;
    public TextField manageusername;
    public TextField medicineBrand;
    public ImageView lockProfile;
    public ImageView unlockProfile;
    public DatePicker dateofBirth;

    public GridPane forUsersOnly;

public void initialize(){
    if (USER.getUserSex().equals("Male")){
        maleDefaultUserProfile.setVisible(true);
        femaleDefaultUserProfile.setVisible(false);
    }else if (USER.getUserSex().equals("Female")){
        maleDefaultUserProfile.setVisible(false);
        femaleDefaultUserProfile.setVisible(true);
    }
    TableViewMedicineDisplay();
    TableViewSoldMedicineDisplay();
}

    private void TableViewMedicineDisplay() {
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


        observableMedicineList = FXCollections.observableArrayList();

        getMedicineFromMEdicineTableForUsersMedicineTable();

        UsersMedicineTable.setItems(observableMedicineList);

    }
    @FXML
    private void getMedicineFromMEdicineTableForUsersMedicineTable() {

        Connection conn = DATABASE.getConnection();
        String input = "SELECT * FROM Medicines ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(input);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MEDICINE med = new MEDICINE(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                        rs.getString("Medicine_Brand"), rs.getString("Medicine_Type"), rs.getInt("Medicine_Quantity"),
                        rs.getFloat("Medicine_price"), rs.getString("Medicine_Expiry_date"),
                        rs.getString("Medicine_Description"),rs.getInt("Pharmacy_id"));


                observableMedicineList.add(med);

               PHARMACYID=med.getPharmacyId();

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

    private void TableViewSoldMedicineDisplay() {
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


        observableSoldMedicineList = FXCollections.observableArrayList();

        getMedicineFromMEdicineTableForUsersSoldMedicineTable();

        UsersSoldMedicineTable.setItems(observableSoldMedicineList);

    }

    @FXML
    private void getMedicineFromMEdicineTableForUsersSoldMedicineTable() {

        Connection conn = DATABASE.getConnection();
        String input = "SELECT * FROM soldDrugs ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(input);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SOLDMEDICINE med = new SOLDMEDICINE(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                        rs.getString("Medicine_Brand"), rs.getString("Medicine_Type"), rs.getInt("Medicine_Quantity"),
                        rs.getFloat("Medicine_price"), rs.getString("Medicine_Expiry_date"),
                        rs.getString("Medicine_Description"),rs.getInt("Pharmacy_id"),rs.getString("Pharmacy_name")
                        ,rs.getString("Pharmacy_ownername"),rs.getString("Pharmacy_Adress"),rs.getInt("Buyer_id"),
                        rs.getString("Buyer_Name"),rs.getString("Buyer_Adress"));

                observableSoldMedicineList.add(med);

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

    public void manageProfile(ActionEvent e) {

            manageProfile.setVisible(true);
            searchbar.setVisible(false);
            USERID= USER.getUserId();
fName.setText(USER.getUserFirstName());
mName.setText(USER.getUserMiddleName());
lName.setText(USER.getUserLastName());
sex.setValue(USER.getUserSex());
dateofBirth.setValue(USER.getUserDateOfBirth());
phoneNum.setText(USER.getUserPhoneNumber());
address.setText(USER.getUserAdress());
manageusername.setText(USER.getUserUserName());
managePassword.setText(USER.getUserPassword());

if (managetextField==null)
    managetextField.setText(managePassword.getText());





    }

    public void closeManageProfile(MouseEvent e) {
            manageProfile.setVisible(false);
            searchbar.setVisible(true);
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
        if (e.getSource()==lockProfile){
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
        if (e.getSource()==unlockProfile){
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




    public void savechanges(ActionEvent event) {

        DATABASE.UpdateuserInfo(USERID, fName.getText(), mName.getText(), lName.getText(), String.valueOf(sex.getSelectionModel().getSelectedItem()),
                String.valueOf(dateofBirth.getValue()), phoneNum.getText(), address.getText(), manageusername.getText()
                , managePassword.getText(),"user");

        if (String.valueOf(sex.getSelectionModel().getSelectedItem()).equals("Male")) {
            maleDefaultUserProfile.setVisible(true);
            femaleDefaultUserProfile.setVisible(false);
        } else if (USER.getUserSex().equals("Female")) {
            maleDefaultUserProfile.setVisible(false);
            femaleDefaultUserProfile.setVisible(true);
        }
    }

    public void logout(MouseEvent event) throws IOException {
        forUsersOnly.setVisible(false);
        Stage stage=new Stage();
        stage.setWidth(forUsersOnly.getWidth());
        stage.setHeight(forUsersOnly.getHeight());
        Parent root= FXMLLoader.load(getClass().getResource("./../View/loginPage.fxml"));
        stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void getSelectedRowValue(MouseEvent event) {
        MEDICINE med = UsersMedicineTable.getSelectionModel().getSelectedItem();
        if (med != null) {
            medicineID.setText(String.valueOf(med.getMedicineId()));
            medicineBrand.setText(med.getMedicineBrand());
           medicineName.setText(med.getMedicineName());
            medicineType.setValue(med.getMedicineType());
            medicinePrice.setText(String.valueOf(med.getMedicinePrice()));
             }

    }

    public void resetValues(ActionEvent event) {
        medicineBrand.setText("");
        medicineName.setText("");
        medicineType.getSelectionModel().clearSelection();
        medicinePrice.setText("");
        medicineID.setText("");
        observableMedicineList.clear();
        TableViewMedicineDisplay();
    }

    public void searchMedicine(ActionEvent event) {
        List<String> parameter = new ArrayList<>();
        StringBuilder input = new StringBuilder("Select * From Medicines where 1=1 ");
        if (medicineID != null && !medicineID.getText().trim().isBlank()) {
                input.append("AND Medicine_id=? ");
            parameter.add(medicineID.getText().trim());
        }
        if (medicineName != null && !medicineName.getText().trim().isBlank()) {
            input.append("AND Medicine_Name=? ");
            parameter.add(medicineName.getText().trim());
        }
        if (medicineBrand != null && !medicineBrand.getText().trim().isBlank()) {
            input.append("AND Medicine_Brand=? ");
            parameter.add(medicineBrand.getText().trim());
        }
//        if (String.valueOf(medicineType.getSelectionModel().getSelectedItem())==""){
//            medicineType.getSelectionModel().clearSelection();
//        }
        if (String.valueOf(medicineType.getSelectionModel().getSelectedItem()).isEmpty())
           medicineType.setValue(null);
        if (String.valueOf(medicineType.getSelectionModel().getSelectedItem()).trim() != null && String.valueOf(medicineType.getSelectionModel().getSelectedItem()).isBlank()) {
            input.append("AND Medicine_Type=? ");
            parameter.add(String.valueOf(medicineType.getSelectionModel().getSelectedItem()));
        }
        if (medicinePrice != null && !medicinePrice.getText().isEmpty()) {
            input.append("AND Medicine_price<=? ");
            parameter.add(String.valueOf(medicinePrice.getText().trim()));
        }
        SearchMedicineTableView(parameter, input);
        for (String i : parameter) {
            ////System.out.println(parameter);
            ////System.out.println(input);
        }

    }

    private void SearchMedicineTableView(List<String> parameter, StringBuilder input) {
        int id = -1;
        Connection conn = DATABASE.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(input.toString());
            for (int i = 0; i < parameter.size(); i++) {
                ps.setString(i+1, parameter.get(i));
            }

            ////System.out.println("Medicine Searched Successfully ");


            ResultSet rs = ps.executeQuery();

//                id = rs.getInt(1);

            //System.out.println("SearchID: "+id);
            MEDICINE med =null;
            observableMedicineList.clear();
            while (rs.next()) {
                id = rs.getInt("Medicine_id");

                med = new MEDICINE(rs.getInt("Medicine_id"), rs.getString("Medicine_Name"),
                        rs.getString("Medicine_Brand"), rs.getString("Medicine_Type"), rs.getInt("Medicine_Quantity"),
                        rs.getFloat("Medicine_price"), rs.getString("Medicine_Expiry_date"),
                        rs.getString("Medicine_Description"),rs.getInt("Pharmacy_id"));

                observableMedicineList.add(med);



            }
            if (med!=null)
            if (med.getMedicineId()==0 &&med.getMedicineName().trim().isEmpty()&&med.getMedicineBrand().trim().isEmpty()&&
                    String.valueOf(medicineType.getSelectionModel().getSelectedItem()).trim().isEmpty() &&med.getMedicinePrice()==0.0 &&med.getMedicineExpirydate().isEmpty()&&med.getMedicineDescription().isEmpty()
                    &&med.getMedicineQuantity()==1){
                observableMedicineList.clear();
                TableViewMedicineDisplay();
                //System.out.println("type:"+medicineType.getSelectionModel().getSelectedItem().toString()+":");
            }

//
//            if (med.getMedicineId()==-1||med.getMedicineName().trim().isEmpty()||med.getMedicineBrand().trim().isEmpty()||
//                    med.getMedicineType().isEmpty() ||med.getMedicinePrice()==0.0 ||med.getMedicineExpirydate().isEmpty()||med.getMedicineDescription().isEmpty()
//                    ||med.getMedicineQuantity()==1){
//                observableList.clear();
//            }
//

//            else{
//                observableList.clear();
////                observableList.addAll(med);
//            }



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


    public void buyMedicine(ActionEvent event) {
        MEDICINE med = UsersMedicineTable.getSelectionModel().getSelectedItem();
       String PHARMACYNAME=null;
       String PHARMACYOWNER=null;
       String PHARMACYADRESS=null;
        if (med != null) {
            int medid = med.getMedicineId();
            //System.out.println("buyed medicine id: "+medid);
            int phaId=med.getPharmacyId();
            //System.out.println("phamedid: "+phaId);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Buying Medicine");
            alert.setHeaderText("Are you sure you want to Buy this medicine?");
            alert.setContentText("Medicine-ID: " + String.valueOf(med.getMedicineId()) + "\n" + "Medicine-Name: " + med.getMedicineName());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Connection conn=DATABASE.getConnection();
                PreparedStatement ps2=null;


                //getting the pharmacy info
                try {

                    String phaInput="Select * from pharmacy where Pharmacy_id=?";
                    ps2=conn.prepareStatement(phaInput);
                    ps2.setInt(1,phaId);

                    ResultSet rs=ps2.executeQuery();
                    if (rs.next()){
                        PHARMACYNAME=rs.getString(2);
                        PHARMACYOWNER=rs.getString(3);
                        PHARMACYADRESS=rs.getString(5);
                         }
                    conn.close();
                }
                catch (SQLException e) {
                    //System.out.println("searching pharmacry");
                    //System.out.println(e.getMessage());
                }



                //System.out.println("expiry date: "+med.getMedicineExpirydate());
                //updating the medicine quantity value
                DATABASE.updaingMedicineInDatabase(med.getMedicineName(),med.getMedicineBrand(),med.getMedicineType(),(med.getMedicineQuantity()-1),
                        med.getMedicinePrice(),med.getMedicineExpirydate(),med.getMedicineDescription(),med.getPharmacyId(),med.getMedicineId());

                observableMedicineList.removeIf(medicine -> medicine.getMedicineId() == medid);


                MEDICINE medicine = new MEDICINE(med.getMedicineId(),med.getMedicineName(),med.getMedicineBrand(),med.getMedicineType(),
                        (med.getMedicineQuantity()-1), med.getMedicinePrice(),med.getMedicineExpirydate(),
                        med.getMedicineDescription(),med.getPharmacyId());
                observableMedicineList.add(medicine);
                UsersMedicineTable.refresh();



                //redistering the sold medicine to the database

               boolean choose=DATABASE.registeringSoldMedicineInDatabase(med.getMedicineId(),med.getMedicineName(),med.getMedicineBrand(),med.getMedicineType(),1
                ,med.getMedicinePrice(),med.getMedicineExpirydate(),med.getMedicineDescription(),phaId,PHARMACYNAME,PHARMACYOWNER,PHARMACYADRESS,
                        USER.getUserId(), USER.getUserFirstName(), USER.getUserAdress());


                SOLDMEDICINE soldmed = new SOLDMEDICINE(med.getMedicineId(),med.getMedicineName(),med.getMedicineBrand(),med.getMedicineType(),1
                        ,med.getMedicinePrice(),String.valueOf(med.getMedicineExpirydate()),med.getMedicineDescription(),phaId,PHARMACYNAME,PHARMACYOWNER,PHARMACYADRESS,
                        USER.getUserId(), USER.getUserFirstName(), USER.getUserAdress());

                if (choose) {
                    observableSoldMedicineList.clear();
                    TableViewSoldMedicineDisplay();
                }else {
                    observableSoldMedicineList.add(soldmed);
                    UsersSoldMedicineTable.refresh();

                }



            }
        }
    }
}
