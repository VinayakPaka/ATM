package atm.state;

import atm.ATM;

public class CardInsertedState implements ATMState {
    private final ATM atm;

    public CardInsertedState(ATM atm) { this.atm = atm; }

    @Override
    public void onEnter() {
        System.out.println("Please enter your PIN.");
    }

    @Override
    public void insertCard(String cardNumber) { System.out.println("Card already inserted."); }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
        atm.setState(new ReadyState(atm));
    }

    @Override
    public void enterPin(String pin) {
        String accountId = atm.getBankService().validatePin(atm.getActiveCardNumber(), pin);
        if (accountId != null) {
            atm.setActiveAccountId(accountId);
            System.out.println("PIN accepted.");
            atm.setState(new AuthorizedState(atm));
        } else {
            System.out.println("Invalid PIN.");
        }
    }

    @Override
    public void checkBalance() { System.out.println("Enter PIN first."); }

    @Override
    public void deposit(double amount) { System.out.println("Enter PIN first."); }

    @Override
    public void withdraw(double amount) { System.out.println("Enter PIN first."); }

    @Override
    public void cancel() { ejectCard(); }
}


