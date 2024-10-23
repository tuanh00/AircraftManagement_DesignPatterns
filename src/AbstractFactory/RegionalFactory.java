package AbstractFactory;

import AirplaneBuilder.*;
import AirshipBuilder.*;
public class RegionalFactory extends AbstractFactory{

    @Override
    public Airplane makeAirplane() {
        IAirplaneBuilder regionalAirplane = new RegionalAirplaneBuilder();
        AirplaneEngineer airplaneEngineer = new AirplaneEngineer(regionalAirplane);
        airplaneEngineer.createAirplane();

        return airplaneEngineer.getAirplane();
    }

    @Override
    public Airship makeAirship() {
        IAirshipBuilder regionalAirship = new RegionalAirshipBuilder();
        AirshipEngineer airshipEngineer = new AirshipEngineer(regionalAirship);
        airshipEngineer.createAirship();

        return airshipEngineer.getAirship();
    }
}
