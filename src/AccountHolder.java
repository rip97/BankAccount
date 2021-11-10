public class AccountHolder
{
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int zip;
    private static int lastCustomerId = 5000;
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

    public String getName()
    {
        return firstName + " " +lastName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void updateAccountHolderName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void updateAddress(String street, String city, String state, int zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
    public String getAccountInfo()
    {
        // return all bank holder info plus account numnber
        return "\nAccount Holder Information"
                + "\n================================="
                + "\nCustomer Number: " + getCustomerId() 
                + "\nAccount Holder: " + getName()
                + "\nAddress: " + street + " " + city + ", " + state + " " + zip;
    }
}
