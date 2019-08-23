package factoryMethod;

public class ElectricityBill implements Bill {
	private String name;

	@Override
	public void calculateTotal() {
		System.out.println("Total for electricity bill:...");

	}

}
