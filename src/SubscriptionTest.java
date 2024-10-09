import static org.junit.Assert.*;

import org.junit.Test;

public class SubscriptionTest {

    @Test
    public void testAcceptPayment() {
        Subscriber subscriber = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);
        Journal journal = new Journal("Dergi1", "1234-5678", 12, 10.0);
        Subscription subscription = new Subscription(new DateInfo(1, 2023, 12), 1, journal, subscriber);

        subscription.acceptPayment(5.0);

        assertEquals(5.0, subscription.getPayment().getReceivedPayment(), 0.001);
    }

    @Test
    public void testCanSend() {
        Subscriber subscriber = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);
        Journal journal = new Journal("Dergi1", "1234-5678", 12, 10.0);
        Subscription subscription = new Subscription(new DateInfo(1, 2023, 12), 1, journal, subscriber);

        subscription.acceptPayment(10.0);

        assertTrue(subscription.canSend(3)); 
    }


}
