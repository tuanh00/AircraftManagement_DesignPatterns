package AbstractFactory;

import Enum.*;
import AirplaneBuilder.*;
import AirshipBuilder.*;

public abstract class AbstractFactory {

    static AbstractFactory regionalFactory = new RegionalFactory();
    static AbstractFactory internationalFactory = new InternationalFactory();
    public static AbstractFactory factoryMethod(Type type) {

        AbstractFactory factory = null;
        switch (type) {
            case REGIONAL:
                factory = regionalFactory;
                break;
            case INTERNATIONAL:
                factory = internationalFactory;
                break;
        }
        return factory;
    }

    public abstract Airplane makeAirplane();
    public abstract Airship makeAirship();

}