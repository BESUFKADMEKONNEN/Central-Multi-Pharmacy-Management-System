package Model;

import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class DATABASE {

    public static Connection getConnection() {


        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn =  DriverManager.getConnection("jdbc:sqlite:src/Database/PharmacySystem.db");

            ////System.out.println("database connection established successfully");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        }
        return conn;
    }

    public static void checkDirectory() {
        String directoryPath = "src/Database";
        String databasePath = "src/Database/PharmacySystem.db";

        File dir = new File(directoryPath);
        File db = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdir();
            //System.out.println("directory created");
        } else {
            //System.out.println("directory already exist");
        }

        if (!db.exists()) {
            try {
                db.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("database created");
        } else {
            //System.out.println("database already exist");
        }

        String tablesFormat1 = "create table IF NOT EXISTS users(" +
                "User_id INTEGER not null," +
                "User_postion varchar(20) ," +
                "First_Name varchar(20)," +
                "Middle_Name varchar(20)," +
                "Last_Name varchar(20)," +
                "Sex varchar(6)," +
                "Birth_Date date," +
                "PhoneNumber varchar(15)," +
                "User_Adress varchar(20)," +
                "User_name varchar(20) ," +
                "User_password text," +
                "primary key(User_id AUTOINCREMENT)  " +
                ");";
        String tablesFormat2 = "create table IF NOT EXISTS pharmacy(" +
                "Pharmacy_id INTEGER not null," +
                "Pharmacy_name varchar(20) not null," +
                "Pharmacy_ownername varchar(20) not null," +
                "Pharmacist_id INTEGER not null," +
                "Pharmacy_Adress varchar(20)," +
                "PhoneNumber varchar(15)," +
                "primary key(Pharmacy_id AUTOINCREMENT)" +
                ");";
        String tablesFormat3 = "create table IF NOT EXISTS Medicines(" +
                "Medicine_id INTEGER not null," +
                "Medicine_Name varchar(20)," +
                "Medicine_Brand varchar(20)," +
                "Medicine_Type varchar(20)," +
                "Medicine_Quantity int," +
                "Medicine_price float," +
                "Medicine_Expiry_date varchar(20)," +
                "Medicine_Description text," +
                "Pharmacy_id INTEGER not null," +
                "Primary key(Medicine_id AUTOINCREMENT));";
        String tablesFormat4 = "create table IF NOT EXISTS soldDrugs(" +
                "Medicine_id INTEGER not null," +
                "Medicine_Name varchar(20)," +
                "Medicine_Brand varchar(20)," +
                "Medicine_Type varchar(20)," +
                "Medicine_Quantity int," +
                "Medicine_price float," +
                "Medicine_Expiry_date varchar(20)," +
                "Medicine_Description text," +
                "Pharmacy_id INTEGER not null," +
                "Pharmacy_name varchar(20) not null," +
                "Pharmacy_ownername varchar(20) not null," +
                "Pharmacy_Adress varchar(20)," +
                "Buyer_id INTEGER not null," +
                "Buyer_Name varchar(20)," +
                "Buyer_Adress varchar(20)," +
                "primary key(Medicine_id )" +
                ");" +
                "ALTER TABLE users  Add CONSTRAINT uniqueUserInfo UNIQUE(User_id,First_Name,User_Adress);" +

                "ALTER TABLE soldDrugs Add CONSTRAINT fk_User_id FOREIGN KEY(Buyer_id,Buyer_Name,Buyer_Adress) " +
                "REFERENCES users(User_id,First_Name,uSER_Adress);" +

                "ALTER TABLE pharmacy  Add CONSTRAINT uniquePharmacyInfo UNIQUE(Pharmacy_id,Pharmacy_name,Pharmacy_ownername,Pharmacy_Adress);" +

                "ALTER TABLE soldDrugs Add CONSTRAINT fk_Pharmacy_id FOREIGN KEY(Pharmacy_id,Pharmacy_name,Pharmacy_ownername,Pharmacy_Adress) " +
                "REFERENCES pharmacy(Pharmacy_id,Pharmacy_name,Pharmacy_ownername,Pharmacy_Adress);";

        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.execute(tablesFormat1);
            statement.execute(tablesFormat2);
            statement.execute(tablesFormat3);
            statement.execute(tablesFormat4);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();

                //System.out.println("table creation connection closed");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
                //System.out.println(e);
            }
        }

    }

    public static int registeringUserInDatabase(String UserPostion, String FName, String MName, String LName, String Sex, String BDate,
                                                String PNumber, String UserAdress, String Username, String UserPassword) throws SQLException {
        Connection conn = getConnection();
        int id = -1;

        String input = "insert into users(User_postion,First_Name,Middle_Name,Last_Name,Sex,Birth_Date,PhoneNumber," +
                "User_Adress,User_name ,User_password) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(input, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, UserPostion);
            ps.setString(2, FName);
            ps.setString(3, MName);
            ps.setString(4, LName);
            ps.setString(5, Sex);
            ps.setString(6, BDate);
            ps.setString(7, PNumber);
            ps.setString(8, UserAdress);
            ps.setString(9, Username);
            ps.setString(10, UserPassword);
            ps.execute();
//            //System.out.println("user with position of: " + UserPostion + " :registered successfully");

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                return id;

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getSQLState(), "Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            conn.close();
//            //System.out.println("user register connection closed");
        }
        return id;
    }


    public static void registeringPharmacyInDatabase(String PharmacyName, String PharmacyOwnername, int Pharmaciestid, String PharmacyAdress, String PhoneNumber) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        String input = "insert into pharmacy(Pharmacy_name,Pharmacy_ownername,Pharmacist_id,Pharmacy_Adress,PhoneNumber) values(?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(input);
            ps.setString(1, PharmacyName);
            ps.setString(2, PharmacyOwnername);
            ps.setInt(3, Pharmaciestid);
            ps.setString(4, PharmacyAdress);
            ps.setString(5, PhoneNumber);
            ps.execute();
