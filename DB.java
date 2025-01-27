import java.util.ArrayList;
import java.util.List;

public class DB {
    private List<Vehicle> vehicles;

    public DB() {
        this.vehicles = new ArrayList<>();
    }
    
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
