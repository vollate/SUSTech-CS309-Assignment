import java.awt.*;

public class RedBall extends Ball {
    public RedBall(int xSpeed, int ySpeed, int ballSize) {
        super(Color.RED, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void notify(char keyChar) {
        switch (keyChar) {
            case 'a':
                setXSpeed(-random.nextInt(3) - 1);
                break;
            case 'd':
                setXSpeed(random.nextInt(3) + 1);
                break;
            case 'w':
                setYSpeed(-random.nextInt(3) - 1);
                break;
            case 's':
                setYSpeed(random.nextInt(3) + 1);
        }
    }

    @Override
    public void checkIntersect(Ball b) {
        if (this.isIntersect(b)) {
            this.setVisible(true);
            setXSpeed(b.getXSpeed());
            setYSpeed(b.getYSpeed());
        } else {
            this.setVisible(false);
        }
    }
}
