package factoryMethod;

public class UtilityBill implements Bill {
	private String name;

	@Override
	public void calculateTotal() {
		System.out.println("Total for utility bill is:...");

	}

}
