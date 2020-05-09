package CompleteOrder;

public class Person {
    public String txtFirstName;
    public String txtLastName;
    public String txtAdress;
    public int txtPostNumber;
    public String txtCity;

    public Person (String fistName, String lastName, String adress, int postNumber, String city){
        this.txtFirstName = fistName;
        this.txtLastName = lastName;
        this.txtAdress = adress;
        this.txtPostNumber = postNumber;
        this.txtCity = city;
    }

    public String getFirstName() {
        return txtFirstName;
    }

    public void setFirstName(String txtFirstName) {
        this.txtFirstName = txtFirstName;
    }

    public String getLastName() {
        return txtLastName;
    }

    public void setLastName(String txtLastName) {
        this.txtLastName = txtLastName;
    }

    public String getAdress() {
        return txtAdress;
    }

    public void setAdress(String txtAdress) {
        this.txtAdress = txtAdress;
    }

    public int getPostNumber() {
        return txtPostNumber;
    }

    public void setPostNumber(int txtPostNumber) {
        this.txtPostNumber = txtPostNumber;
    }

    public String getCity() {
        return txtCity;
    }

    public void setCity(String txtCity) {
        this.txtCity = txtCity;
    }

    @Override
    public String toString (){
        return String.format("Navn: %s %s; Adresse: %s; Postnummer: %s; Poststed: %S", txtFirstName, txtLastName,
                txtAdress, txtPostNumber, txtCity);
    }
}
