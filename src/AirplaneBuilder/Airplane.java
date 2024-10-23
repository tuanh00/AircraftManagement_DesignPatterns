package AirplaneBuilder;

import Aircraft.Aircraft;
import Enum.*;
import States.AircraftState.*;
import States.EngineState.*;
import States.LandingState.*;
import States.Mode.ModeState;
import States.Mode.OnGround;

public class Airplane extends Aircraft {
    private static int lastId = 1; // Static variable to keep track of the last assigned ID

    private final int airplaneID; // Instance variable to store each Airplane's unique ID
    private Type type;
    private Capacity capacity;
    private Engine_Type engineType;
    private int altitude = 0;
    AircraftState currentAircraftState; // Used to indicate Good or Crashed (exploded)
    EngineState currentEngineState; // Used to indicate Start Stop
    LandingState currentLandingState; // Used to indicate landed or take off
    ModeState currentModeState;

    public Airplane() {
        this.airplaneID = lastId++;
        currentAircraftState = Good.Instance();
        currentEngineState = OFF.Instance();
        currentLandingState = No.Instance();
        currentModeState = OnGround.Instance();
    }

    public int getAirplaneId() {
        return airplaneID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    public Engine_Type getEngineType() {
        return engineType;
    }

    public void setEngineType(Engine_Type engineType) {
        this.engineType = engineType;
    }

    public int getAltitude() {
        return altitude;
    }

    // private setAltitude method
    private void setAltitude(int newAltitude) {
        if (newAltitude >= 0) { // Ensure altitude is not negative
            this.altitude = newAltitude;
        } else {
            System.out.println("Altitude cannot be negative.");
        }
    }
    public AircraftState getCurrentAircraftState() {
        return currentAircraftState;
    }

    public void setCurrentAircraftState(AircraftState currentAircraftState) {
        this.currentAircraftState = currentAircraftState;
    }

    public EngineState getCurrentEngineState() {
        return currentEngineState;
    }

    public void setCurrentEngineState(EngineState currentEngineState) {
        this.currentEngineState = currentEngineState;
    }

    public LandingState getCurrentLandingState() {
        return currentLandingState;
    }

    public void setCurrentLandingState(LandingState currentLandingState) {
        this.currentLandingState = currentLandingState;
    }
    public void setCurrentModeState(ModeState currentModeState) {
        this.currentModeState = currentModeState;
    }
    public ModeState getCurrentModeState() {return currentModeState;}

    public void triggerEngine(){ //depends on current engine state, if state is active, this method cannot be called
        currentEngineState.triggerEngine(this);
    }
    public void triggerMode(){//depends on current stance state, if already took off, method cannot be
        this.currentModeState.triggerMode(this);
    }
    public void triggerState(){//depends on current aircraft state, if damaged, then repair. if repaired, then damage
        this.currentAircraftState.triggerState(this);
    }
    public void triggerLandingState(){this.currentLandingState.triggerLandingState(this);}

    public void resetToDefault() {
        currentAircraftState = Good.Instance();
        currentEngineState = OFF.Instance();
        currentLandingState = No.Instance();
        currentModeState = OnGround.Instance();
        altitude = 0;
    }

    public void repair() {
        // Repairing is used if the airplane has crashed (exploded)
        if (currentAircraftState == Crashed.Instance()) {
            resetToDefault();
            System.out.println("Airplane repaired and reset to default.");
        }
    }

    //private setter method for altitude
    public void increaseAltitude(int amount) {
        // If amount is 0 or less, default to 1000
        int changeAmount = (amount <= 0) ? 1000 : amount;
        this.altitude += changeAmount;
        System.out.println("Altitude increased to: " + this.altitude + " feet.");

        if (this.altitude >= 12000) {
            triggerState(); //airplane is crashed
        } else if (this.altitude >= 10000) {
            System.out.println("Dangerous Altitude");
        }
    }

    public void decreaseAltitude(int amount) {
        if (this.altitude == 0) {
            System.out.println("Airplane is already on the ground.");
        } else {
            // If amount is 0, decrease by 1000 as default
            int decreaseAmount = (amount == 0) ? 1000 : amount;
            this.altitude -= decreaseAmount;

            if (this.altitude < 0) {
                this.altitude = 0;
            }

            System.out.println("Altitude decreased to: " + this.altitude + " feet.");

            if (this.altitude == 0) {
                triggerMode(); // Assuming this changes the airplane's mode to on ground
                triggerLandingState(); // Assuming this sets the landing state appropriately
                System.out.println("Airplane landed.");
            }
        }
    }

    //Airplane toString() override method
    @Override
    public String toString() {
        String status = currentAircraftState==Crashed.Instance() ? "Crashed" : "Operational";
        String altitudeStatus = (altitude == 0) ? "Landed" : altitude + " feet";
        return String.join("\n",
                "----------------------------------\nAirplane Status Report\n----------------------------------",
                "ID: " + airplaneID,
                "Type: " + (type != null ? type.toString() : "Not specified"),
                "Capacity: " + (capacity != null ? capacity.toString() : "Not specified"),
                "Engine Type: " + (engineType != null ? engineType.toString() : "Not specified"),
                "Altitude: " + altitudeStatus,
                "Current State: " + status);
    }
}
