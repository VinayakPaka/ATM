package atm.state;

import atm.ATM;

public class ReadyState implements ATMState {
    private final ATM atm;

    public ReadyState(ATM atm) { this.atm = atm; }

    @Override
    public void onEnter() {
        System.out.println("ATM Ready. Please insert your card.");
        atm.setActiveCardNumber(null);
        atm.setActiveAccountId(null);
    }

    @Override
    public void insertCard(String cardNumber) {
        if (atm.getBankService().validateCard(cardNumber)) {
            atm.setActiveCardNumber(cardNumber);
            System.out.println("Card accepted.");
            atm.setState(new CardInsertedState(atm));
        } else {
            System.out.println("Invalid card.");
        }
    }

    @Override
    public void ejectCard() { System.out.println("No card to eject."); }

    @Override
    public void enterPin(String pin) { System.out.println("Insert card first."); }

    @Override
    public void checkBalance() { System.out.println("Insert card first."); }

    @Override
    public void deposit(double amount) { System.out.println("Insert card first."); }

    @Override
    public void withdraw(double amount) { System.out.println("Insert card first."); }

    @Override
    public void cancel() { System.out.println("Nothing to cancel."); }
}


