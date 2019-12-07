package sample;

public class Customer {
    private int columnNo;
    private String CustomerID;
    private String Name;
    private String PhoneNo;
    private String Email;
    private boolean Member;

    public Customer(int No, String customerID, String name, String phoneNo, String email, boolean member) {
        columnNo = No;
        CustomerID = customerID;
        Name = name;
        PhoneNo = phoneNo;
        Email = email;
        Member = member;
    }

    public int getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(int columnNo) {
        this.columnNo = columnNo;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isMember() {
        return Member;
    }

    public void setMember(boolean member) {
        Member = member;
    }

}
