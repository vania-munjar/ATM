import java.security.NoSuchAlgorithmException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {
    private String firstName;
    private String lastName;
    private String uuid;// unique universal identifier
    private byte pinHash[];// MD5 hash of user's pin
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank theBank) {
        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //store pin's MD5 hash for security reasons
        try {
            MessageDigest  md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
            // gettting memory of bin object & digesting them through our algorithm
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        //get a new uuid for the user
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //print out log msg
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, uuid);

    }

    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
    public String getUUID(){
        return this.uuid;
    }

    public boolean validatePin(String aPin){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }
    public String getFirstName(){
         return this.firstName;
    }

    public void printAccountsSummary(){
        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for(int a = 0; a < this.accounts.size(); a++){
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }
    public int numAccounts(){
        return this.accounts.size();
    }
    public void printAcctTransHistory(int acctIdx){
        this.accounts.get(acctIdx).printTransHistory();
    }
    public double getAcctBalance(int acctIdx){
        return this.accounts.get(acctIdx).getBalance();
    }

    public String getAcctUUID(int acctIdx){
        return this.accounts.get(acctIdx).getUUID();
    }

    public void addAcctTransaction(int acctIdx, double amount, String memo){
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }

}