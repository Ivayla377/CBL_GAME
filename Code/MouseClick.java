import java.awt.*;
import java.awt.event.*;

public class MouseClick implements MouseListener {

    public int clickX;
    public int clickY;

    @Override
    public void mouseClicked(MouseEvent e) {
        clickX = e.getX();
        clickY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
