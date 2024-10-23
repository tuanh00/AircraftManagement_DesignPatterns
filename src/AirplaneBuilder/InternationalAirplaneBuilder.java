package AirplaneBuilder;

import Enum.*;
public class InternationalAirplaneBuilder implements IAirplaneBuilder{
    private Airplane airplane;
    public InternationalAirplaneBuilder(){airplane = new Airplane();}

    @Override
    public void setType() {
        airplane.setType(Type.INTERNATIONAL);
    }

    @Override
    public void setCapacity() {airplane.setCapacity(Capacity.VERY_LARGE);}

    @Override
    public void setEngineType() {airplane.setEngineType(Engine_Type.ULTRA_HIGH_TURBOFAN);}

    @Override
    public Airplane getAirplane() {
        return this.airplane;
    }
}
