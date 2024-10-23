package AbstractFactory;

import AirplaneBuilder.*;
import AirshipBuilder.*;

public class InternationalFactory extends AbstractFactory{
    @Override
    public Airplane makeAirplane() {
        IAirplaneBuilder internationalAirplane = new InternationalAirplaneBuilder();
        AirplaneEngineer airplaneEngineer = new AirplaneEngineer(internationalAirplane);
        airplaneEngineer.createAirplane();

        return airplaneEngineer.getAirplane();
    }

    @Override
    public Airship makeAirship() {
        IAirshipBuilder internationalAirship = new InternationalAirshipBuilder();
        AirshipEngineer airshipEngineer = new AirshipEngineer(internationalAirship);
        airshipEngineer.createAirship();

        return airshipEngineer.getAirship();
    }
}
