package atm;

import atm.services.BankService;
import atm.state.ATMState;
import atm.state.ReadyState;

public class ATM {
    private ATMState state;
    private final BankService bankService;
    private String activeCardNumber;
    private String activeAccountId;
    private String bankName;

    public ATM(BankService bankService) {
        this.bankService = bankService;
        this.state = new ReadyState(this);
    }

    public void setState(ATMState next) {
        this.state = next;
        this.state.onEnter();
    }

    public BankService getBankService() {
        return bankService;
    }

    public String getActiveCardNumber() {
        return activeCardNumber;
    }

    public void setActiveCardNumber(String activeCardNumber) {
        this.activeCardNumber = activeCardNumber;
    }

    public String getActiveAccountId() {
        return activeAccountId;
    }

    public void setActiveAccountId(String activeAccountId) {
        this.activeAccountId = activeAccountId;
    }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    // Delegated operations
    public void insertCard(String cardNumber) { state.insertCard(cardNumber); }
    public void ejectCard() { state.ejectCard(); }
    public void enterPin(String pin) { state.enterPin(pin); }
    public void checkBalance() { state.checkBalance(); }
    public void deposit(double amount) { state.deposit(amount); }
    public void withdraw(double amount) { state.withdraw(amount); }
    public void cancel() { state.cancel(); }
    public void miniStatement() { state.miniStatement(); }
}


