public class AccountHolder
{
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int zip;
    private static int lastCustomerId = 5003;
    private int customerId;

    // private ArrayList<BankAccount> accounts;

    public AccountHolder(String firstName, String lastName, String street,
                         String city, String state, int zip)
    {
        lastCustomerId++;
        this.customerId = lastCustomerId;
        this.firstName = firstName;
        this.lastName =lastName;
        this.street =street;
        this.city =city;
        this. state =state;
        this.zip = zip;
    }

    public AccountHolder(){}

    public String getFirstName() {return firstName;}

    public String getLastName(){return lastName;}

    public String getStreet(){return street;}

    public String getCity(){return city;}

    public String getState(){return state;}

    public int getZip(){return zip;}

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int id)
    {
        this.customerId = id;
    }

    public void setFirstName(String n)
    {
        this.firstName = n;
    }

    public void setLastName(String n)
    {
        this.lastName= n;
    }

    public void setStreet(String n)
    {
        this.street = n;
    }

    public void setCity(String n)
    {
        this.city = n;
    }

    public void setState(String n)
    {
        this.state = n;
    }

    public void setZip(int zip)
    {
        this.zip = zip;
    }

//    public void updateAccountHolderName(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    public void updateAddress(String street, String city, String state, int zip) {
//        this.street = street;
//        this.city = city;
//        this.state = state;
//        this.zip = zip;
//    }
    
    public String toString()
    {
        // return all bank holder info plus account numnber
        return "\nAccount Holder Information"
                + "\n================================="
                + "\nCustomer Number: " + getCustomerId()
                + "\nAccount Holder: " + firstName + " " + lastName
                + "\nAddress: " + street + " " + city + ", " + state + " " + zip;
    }
}
