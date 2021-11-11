import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IRA extends BankAccount {

    private double totalContributuons;

    private String birthDate;

    private double taxIncomeAmt;

    public IRA() {}

    public IRA(String birthDate, double taxIncomeAmt, String accountHolder, double initialDeposit)
    {
        this.accountHolder = accountHolder;
        this.birthDate = birthDate; // in the format yyyy-MM-dd
        this.taxIncomeAmt = taxIncomeAmt;
        this.balance += initialDeposit;
        System.out.println("\nCongratulations on your new IRA Account!!");
        System.out.println(toString());
    }

    public void deposit(double amount) {
        if(!contributionCheck(amount))
           balance += amount;
        else
            System.out.println("You have contributed your max contributions for the year!");
    }

    public void withdraw(double amount) {
        if(balance > 0)
            balance-= amount;
        else
            System.out.println("Insufficent funds!");
    }

    public double getBalance() {
        return balance;
    }

    protected double determineAge()
    {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate pastDate = LocalDate.parse(birthDate, dateTimeFormatter);
        Duration diff = Duration.between(pastDate.atStartOfDay(), currentDate.atStartOfDay());

        return diff.toDays()/ 365.0;

    }

    protected boolean contributionCheck(double amount)
    {
        this.totalContributuons += amount;
        if(determineAge() == 59.5 && totalContributuons > 6000 )
        {
            return true;
        }
        else if(determineAge() > 59.5 && totalContributuons > 7000)
        {
            return true;
        }
        else
            return false;
    }

    public String toString()
    {
        return String.format("IRA Account Information:\n" +
                "=======================\n" +
                "Holder: %s\n" +
                "Account Number: %d\n" +
                "Balance: $%.2f\n" +
                "*** REMEMBER: Do not share your Account Number with anyone!!", accountHolder, accountNumber, balance);

    }

}
