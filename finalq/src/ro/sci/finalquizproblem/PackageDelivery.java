package ro.sci.finalquizproblem;

import java.util.List;
import java.util.Objects;

/**
 * This class represents the package delivery thread.
 *
 * @author Emanuel Pruker
 */
public class PackageDelivery extends Thread {
    private static final double PROFIT_MULTIPLIER = 3;
    private List<Package> packages;

    /**
     * Class constructor.
     *
     * @param packages
     */
    public PackageDelivery(List<Package> packages) {
        super();
        this.packages = packages;
    }

    /**
     * This method delivers the packages, for each package which goes the same location in the current date will start a thread.
     * Also prints number of packages before starting a thread and the merchandise & profit values for each delivery at "arrival".
     */
    @Override
    public void run() {
        try {
            Package first = packages.stream().findFirst().orElse(null);
            System.out.println(String.format("Delivering %d package(s) to %s...", packages.size(), first.getTargetLocation()));
            Thread.sleep(first.getDistance() * 1000);
            System.out.println(String.format("%d package(s) delivered to %s, merchandise value: %.2f and the profit is %.2f.", packages.size(), first.getTargetLocation(), calculateMerchandiseValue(), calculateProfit()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Summarize the merchandise values of all the packages that are not null in the list.
     *
     * @return the total merchandise value
     */
    private double calculateMerchandiseValue() {
        return packages.stream().filter(Objects::nonNull).mapToDouble(Package::getMerchandiseValue).sum();
    }

    /**
     * Summarize the delivery distance and multiplies with the PROFIT_MULTIPLIER
     *
     * @return the total profit value
     */
    private double calculateProfit() {
        return packages.stream().filter(Objects::nonNull).mapToDouble(Package::getDistance).sum() * PROFIT_MULTIPLIER;
    }
}
