package gui.util;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class JMyNumberTextField extends JTextField {

    private static final long serialVersionUID = 1L;

    public JMyNumberTextField(int len){
        super(len);
    }


    @Override
    public void processKeyEvent(KeyEvent ev) {
        if (Character.isDigit(ev.getKeyChar())) {
            super.processKeyEvent(ev);
        }
        ev.consume();
        return;
    }

    public Integer getNumber() {
        Integer result = null;
        String text = getText();
        if (text != null && !"".equals(text)) {
            result = Integer.valueOf(text);
        }
        return result;
    }
}
