import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Garage {

    private Protocol_Garage protocol;
    private List<Vehicle> vehicles;
    private int currentIndex = 0;
    private int remainingFixTime = 0;
    private ScheduledExecutorService executor;

    public Garage(Protocol_Garage protocol, List<Vehicle> vehicles) {
        this.protocol = protocol;
        this.vehicles = vehicles;
    }

    public void start() {
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> tick(), 0, 1, TimeUnit.SECONDS);
    }

    private void tick() {
        if (currentIndex < vehicles.size()) {
            Vehicle currentVehicle = vehicles.get(currentIndex);

            if (remainingFixTime == 0) {
                remainingFixTime = currentVehicle.getFixTime();
                System.out.println("מתקן את: " + currentVehicle.getName());
            }
            remainingFixTime--;

            if (remainingFixTime == 0) {
                if (currentVehicle instanceof Fixable) {
                    ((Fixable) currentVehicle).fixed();
                }
                currentIndex++;
            }
        } else {
            System.out.println("כל כלי הרכב תוקנו!");
            protocol.fixed();
            stop();
        }
    }

    private void stop() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            System.out.println("המוסך נעצר ");
        }
    }
}
