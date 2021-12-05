public class Roth extends IRA{

    private int grossIncome;

    public Roth(){}

    public Roth(int grossIncome, String birthDate, double taxIncomeAmt,double initialDeposit,int holderID)
    {
        super(birthDate,taxIncomeAmt,initialDeposit,holderID);
        this.grossIncome = grossIncome;
    }

    public void setGrossIncome(int income) { this.grossIncome = income;}

    public int getGrossIncome(){return grossIncome;}

    public void deposit(double amount)
    {
        if(!contributionCheck(amount))
            balance += amount;
        else
            System.out.println("You have contributed your max contributions for the year!");
    }

    public void withdraw(double amount)
    {
        if(balance > 0)
            withdraw(amount);
        else
            System.out.println("Insufficent funds!");
    }

    public String toString() {
        return super.toString();
    }

}
