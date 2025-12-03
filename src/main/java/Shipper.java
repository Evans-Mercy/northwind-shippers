public class Shipper {
    int shipperID;
    String companyName;
    String phone;

    public Shipper(int shipperID, String companyName, String phone) {
        this.shipperID = shipperID;
        this.companyName = companyName;
        this.phone = phone;
    }

    public Shipper(String companyName, String phone) {
        this.companyName = companyName;
        this.phone = phone;
    }


    public int getShipperID() {
        return shipperID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Shipper{" +
                "companyName='" + companyName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
