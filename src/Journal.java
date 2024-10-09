import java.util.*;

public class Journal {

    private String name, issn;
    private int frequency;
    private double issuePrice;

    private List<Subscription> subscriptions;

    public Journal(String name, String issn, int frequency, double issuePrice) {
        this.name = name;
        this.issn = issn;
        this.frequency = frequency;
        this.issuePrice = issuePrice;
        this.subscriptions = new ArrayList<>();
    }

    public boolean addSubscription(Subscription sub) {
        if (!subscriptions.contains(sub)) {
            subscriptions.add(sub);
            return true;
        }
        return false;
    }

    public String getIssn() {
        return issn;
    }

	public String getName() {
		
		return name;
	}

	public double getIssuePrice() {
		
		return issuePrice;
	}

	public List<Subscription> getSubscriptions() {
		
		return subscriptions;
	}
	
	public Subscription getSubscription(Subscriber subscriber) {
        for (Subscription subscription : subscriptions) {
            if (subscription.getSubscriber() == subscriber) {
                return subscription;
            }
        }
        return null;
    }

}
