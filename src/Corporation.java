





import java.util.*;

public class Corporation extends Subscriber {

    private String bankName;
    private int bankCode;
    private int issueDay, issueMonth, issueYear;
    private int accountNumber;
    private List<Subscription> subscriptions;

    public Corporation(String name, String address, String bankName, int bankCode, int issueDay, int issueMonth, int issueYear, int accountNumber) {
        super(name, address);
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.issueDay = issueDay;
        this.issueMonth = issueMonth;
        this.issueYear = issueYear;
        this.accountNumber = accountNumber;
        this.subscriptions = new ArrayList<>();
    }
    

    @Override
    public String getBillingInformation() {
    	
        return "Corporation Subscriber: " + getName() +
                "\nAddress: " + getAddress() +
                "\nBank Name: " + bankName +
                "\nBank Code: " + bankCode +
                "\nIssue Date: " + issueDay + "/" + issueMonth + "/" + issueYear +
                "\nAccount Number: " + accountNumber;
    }

    public String getAddress() {
		
		return super.getAddress();
	}


	public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

	public void subscribeToJournal(Journal journal, int copiesToAdd) {
	    Subscription existingSubscription = getSubscription(journal);

	    if (existingSubscription != null) {
	        existingSubscription.increaseCopies(copiesToAdd);
	    } else {
	        Subscription newSubscription = new Subscription(
	                new DateInfo(1, 2023, 12), copiesToAdd, journal, this);
	        subscriptions.add(newSubscription);
	        journal.addSubscription(newSubscription);
	    }
	}


    public void acceptPaymentForJournal(Journal journal, double amount) {
        Subscription subscription = getSubscription(journal);

        if (subscription != null) {
            subscription.acceptPayment(amount);
            System.out.println("Payment accepted for " + journal.getName() +
                    " - Subscriber: " + getName() +
                    ", Amount: " + amount);
        } else {
            System.out.println("Subscription not found for " + journal.getName() +
                    " - Subscriber: " + getName());
        }
    }

    private Subscription getSubscription(Journal journal) {
        for (Subscription subscription : subscriptions) {
            if (subscription.getJournal().equals(journal)) {
                return subscription;
            }
        }
        return null;
    }


	@Override
	public double getDiscountRatio() {
		// TODO Auto-generated method stub
		return 0;
	}
}
