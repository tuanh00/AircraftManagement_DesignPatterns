package Aircraft;

import States.AircraftState.*;
import States.EngineState.*;
import States.LandingState.*;
import States.Mode.*;
public abstract class Aircraft {
    abstract public void setCurrentAircraftState(AircraftState currentAircraftState);
    abstract public void setCurrentEngineState(EngineState currentEngineState);
    abstract public void setCurrentLandingState(LandingState currentLandingState);
    abstract public void setCurrentModeState(ModeState currentModeState) ;
    abstract public void increaseAltitude(int increase);
    abstract public void decreaseAltitude(int decrease);
}
