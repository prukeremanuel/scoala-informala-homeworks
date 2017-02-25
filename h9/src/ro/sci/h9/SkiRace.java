package ro.sci.h9;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents a ski race.
 *
 * @author Emanuel Pruker
 */
public class SkiRace {
    private String[] top3 = {"Winner", "Runner-up", "Third Place"};
    private Set<Result> results = new TreeSet<Result>(new ResultComparator());

    /**
     * Prints the top 3 results of the race.
     */
    public String printTop3() {
        Iterator<Result> iterator = results.iterator();
        int i = 0;
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            if (i < 3) {
                System.out.println(String.format("%s - %s", top3[i], next));
                builder.append(String.format("%s - %s", top3[i], next)).append("\n");

            }
            i++;
        }
        return builder.toString();
    }

    /**
     * Adds a result to the set
     *
     * @param s the string used to build a result
     */
    public void addResult(String s) {
        try {
            Result result = Result.fromString(s);
            results.add(result);
        } catch (Exception e) {
            System.out.println("Could not add result for: " + s + " because " + e);
        }

    }

    private static class ResultComparator implements Comparator<Result> {

        @Override
        public int compare(Result o1, Result o2) {
            return o1.getSkiTimeWithPenalties().compareTo(o2.getSkiTimeWithPenalties());
        }
    }
}
