package Model;

public class SEARCHEDMEDICINE {
    private  int medicineId;
        private  String medicineName;
        private  String medicineBrand;
        private  String medicineType;
        private  int medicineQuantity;
        private  float medicinePrice;
        private  String medicineExpirydate;
        private  String medicineImage;
        private  String medicineDescription;
        private  int pharmacyId;


        public  SEARCHEDMEDICINE(int MedicineId, String MedicineName, String MedicineBrand, String MedicineType, int MedicineQuantity,
                         float MedicinePrice, String MedicineExpirydate, String MedicineDescription) {
            medicineId = MedicineId;
            medicineName = MedicineName;
            medicineBrand = MedicineBrand;
            medicineType = MedicineType;
            medicineQuantity = MedicineQuantity;
            medicinePrice = MedicinePrice;
            medicineExpirydate = MedicineExpirydate;
            medicineDescription = MedicineDescription;

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
        public int getMedicineQuantity() {
            return medicineQuantity;
        }
        public String getMedicineExpirydate() {
            return medicineExpirydate;
        }
        public String getMedicineImage() {
            return medicineImage;
        }

        public int getPharmacyId() {
            return pharmacyId;
        }

    }

