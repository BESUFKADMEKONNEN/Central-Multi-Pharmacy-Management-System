package Model;

public class USERTABLE {

        private  int userId;
        private  String userPosition;
        private  String userFirstName;
        private  String userMiddleName;
        private  String userLastName;
        private  String userSex;
        private  String userDateOfBirth;
        private  String userPhoneNumber;
        private  String userAdress;
        private  String userUserName;



        public  int getUserId() {
            return userId;
        }
        public  String getUserPosition() {
            return userPosition;
        }

        public  String getUserFirstName() {
            return userFirstName;
        }
        public  String getUserMiddleName() {
            return userMiddleName;
        }
        public  String getUserLastName() {
            return userLastName;
        }
        public  String getUserSex() {
            return userSex;
        }
        public  String getUserDateOfBirth() {
            return userDateOfBirth;
        }
        public  String getUserPhoneNumber() {
            return userPhoneNumber;
        }
        public  String getUserAdress() {return userAdress;}
        public  String getUserUserName() {
            return userUserName;
        }

        public USERTABLE(int Id, String Position, String FirstName, String MiddleName, String LastName, String Sex, String DateOfBirth
                , String PhoneNumber, String Adress, String UserName) {
            userId = Id;
            userPosition = Position;
            userFirstName = FirstName;
            userMiddleName = MiddleName;
            userLastName = LastName;
            userSex = Sex;
            userDateOfBirth = DateOfBirth;
            userPhoneNumber = PhoneNumber;
            userAdress = Adress;
            userUserName = UserName;

        }



    }


