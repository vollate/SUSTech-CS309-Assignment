import java.util.ArrayDeque;
import java.util.Queue;

public class RemoteCommandQueue {
    Queue<CMD> commands;

    public RemoteCommandQueue() {
        commands = new ArrayDeque<>();
    }

    public void buttonPressed(CMD command) {
        commands.add(command);
    }


    public void commandExecute() {
        for (CMD command : commands) {
            command.execute();
        }
    }

    public void commandUndo() {
        for (CMD command : commands) {
            command.undo();
        }
    }
}
