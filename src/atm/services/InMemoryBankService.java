package atm.services;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InMemoryBankService implements BankService {
    private static class AccountRec {
        String id;
        String cardNumber;
        String pin;
        double balance;
        Deque<String> transactions = new ArrayDeque<>();
        AccountRec(String id, String cardNumber, String pin, double balance) {
            this.id = id; this.cardNumber = cardNumber; this.pin = pin; this.balance = balance;
        }
    }

    private final Map<String, AccountRec> accountsByCard = new HashMap<>();
    private final Map<String, AccountRec> accountsById = new HashMap<>();

    public InMemoryBankService() {
        // seed demo account
        AccountRec acc = new AccountRec("ACC-001", "CARD-1234", "4321", 100.0);
        accountsByCard.put(acc.cardNumber, acc);
        accountsById.put(acc.id, acc);
        acc.transactions.add("OPEN balance=100.0");
    }

    @Override
    public boolean validateCard(String cardNumber) {
        return accountsByCard.containsKey(cardNumber);
    }

    @Override
    public String validatePin(String cardNumber, String pin) {
        AccountRec acc = accountsByCard.get(cardNumber);
        if (acc != null && acc.pin.equals(pin)) {
            return acc.id;
        }
        return null;
    }

    @Override
    public double getBalance(String accountId) {
        return accountsById.get(accountId).balance;
    }

    @Override
    public void deposit(String accountId, double amount) {
        AccountRec acc = accountsById.get(accountId);
        acc.balance += amount;
        acc.transactions.add("DEPOSIT +" + amount + ", bal=" + acc.balance);
    }

    @Override
    public boolean withdraw(String accountId, double amount) {
        AccountRec acc = accountsById.get(accountId);
        if (acc.balance >= amount) {
            acc.balance -= amount;
            acc.transactions.add("WITHDRAW -" + amount + ", bal=" + acc.balance);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getMiniStatement(String accountId, int limit) {
        AccountRec acc = accountsById.get(accountId);
        List<String> list = new ArrayList<>();
        if (acc == null) return list;
        int count = 0;
        for (String item : acc.transactions) {
            list.add(item);
            count++;
        }
        // return last `limit` items
        int from = Math.max(0, list.size() - limit);
        return list.subList(from, list.size());
    }
}


