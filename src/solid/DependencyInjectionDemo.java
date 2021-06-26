package solid;

public class DependencyInjectionDemo {

    public static interface Printer {
        public void write(String message);
    }

    public static class DisplayPrinter implements Printer{
        public void write(String message) {
            System.out.println(message);
        }
    }

    public static void printMessage(String message, Printer printer) {
        printer.write(message);
    }

}
