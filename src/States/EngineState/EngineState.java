package States.EngineState;
import Aircraft.Aircraft;
public class EngineState {
    public void triggerEngine(Aircraft aircraft){
        aircraft.setCurrentEngineState(ON.Instance());
        System.out.println("Engine turned ON successfully.");

    }
}
