package prototype;

import java.awt.Color;

import mvc.Point;

public class TestPrototype {

	public static void main(String[] args) {
		LineDeep ls = new LineDeep(new Point(10, 10, Color.RED), new Point(20, 20, Color.RED), Color.RED);
		LineDeep lsClone = ls.clone();

		System.out.println(ls.getStartPoint());
		System.out.println(lsClone.getStartPoint());
	}
	
//	Prototype pattern provides a mechanism to copy the original object to a new object 
//	and then modify it according to our needs. Prototype design pattern uses java cloning to copy the object.
//	Suppose we have an Object that loads data from database. Now we need to modify this data 
//	in our program multiple times, so it’s not a good idea to create the Object using new keyword 
//	and load all the data again from database.
//	The better approach would be to clone the existing 
//	object into a new object and then do the data manipulation.
	
}
