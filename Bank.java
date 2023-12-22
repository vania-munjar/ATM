import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserUUID(){
        //inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do{
            //generate number
            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            //check to make sure it's unique
            nonUnique = false;
            for(User u: this.users){
                if(uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                }
            }

        }while (nonUnique);

        return uuid;
    }
    public String getNewAccountUUID(){
        //inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do{
            //generate number
            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            //check to make sure it's unique
            nonUnique = false;
            for(Account a: this.accounts){
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                }
            }

        }while (nonUnique);

        return uuid;
    }
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
    public  User addUser(String firstName, String lastName, String pin){
        //create a new User object & add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a savings account for the user
        Account newAccount = new Account("Savings", newUser, this);
        //add to holder & bank lists
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    public User userLogin(String userID, String pin){
        //search through list of users
        for(User u : this.users){
            //check if user ID is correct
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }
        //if we haven't found user nor correct pin
        return null;
    }
    public String getName(){
        return this.name;
    }

}
