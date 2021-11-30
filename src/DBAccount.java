import java.io.*;
import java.util.ArrayList;

public class DBAccount
{
    private final String csvFile;
    private BufferedReader csvReader;
    private BufferedWriter csvWriter;
    private ArrayList<String> oldFile;

    public DBAccount(String csvFile)
    {
        this.csvFile = csvFile;
        this.oldFile = new ArrayList<>();
    }

    // reads the file
    public void read(ArrayList<String> rows)
    {
        if(rows.isEmpty())
        {
            try
            {
                csvReader= new BufferedReader(new FileReader(csvFile));
                String line;
                while ((line = csvReader.readLine()) != null) {

                    rows.add(line);
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
            rows.clear();
            try
            {
                csvReader= new BufferedReader(new FileReader(csvFile));
                String line;
                while ((line = csvReader.readLine()) != null) {

                    rows.add(line);
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
    public void create(String newHolder) {

        String[] info = newHolder.split(",");
        read(oldFile);
        if (!checkID(oldFile, Integer.parseInt(info[0]))) {
            oldFile.add(newHolder);
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

        for(String account: oldFile)
        {
            if(counter > 0)
            {
                String[] acct = account.split(",");
                if (acct[0].equals(String.valueOf(accountID)))
                {
                    index = counter;
                }
            }
            counter++;
        }
        if(index > 0)
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
    public void update(int accountID, String info)
    {
        // get contents of the file
        read(oldFile);

        // update the selected account ID
        int index = 0;
        for(String accounts: oldFile)
        {
            String[] cols = accounts.split(",");
            if(cols[0].equals(String.valueOf(accountID)))
            {
                index = oldFile.indexOf(accounts);
                break;
            }
        }

        // replace the string at the index
        oldFile.set(index,info);

        // rewrite the file
        update(oldFile);

    }

    /*
        Helper methods to support the CRUD methods
     */

    // method is used to rewrite the file
    private void update(ArrayList<String> accounts)
    {
        try
        {
            csvWriter= new BufferedWriter(new FileWriter(csvFile));
            for(String account: accounts)
            {
                csvWriter.write(account);
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
    private boolean checkID(ArrayList<String> accounts, int accountID)
    {
        for(String account: accounts)
        {
            String[] cols = account.split(",");
            if(String.valueOf(accountID).equals(cols[0]))
            {
                return true;
            }
        }
        return false;
    }

    // gets the ID in the file
    private int getID(ArrayList<String> accounts, int accountID)
    {
        for(String account: accounts)
        {
            String[] cols = account.split(",");
            if(String.valueOf(accountID).equals(cols[0]))
            {
                return Integer.parseInt(cols[0]);
            }
        }
        return -1;
    }

}
