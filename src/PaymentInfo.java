public class PaymentInfo {

    private final double discountRatio;
    private double receivedPayment;

    public PaymentInfo(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    public void increasePayment(double amount) {
        receivedPayment += amount;
    }

    public double getReceivedPayment() {
        return receivedPayment;
    }

    public double getDiscountedAmount(double amount) {
        return amount * (1 - discountRatio);
    }

    public boolean isPaymentComplete(double requiredAmount) {
        return receivedPayment >= requiredAmount;
    }
}
