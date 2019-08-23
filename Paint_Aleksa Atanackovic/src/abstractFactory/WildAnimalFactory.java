package abstractFactory;

public class WildAnimalFactory extends AnimalFactory {

	@Override
	public Animal createAnimal(String type) {
		switch (type) {
		case "bear":
			return new Bear();
		case "dinosaur":
			return new Dinosaur();
		default:
			return null;
		}
	}

}
