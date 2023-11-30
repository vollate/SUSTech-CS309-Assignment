
public class AirConditionerOffCommand implements CMD {
    private AirConditioner airConditioner;

    public AirConditionerOffCommand(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }

    public void execute(){
        airConditioner.off();
    }

    public void undo(){
        airConditioner.on();
    }
}