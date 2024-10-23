package AirshipBuilder;

public class AirshipEngineer {
    private IAirshipBuilder iAirshipBuilder;
    public AirshipEngineer(IAirshipBuilder iAirshipBuilder){this.iAirshipBuilder = iAirshipBuilder;}
    public void createAirship(){
        iAirshipBuilder.setType();
        iAirshipBuilder.setCapacity();
        iAirshipBuilder.setEngineType();
    }
    public Airship getAirship(){return this.iAirshipBuilder.getAirship();}
}
