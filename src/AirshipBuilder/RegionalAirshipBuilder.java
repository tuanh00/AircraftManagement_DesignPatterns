package AirshipBuilder;

import Enum.*;
public class RegionalAirshipBuilder implements IAirshipBuilder{
    private Airship airship;
    public RegionalAirshipBuilder(){airship = new Airship();}

    @Override
    public void setType() {
        airship.setType(Type.REGIONAL);
    }

    @Override
    public void setCapacity() {airship.setCapacity(Capacity.LARGE);}

    @Override
    public void setEngineType() {airship.setEngineType(Engine_Type.HIGH_TURBOFAN);}

    @Override
    public Airship getAirship() {
        return this.airship;
    }
}
