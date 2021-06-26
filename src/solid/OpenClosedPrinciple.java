package solid;

import java.util.ArrayList;
import java.util.List;

public class OpenClosedPrinciple {

    private static interface Shape {

    }

    public static class Rectangle implements Shape {
        int width;
        int area;
    }

    public static class Square implements Shape {
        int side;
    }

    public static double computeTotalArea(Shape[] shapes) {

        double area  = 0;

        for ( Shape shape : shapes) {
            if (shape instanceof Rectangle) {
                // compute rectangle area
            } else if (shape instanceof Square) {
                // compute square area
            }
        }

        return area;
    }

    // Solution each class should expose the method to compute the shape

}
