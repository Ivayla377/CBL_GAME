import java.awt.event.*;


/**
 * Handles key related events.
 */
public class KeyInput implements KeyListener {

    public boolean isUp;
    public boolean isDown;
    public boolean isLeft;
    public boolean isRight;
    public boolean restart;
    public boolean boast;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        leftPressed(code, e);

        rightPressed(code, e);

        restartPressed(code, e);

        boostPressed(code, e);
        
    }

    /**
     * Check if key is pressed.
     * @param code Numeric value corresponding to pressed key
     * @param e The even corresponding to the pressed key
     */
    public void leftPressed(int code, KeyEvent e) {
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_NUMPAD4) {
            isLeft = true;
        }
    }

    /**
     * Check if key is pressed.
     * @param code Numeric value corresponding to pressed key
     * @param e The even corresponding to the pressed key
     */
    public void rightPressed(int code, KeyEvent e) {
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_NUMPAD6) {
            isRight = true;
        }
    }

    /**
     * Check if key is pressed.
     * @param code Numeric value corresponding to pressed key
     * @param e The even corresponding to the pressed key
     */
    public void restartPressed(int code, KeyEvent e) {
        if (code == KeyEvent.VK_R) {
            restart = true;
        }
    }

    /**
     * Check if key is pressed.
     * @param code Numeric value corresponding to pressed key
     * @param e The even corresponding to the pressed key
     */
    public void boostPressed(int code, KeyEvent e) {
        if (code == KeyEvent.VK_SPACE) {
            boast = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        leftReleased(code, e);

        rightReleased(code, e);

        restartReleased(code, e);

        boostReleased(code, e);
        
    }

    /**
     * Check if key is released.
     * @param code Numeric value corresponding to pressed key
     * @param e The even corresponding to the pressed key
     */
    public void leftReleased(int code, KeyEvent e) {
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_NUMPAD4) {
            isLeft = false;
        }
    }

    /**
     * Check if key is released.
     * @param code Numeric value corresponding to pressed key
     * @param e The even corresponding to the pressed key
     */
    public void rightReleased(int code, KeyEvent e) {
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_NUMPAD6) {
            isRight = false;
        }
    }

    /**
     * Check if key is released.
     * @param code Numeric value corresponding to pressed key
     * @param e The even corresponding to the pressed key
     */
    public void restartReleased(int code, KeyEvent e) {
        if (code == KeyEvent.VK_R) {
            restart = false;
        }
    }

    /**
     * Check if key is released.
     * @param code Numeric value corresponding to pressed key
     * @param e The even corresponding to the pressed key
     */
    public void boostReleased(int code, KeyEvent e) {
        if (code == KeyEvent.VK_SPACE) {
            boast = false;
        }
    }

}