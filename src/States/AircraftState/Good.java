package States.AircraftState;

import Aircraft.Aircraft;

public class Good extends AircraftState{
    private static Good instance = new Good();
    public static Good Instance(){return instance;}
    public void triggerState(Aircraft aircraft){
        aircraft.setCurrentAircraftState(Crashed.Instance());
        System.out.println("Aircraft is crashed...");
    }
}
