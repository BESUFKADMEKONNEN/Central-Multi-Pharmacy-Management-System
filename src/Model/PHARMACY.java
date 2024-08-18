package Model;

public class PHARMACY {
    private int pharmacyId;
    private String pharmacyName;
    private String pharmacyOwnerName;
    private int pharmacistId;
    private String pharmacyAdress;
    private String pharmacyPhoneNumber;

    public PHARMACY(int pharmacyId, String pharmacyName, String pharmacyOwner, int pharmacistId, String pharmacyAdress, String pharmacyPhoneNumber) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.pharmacyOwnerName = pharmacyOwner;
        this.pharmacistId = pharmacistId;
        this.pharmacyAdress = pharmacyAdress;
        this.pharmacyPhoneNumber = pharmacyPhoneNumber;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }



    public int getPharmacistId() {
        return pharmacistId;
    }

    public String getPharmacyAdress() {
        return pharmacyAdress;
    }

    public String getPharmacyPhoneNumber() {
        return pharmacyPhoneNumber;
    }

    public String getPharmacyOwnerName() {
        return pharmacyOwnerName;
    }
}
