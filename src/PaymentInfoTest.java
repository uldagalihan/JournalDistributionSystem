import static org.junit.Assert.*;

import org.junit.Test;

public class PaymentInfoTest {

    @Test
    public void testIncreasePayment() {
        Subscriber subscriber = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);
        PaymentInfo paymentInfo = new PaymentInfo(subscriber.getDiscountRatio());

        paymentInfo.increasePayment(15.0);

        assertEquals(15.0, paymentInfo.getReceivedPayment(), 0.001);
    }

    @Test
    public void testGetDiscountedAmount() {
        Subscriber subscriber = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);
        PaymentInfo paymentInfo = new PaymentInfo(subscriber.getDiscountRatio());

        double discountedAmount = paymentInfo.getDiscountedAmount(20.0);

        assertEquals(8.0, discountedAmount, 0.001);
    }

    @Test
    public void testIsPaymentComplete() {
        Subscriber subscriber = new Individual("Alihan Uludağ", "Namık Kemal Mahallesi", "1234-5678-9012-3456", 10, 2025, 123);
        PaymentInfo paymentInfo = new PaymentInfo(subscriber.getDiscountRatio());

        paymentInfo.increasePayment(25.0);

        assertTrue(paymentInfo.isPaymentComplete(20.0));
        assertFalse(paymentInfo.isPaymentComplete(30.0));
    }


}
