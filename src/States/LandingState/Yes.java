package States.LandingState;

import Aircraft.Aircraft;

public class Yes extends LandingState{
    private static Yes instance = new Yes();
    public static Yes Instance(){return instance;}
    public void triggerLandingState(Aircraft aircraft){
        aircraft.setCurrentLandingState(No.Instance());
        System.out.println("Not in landing mode");
    }
}
