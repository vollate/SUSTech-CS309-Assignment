import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        AirConditioner roomAirConditioner = new AirConditioner("bedRoom");
        AirConditioner livingAirConditioner = new AirConditioner("livingRoom");
        RemoteCommandQueue remoteControl = new RemoteCommandQueue();
        Light roomLight = new Light("bedRoom");
        Light livingLight = new Light("livingRoom");

        Scanner input = new Scanner(System.in);
        System.out.println("Please input operation number: 1-9,[1,3,5,7] is on command,[2,4,6,8] is off command, 9 is to show state terminate by 0:");
        int op = 0;
        do {
            try {
                op = input.nextInt();
                switch (op) {
                    case 1:
                        remoteControl.buttonPressed(new AirConditionerOnCommand(roomAirConditioner));
                        break;
                    case 2:
                        remoteControl.buttonPressed(new AirConditionerOffCommand(roomAirConditioner));
                        break;
                    case 3:
                        remoteControl.buttonPressed(new AirConditionerOnCommand(livingAirConditioner));
                        break;
                    case 4:
                        remoteControl.buttonPressed(new AirConditionerOffCommand(livingAirConditioner));
                        break;
                    case 5:
                        remoteControl.buttonPressed(new LightOnCommand(roomLight));
                        break;
                    case 6:
                        remoteControl.buttonPressed(new LightOffCommand(roomLight));
                        break;
                    case 7:
                        remoteControl.buttonPressed(new LightOnCommand(livingLight));
                        break;
                    case 8:
                        remoteControl.buttonPressed(new LightOffCommand(livingLight));
                        break;
                    case 9:
                        showState(new AirConditioner[]{roomAirConditioner, livingAirConditioner}
                                , new Light[]{roomLight, livingLight});
                        break;
                    case 10:
                        remoteControl.commandExecute();
                        break;
                    case 11:
                        remoteControl.commandUndo();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Exception:" + e);
                input.nextLine();
            }
        } while (op != 0);

        input.close();
    }

    public static void showState(AirConditioner[] airConditioners, Light[] lights) {
        for (AirConditioner a : airConditioners) {
            System.out.println(a);
        }
        for (Light l : lights) {
            System.out.println(l);
        }

    }
}
