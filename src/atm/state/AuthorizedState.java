package atm.state;

import atm.ATM;
import java.util.List;

public class AuthorizedState implements ATMState {
    private final ATM atm;

    public AuthorizedState(ATM atm) { this.atm = atm; }

    @Override
    public void onEnter() {
        System.out.println("Authenticated. Choose an operation: balance, deposit, withdraw, eject.");
    }

    @Override
    public void insertCard(String cardNumber) { System.out.println("Operation in progress. Eject current card first."); }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
        atm.setState(new ReadyState(atm));
    }

    @Override
    public void enterPin(String pin) { System.out.println("Already authenticated."); }

    @Override
    public void checkBalance() {
        double balance = atm.getBankService().getBalance(atm.getActiveAccountId());
        System.out.println("Balance: " + balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) { System.out.println("Invalid amount."); return; }
        atm.getBankService().deposit(atm.getActiveAccountId(), amount);
        System.out.println("Deposited: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) { System.out.println("Invalid amount."); return; }
        boolean ok = atm.getBankService().withdraw(atm.getActiveAccountId(), amount);
        if (ok) System.out.println("Withdrawn: " + amount);
        else System.out.println("Insufficient funds.");
    }

    @Override
    public void cancel() { ejectCard(); }

    @Override
    public void miniStatement() {
        List<String> items = atm.getBankService().getMiniStatement(atm.getActiveAccountId(), 5);
        System.out.println("Mini Statement (last " + items.size() + "):");
        for (String it : items) System.out.println(" - " + it);
    }
}


