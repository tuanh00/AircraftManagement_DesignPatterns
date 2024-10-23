package JUnitTest;

import AirplaneBuilder.*;
import States.LandingState.*;
import States.EngineState.*;
import States.AircraftState.*;
import States.Mode.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirplaneTests {
    private Airplane airplane;

    @BeforeEach
    void setUp() {
        airplane = new Airplane();
    }

    @Test
    @DisplayName("Create an airplane with default values")
    void testCreateAirplaneWithDefaults() {
        assertNotNull(airplane, "Airplane should be created");
        assertEquals(1, airplane.getAirplaneId(), "First airplane ID should be 1");
        assertEquals(Good.Instance(), airplane.getCurrentAircraftState(), "Default aircraft state should be Good");
        assertEquals(OFF.Instance(), airplane.getCurrentEngineState(), "Default engine state should be Off");
        assertEquals(No.Instance(), airplane.getCurrentLandingState(), "Default landing state should be No");
        assertEquals(OnGround.Instance(), airplane.getCurrentModeState(), "Default mode state should be OnGround");
        assertEquals(0, airplane.getAltitude(), "Default altitude should be 0");
    }

    @Test
    @DisplayName("Increase altitude under safe conditions")
    void testIncreaseAltitudeAsDefault() {
        airplane.increaseAltitude(-1);
        assertEquals(1000, airplane.getAltitude(),
                "Altitude should be increased by 1000 feet.");
    }

    @Test
    @DisplayName("Decrease altitude under safe conditions")
    void testDecreaseAltitudeAsDefault() {
        airplane.increaseAltitude(-1);
        airplane.triggerMode();
        airplane.decreaseAltitude(1000);

        ModeState currentModeState = airplane.getCurrentModeState();
        LandingState currentLandingState = airplane.getCurrentLandingState();

        assertEquals(OnGround.Instance(), currentModeState, "Should be on ground");
        assertEquals(Yes.Instance(), currentLandingState, "Should be in landing");
        assertEquals(0, airplane.getAltitude(),
                "Altitude should be decrease to 0");
    }

    @Test
    @DisplayName("Test for dangerous altitude warning")
    void testDangerousAltitudeWarning() {
        airplane.increaseAltitude(10000);
        AircraftState currentState = airplane.getCurrentAircraftState();
        assertEquals(Good.Instance(), currentState, "Should warn of dangerous altitude at 10000 feet");
    }

    @Test
    @DisplayName("Test for airplane crashed at high altitude")
    void testAirplaneExplosionAtHighAltitude() {
        airplane.increaseAltitude(12000);
        AircraftState currentState = airplane.getCurrentAircraftState();
        assertEquals(Crashed.Instance(), currentState, "Should explode at 12000 feet");
    }

    @Test
    @DisplayName("No actions allowed after crash except repair")
    void testNoActionsAfterCrash() {
        airplane.increaseAltitude(12000); // This should crash the airplane

        // Assert airplane is still crashed
        assertEquals(Crashed.Instance(), airplane.getCurrentAircraftState(), "Should explode at 12000 feet");
        // Then repair and check states
        airplane.repair();
        assertAll(
                () -> assertEquals(1, airplane.getAirplaneId(), "First airplane ID should be 1"),
                () -> assertTrue(airplane.getCurrentAircraftState() instanceof Good, "Airplane should be in Good state after repair."),
                () -> assertTrue(airplane.getCurrentEngineState() instanceof OFF, "Engine should be OFF after repair."),
                () -> assertTrue(airplane.getCurrentLandingState() instanceof No, "Landing state should be NO after repair."),
                () -> assertEquals(0, airplane.getAltitude(), "Altitude should be reset to 0 after repair.")
        );
    }

}
