import java.io.*;
import java.util.ArrayList;

public class DBAccount
{
    private final String csvFile;
    private BufferedReader csvReader;
    private BufferedWriter csvWriter;
    private ArrayList<AccountHolder> oldFile;

    public DBAccount(String csvFile)
    {
        this.csvFile = csvFile;
        this.oldFile = new ArrayList<>();
    }

    // reads the file
    public void read(ArrayList<AccountHolder> holders)
    {
        if(holders.isEmpty())
        {
            try
            {
                csvReader= new BufferedReader(new FileReader(csvFile));
                String line;
                while ((line = csvReader.readLine()) != null) {

                    AccountHolder holder = new AccountHolder();
                    String[] cols = line.split(",");
                    for(int i = 0; i < cols.length; i++)
                    {
                        switch(i)
                        {
                            case 0:
                               holder.setCustomerId(Integer.parseInt(cols[0]));
                               break;
                            case 1:
                                holder.setFirstName(cols[1]);
                            case 2:
                                holder.setLastName(cols[2]);
                            case 3:
                                holder.setStreet(cols[3]);
                            case 4:
                                holder.setCity(cols[4]);
                            case 5:
                                holder.setState(cols[5]);
                            case 6:
                                holder.setZip(Integer.parseInt(cols[6]));
                        }
                    }
                    holders.add(holder);

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
            holders.clear();
            try
            {
                csvReader= new BufferedReader(new FileReader(csvFile));
                String line;
                while ((line = csvReader.readLine()) != null) {

                    AccountHolder holder = new AccountHolder();
                    String[] cols = line.split(",");
                    for(int i = 0; i < cols.length; i++)
                    {
                        switch(i)
                        {
                            case 0:
                                holder.setCustomerId(Integer.parseInt(cols[0]));
                                break;
                            case 1:
                                holder.setFirstName(cols[1]);
                            case 2:
                                holder.setLastName(cols[2]);
                            case 3:
                                holder.setStreet(cols[3]);
                            case 4:
                                holder.setCity(cols[4]);
                            case 5:
                                holder.setState(cols[5]);
                            case 6:
                                holder.setZip(Integer.parseInt(cols[6]));
                        }
                    }
                    holders.add(holder);

                }
                csvReader.close();

            }
            catch (IOException e)
            {
                e.printStackTrace();

            }
        }
    }

    // adds a new account ID and info
    public void create(AccountHolder holder) {

        read(oldFile);
        if (!checkID(oldFile, holder.getCustomerId())) {
            oldFile.add(holder);
            update(oldFile);
        } else {
            System.out.println("Account ID already exists!");
        }
    }

    // method deletes a users account ID and assoicated info
    public void delete(int accountID)
    {
        read(oldFile);
        int index = 0;
        int counter = 0;

        for(AccountHolder account: oldFile)
        {
            if (accountID == account.getCustomerId())
            {
                index = counter;
                break;
            }

            counter++;
        }
        if(index > -1)
        {
            oldFile.remove(index);
            update(oldFile);
        }
        else
        {
            System.out.println("This ID doesn't exist!");
        }


    }

    // method updates an account holders info
    public void update(int accountID, AccountHolder accountHolder)
    {
        // get contents of the file
        read(oldFile);

        // update the selected account ID
        int index = 0;
        for(AccountHolder accounts: oldFile)
        {
            if(accounts.getCustomerId() == accountID)
            {
                index = oldFile.indexOf(accounts);
                break;
            }
        }
        String info = String.format("%d,%s,%s,%s,%s,%s,%d",accountHolder.getCustomerId(),accountHolder.getFirstName(),accountHolder.getLastName(),
                accountHolder.getStreet(),accountHolder.getCity(),accountHolder.getState(),accountHolder.getZip());
        String[] cols = info.split(",");
        for(int i = 0; i < cols.length; i++)
        {
            switch(i)
            {
                case 0:
                    oldFile.get(index).setCustomerId(Integer.parseInt(cols[0]));
                    break;
                case 1:
                    oldFile.get(index).setFirstName(cols[1]);
                case 2:
                    oldFile.get(index).setLastName(cols[2]);
                case 3:
                    oldFile.get(index).setStreet(cols[3]);
                case 4:
                    oldFile.get(index).setCity(cols[4]);
                case 5:
                    oldFile.get(index).setState(cols[5]);
                case 6:
                    oldFile.get(index).setZip(Integer.parseInt(cols[6]));
            }
        }

        // rewrite the file
        update(oldFile);

    }

    /*
        Helper methods to support the CRUD methods
     */

    // method is used to rewrite the file
    private void update(ArrayList<AccountHolder> accounts)
    {
        try
        {
            csvWriter= new BufferedWriter(new FileWriter(csvFile));
            for(AccountHolder account: accounts)
            {
                csvWriter.write(String.format("%d,%s,%s,%s,%s,%s,%d",account.getCustomerId(),account.getFirstName(),
                        account.getLastName(),account.getStreet(),account.getCity(),account.getState(),account.getZip()));
                csvWriter.newLine();
            }
            csvWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // checks for double instance of an ID
    private boolean checkID(ArrayList<AccountHolder> accounts, int accountID)
    {
        for(AccountHolder account: accounts)
        {
            if(account.getCustomerId() == accountID)
            {
                return true;
            }
        }
        return false;
    }

    // gets the ID in the file
    private int getID(ArrayList<AccountHolder> accounts, int accountID)
    {
        for(AccountHolder account: accounts)
        {

            if(accountID == account.getCustomerId())
            {
                return account.getCustomerId();
            }
        }
        return -1;
    }

}
