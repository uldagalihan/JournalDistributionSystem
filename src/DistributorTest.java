import static org.junit.Assert.*;
import org.junit.Test;

public class DistributorTest {

    @Test
    public void testAddJournal() {
        Distributor distributor = new Distributor();
        Journal journal = new Journal("Örnek Dergi", "1234-5678", 12, 10.0);

        assertTrue(distributor.addJournal(journal));
    }

    @Test
    public void testAddSubscriber() {
        Distributor distributor = new Distributor();
        Subscriber subscriber = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);

        assertTrue(distributor.addSubscriber(subscriber));
    }


}
