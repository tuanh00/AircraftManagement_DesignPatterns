package AirplaneBuilder;

public interface IAirplaneBuilder {

    void setType();
    void setCapacity();
    void setEngineType();

    Airplane getAirplane();
}