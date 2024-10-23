package AirplaneBuilder;

import Enum.*;
public class RegionalAirplaneBuilder implements IAirplaneBuilder{
    private Airplane airplane;
    public RegionalAirplaneBuilder(){airplane = new Airplane();}

    @Override
    public void setType() {
        airplane.setType(Type.REGIONAL);
    }

    @Override
    public void setCapacity() {airplane.setCapacity(Capacity.LARGE);}

    @Override
    public void setEngineType() {airplane.setEngineType(Engine_Type.HIGH_TURBOFAN);}

    @Override
    public Airplane getAirplane() {
        return this.airplane;
    }
}
