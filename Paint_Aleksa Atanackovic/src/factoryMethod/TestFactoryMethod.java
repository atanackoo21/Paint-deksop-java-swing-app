package factoryMethod;

public class TestFactoryMethod {

	public static void main(String[] args) {
		BillProcess bp = new BillProcessFactory();

		bp.storeBill("electricity");
		bp.storeBill("gas");

	}

}
