package solid;

public class InterfaceSegregationPrincipleTest {

    public static interface Animal {
        public void feed();
        // adding method specific to few class cause other classes to override the method - define a new class
    }

    public static class Dog implements Animal {

        @Override
        public void feed() {
            System.out.println("Feeding the dog");
        }
    }

    public static class Tiger implements Animal {

        @Override
        public void feed() {
            System.out.println("Feeding the tiger");
        }
    }

}
