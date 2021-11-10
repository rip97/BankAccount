public abstract class BankAccount
{
    //Bank Account Variables
    protected String accountHolder;
    private static int lastAccountNum = 1000;
    protected int accountNumber;
    protected double balance;

    //Default Constructor Method
    public BankAccount() {
        lastAccountNum++;
        this.accountNumber = lastAccountNum;
    }

    /*    
    //Alterate Bank Account Constructor
    public BankAccount(double initialDeposit) {
        lastAccountNum++;
        this.accountNumber = lastAccountNum;
        balance = initialDeposit;
    }
    */

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
