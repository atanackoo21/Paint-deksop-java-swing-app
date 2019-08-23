package factoryMethod;

public abstract class BillProcess {
	protected Bill bill;

	public void storeBill(String type) {
		createBill(type);

		// obrada
		bill.calculateTotal();
	}

	public abstract void createBill(String type);

}
