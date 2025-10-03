public class App {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.println("Select Bank:\n1) DemoBank\n2) OtherBank (same demo backend)\nEnter choice: ");
        String bankChoice = sc.nextLine();
        String bankName = "DemoBank";
        var bankService = new atm.services.InMemoryBankService();
        if ("2".equals(bankChoice)) bankName = "OtherBank";

        var atm = new atm.ATM(bankService);
        atm.setBankName(bankName);

        System.out.println("Welcome to " + bankName + " ATM");
        // Hardcoded test credentials for quick testing
        String card = "CARD-1234";
        System.out.println("Using test card: " + card);
        atm.insertCard(card);
        if (atm.getActiveCardNumber() == null) {
            System.out.println("Session ended (invalid card).");
            return;
        }
        String pin = "4321";
        System.out.println("Using test PIN: " + pin);
        atm.enterPin(pin);

        boolean running = true;
        while (running) {
            System.out.println("\nSelect Operation:");
            System.out.println("1) Balance");
            System.out.println("2) Deposit");
            System.out.println("3) Withdraw");
            System.out.println("4) Mini Statement");
            System.out.println("5) Eject Card");
            System.out.println("6) Cancel/Exit");
            System.out.print("Choice: ");
            String c = sc.nextLine();
            switch (c) {
                case "1":
                    atm.checkBalance();
                    break;
                case "2":
                    System.out.print("Amount to deposit: ");
                    try { double amt = Double.parseDouble(sc.nextLine()); atm.deposit(amt); } catch (Exception e) { System.out.println("Invalid amount."); }
                    break;
                case "3":
                    System.out.print("Amount to withdraw: ");
                    try { double amt = Double.parseDouble(sc.nextLine()); atm.withdraw(amt); } catch (Exception e) { System.out.println("Invalid amount."); }
                    break;
                case "4":
                    atm.miniStatement();
                    break;
                case "5":
                    atm.ejectCard();
                    running = false;
                    break;
                case "6":
                    atm.cancel();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}


