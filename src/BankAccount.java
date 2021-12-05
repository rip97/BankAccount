public abstract class BankAccount
{
    //Bank Account Variables
    private static int lastAccountNum = 1003;
    protected int accountNumber;
    protected double balance;
    protected int holderID;

    public BankAccount(int holderID) {
        lastAccountNum++;
        this.accountNumber = lastAccountNum;
        this.holderID = holderID;
    }

    //Default Constructor Method
    public BankAccount()
    {
        lastAccountNum++;
        this.accountNumber = lastAccountNum;
    }

    public void setHolderID(int holderID) {
        this.holderID = holderID;
    }

    public int getHolderID() {
        return holderID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance()
    {
        return this.balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    //Abstract methods to be created in sub classes
    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract String toString();


}
