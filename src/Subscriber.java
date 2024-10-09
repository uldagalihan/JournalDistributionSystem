public abstract class Subscriber {

    private String name, address;

    public Subscriber(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public abstract String getBillingInformation();

    public String getName() {
        return name;
    }
    public String getAddress() {
    	return address;
    }

	public abstract double getDiscountRatio();
}
