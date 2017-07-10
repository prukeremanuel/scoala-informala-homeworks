package ro.sci.h8;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Emanuel Pruker
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    public static void main(String[] args) {

//        Scanner reader = new Scanner(System.in);
//        System.out.println("Enter a distance: ");
//        String input1 = reader.nextLine();
//        MetricUnit unit1 = MetricUnit.fromString(input1);
//        System.out.println("Enter the second distance: ");
//        String input2 = reader.nextLine();
//        MetricUnit unit2 = MetricUnit.fromString(input2);
//        System.out.println("Enter the third distance: ");
//        String input3 = reader.nextLine();
//        MetricUnit unit3 = MetricUnit.fromString(input3);
//
//        MetricUnit result = unit1.add(unit2).add(unit3);
//        System.out.println(result);
try {
    MetricUnitCalculator calculator = new MetricUnitCalculator(args);
    MetricUnit result = calculator.calculate();
  //  LOGGER.
} catch (IllegalArgumentException e) {

        }
    }
}