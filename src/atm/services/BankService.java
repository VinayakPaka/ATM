package atm.services;

import java.util.List;

public interface BankService {
    boolean validateCard(String cardNumber);
    // returns accountId if valid, otherwise null
    String validatePin(String cardNumber, String pin);
    double getBalance(String accountId);
    void deposit(String accountId, double amount);
    boolean withdraw(String accountId, double amount);
    List<String> getMiniStatement(String accountId, int limit);
}


