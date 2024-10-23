package AirplaneBuilder;

public class AirplaneEngineer {
    private IAirplaneBuilder iAirplaneBuilder;
    public AirplaneEngineer(IAirplaneBuilder iAirplaneBuilder){this.iAirplaneBuilder = iAirplaneBuilder;}
    public void createAirplane(){
        iAirplaneBuilder.setType();
        iAirplaneBuilder.setCapacity();
        iAirplaneBuilder.setEngineType();
    }
    public Airplane getAirplane(){return this.iAirplaneBuilder.getAirplane();}
}

