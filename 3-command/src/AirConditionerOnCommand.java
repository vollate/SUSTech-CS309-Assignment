public class AirConditionerOnCommand implements CMD {
    private AirConditioner airConditioner;

    public AirConditionerOnCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    public void execute() {
        airConditioner.on();
    }


    public void undo() {
        airConditioner.off();
    }
}