package ex1;

import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.annotation.Before;
import ch.usi.dag.disl.marker.BodyMarker;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Instrumentation {

    // Thread-safe counters for instance field accesses
    private static final AtomicInteger instanceFieldReads = new AtomicInteger(0);
    private static final AtomicInteger instanceFieldWrites = new AtomicInteger(0);

    // Thread-safe set for unique static field accesses
    private static final ConcurrentHashMap<String, Boolean> uniqueStaticFields = new ConcurrentHashMap<>();

    // Increment the counter for instance field reads
    @Before(marker = BodyMarker.class, guard = InstanceFieldReadGuard.class)
    public static void onInstanceFieldRead() {
        instanceFieldReads.incrementAndGet();
    }

    // Increment the counter for instance field writes
    @Before(marker = BodyMarker.class, guard = InstanceFieldWriteGuard.class)
    public static void onInstanceFieldWrite() {
        instanceFieldWrites.incrementAndGet();
    }

    // Track unique static fields accessed
    @Before(marker = BodyMarker.class, guard = StaticFieldAccessGuard.class)
    public static void onStaticFieldAccess(String fieldName) {
        uniqueStaticFields.putIfAbsent(fieldName, true);
    }

    // Print the results at the end of application execution
    @After(marker = BodyMarker.class)
    public static void printResults() {
        System.out.println("Reads from instance fields: " + instanceFieldReads.get());
        System.out.println("Writes to instance fields: " + instanceFieldWrites.get());
        System.out.println("Unique static fields accessed: " + uniqueStaticFields.size());
    }
}
