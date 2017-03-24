package ro.sci.finalquizproblem;

import java.time.LocalDate;

/**
 * @author Emanuel Pruker
 */
public class Main {

    public static void main(String[] args) {
        PackageStore packageStore = new PackageStore(LocalDate.of(2017, 1, 1));

        packageStore.addPackage(new Package("Cluj", 20, 100.1, LocalDate.of(2017, 1, 1)));
        packageStore.addPackage(new Package("Cluj", 20, 100.1, LocalDate.of(2017, 1, 2)));
        packageStore.addPackage(new Package("Timisoara", 10, 3050, LocalDate.of(2017, 1, 1)));
        packageStore.addPackage(new Package("Cluj", 20, 100.1, LocalDate.of(2017, 1, 1)));
        packageStore.addPackage(new Package("Iasi", 40, 123, LocalDate.of(2017, 1, 1)));
        packageStore.addPackage(new Package("Cluj", 20, 100.1, LocalDate.of(2017, 1, 1)));
        packageStore.addPackage(new Package("Brasov", 33, 100.1, LocalDate.of(2017, 1, 1)));
        packageStore.addPackage(new Package("Bucuresti", 140, 1230, LocalDate.of(2017, 1, 2)));

        packageStore.dispach();
    }
}
