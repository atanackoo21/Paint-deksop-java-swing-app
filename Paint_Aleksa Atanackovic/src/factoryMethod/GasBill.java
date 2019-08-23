package factoryMethod;

public class GasBill implements Bill {
	private String name;

	@Override
	public void calculateTotal() {
		System.out.println("Total for gas bill is:...");

	}

}
