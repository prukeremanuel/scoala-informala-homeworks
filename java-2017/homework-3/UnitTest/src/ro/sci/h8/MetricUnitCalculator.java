package ro.sci.h8;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Emanuel Pruker
 */
public class MetricUnitCalculator {

    private String input = "";
    private final List<String> operators = new ArrayList<>();

    public MetricUnitCalculator(String[] args) {


        if (args.length < 1) {
         //   LOGGER.warning("No input");
        }
        if (args[0].equals("+") || args[0].equals("-")) {
         //   LOGGER.warning("Invalid input");
        }


        for (String arg : args) {
            if (arg.contains("m")) {
                input += " ";
            }
            input += arg;

            if (arg.equals("+") || arg.equals("-")) {
                operators.add(arg);
            }
        }
    }


    public MetricUnit calculate() {
        String[] units = input.split("\\+|\\-");
        MetricUnit unit1 = MetricUnit.fromString(units[0]);
        for (int i = 0; i < operators.size(); i++) {
            MetricUnit unit = operators.get(i).equals("-") ? MetricUnit.fromString(operators.get(i) + units[i + 1]) : MetricUnit.fromString(units[i + 1]);
            unit1 = unit1.add(unit);

        }
        return unit1;
    }
}

