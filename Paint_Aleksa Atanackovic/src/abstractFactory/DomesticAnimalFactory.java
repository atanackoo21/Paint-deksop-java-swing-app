package abstractFactory;

public class DomesticAnimalFactory extends AnimalFactory {

	@Override
	public Animal createAnimal(String type) {
		if (type.equals("cow")) {
			return new Cow();
		} else if (type.equals("dog")) {
			return new Dog();
		} else {
			return null;
		}
	}

}
