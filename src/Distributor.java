import java.io.*;
import java.util.*;

import javax.swing.JTextArea;

public class Distributor implements Serializable {

    private static final long serialVersionUID = 1L;
	private Hashtable<String, Journal> journals;
    private Vector<Subscriber> subscribers;

    public Distributor() {
        this.journals = new Hashtable<>();
        this.subscribers = new Vector<>();
    }
    
    public Hashtable<String, Journal> getJournals() {
        return journals;
    }

    public Vector<Subscriber> getSubscribers() {
        return subscribers;
    }

    public boolean addJournal(Journal j) {
        if (!journals.containsKey(j.getIssn())) {
            journals.put(j.getIssn(), j);
            return true;
        }
        return false;
    }

    public Journal searchJournal(String issn) {
        return journals.get(issn);
    }

    public boolean addSubscriber(Subscriber s) {
        if (!subscribers.contains(s)) {
            subscribers.add(s);
            return true;
        }
        return false;
    }

    public Subscriber searchSubscriber(String name) {
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getName().equals(name)) {
                return subscriber;
            }
        }
        return null;
    }

    public boolean addSubscription(String issn, Subscriber s, Subscription newSubscription) {
        Journal journal = journals.get(issn);

        if (journal != null && subscribers.contains(s)) {
            // Aboneliğin daha önce eklenip eklenmediğini kontrol et
            Subscription existingSubscription = journal.getSubscription(s);

            if (existingSubscription != null) {
                // Eğer zaten var olan bir abonelik varsa kopya sayısını artır
                existingSubscription.increaseCopies(newSubscription.getCopies());
            } else {
                // Yeni bir abonelik ekleniyor
                return journal.addSubscription(newSubscription);
            }
        }
        return false;
    }

    public void listAllSendingOrders(int month, int year) {
        System.out.println("Sending Orders for " + month + "/" + year + ":");

        for (Journal journal : journals.values()) {
            List<Subscription> activeSubscriptions = new ArrayList<>();

            for (Subscription subscription : journal.getSubscriptions()) {
                if (subscription.canSend(month)) {
                    activeSubscriptions.add(subscription);
                }
            }

            if (!activeSubscriptions.isEmpty()) {
                System.out.println("Journal: " + journal.getName());
                for (Subscription subscription : activeSubscriptions) {
                    System.out.println(" - Subscriber: " + subscription.getSubscriber().getName());
                }
            }
        }
    }

    public void listSendingOrders(int month, int year, String issn) {
        Journal journal = journals.get(issn);

        if (journal != null) {
            System.out.println("Sending Orders for " + journal.getName() + " - " + month + "/" + year + ":");

            List<Subscription> activeSubscriptions = new ArrayList<>();

            for (Subscription subscription : journal.getSubscriptions()) {
                if (subscription.canSend(month)) {
                    activeSubscriptions.add(subscription);
                }
            }

            if (!activeSubscriptions.isEmpty()) {
                for (Subscription subscription : activeSubscriptions) {
                    System.out.println(" - Subscriber: " + subscription.getSubscriber().getName());
                }
            }
        }
    }


    public void listIncompletePayments() {
        System.out.println("Incomplete Payments:");

        for (Subscriber subscriber : subscribers) {
            for (Journal journal : journals.values()) {
                Subscription subscription = journal.getSubscription(subscriber);

                if (subscription != null && !subscription.canSend()) {
                    double remainingAmount = journal.getIssuePrice() - subscription.getPayment().getReceivedPayment();
                    System.out.println("Subscriber: " + subscriber.getName() +
                            ", Journal: " + journal.getName() +
                            ", Remaining Amount: " + remainingAmount +
                            ", Subscription Date Range: " + subscription.getDateInfo().toString());
                }
            }
        }
    }


    public void listSubscriptions(String subscriberName, JTextArea resultArea) {
        Subscriber subscriber = searchSubscriber(subscriberName);

        if (subscriber != null) {
            resultArea.append("Subscriptions for " + subscriber.getName() + ":\n");

            for (Journal journal : journals.values()) {
                Subscription subscription = journal.getSubscription(subscriber);

                if (subscription != null) {
                    resultArea.append(" - Journal: " + journal.getName() +
                            ", Copies: " + subscription.getCopies() +
                            ", Payment: " + subscription.getPayment().getReceivedPayment() + "\n");
                }
            }
        }
    }


    public void listSubscriptionsByISSN(String issn, JTextArea resultArea) {
        Journal journal = searchJournal(issn);

        if (journal != null) {
            resultArea.append("Subscriptions for " + journal.getName() + ":\n");

            for (Subscriber subscriber : subscribers) {
                Subscription subscription = journal.getSubscription(subscriber);

                if (subscription != null) {
                    resultArea.append(" - Subscriber: " + subscriber.getName() +
                            ", Copies: " + subscription.getCopies() +
                            ", Payment: " + subscription.getPayment().getReceivedPayment() + "\n");
                }
            }
        }
    }


    public void saveState(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("State saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


public void loadState(String filename) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
        Distributor loadedDistributor = (Distributor) ois.readObject();
        this.journals = loadedDistributor.journals;
        this.subscribers = loadedDistributor.subscribers;
        System.out.println("State loaded successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}



public String report() {
	String rapor= "Generating Report...";
	rapor +=" Number of Journals: " + journals.size();
	rapor +=" Number of Subscribers: " + subscribers.size();

    return rapor;
}


}




