package atm.state;

public interface ATMState {
    default void onEnter() {}

    void insertCard(String cardNumber);
    void ejectCard();
    void enterPin(String pin);
    void checkBalance();
    void deposit(double amount);
    void withdraw(double amount);
    void cancel();
    default void miniStatement() { System.out.println("Mini statement not available in this state."); }
}


