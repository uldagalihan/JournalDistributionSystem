import static org.junit.Assert.*;

import org.junit.Test;

public class CorporationTest {

    @Test
    public void testGetBillingInformation() {
        Corporation corporation = new Corporation("ABC Corp", "456 Business St.", "ABC Bank", 123, 15, 5, 2022, 789);
        String billingInfo = corporation.getBillingInformation();

        String expectedInfo = "Corporation Subscriber: ABC Corp\n" +
                "Address: 456 Business St.\n" +
                "Bank Name: ABC Bank\n" +
                "Bank Code: 123\n" +
                "Issue Date: 15/5/2022\n" +
                "Account Number: 789";

        assertEquals(expectedInfo, billingInfo);
    }

    @Test
    public void testSubscribeToJournal() {
        Journal journal = new Journal("Tech Magazine", "123-456", 12, 20.0);
        Corporation corporation = new Corporation("ABC Corp", "456 Business St.", "ABC Bank", 123, 15, 5, 2022, 789);

        corporation.subscribeToJournal(journal, 2);

        assertEquals(1, journal.getSubscriptions().size());
        assertEquals(1, corporation.getSubscriptions().size());
        
    }


}
