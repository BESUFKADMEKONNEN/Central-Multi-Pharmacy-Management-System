package Model;

public class MEDICINEPHARMACY {
    private int medicineId;
    private String medicineName;
    private float medicinePrice;
    private int pharmacyId;
    private String pharmacyName;
    private String pharmacyAddress;

    public MEDICINEPHARMACY(int MedicineId, String MedicineName, float MedicinePrice, int PharmacyId, String PharmacyName,
                            String PharmacyAddress) {
        this.medicineId = MedicineId;
        this.medicineName = MedicineName;
        this.medicinePrice = MedicinePrice;
        this.pharmacyId = PharmacyId;
        this.pharmacyName = PharmacyName;
        this.pharmacyAddress = PharmacyAddress;
    }


    public int getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public float getMedicinePrice() {
        return medicinePrice;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }
}
