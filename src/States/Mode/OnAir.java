package States.Mode;

import Aircraft.Aircraft;

public class OnAir extends ModeState{
    private static OnAir instance = new OnAir();
    public static OnAir Instance(){return instance;}
    public void triggerMode(Aircraft aircraft){
        aircraft.setCurrentModeState(OnGround.Instance());
        System.out.println("Aircraft landed!");
    }
}
