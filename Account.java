import java.util.ArrayList;

public class Account {
    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank theBank){
        //set acc name & holder
        this.name = name;
        this.holder = holder;

        //get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        //init transactions
        this.transactions = new ArrayList<Transaction>();

    }

    public String getUUID(){
        return this.uuid;
    }

    public String getSummaryLine(){
        //get account's balance
        double balance = this.getBalance();

        //format summary line based on whether the balance is negative
        if(balance >= 0){
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else{
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }
    //hardcoded $ unconvential though

    public double getBalance(){
        double balance = 0;
        for(Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }
    public void printTransHistory(){
        System.out.printf("\nTransaction history for account %s \n", this,uuid);
        for(int t = this.transactions.size() - 1; t >= 0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo){
        //create a new transaction object & add it to our list
        Transaction newTrans = new Transaction(amount,memo, this);
        this.transactions.add(newTrans);
    }

}
