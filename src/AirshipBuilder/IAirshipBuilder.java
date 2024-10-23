package AirshipBuilder;

public interface IAirshipBuilder {
    void setType();
    void setCapacity();
    void setEngineType();

    Airship getAirship();
}
