public class Savings extends BankAccount{
  //Interest rate is 0.15%
  private static final double InterestRate = 0.0015;
  private double MinimumDeposit = 50.00;
  private int NumofWithdrawals = 0;
  private int WithdrawalLimit= 9;
  private static final int WithdrawalFee = 5;


  public Savings() {
    super();
    System.out.println("\n Congratulations on your new Savings Account!!");
    System.out.println(toString());
  }

  public Savings(double initialDeposit) {
    //super(initialDeposit);
    super();

    if(initialDeposit < MinimumDeposit)
    {
      System.out.println("\n The minimum deposit is $50.00, please try again!");

    }
    else
    {
      balance += initialDeposit;
      System.out.println("\nCongratulations on your new Account!!");
      System.out.println(toString());
    }
  }

  public double getInterestRate() {
    return InterestRate;
  }

  /*public int getWithdrawalLimit() {
    return withdrawalLimit;
  }*/

  public int getWithdrawalFee() {
    return WithdrawalFee;
  }


  private void withdrawlimit(int NumofWithdrawals){
    if(WithdrawalLimit >= NumofWithdrawals)
    {
      System.out.println("\n The withdrawalLimit is 9 transactions, please try again next month!");
    }
    else
    {
      System.out.println("You have made " +NumofWithdrawals+ ".");
    }
  }

  // make note of this method
  public void withdraw(double amount)
  {
    if (amount > MinimumDeposit)
    {
      System.out.println("Declined. This amount can not surpass minimum deposit");
    }
    else
    {
      if((amount + WithdrawalFee) >= balance)
      {
        System.out.println("Declined. This amount cannot have a zero balance");

      }
      else
      {
        System.out.printf("Withdrawal Amount: $%.2f%n", amount);
        System.out.printf("Transaction Fee: %.2f%n", WithdrawalFee);
        System.out.printf("Your Account balance: $%.2f%n", balance);
      }
    }

  }

  public void deposit(double amount){
    if(amount<=0)
    {
      System.out.println("Amount to be deposited should be positive");
    }
    else
    {
      balance += amount;
      System.out.printf("You've deposited: $%.2f%n", amount);
      System.out.printf("Your new balance is: $%.2f%n", balance);
    }
  }

  public String toString() {
    return "New Savings Account information:"
            + "\n================================="
            + "\nAccountNumber: " + accountNumber
            + "\nBalance: $" + balance
            + "\nWithdrawal Limit:" + WithdrawalLimit + "\n"
            + "*** REMEMBER: Do not share your Account Number with anyone!!";
  }
}
