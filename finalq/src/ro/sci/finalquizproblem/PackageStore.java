package ro.sci.finalquizproblem;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the package store.
 *
 * @author Emanuel Pruker
 */
public class PackageStore {
    private List<Package> packages = new ArrayList<>();
    private LocalDate currentDate;

    /**
     * Class constructor which sets the current date of delivery.
     *
     * @param currentdate the current date of delivery
     */
    public PackageStore(LocalDate currentdate) {
        this.currentDate = currentdate;
    }

    /**
     * Adds a package to the packages list.
     *
     * @param p current package
     */
    public void addPackage(Package p) {
        if (p != null) {
            packages.add(p);
        }
    }

    /**
     * Creates and starts each thread for each key of the map which has the target locations as keys
     * and lists with packages as values.
     */
    public void dispach() {
        Map<String, List<Package>> sortedPackages = sortPackagesForCurrentDate();
        List<PackageDelivery> deliveries = new ArrayList<>();

        for (String key : sortedPackages.keySet()) {
            PackageDelivery delivery = new PackageDelivery(sortedPackages.get(key));
            deliveries.add(delivery);
            delivery.start();
        }

        deliveries.forEach(d -> {
            try {
                d.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private Map<String, List<Package>> sortPackagesForCurrentDate() {
        Map<String, List<Package>> result = new HashMap<>();
        for (Package p : packages) {
            if (currentDate.equals(p.getDeliveryDate())) {
                if (!result.containsKey(p.getTargetLocation())) {
                    result.put(p.getTargetLocation(), new ArrayList<>());
                }
                result.get(p.getTargetLocation()).add(p);
            }
        }
        return result;
    }
}
