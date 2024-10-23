package States.LandingState;

import Aircraft.Aircraft;

public class LandingState {
    public void triggerLandingState(Aircraft aircraft){
        aircraft.setCurrentLandingState(Yes.Instance());
        System.out.println("In landing mode");
    }
}
