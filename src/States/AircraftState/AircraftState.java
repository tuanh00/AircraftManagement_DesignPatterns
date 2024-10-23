package States.AircraftState;
import Aircraft.Aircraft;
public class AircraftState {
    public void triggerState(Aircraft aircraft){
        aircraft.setCurrentAircraftState(Good.Instance());
        System.out.println("Aircraft is repaired!");
    }
}
