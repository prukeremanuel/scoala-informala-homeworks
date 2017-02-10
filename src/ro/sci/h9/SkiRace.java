package ro.sci.h9;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Emanuel Pruker
 */
public class SkiRace {
    private static String[] top3 = {"Winner", "Runner-up", "Third Place"};
    Set<Result> results;

    public SkiRace(String input) {
        if (input == null) {
            throw new IllegalArgumentException("The input cannot be null");
        }
        results = new TreeSet<>(new ResultComparator());
        for (String line : input.split("\n")) {
            results.add(Result.fromString(line));
        }

    }

    public SkiRace(Set<Result> results) {
        this.results = results;
    }

    public String printTop3(){
        StringBuilder builder = new StringBuilder();
        Object[] resultsArray = results.toArray();
        for (int i = 0; i < 3 && i < results.size(); i++) {
            builder.append(String.format("%s - %s", top3[i], resultsArray[i]));
            builder.append("\n");
        }
        return builder.toString();
    }

    private static class ResultComparator implements Comparator<Result> {

        @Override
        public int compare(Result o1, Result o2) {
            return o1.getSkiTimeWithPenalties().compareTo(o2.getSkiTimeWithPenalties());
        }
    }
}
