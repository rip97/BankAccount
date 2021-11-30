public class Roth extends IRA{

    private int grossIncome;

    public Roth(int grossIncome, String birthDate, double taxIncomeAmt, String accountHolder, double initialDeposit)
    {
        super(birthDate,taxIncomeAmt,accountHolder, initialDeposit);
        this.grossIncome = grossIncome;
    }

    public void deposit(double amount)
    {
        if(!contributionCheck(amount)) {
            balance += amount;
            System.out.printf("You've deposited: $%.2f%n", amount);
            System.out.printf("Your new balance is: $%.2f%n", balance);
        }
        else
            System.out.println("You have contributed your max contributions for the year!");
    }

    public void withdraw(double amount)
    {
        if(balance > 0) {
            balance = balance - amount;
            System.out.printf("Withdrawal Amount: $%.2f%n", amount);
            System.out.printf("Your Account balance: $%.2f%n", balance);
        }
        else
            System.out.println("Insufficent funds!");
    }

}
