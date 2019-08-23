package factoryMethod;

public class BillProcessFactory extends BillProcess {

	@Override
	public void createBill(String type) {
		if (type.equals("electricity")) {
			bill = new ElectricityBill();
		} else if (type.equals("gas")) {
			bill = new GasBill();
		} else if (type.equals("utility")) {
			bill = new UtilityBill();
		}

	}

}
