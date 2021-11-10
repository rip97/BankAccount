public class Roth extends IRA{

    private int grossIncome;

    public Roth(int grossIncome, String birthDate, double taxIncomeAmt, String accountHolder)
    {
        super(birthDate,taxIncomeAmt,accountHolder);
        this.grossIncome = grossIncome;
        System.out.println("\nCongratulations on your new Account!!");
        System.out.println(toString());
    }

    public void deposit(double amount)
    {
        if(!contributionCheck(amount))
            balance += amount;
        else
            System.out.println("You have contributed your max contributions for the year!");
    }

    public void taxFreeWithDraw(double amount)
    {
        if(balance > 0)
            withdraw(amount);
        else
            System.out.println("Insufficent funds!");
    }
    public String toString()
    {
        return String.format("Roth IRA Account:\n" +
            "Holder: %s\n" +
            "Balance: %f\n", accountHolder, balance);
    }

}
