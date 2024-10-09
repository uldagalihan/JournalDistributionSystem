import static org.junit.Assert.*;

import org.junit.Test;

public class IndividualTest {

    @Test
    public void testGetBillingInformation() {
        Individual individual = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);
        String billingInfo = individual.getBillingInformation();

        String expectedInfo = "Individual Subscriber: Alihan Uludağ\n" +
                "Address: Namık Kemal Mahallesi\n" +
                "Credit Card: 1234-5678-9012-3456\n" +
                "Expire Date: 10/2025\n" +
                "CCV: 123";

        assertEquals(expectedInfo, billingInfo);
    }

    @Test
    public void testSubscribeToJournal() {
        Journal journal = new Journal("Dergi1", "123-456", 12, 20.0);
        Individual individual = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);

        individual.subscribeToJournal(journal, 3);

        assertEquals(1, journal.getSubscriptions().size());
        assertEquals(1, individual.getSubscriptions().size());

        Subscription subscription = individual.getSubscriptions().get(0);
        assertEquals(3, subscription.getCopies());
    }


}