//            //System.out.println("pharmacy registered successfully");


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
//            //System.out.println("pharmacy register connection closed");
        }
    }

    public static int searchPharmacistsPharmacyid(int PharmacistId) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        String input = "SELECT * FROM pharmacy WHERE Pharmacist_id=?";
        int val = -1;

        try {
            ps = conn.prepareStatement(input);
            ps.setInt(1, PharmacistId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
//                //System.out.println("help: " + PharmacistId);
                val = rs.getInt("Pharmacy_id");
                return val;
            }
            rs.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "search pharmacists pharmacy", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "search pharmacists pharmacy", JOptionPane.WARNING_MESSAGE);
            }
        }
        return val;
    }



    public static boolean registeringSoldMedicineInDatabase(int MedicineId,String MedicineName, String MedicineBrand, String MedicineType, int MedicineQuantity, float Medicineprice, String MedicineExpiryDate,
                                                         String MedicineDescription, int Pharmacyid, String Pharmacyname, String Pharmacyownername, String PharmacyAdress, int Userid, String FirstName,
                                                         String UserAdress) {
        Connection conn = getConnection();
        PreparedStatement psc = null;
        PreparedStatement ps = null;
        try {

    String check="Select * From soldDrugs where Medicine_id=?";
       psc=conn.prepareStatement(check);
       psc.setInt(1,MedicineId);
       ResultSet rs= psc.executeQuery();

       if (rs.next()){
int quan=rs.getInt("Medicine_Quantity");
int sum=quan+MedicineQuantity;
           String input = "Update soldDrugs set Medicine_Quantity=? Where Medicine_id=?";
           ps=conn.prepareStatement(input);
           ps.setInt(1,sum);
           ps.setInt(2,MedicineId);
           ps.execute();
//           //System.out.println("Sold Medicine updated successfully");
return true;
       }
       else {


           String input = "Insert into soldDrugs(Medicine_id,Medicine_Name ,Medicine_Brand,Medicine_Type,Medicine_Quantity," +
                   "Medicine_price,Medicine_Expiry_date, Medicine_Description ,Pharmacy_id ,Pharmacy_name,Pharmacy_ownername," +
                   "Pharmacy_Adress,Buyer_id,Buyer_Name,Buyer_Adress) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


           ps = conn.prepareStatement(input);
           ps.setInt(1, MedicineId);
           ps.setString(2, MedicineName);
           ps.setString(3, MedicineBrand);
           ps.setString(4, MedicineType);
           ps.setInt(5, MedicineQuantity);
           ps.setFloat(6, Medicineprice);
           ps.setString(7, MedicineExpiryDate);
           ps.setString(8, MedicineDescription);
           ps.setInt(9, Pharmacyid);
           ps.setString(10, Pharmacyname);
           ps.setString(11, Pharmacyownername);
           ps.setString(12, PharmacyAdress);
           ps.setInt(13, Userid);
           ps.setString(14, FirstName);
           ps.setString(15, UserAdress);
           ps.execute();
//           //System.out.println("Sold Medicine registered successfully");
       }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Sold Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
//            //System.out.println(e.getMessage());
//            //System.out.println(e.getCause());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Sold Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
//            //System.out.println("Sold Medicine register connection closed");
        }
        return false;
    }


    public static int registeringMedicineInDatabase(String MedicineName, String MedicineBrand, String MedicineType, int MedicineQuantity, float Medicineprice, String MedicineExpiryDate,
                                                     String MedicineDescription, int Pharmacyid) {
int id=-1;
//        //System.out.println("hello i am here");
        Connection conn = getConnection();
        PreparedStatement ps = null;
        String input = "insert  into Medicines(Medicine_Name,Medicine_Brand,Medicine_Type,Medicine_Quantity,Medicine_price,Medicine_Expiry_date,Medicine_Description,Pharmacy_id" +
                ") values(?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(input,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, MedicineName);
            ps.setString(2, MedicineBrand);
            ps.setString(3, MedicineType);
            ps.setInt(4, MedicineQuantity);
            ps.setFloat(5, Medicineprice);
            ps.setString(6, MedicineExpiryDate);
            ps.setString(7, MedicineDescription);
            ps.setInt(8, Pharmacyid);

            ps.execute();
//            //System.out.println("Medicine registered successfully");
            ResultSet rs=ps.getGeneratedKeys();

            if (rs.next()){
                id=rs.getInt(1);
                return id;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
//            //System.out.println("Medicine register connection closed");
        }
        return id;
    }

    public static int adminRegisteringPharmacyInDatabase(String PharmacyName, String PharmacyOwnername, int PharmacistId, String PharmacyAdress, String PharmacyPhonanumber) {
int id=-1;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        String input = "insert  into pharmacy(Pharmacy_name,Pharmacy_ownername,Pharmacist_id,Pharmacy_Adress,PhoneNumber) values(?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(input,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, PharmacyName);
            ps.setString(2, PharmacyOwnername);
            ps.setInt(3, PharmacistId);
            ps.setString(4, PharmacyAdress);
            ps.setString(5, PharmacyPhonanumber);
            ps.execute();
//            //System.out.println("Medicine registered successfully");
            ResultSet rs=ps.getGeneratedKeys();

            if (rs.next()){
                id=rs.getInt(1);
                return id;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Pharmacy Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
//            //System.out.println("Medicine register connection closed");
        }
        return id;
    }

    public static String signinCheck(String inputUsername, String inputPassword) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        String input = "SELECT * FROM users WHERE User_name=? AND User_password=?";

        try {
            ps = conn.prepareStatement(input);
            ps.setString(1, inputUsername);
            ps.setString(2, inputPassword);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String pos = rs.getString("USER_postion");
                USER.setUserInfo(rs.getInt("USER_id"), pos, rs.getString("First_Name"),
                        rs.getString("Middle_Name"), rs.getString("Last_Name"),
                        rs.getString("Sex"), rs.getString("Birth_Date"), rs.getString("PhoneNumber"),
                        rs.getString("USER_Adress"), rs.getString("USER_name"), rs.getString("USER_password"));
                return pos;

            }
            rs.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "sign in user 1 in database", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "sign in user 2 in database", JOptionPane.WARNING_MESSAGE);
            }
        }

        return "null";
    }


    public static void UpdateuserInfo(int Userid, String FName, String MName, String LName, String Sex, String BDate,
                                      String PNumber, String UserAdress, String Username, String UserPassword,String userPostion) {


        try (Connection conn = getConnection()) {
            String input = "update users set First_Name=?,Middle_Name=?,Last_Name=?,Sex=?,Birth_Date=?,PhoneNumber=?," +
                    "User_Adress=?,User_name=? ,User_password=? ,User_postion=? where User_id=?";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(input);

            ps.setString(1, FName);
            ps.setString(2, MName);
            ps.setString(3, LName);
            ps.setString(4, Sex);
            ps.setString(5, BDate);
            ps.setString(6, PNumber);
            ps.setString(7, UserAdress);
            ps.setString(8, Username);
            ps.setString(9, UserPassword);
            ps.setString(10, userPostion);
          ps.setInt(11, Userid);

            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
//            //System.out.println("user update connection closed");
        }

    }

    public static int updaingMedicineInDatabase(String MedicineName, String MedicineBrand, String MedicineType, int MedicineQuantity, float Medicineprice, String MedicineExpiryDate,
                                                String MedicineDescription, int Pharmacyid,int Medicineid) {
            int id=-1;
//            //System.out.println("hello i am here");
            Connection conn = getConnection();
            PreparedStatement ps = null;
            String input = "UPDATE Medicines set Medicine_Name=?,Medicine_Brand=?,Medicine_Type=?,Medicine_Quantity=?,Medicine_price=?," +
                    "Medicine_Expiry_date=?,Medicine_Description=?,Pharmacy_id=? where Medicine_id=?";
            try {
                ps = conn.prepareStatement(input,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, MedicineName);
                ps.setString(2, MedicineBrand);
                ps.setString(3, MedicineType);
                ps.setInt(4, MedicineQuantity);
                ps.setFloat(5, Medicineprice);
                ps.setString(6, MedicineExpiryDate);
                ps.setString(7, MedicineDescription);
                ps.setInt(8, Pharmacyid);
                ps.setInt(9, Medicineid);

                ps.execute();
//                //System.out.println("Medicine updated successfully");
                ResultSet rs=ps.getGeneratedKeys();

                if (rs.next()){
                    id=rs.getInt(1);
                    return id;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
                }
//                //System.out.println("Medicine Updated connection closed");
            }
            return id;
    }
 public static int updaingPharmacyInfoInDatabase(int Pharmacyid,String PharmacyName, String PharmacyOwnername, int PharmacistId, String PharmacyAdress, String PharmacyPhonanumber) {
            int id=-1;
            Connection conn = getConnection();
            PreparedStatement ps = null;
            String input = "UPDATE pharmacy set Pharmacy_name=? ,Pharmacy_ownername=?,Pharmacist_id=?,Pharmacy_Adress=?,PhoneNumber=? where Pharmacy_id=?";
            try {
                ps = conn.prepareStatement(input,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, PharmacyName);
                ps.setString(2, PharmacyOwnername);
                ps.setInt(3, PharmacistId);
                ps.setString(4, PharmacyAdress);
                ps.setString(5, PharmacyPhonanumber);
                ps.setInt(6, Pharmacyid);

                ps.execute();
//                //System.out.println("Pharmacy info updated successfully");
                ResultSet rs=ps.getGeneratedKeys();

                if (rs.next()){
                    id=rs.getInt(1);
                    return id;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Pharmacy Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
                }
//                //System.out.println("Pharmacy Updated connection closed");
            }
            return id;
    }
    public static void DeletingPharmacyInDatabase(int Pharmacyid) {

        Connection conn = getConnection();
        PreparedStatement ps = null;
        String input = "Delete From pharmacy where Pharmacy_id=?";
        try {
            ps = conn.prepareStatement(input,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, Pharmacyid);

            ps.execute();

//            //System.out.println("Pharmacy Deleted Successfully ");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY DELETION Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
//            //System.out.println("Pharmacy deleting connection closed");
        }

    }

    public static void DeletingMedicineInDatabase(int Medicineid) {

        Connection conn = getConnection();
        PreparedStatement ps = null;
        String input = "Delete From Medicines where Medicine_id=?";
        try {
            ps = conn.prepareStatement(input,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, Medicineid);

            ps.execute();

//            //System.out.println("Medicine Deleted Successfully ");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
//            //System.out.println("Medicine deleting connection closed");
        }

    }

    public static int SearchMedicine(List<String> parameter, StringBuilder input) {
int id=-1;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(input.toString());
            for (int i=0;i<parameter.size();i++){
                ps.setString(i,parameter.get(i));
            }


            ps.execute();

//            //System.out.println("Medicine Searched Successfully ");

            ResultSet rs=ps.getGeneratedKeys();

            if (rs.next()){
                id=rs.getInt(1);


                return id;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Medicine Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Model.PHARMACY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
//            //System.out.println("Medicine deleting connection closed");
        }
return id;
    }


    public static void DeleteUser(int userid) {

        Connection conn = getConnection();
        PreparedStatement ps = null;
        String input = "Delete From users where User_id=?";
        try {
            ps = conn.prepareStatement(input,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userid);

            ps.execute();

//            //System.out.println("User Deleted Successfully ");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "User Deletion Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "User DeletionY Model.DATABASE CONNECTION", JOptionPane.WARNING_MESSAGE);
            }
//            //System.out.println("User deleting connection closed");
        }

    }
}



