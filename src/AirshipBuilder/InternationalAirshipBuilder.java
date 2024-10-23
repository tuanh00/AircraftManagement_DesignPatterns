package AirshipBuilder;

import Enum.*;
public class InternationalAirshipBuilder implements IAirshipBuilder{
    private Airship airship;
    public InternationalAirshipBuilder(){airship = new Airship();}

    @Override
    public void setType() {
        airship.setType(Type.INTERNATIONAL);
    }

    @Override
    public void setCapacity() {airship.setCapacity(Capacity.VERY_LARGE);}

    @Override
    public void setEngineType() {airship.setEngineType(Engine_Type.ULTRA_HIGH_TURBOFAN);}

    @Override
    public Airship getAirship() {
        return this.airship;
    }
}
