import java.awt.event.KeyListener;

public interface Subject<T> extends KeyListener {
    public void registerObserver(T o);

    public void removeObserver(T o);

    public void notifyObservers(char KeyChar);

    public void notifyObservers(MainPanel.GameStatus status);

    public void notifyObservers(T o);
}
