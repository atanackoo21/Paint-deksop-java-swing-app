package abstractFactory;

public class TestAbstractFactory {

	public static void main(String[] args) {
		AnimalFactory afd = FactoryOfAnimalFactory.createAnimalFactory("domestic");
		AnimalFactory afw = FactoryOfAnimalFactory.createAnimalFactory("wild");

		Animal cow = afd.createAnimal("cow");
		Animal dog = afd.createAnimal("dog");
		Animal bear = afw.createAnimal("bear");
		Animal dinosaur = afw.createAnimal("dinosaur");

		System.out.println(cow.goes());
		System.out.println(dog.goes());
		System.out.println(bear.goes());
		System.out.println(dinosaur.goes());
	}

}
