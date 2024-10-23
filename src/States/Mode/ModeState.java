package States.Mode;

import Aircraft.Aircraft;

public class ModeState {
    public void triggerMode(Aircraft aircraft){
        aircraft.setCurrentModeState(OnAir.Instance());
        System.out.println("Aircraft took off!");
    }
}
