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
        if (commands.isEmpty()) {
            System.out.println("No command to execute!");
            return;
        }
        for (CMD command : commands) {
            command.execute();
        }
    }

    public void commandUndo() {
        if (commands.isEmpty()) {
            System.out.println("No command to undo!");
            return;
        }
        for (CMD command : commands) {
            command.undo();
        }
    }
}
