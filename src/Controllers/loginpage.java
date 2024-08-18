package Controllers;

import Model.DATABASE;
import Model.USER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class loginpage {

    public double StageWidth;
    public double StageHeight;
    public GridPane signupPage0;
    public GridPane signupPage1;
    public GridPane firstLoginPage;
    public ImageView eyeLoginPagePasswordField;
    public ImageView eyeSlashLoginPagePasswordField;
    public PasswordField loginPasswordField;
    public TextField loginTextPasswordField;

    public Button administratorPostionButton;
    public Button pharmacistPostionButton;
    public Button userPostionButton;
    public TextField firstNameTextField;
    public Button prevSignupButton1;
    public Button nextSignupButton1;
    public Button cancelSignupButton1;
    public DatePicker dateOfBirth;
    public TextField phoneNumber;
    public ComboBox sex;
    public TextField usernameField;
    public PasswordField signupPasswordField;
    public TextField signupTextPasswordField;
    public PasswordField signupConfirmPasswordField;
    public TextField signupTextConfirmPasswordField;
    public ImageView eyesignupPasswordField;
    public ImageView eyeSlashsignupPasswordField;
    public ImageView eyesignupConfirmPasswordField;
    public ImageView eyeSlashsignupConfirmPasswordField;
    public Button prevSignupButton3;
    public Button nextSignupButton3;
    public Button cancelSignupButton3;
    public Button prevSignupButton2;
    public Button nextSignupButton2;
    public Button cancelSignupButton2;
    public TextField adress;
    public GridPane signupPage2;
    public GridPane signupPage3;
    public GridPane signupPage4;

    public CheckBox checkInformationValidity;
    public Button prevSignupButton4;
    public Button nextSignupButton4;
    public Button cancelSignupButton4;

    public String postion = null;
    public Button cancelSignupButton0;
    public ImageView eyeSlashverifyAuthorityPasswordField;
    public ImageView eyeverifyAuthorityPasswordField;
    public TextField verifyAuthorityTextField;
    public PasswordField verifyAuthorityPasswordField;
    public TextField companyName;
    public TextField ownerName;
    public Button nextVerifyPageButton;
    public Button prevVerifyPageButton;
    public Button cancelVerifyPageButton;
    public GridPane verifyAuthorityPage;
    public GridPane pharmacyInfoPage;
    public Button cancelPharmacyButton;
    public Button nextPharmacyButton;
    public Button prevPharmacyButton;
    public TextField middleNameTextField;
    public TextField lastNameTextField;
    public TextField loginUsernameField;


    @FXML
    private Button signinButton;
    @FXML
    private Button signupButton;
    @FXML
    private BorderPane pane;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView loginBackground;
    private int nextPageCount = 0;

    public void loginPagePassword(MouseEvent e) {
        if (e.getSource() == eyeSlashLoginPagePasswordField) {
            eyeLoginPagePasswordField.setVisible(true);
            eyeSlashLoginPagePasswordField.setVisible(false);

            loginPasswordField.setVisible(false);
            loginTextPasswordField.setVisible(true);
            loginTextPasswordField.setText(loginPasswordField.getText());
        }
        if (e.getSource() == eyeLoginPagePasswordField) {
            eyeLoginPagePasswordField.setVisible(false);
            eyeSlashLoginPagePasswordField.setVisible(true);

            loginPasswordField.setVisible(true);
            loginTextPasswordField.setVisible(false);
            loginPasswordField.setText(loginTextPasswordField.getText());
        }

    }

    public void signupPassword(MouseEvent e) {
        if (e.getSource() == eyeSlashsignupPasswordField) {
            eyesignupPasswordField.setVisible(true);
            eyeSlashsignupPasswordField.setVisible(false);

            signupPasswordField.setVisible(false);
            signupTextPasswordField.setVisible(true);
            signupTextPasswordField.setText(signupPasswordField.getText());
        }
        if (e.getSource() == eyesignupPasswordField) {
            eyesignupPasswordField.setVisible(false);
            eyeSlashsignupPasswordField.setVisible(true);

            signupPasswordField.setVisible(true);
            signupTextPasswordField.setVisible(false);
            signupPasswordField.setText(signupTextPasswordField.getText());
        }
    }

    public void verifyAuthorityCode(MouseEvent e) {
        if (e.getSource() == eyeSlashverifyAuthorityPasswordField) {
            eyeverifyAuthorityPasswordField.setVisible(true);
            eyeSlashverifyAuthorityPasswordField.setVisible(false);

            verifyAuthorityPasswordField.setVisible(false);
            verifyAuthorityTextField.setVisible(true);
            verifyAuthorityTextField.setText(verifyAuthorityPasswordField.getText());
        }
        if (e.getSource() == eyeverifyAuthorityPasswordField) {
            eyeverifyAuthorityPasswordField.setVisible(false);
            eyeSlashverifyAuthorityPasswordField.setVisible(true);

            verifyAuthorityPasswordField.setVisible(true);
            verifyAuthorityTextField.setVisible(false);
            verifyAuthorityPasswordField.setText(verifyAuthorityTextField.getText());
        }
    }

    public void signupConfirmPassword(MouseEvent e) {
        if (e.getSource() == eyeSlashsignupConfirmPasswordField) {
            eyesignupConfirmPasswordField.setVisible(true);
            eyeSlashsignupConfirmPasswordField.setVisible(false);

            signupConfirmPasswordField.setVisible(false);
            signupTextConfirmPasswordField.setVisible(true);
            signupTextConfirmPasswordField.setText(signupConfirmPasswordField.getText());
        }
        if (e.getSource() == eyesignupConfirmPasswordField) {
            eyesignupConfirmPasswordField.setVisible(false);
            eyeSlashsignupConfirmPasswordField.setVisible(true);

            signupConfirmPasswordField.setVisible(true);
            signupTextConfirmPasswordField.setVisible(false);
            signupConfirmPasswordField.setText(signupTextConfirmPasswordField.getText());
        }
    }

    public void initialize() {
        loginBackground.fitWidthProperty().bind(pane.widthProperty());
        loginBackground.fitHeightProperty().bind(pane.heightProperty());

//      ? signupPage1Background.fitWidthProperty().bind(pane.widthProperty());
//      signupPage1Background.fitHeightProperty().bind(pane.heightProperty());
    }

    public void signinOperation(ActionEvent e) throws IOException {


        Stage stage = new Stage();
        StageWidth=stage.getWidth();
        StageHeight= stage.getHeight();

        String postionIdentifier = DATABASE.signinCheck(loginUsernameField.getText().trim(), loginPasswordField.getText().trim());


        //System.out.println("user info");
        //System.out.println(USER.getUserId() + "\n" +
//                USER.getUserFirstName() + "\n" +    USER.getUserPosition() + "\n" +
//                USER.getUserSex() + "\n");
        //System.out.println(USER.getUserPosition());

        String resource="./../View/loginPage.fxml";
        switch (postionIdentifier) {
            case "user" -> resource= "./../View/userPage.fxml";
            case "pharmacist" ->resource= "./../View/pharmacistPage.fxml";
            case "administrator" ->resource= "./../View/administratorPage.fxml";
            default ->  JOptionPane.showMessageDialog(null, "INVALID USERNAME OR PASSWORD! \n           OR Model.USER DOESN'T EXIST!.", "Login Failure", JOptionPane.WARNING_MESSAGE);
        }

        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(resource))));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        if (StageWidth!=0 || StageHeight!=0){
            stage.setHeight(StageHeight);
            stage.setWidth(StageWidth);
        }else{
            stage.setWidth(pane.getWidth());
            stage.setHeight(pane.getHeight());
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }








    public void nextPharmacy(ActionEvent e) {
        if (e.getSource() == pharmacistPostionButton || e.getSource() == administratorPostionButton) {
            String pos = JOptionPane.showInputDialog("ENTER THE VERIFICATION CODE");

            if (pos != null) {
                if (pos.equals("pha@123")) {

                    postion="Pharmacist";

                } else if (pos.equals("admin@123")) {

                    postion="Administrator";
                }
            }
        }
        else if(e.getSource() ==userPostionButton){
            postion="User";
            nextPageCount+=1;
        }

    }

    public void switchSignUpPage(ActionEvent e){
        if (e.getSource()==prevSignupButton1 ||e.getSource()==prevSignupButton2||e.getSource()==prevSignupButton3
                ||e.getSource()==prevSignupButton4 || e.getSource()==prevPharmacyButton ||e.getSource()==prevVerifyPageButton){
            if (e.getSource()==prevPharmacyButton) {
                companyName.setText(null);
                ownerName.setText(null);
            }
            if (e.getSource()==prevVerifyPageButton) {
                verifyAuthorityTextField.setText(null);
                verifyAuthorityPasswordField.setText(null);
            }
            if (e.getSource()==prevSignupButton1){
                firstNameTextField.setText(null);
                middleNameTextField.setText(null);
                lastNameTextField.setText(null);
            }
            if (e.getSource()==prevSignupButton2){
                sex.setValue(null);
                dateOfBirth.setValue(null);
                phoneNumber.setText(null);
                adress.setText(null);
            }
            if (e.getSource()==prevSignupButton3){
                usernameField.setText(null);
                signupPasswordField.setText(null);
                signupTextPasswordField.setText(null);
                signupConfirmPasswordField.setText(null);
                signupTextConfirmPasswordField.setText(null);
            }
            if (e.getSource()==prevSignupButton4){
                checkInformationValidity.setSelected(false);
            }

            if (e.getSource()==prevSignupButton1  && postion.equals("user"))
                nextPageCount=nextPageCount-3;
            else if (e.getSource()==prevSignupButton1  && postion.equals("administrator"))
                nextPageCount=nextPageCount-3;
            else if (e.getSource()==prevSignupButton1  && postion.equals("pharmacist")){
                verifyAuthorityPage.setVisible(true);
                nextPageCount=nextPageCount-3;
            }  else if (e.getSource()==prevPharmacyButton  && postion.equals("pharmacist")){
                verifyAuthorityPage.setVisible(true);
                nextPageCount=nextPageCount-2;
            }
            else
                nextPageCount-=1;

            prevPageMethod();
        }

        else  if (e.getSource() == signupButton ||e.getSource()==nextSignupButton1 ||e.getSource()==nextSignupButton2
                ||e.getSource()==nextSignupButton3||e.getSource()==nextSignupButton4 || e.getSource()==nextVerifyPageButton
                ||e.getSource()==nextPharmacyButton) {

            if (e.getSource()==nextPharmacyButton){
                if (companyName.getText().isEmpty()|| ownerName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"PLEASE FULFILL THE FORM CORRECTLY !\n              REGISTERATION FAILED.\n",
                            "CONFIRM REGISTRATION",JOptionPane.ERROR_MESSAGE);}
                else{
                    nextPageCount+=1;
                    nextPageMethod();
                }
            }

            if (e.getSource()==nextSignupButton1){
                if (firstNameTextField.getText().isEmpty()|| middleNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"PLEASE FULFILL THE FORM CORRECTLY !\n              REGISTERATION FAILED.\n",
                            "CONFIRM REGISTRATION",JOptionPane.ERROR_MESSAGE);}
                else{
                    nextPageCount+=1;
                    nextPageMethod();
                }
            }

            if (e.getSource()==nextSignupButton2){
                if (sex.getSelectionModel().isEmpty()||phoneNumber.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"PLEASE FULFILL THE FORM CORRECTLY !\n              REGISTERATION FAILED.\n",
                            "CONFIRM REGISTRATION",JOptionPane.ERROR_MESSAGE);}
                else{
                    nextPageCount+=1;
                    nextPageMethod();
                }
            }

            if (e.getSource()==nextSignupButton3){
                if (usernameField.getText().isEmpty()||signupPasswordField.getText().isEmpty()
                        || signupConfirmPasswordField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"PLEASE FULFILL THE FORM CORRECTLY !\n              REGISTERATION FAILED.\n",
                            "CONFIRM REGISTRATION",JOptionPane.ERROR_MESSAGE);}
                else {
                    if (signupPasswordField.getText().trim().equals(signupConfirmPasswordField.getText().trim())){
                        nextPageCount += 1;
                        nextPageMethod();
                    }
                    else { JOptionPane.showMessageDialog(null, "PASSWORD DOESN'T MATCH  !\n       REGISTERATION FAILED.\n",
                            "AUTHENTICATION", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            if (e.getSource()==nextSignupButton4){
                if (!checkInformationValidity.isSelected()){
                    JOptionPane.showMessageDialog(null,"PLEASE FULFILL THE FORM CORRECTLY !\n              REGISTERATION FAILED.\n",
                            "CONFIRM REGISTRATION",JOptionPane.ERROR_MESSAGE);}
                else{
                    nextPageCount+=1;
                    nextPageMethod();
                    registerUser();
                }

            }

            if (e.getSource()==nextVerifyPageButton &&postion.equals("administrator")){
                if (verifyAuthorityPasswordField.getText().equals("admin@123")||verifyAuthorityTextField.getText().equals("admin@123")){
                    JOptionPane.showMessageDialog(null,"Verified Successfully","Authentication",JOptionPane.INFORMATION_MESSAGE);
                    nextPageCount=nextPageCount+2;
                    nextPageMethod();}
                else
                    JOptionPane.showMessageDialog(null,"Invalid Verification Code","Authentication",JOptionPane.WARNING_MESSAGE);
            }
            else if (e.getSource()==nextVerifyPageButton &&postion.equals("pharmacist")){

                if (verifyAuthorityPasswordField.getText().equals("pha@123")||verifyAuthorityTextField.getText().equals("pha@123")){
                    JOptionPane.showMessageDialog(null,"Verified Successfully","Authentication",JOptionPane.INFORMATION_MESSAGE);
                    nextPageCount+=1;
                    nextPageMethod();}
                else
                    JOptionPane.showMessageDialog(null,"Invalid Verification Code","Authentication",JOptionPane.WARNING_MESSAGE);
            }
            else if (e.getSource()==signupButton){

                nextPageCount+=1;
                nextPageMethod();
            }

        }


        else  if (e.getSource()==cancelSignupButton0 ||e.getSource()==cancelSignupButton1 ||e.getSource()==cancelSignupButton2
                ||e.getSource()==cancelSignupButton3||e.getSource()==cancelSignupButton4 ||e.getSource()==cancelPharmacyButton
                ||e.getSource()==cancelVerifyPageButton){
            nextPageCount=0;

            verifyAuthorityTextField.setText(null);
            verifyAuthorityPasswordField.setText(null);

            firstLoginPage.setVisible(true);
            pane.setCenter(firstLoginPage);
            signupPage0.setDisable(true);

            verifyAuthorityPage.setDisable(true);
            pharmacyInfoPage.setDisable(true);

            signupPage1.setDisable(true);
            signupPage2.setDisable(true);
            signupPage3.setDisable(true);
            signupPage4.setDisable(true);


            verifyAuthorityPage.setVisible(true);
            pharmacyInfoPage.setVisible(true);

            signupPage0.setVisible(true);
            signupPage1.setVisible(true);
            signupPage2.setVisible(true);
            signupPage3.setVisible(true);
            signupPage4.setVisible(true);


loginUsernameField.setText(null);
loginPasswordField.setText(null);
loginTextPasswordField.setText(null);

            companyName.setText(null);
            ownerName.setText(null);
            verifyAuthorityTextField.setText(null);
            verifyAuthorityPasswordField.setText(null);
            firstNameTextField.setText(null);
            middleNameTextField.setText(null);
            lastNameTextField.setText(null);
            sex.setValue(null);
            dateOfBirth.setValue(null);
            phoneNumber.setText(null);
            adress.setText(null);
            usernameField.setText(null);
            signupPasswordField.setText(null);
            signupTextPasswordField.setText(null);
            signupConfirmPasswordField.setText(null);
            signupTextConfirmPasswordField.setText(null);
            checkInformationValidity.setSelected(false);
        }

        else if(e.getSource() == administratorPostionButton){
            postion="administrator";
            nextPageCount+=1;
            nextPageMethod();
        }else if(e.getSource() == pharmacistPostionButton) {
            postion="pharmacist";
            nextPageCount+=1;
            nextPageMethod();
        }
        else if(e.getSource() ==userPostionButton){
            postion="user";
            nextPageCount=3;

            nextPageCount+=1;
            nextPageMethod();
        }
        ////System.out.println(nextPageCount);

    }

    private void registerUser(){
        try {
            int pharmacistid=DATABASE.registeringUserInDatabase(postion,firstNameTextField.getText(),middleNameTextField.getText(),lastNameTextField.getText()
                    ,String.valueOf(sex.getSelectionModel().getSelectedItem()),String.valueOf(dateOfBirth.getValue()),phoneNumber.getText(),adress.getText(),usernameField.getText(),
                    signupPasswordField.getText());

            if (postion.trim().equals("pharmacist")){
                ////System.out.println("pharmacist id: "+pharmacistid);
                DATABASE.registeringPharmacyInDatabase(companyName.getText(),ownerName.getText(),pharmacistid,adress.getText(),phoneNumber.getText());
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage()+"in the registration method","Model.DATABASE CONNECTION",JOptionPane.WARNING_MESSAGE);

        }



        companyName.setText(null);
        ownerName.setText(null);
        verifyAuthorityTextField.setText(null);
        verifyAuthorityPasswordField.setText(null);
        firstNameTextField.setText(null);
        middleNameTextField.setText(null);
        lastNameTextField.setText(null);
        sex.setValue(null);
        dateOfBirth.setValue(null);
        phoneNumber.setText(null);
        adress.setText(null);
        usernameField.setText(null);
        signupPasswordField.setText(null);
        signupTextPasswordField.setText(null);
        signupConfirmPasswordField.setText(null);
        signupTextConfirmPasswordField.setText(null);
        checkInformationValidity.setSelected(false);
    }

    private void prevPageMethod() {
        verifyAuthorityTextField.setText("");
        verifyAuthorityPasswordField.setText("");

        switch (nextPageCount) {
            case 0 -> {
                firstLoginPage.setVisible(true);
                pane.setCenter(firstLoginPage);
                signupPage0.setDisable(true);
            } case 1 -> {
                signupPage0.setVisible(true);
                pane.setCenter(signupPage0);
                verifyAuthorityPage.setDisable(true);
            }   case 2 -> {
                verifyAuthorityPage.setVisible(true);
                pane.setCenter(verifyAuthorityPage);
                pharmacyInfoPage.setDisable(true);
            }  case 3 -> {
                pharmacyInfoPage.setVisible(true);
                pane.setCenter(pharmacyInfoPage);
                signupPage1.setDisable(true);
            }case 4 -> {

                signupPage1.setVisible(true);
                pane.setCenter(signupPage1);
                signupPage2.setDisable(true);
            } case 5 -> {
                signupPage2.setVisible(true);
                pane.setCenter(signupPage2);
                signupPage3.setDisable(true);
            }
            case 6 -> {
                signupPage3.setVisible(true);
                pane.setCenter(signupPage3);
                signupPage4.setDisable(true);
            }case 7 -> {
                signupPage4.setVisible(true);
                pane.setCenter(signupPage4);
                signupPage4.setDisable(true);
            }
            case 8 -> {
                nextPageCount=0;

                signupPage4.setVisible(false);
                pane.setCenter(firstLoginPage);
                firstLoginPage.setVisible(true);

                verifyAuthorityPage.setVisible(true);
                pharmacyInfoPage.setVisible(true);

                signupPage0.setVisible(true);
                signupPage1.setVisible(true);
                signupPage2.setVisible(true);
                signupPage3.setVisible(true);
                signupPage4.setVisible(true);
            }
        }
    }

    private void nextPageMethod() {
        verifyAuthorityTextField.setText("");
        verifyAuthorityPasswordField.setText("");
        switch (nextPageCount) {

            case 1 -> {
                firstLoginPage.setVisible(false);
                pane.setCenter(signupPage0);
                signupPage0.setDisable(false);
            }  case 2 -> {

                signupPage0.setVisible(false);
                pane.setCenter(verifyAuthorityPage);
                verifyAuthorityPage.setDisable(false);
            }  case 3 -> {
                verifyAuthorityPage.setVisible(false);
                pane.setCenter(pharmacyInfoPage);
                pharmacyInfoPage.setDisable(false);
            }  case 4 -> {

                signupPage0.setVisible(false);
                pane.setCenter(signupPage1);
                signupPage1.setDisable(false);
            }  case 5 -> {
                signupPage1.setVisible(false);
                pane.setCenter(signupPage2);
                signupPage2.setDisable(false);
            }  case 6 -> {
                signupPage2.setVisible(false);
                pane.setCenter(signupPage3);
                signupPage3.setDisable(false);
            }case 7 -> {
                signupPage3.setVisible(false);
                pane.setCenter(signupPage4);
                signupPage4.setDisable(false);
            }
            case 8 -> {
                nextPageCount=0;

                signupPage4.setVisible(false);
                pane.setCenter(firstLoginPage);
                firstLoginPage.setVisible(true);



                verifyAuthorityPage.setVisible(true);
                pharmacyInfoPage.setVisible(true);

                signupPage0.setVisible(true);
                signupPage1.setVisible(true);
                signupPage2.setVisible(true);
                signupPage3.setVisible(true);
                signupPage4.setVisible(true);
            }
        }
    }



}

