import java.beans.JavaBean;
import java.io.*;
import java.util.ArrayList;
import java.util.StringJoiner;

public class DBBankAcct {

    private final String csvFile;
    private BufferedReader csvReader;
    private BufferedWriter csvWriter;
    private ArrayList<BankAccount> oldFile;

    public DBBankAcct(String csvFile)
    {
        this.csvFile = csvFile;
        this.oldFile = new ArrayList<>();
    }

    // reads the data file
    public void read(ArrayList<BankAccount> bankActs)
    {
        if(bankActs.isEmpty())
        {
            try
            {
                csvReader= new BufferedReader(new FileReader(csvFile));
                String line;
                while ((line = csvReader.readLine()) != null) {

                    BankAccount account;
                    String[] cols = line.split(",");

                    if(cols[3].equals("s"))
                        account = new Savings();
                    else if(cols[3].equals("c"))
                        account = new Checking();
                    else if(cols[3].equals("pc"))
                        account = new PremiumChecking();
                    else if(cols[3].equals("hy"))
                        account = new HighYield();
                    else if(cols[3].equals("r"))
                        account = new Roth();
                    else
                        account = new Traditional();

                    account.setAccountNumber(Integer.parseInt(cols[0]));
                    account.setBalance(Double.parseDouble(cols[2]));

                    if(account instanceof Roth)
                    {

                        ((Roth) account).setGrossIncome(Integer.parseInt(cols[5]));
                    }
                    if(account instanceof IRA)
                    {
                        ((IRA) account).setBirthDate(cols[4]);
                        ((IRA) account).setTaxIncomeAmt(Double.parseDouble(cols[6]));
                    }

                    bankActs.add(account);
                }

                csvReader.close();

            }
            catch (IOException e)
            {
                e.printStackTrace();

            }
        }
        else
        {
            bankActs.clear();
            try
            {
                csvReader= new BufferedReader(new FileReader(csvFile));
                String line;
                while ((line = csvReader.readLine()) != null) {

                    BankAccount account;
                    String[] cols = line.split(",");

                    if(cols[3].equals("s"))
                        account = new Savings();
                    else if(cols[3].equals("c"))
                        account = new Checking();
                    else if(cols[3].equals("pc"))
                        account = new PremiumChecking();
                    else if(cols[3].equals("hy"))
                        account = new HighYield();
                    else if(cols[3].equals("r"))
                        account = new Roth();
                    else
                        account = new Traditional();

                    account.setAccountNumber(Integer.parseInt(cols[0]));
                    account.setBalance(Double.parseDouble(cols[2]));

                    if(account instanceof Roth)
                    {

                        ((Roth) account).setGrossIncome(Integer.parseInt(cols[5]));
                    }
                    if(account instanceof Traditional)
                    {
                        ((IRA) account).setBirthDate(cols[4]);
                        ((IRA) account).setTaxIncomeAmt(Double.parseDouble(cols[6]));
                    }

                    bankActs.add(account);
                }
                csvReader.close();

            }
            catch (IOException e)
            {
                e.printStackTrace();

            }
        }
    }

    // creates new row in data file
    public void create(BankAccount account)
    {
        read(oldFile);
        if (!checkID(oldFile, account.getAccountNumber())) {
            oldFile.add(account);
            updateFile(oldFile);
        } else {
            System.out.println("Account ID already exists!");
        }
    }

    // deletes row in data file
    public void delete(int acctNum)
    {
        read(oldFile);
        int index = 0;
        int counter = 0;

        for(BankAccount account: oldFile)
        {
            if(acctNum == account.getAccountNumber()) {
                index = counter;
                break;
            }
            counter++;
        }
        if(index > -1)
        {
            oldFile.remove(index);
            updateFile(oldFile);
        }
        else
        {
            System.out.println("This ID doesn't exist!");
        }


    }

    // updates the account balance
    public void update(int acctNum, double newBal)
    {
        // get contents of the file
        read(oldFile);

        // update the selected account ID
        int index = 0;

        for(BankAccount accounts: oldFile)
        {

            if(acctNum == accounts.getAccountNumber())
            {
                index = oldFile.indexOf(accounts);
                break;
            }
        }
        // update the new balance at the object location
        oldFile.get(index).setBalance(newBal);

        // rewrite the file
        updateFile(oldFile);
    }

    /*
        Helper methods for the CRUD methods
     */

    // rewrites the file
    private void updateFile(ArrayList<BankAccount> accounts)
    {
        try
        {
            csvWriter= new BufferedWriter(new FileWriter(csvFile));
            for(BankAccount account: accounts)
            {
                if(!(account instanceof IRA))
                {
                    String newLine = String.format("%d,%d,%f,", account.getAccountNumber(),account.getHolderID(),account.getBalance());
                    if(account instanceof Checking)
                        newLine = newLine + "c";
                    if(account instanceof PremiumChecking)
                        newLine = newLine + "pc";
                    if(account instanceof Savings)
                        newLine = newLine + "s";
                    if(account instanceof HighYield)
                        newLine = newLine + "hy";

                    csvWriter.write(newLine);
                }
                else
                {
                    String newLine;
                    if(account instanceof Roth)
                        newLine = String.format("%d,%d,%f,r,%s,%f,%d", account.getAccountNumber(),account.getHolderID(),account.getBalance(),((Roth) account).getBirthDate(),
                                ((Roth) account).getTaxIncomeAmt(),((Roth) account).getGrossIncome());
                    else
                        newLine = String.format("%d,%d,%f,t,%s,%f", account.getAccountNumber(),account.getHolderID(),account.getBalance(),
                                ((IRA) account).getBirthDate(),((IRA) account).getTaxIncomeAmt());

                    csvWriter.write(newLine);
                }

                csvWriter.newLine();
            }
            csvWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // method checks for duplicate acctNums
    private boolean checkID(ArrayList<BankAccount> accounts, int accountID)
    {
        for(BankAccount account: accounts)
        {
            if(accountID == account.getAccountNumber())
                return true;
        }
        return false;
    }

    // gets the ID in the file
    private int getID(ArrayList<BankAccount> accounts, int accountID)
    {
        for(BankAccount account: accounts)
        {
            if(accountID == account.getAccountNumber())
               return account.getAccountNumber();
        }
        return -1;
    }
}
