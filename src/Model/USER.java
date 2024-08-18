package Model;

import java.time.LocalDate;

public class USER {

    private static int userId;
    private static String userPosition;
    private static String userFirstName;
    private static String userMiddleName;
    private static String userLastName;
    private static String userSex;
    private static LocalDate userDateOfBirth;
    private static String userPhoneNumber;
    private static String userAdress;
    private static String userUserName;
    private static String userPassword;


    public static int getUserId() {
        return userId;
    }
    public static String getUserPosition() {
        return userPosition;
    }

    public static String getUserFirstName() {
        return userFirstName;
    }
    public static String getUserMiddleName() {
        return userMiddleName;
    }
    public static String getUserLastName() {
        return userLastName;
    }
    public static String getUserSex() {
        return userSex;
    }
    public static LocalDate getUserDateOfBirth() {
        return userDateOfBirth;
    }
    public static String getUserPhoneNumber() {
        return userPhoneNumber;
    }
    public static String getUserAdress() {return userAdress;}
    public static String getUserUserName() {
        return userUserName;
    }
    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserInfo(int Id,String Position,String FirstName,String MiddleName,String LastName,String Sex,String DateOfBirth
    ,String PhoneNumber,String Adress,String UserName,String Password) {
        userId = Id;
        userPosition = Position;
        userFirstName = FirstName;
        userMiddleName = MiddleName;
        userLastName = LastName;
        userSex = Sex;
        userDateOfBirth = LocalDate.parse(DateOfBirth);
        userPhoneNumber = PhoneNumber;
        userAdress = Adress;
        userUserName = UserName;
        userPassword = Password;
    }

    public static void resetUserInfo() {
        userId =-1;
        userPosition = null;
        userFirstName = null;
        userMiddleName = null;
        userLastName = null;
        userSex = null;
        userDateOfBirth = null;
        userPhoneNumber = null;
        userAdress = null;
        userUserName = null;
        userPassword = null;
    }
    public USER(){
    }
}
