import java.util.Date;

public class Subscription {

    private final DateInfo dates;
    private PaymentInfo payment;
    private int copies=0;
    private final Journal journal;
    private final Subscriber subscriber;

    public Subscription(DateInfo dates, int copies, Journal journal, Subscriber subscriber) {
        this.dates = dates;
        this.copies = copies;
        this.journal = journal;
        this.subscriber = subscriber;
        this.payment = new PaymentInfo(subscriber.getDiscountRatio());
    }

    public void acceptPayment(double amount) {
        payment.increasePayment(amount);
    }

    public boolean canSend(int issueMonth) {
        return payment.isPaymentComplete(journal.getIssuePrice())
                && dates.isSubscriptionActive(issueMonth);
    }
    
    public int getCopies() {
        return copies;
    }
    
    public PaymentInfo getPayment() {
        return payment;
    }
    
    public boolean canSend() {
        Date currentDate = new Date(); // Şu anki tarih
        int currentMonth = currentDate.getMonth() + 1;

        return dates.getStartMonth() <= currentMonth && currentMonth <= dates.getEndMonth() &&
                payment.getReceivedPayment() >= (copies * journal.getIssuePrice());
    }



    public void increaseCopies(int additionalCopies) {
        copies+=additionalCopies;
    }

    public Journal getJournal() {
        return journal;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }
    
    public DateInfo getDateInfo() {
        return dates;
    }
    
    
}
