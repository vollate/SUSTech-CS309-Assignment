import java.awt.*;

public class BlueBall extends Ball {
    private boolean lastIntersect = false;

    public BlueBall(int xSpeed, int ySpeed, int ballSize) {
        super(Color.BLUE, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void notify(char keyChar) {
        setXSpeed(-1 * getXSpeed());
        setYSpeed(-1 * getYSpeed());
    }

    @Override
    public void checkIntersect(Ball b) {
        if (this.isIntersect(b)) {
            this.setVisible(true);
            if (this.isIntersect(b) != lastIntersect) {
                setXSpeed(-1 * getXSpeed());
                setYSpeed(-1 * getYSpeed());
            }
            lastIntersect = true;
        } else {
            lastIntersect = false;
            this.setVisible(false);
        }
    }
}
