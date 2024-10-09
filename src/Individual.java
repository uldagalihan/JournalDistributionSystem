import java.util.*;

public class Individual extends Subscriber {

    private String creditCardNr;
    private int expireMonth, expireYear;
    private int CCV;
    private List<Subscription> subscriptions;

    public Individual(String name, String address, String creditCardNr, int expireMonth, int expireYear, int CCV) {
        super(name, address);
        this.creditCardNr = creditCardNr;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.CCV = CCV;
        this.subscriptions = new ArrayList<>();
    }
    
    public String getAddress() {
		return super.getAddress();
	}

    @Override
    public String getBillingInformation() {
        // Örnek bir fatura bilgisi
        return "Individual Subscriber: " + getName() +
                "\nAddress: " + getAddress() +
                "\nCredit Card: " + creditCardNr +
                "\nExpire Date: " + expireMonth + "/" + expireYear +
                "\nCCV: " + CCV;
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
