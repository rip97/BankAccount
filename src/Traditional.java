import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Traditional extends IRA{
    private static double PENTALY = 0.10;
    private double requiredAge;
    private double minDistributuion;

    public Traditional(String birthDate, double taxIncomeAmt, String accountHolder)
    {
        super(birthDate,taxIncomeAmt,accountHolder);


    }

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
        {

            if(checkPentaly())
                balance = balance - ((amount * PENTALY)+amount);
            else
                balance = balance -amount;
        }
        else
            System.out.println("Insufficent funds!");
    }

    private boolean checkPentaly()
    {
        return determineAge() < 70.0;
    }

    public void checkRequiredAge()
    {
        if(determineAge() >= 70.0)
            minDistributuion = calculateMinDist((int)determineAge());
            System.out.printf("You are required to take a minimun distribution of %.2f based on your age.\n",minDistributuion);

    }

    private double calculateMinDist(int age)
    {
        // distrbuitons for ages up to age 93
        ArrayList<Double> distPeriod = new ArrayList<Double>(Arrays.asList(27.4,26.6,25.6,24.7,23.8,22.9,22.0,21.2,20.3,19.5,18.7,17.9,17.1,16.3,15.5,14.8,14.1,13.4,12.7,12.0,11.4,10.8,10.2,9.6));
        double distPrdVal = 0;
        for(int i = 0; i < distPeriod.size(); i++)
        {
            if(age - 70 ==  i)
            {
                distPrdVal = distPeriod.get(i);
                break;
            }
        }

        return balance / distPrdVal;

    }
}
