import java.awt.*;

public class WhiteBall extends Ball {
    public WhiteBall(int xSpeed, int ySpeed, int ballSize) {
        super(Color.WHITE, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void notify(char keyChar) {
        if (gameStatus == MainPanel.GameStatus.START) {
            switch (keyChar) {
                case 'a':
                    setXSpeed(-8);
                    break;
                case 'd':
                    setXSpeed(8);
                    break;
                case 'w':
                    setYSpeed(-8);
                    break;
                case 's':
                    setYSpeed(8);
                    break;
            }
        }
    }

    @Override
    public void checkIntersect(Ball b) {
    }
}
