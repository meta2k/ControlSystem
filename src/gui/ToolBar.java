package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel implements ActionListener{

    private JButton editBtn;
    private JButton adjBtn;
    private JTable table;

    public ToolBar(JTable table){
        this.table = table;
        this.editBtn = new JButton("edit");
        this.adjBtn = new JButton("Add Adjecent");

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(editBtn);
        add(adjBtn);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == editBtn) {
            //TODO
        } else if (btn == adjBtn) {
            //TODO
        }
    }


}
