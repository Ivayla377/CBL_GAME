import java.awt.event.*;


/**
 * Handles key related events.
 */
public class KeyInput implements KeyListener {

    public boolean isUp;
    public boolean isDown;
    public boolean isLeft;
    public boolean isRight;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_NUMPAD4) {
            isLeft = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_NUMPAD6) {
            isRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_NUMPAD4) {
            isLeft = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_NUMPAD6) {
            isRight = false;
        }
    }

}