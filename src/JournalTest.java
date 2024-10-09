import static org.junit.Assert.*;

import org.junit.Test;

public class JournalTest {

    @Test
    public void testAddSubscription() {
        Journal journal = new Journal("Dergi1", "1234-5678", 12, 10.0);
        Subscriber subscriber = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);
        Subscription subscription = new Subscription(new DateInfo(1, 2023, 12), 1, journal, subscriber);

        assertTrue(journal.addSubscription(subscription));
    }

    @Test
    public void testGetSubscription() {
        Journal journal = new Journal("Dergi1", "1234-5678", 12, 10.0);
        Subscriber subscriber = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);
        Subscription subscription = new Subscription(new DateInfo(1, 2023, 12), 1, journal, subscriber);

        journal.addSubscription(subscription);

        assertNotNull(journal.getSubscription(subscriber));
    }


}
