package Model;

public class SOLDMEDICINE {
        private  int medicineId;
        private  String medicineName;
        private  String medicineBrand;
        private  String medicineType;
        private  int medicineQuantity;
        private  float medicinePrice;
        private  String medicineExpirydate;
        private  String medicineDescription;
        private  int pharmacyId;
        private  String pharmacyName;
        private  String pharmacyAdress;
        private  String pharmacyOwnerName;
        private  int userId;
        private  String userName;
        private  String userAdress;



    public  SOLDMEDICINE(int MedicineId, String MedicineName, String MedicineBrand, String MedicineType,int MedicineQuantity,
                                float MedicinePrice,String MedicineExpiryDate, String MedicineDescription,
                                int PharmacyId,String PharmacyName,String PharmacyOwnerName,String PharmacyAdress,int UserId, String UserName,
                                    String UserAdress) {
            medicineId = MedicineId;
            medicineName = MedicineName;
            medicineBrand = MedicineBrand;
            medicineType = MedicineType;
            medicineQuantity = MedicineQuantity;
            medicinePrice = MedicinePrice;
            medicineExpirydate = MedicineExpiryDate;
            medicineDescription = MedicineDescription;
            pharmacyId = PharmacyId;
            pharmacyName=PharmacyName;
            pharmacyOwnerName=PharmacyOwnerName;
            pharmacyAdress=PharmacyAdress;
            userId = UserId;
            userName = UserName;
            userAdress = UserAdress;
        }



    public int getMedicineId() {
            return medicineId;
        }
        public String getMedicineName() {
            return medicineName;
        }
        public String getMedicineBrand() {
            return medicineBrand;
        }
        public String getMedicineType() {
            return medicineType;
        }
        public float getMedicinePrice() {
            return medicinePrice;
        }
        public String getMedicineDescription() {
            return medicineDescription;
        }
        public int getUserId() {
            return userId;
        }
        public String getUserName() {
            return userName;
        }
        public String getUserAdress() {
            return userAdress;
        }
        public int getMedicineQuantity() {
            return medicineQuantity;
        }

    public int getPharmacyId() {

        return pharmacyId;
    }
    public String getPharmacyName() {
        return pharmacyName;
    }
    public String getPharmacyAdress() {
        return pharmacyAdress;
    }
    public String getPharmacyOwnerName() {
        return pharmacyOwnerName;
    }

    public String getMedicineExpirydate() {
        return medicineExpirydate;
    }
}
