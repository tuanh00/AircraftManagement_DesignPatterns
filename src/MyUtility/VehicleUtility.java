package MyUtility;

import AbstractFactory.AbstractFactory;
import AirplaneBuilder.Airplane;
import AirshipBuilder.Airship;
import Enum.Type;
import States.AircraftState.Crashed;
import States.EngineState.ON;
import States.LandingState.Yes;
import States.Mode.OnGround;

import java.util.Scanner;

public class VehicleUtility {
    public static void run(){
        AbstractFactory factory = AbstractFactory.factoryMethod(Type.INTERNATIONAL);
        Airplane internationalAirplane = factory.makeAirplane();
        factory = AbstractFactory.factoryMethod(Type.INTERNATIONAL);
        Airship internationalAirship = factory.makeAirship();
        factory = AbstractFactory.factoryMethod(Type.REGIONAL);
        Airplane regionalAirplane = factory.makeAirplane();
        factory = AbstractFactory.factoryMethod(Type.REGIONAL);
        Airship regionalAirship = factory.makeAirship();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=================================");
            System.out.println("Aircraft Control System");
            System.out.println("=================================");
            System.out.println("1. Airplane Operations");
            System.out.println("2. Airship Operations");
            System.out.println("3. Exit");
            System.out.print("\nSelect a vehicle: ");

            String vehicleChoice = scanner.nextLine();

            switch (vehicleChoice) {
                case "1":
                    while(true)
                    {
                        if(!airplaneTypeMenu(scanner, regionalAirplane, internationalAirplane)) {
                            break;
                        }
                    }
                    break;
                case "2":
                    while(true)
                    {
                        if(!airshipTypeMenu(scanner, regionalAirship, internationalAirship)) {
                            break;
                        }
                    }
                    break;
                case "3":
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static boolean airplaneTypeMenu(Scanner scanner, Airplane regionalAirplane, Airplane internationalAirplane) {
        System.out.println("\n=================================");
        System.out.println("Choose Airplane Type");
        System.out.println("=================================");
        System.out.println("1. Regional Airplane");
        System.out.println("2. International Airplane");
        System.out.println("3. Return to Main Menu");
        System.out.print("\nEnter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                airplaneMenu(regionalAirplane, scanner, Type.REGIONAL.toString());
                break;
            case "2":
                airplaneMenu(internationalAirplane, scanner, Type.INTERNATIONAL.toString());
                break;
            case "3":
                return false;
            default:
                System.out.println("Invalid choice, please try again.");
        }
        return true;
    }


    private static boolean airshipTypeMenu(Scanner scanner, Airship regionalAirship, Airship internationalAirship) {
        System.out.println("\n=================================");
        System.out.println("Choose Airship Type");
        System.out.println("=================================");
        System.out.println("1. Regional Airship");
        System.out.println("2. International Airship");
        System.out.println("3. Return to Main Menu");
        System.out.print("\nEnter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                airshipMenu(regionalAirship, scanner, Type.REGIONAL.toString());
                break;
            case "2":
                airshipMenu(internationalAirship, scanner, Type.INTERNATIONAL.toString());
                break;
            case "3":
                return false;
            default:
                System.out.println("Invalid choice, please try again.");
        }
        return true;
    }

    private static void airplaneMenu(Airplane airplane, Scanner scanner, String type) {
        airplane.resetToDefault();
        while (true) {
            System.out.println("Press enter to continue");
            scanner.nextLine();
            System.out.println("\n=================================");
            System.out.println("Airplane Operations - " + type);
            System.out.println("=================================");
            System.out.println("1. Trigger Engine");
            System.out.println("2. Take Off");
            System.out.println("3. Change Landing Mode");
            System.out.println("4. Increase Altitude");
            System.out.println("5. Decrease Altitude");
            System.out.println("6. Display Information");
            System.out.println("7. Return to Airplane Type Selection");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // Trigger Engine ON/OFF
                    if (airplane.getCurrentAircraftState().equals(Crashed.Instance())) {
                        System.out.println("The airplane has crashed. Press Enter to repair it.");
                        String repairChoice = scanner.nextLine().trim().toLowerCase();
                        if (repairChoice.equals("")) {
                            airplane.repair();
                            System.out.println("Airplane repaired and reset to default.");
                        } else {
                            System.out.println("Repair action canceled. The airplane remains in a crashed state.");
                        }
                    } else if (airplane.getCurrentModeState() != OnGround.Instance()) {
                        System.out.println("Can't turn off the engine while flying!");
                    } else {
                        // Proceed to trigger engine only if the airplane is on ground and it's not crashed
                        airplane.triggerEngine();

                    }
                    break;

                case "2": // Take off
                    if(airplane.getCurrentAircraftState() == Crashed.Instance()){
                        System.out.println("The airplane has crashed. Press Enter to repair it.");
                        String repairChoice = scanner.nextLine().trim().toLowerCase();
                        if (repairChoice.equals("")) {
                            airplane.repair();
                            System.out.println("Airplane repaired and reset to default.");
                        } else {
                            System.out.println("Repair action canceled. The airplane remains in a crashed state.");
                        }
                    }else {
                        if (airplane.getCurrentEngineState() == ON.Instance()) {
                            if (airplane.getCurrentModeState() == OnGround.Instance()) {
                                airplane.triggerMode();
                            }
                            else{
                                System.out.println("Airplane is flying.");
                            }

                        } else System.out.println("Turn the airplane engine on first.");
                    }
                    break;

                case "3": // Change Landing State
                    if (airplane.getCurrentAircraftState().equals(Crashed.Instance())) {
                        System.out.println("Airplane is crashed. Press Enter to repair it.");
                        String repairChoice = scanner.nextLine().trim().toLowerCase();
                        if (repairChoice.equals("")) {
                            airplane.repair();
                            System.out.println("Airplane repaired and reset to default.");
                        } else {
                            System.out.println("Repair action canceled. The airplane remains in a crashed state.");
                        }

                    }
                    else if(airplane.getCurrentModeState()==OnGround.Instance()){
                        System.out.println("Airplane is on the ground.");
                    }
                    else {
                        airplane.triggerLandingState();
                    }
                    break;


                case "4": //Increase altitude
                    if(airplane.getCurrentModeState() != OnGround.Instance()){
                        if(airplane.getCurrentEngineState() == ON.Instance()){
                            if(airplane.getCurrentAircraftState()== Crashed.Instance()){
                                System.out.println("The airplane has crashed. Press Enter to repair it.");
                                String repairChoice = scanner.nextLine().trim().toLowerCase();
                                if (repairChoice.equals("")) {
                                    airplane.repair();
                                    System.out.println("Airplane repaired and reset to default.");
                                } else {
                                    System.out.println("Repair action canceled. The airplane remains in a crashed state.");
                                }
                            }else {
                                if(airplane.getCurrentLandingState()== Yes.Instance()){
                                    System.out.println("In landing mode, you can't increase your altitude.");
                                } else {
                                    int amount;
                                    try {
                                        System.out.println("Increase altitude amount:");
                                        amount = Integer.parseInt(scanner.nextLine());
                                        airplane.increaseAltitude(amount);
                                        System.out.println("Current altitude: " + airplane.getAltitude() + " feet.");
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a numeric value.");
                                    }
                                }
                            }
                        } else {
                            System.out.println("Turn the airplane on first!");
                        }
                    } else {
                        System.out.println("Can't increase altitude. The airplane is on the ground! Take off first.");
                    }
                    break;


                case "5": // Decrease altitude
                    if (airplane.getCurrentAircraftState().equals(Crashed.Instance())) {
                        System.out.println("The airplane has crashed. Press Enter to repair it.");
                        String repairChoice = scanner.nextLine().trim().toLowerCase();
                        if (repairChoice.equals("")) {
                            airplane.repair();
                            System.out.println("Airplane repaired and reset to default.");
                        } else {
                            System.out.println("Repair action canceled. The airplane remains in a crashed state.");
                        }
                    } else if (airplane.getCurrentEngineState().equals(ON.Instance()) && airplane.getCurrentModeState() != OnGround.Instance()) {
                        try {
                            System.out.println("Decrease altitude amount: ");
                            int amount = Integer.parseInt(scanner.nextLine());
                            if (!airplane.getCurrentLandingState().equals(Yes.Instance()) && amount > 0) {
                                // Not in landing mode and trying to decrease altitude
                                airplane.setCurrentAircraftState(Crashed.Instance());
                                System.out.println("Airplane crashed due to land without in landing mode!");
                            } else {
                                airplane.decreaseAltitude(amount);
                                System.out.println("Current altitude: " + airplane.getAltitude() + " feet.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                        }
                    } else {
                        System.out.println("Either the engine is off, or the airplane is already on the ground.");
                    }
                    break;

                case "6":
                    System.out.println(airplane);
                    break;

                case "7":
                    return;
                default:
                    System.out.println("Invalid action, please try again.");
            }
         }
    }

    private static void airshipMenu(Airship airship, Scanner scanner, String type) {
        airship.resetToDefault();
        while (true) {
            System.out.println("Press enter to continue");
            scanner.nextLine();
            System.out.println("\n=================================");
            System.out.println("Airship Operations - " + type);
            System.out.println("=================================");
            System.out.println("1. Trigger Engine");
            System.out.println("2. Take Off");
            System.out.println("3. Change Landing Mode");
            System.out.println("4. Increase Altitude");
            System.out.println("5. Decrease Altitude");
            System.out.println("6. Display Information");
            System.out.println("7. Return to Airplane Type Selection");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // Trigger Engine ON/OFF
                    if (airship.getCurrentAircraftState().equals(Crashed.Instance())) {
                        System.out.println("The airship has crashed. Press Enter to repair it.");
                        String repairChoice = scanner.nextLine().trim().toLowerCase();
                        if (repairChoice.equals("")) {
                            airship.repair();
                            System.out.println("Airship repaired and reset to default.");
                        } else {
                            System.out.println("Repair action canceled. The airship remains in a crashed state.");
                        }
                    } else if (airship.getCurrentModeState() != OnGround.Instance()) {
                        System.out.println("Can't turn off the engine while flying!");
                    } else {
                        // Proceed to trigger engine only if the airplane is on ground and it's not crashed
                        airship.triggerEngine();

                    }
                    break;

                case "2": // Take off
                    if(airship.getCurrentAircraftState() == Crashed.Instance()){
                        System.out.println("The airship has crashed. Press Enter to repair it.");
                        String repairChoice = scanner.nextLine().trim().toLowerCase();
                        if (repairChoice.equals("")) {
                            airship.repair();
                            System.out.println("The airship repaired and reset to default.");
                        } else {
                            System.out.println("Repair action canceled. The airship remains in a crashed state.");
                        }
                    }else {
                        if (airship.getCurrentEngineState() == ON.Instance()) {
                            if (airship.getCurrentModeState() == OnGround.Instance()) {
                                airship.triggerMode();
                            }
                            else{
                                System.out.println("The airship is flying.");
                            }

                        } else System.out.println("Turn the airship engine on first.");
                    }
                    break;

                case "3": // Change Landing State
                    if (airship.getCurrentAircraftState().equals(Crashed.Instance())) {
                        System.out.println("The airship is crashed. Press Enter to repair it.");
                        String repairChoice = scanner.nextLine().trim().toLowerCase();
                        if (repairChoice.equals("")) {
                            airship.repair();
                            System.out.println("The airship repaired and reset to default.");
                        } else {
                            System.out.println("Repair action canceled. The airship remains in a crashed state.");
                        }

                    }
                    else if(airship.getCurrentModeState()==OnGround.Instance()){
                        System.out.println("The airship is on the ground.");
                    }
                    else {
                        airship.triggerLandingState();
                    }
                    break;


                case "4": //Increase altitude
                    if(airship.getCurrentModeState() != OnGround.Instance()){
                        if(airship.getCurrentEngineState() == ON.Instance()){
                            if(airship.getCurrentAircraftState()== Crashed.Instance()){
                                System.out.println("The airship has crashed. Press Enter to repair it.");
                                String repairChoice = scanner.nextLine().trim().toLowerCase();
                                if (repairChoice.equals("")) {
                                    airship.repair();
                                    System.out.println("The airship repaired and reset to default.");
                                } else {
                                    System.out.println("Repair action canceled. The airship remains in a crashed state.");
                                }
                            }else {
                                if(airship.getCurrentLandingState()== Yes.Instance()){
                                    System.out.println("In landing mode, you can't increase your altitude.");
                                } else {
                                    int amount;
                                    try {
                                        System.out.println("Increase altitude amount:");
                                        amount = Integer.parseInt(scanner.nextLine());
                                        airship.increaseAltitude(amount);
                                        System.out.println("Current altitude: " + airship.getAltitude() + " feet.");
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a numeric value.");
                                    }
                                }
                            }
                        } else {
                            System.out.println("Turn the airship on first!");
                        }
                    } else {
                        System.out.println("Can't increase altitude. The airship is on the ground! Take off first.");
                    }
                    break;


                case "5": // Decrease altitude
                    if (airship.getCurrentAircraftState().equals(Crashed.Instance())) {
                        System.out.println("The airship has crashed. Press Enter to repair it.");
                        String repairChoice = scanner.nextLine().trim().toLowerCase();
                        if (repairChoice.equals("")) {
                            airship.repair();
                            System.out.println("The airship repaired and reset to default.");
                        } else {
                            System.out.println("Repair action canceled. The airship remains in a crashed state.");
                        }
                    } else if (airship.getCurrentEngineState().equals(ON.Instance()) && airship.getCurrentModeState() != OnGround.Instance()) {
                        try {
                            System.out.println("Decrease altitude amount: ");
                            int amount = Integer.parseInt(scanner.nextLine());
                            if (!airship.getCurrentLandingState().equals(Yes.Instance()) && amount > 0) {
                                // Not in landing mode and trying to decrease altitude
                                airship.setCurrentAircraftState(Crashed.Instance());
                                System.out.println("The airship crashed due to land without in landing mode!");
                            } else {
                                airship.decreaseAltitude(amount);
                                System.out.println("Current altitude: " + airship.getAltitude() + " feet.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                        }
                    } else {
                        System.out.println("Either the engine is off, or the airship is already on the ground.");
                    }
                    break;

                case "6":
                    System.out.println(airship);
                    break;

                case "7":
                    return;
                default:
                    System.out.println("Invalid action, please try again.");
            }

        }
    }
}
