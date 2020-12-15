package gui;


import gui.util.JMyNumberTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel{

    private JLabel nameLabel;
    private JLabel xcordLabel;
    private JLabel ycordLabel;
    private JTextField nameField;
    private JMyNumberTextField xcordField;
    private JMyNumberTextField ycordField;

    private JRadioButton workingR;
    private JRadioButton defectR;
    private JRadioButton offlineR;
    private ButtonGroup statusG;

    private JComboBox<String> occu;


    private JButton okBtn;


    private FormListener formListener;

    public FormPanel(){

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        xcordLabel = new JLabel("xcord: ");
        ycordLabel = new JLabel("ycord: ");

        nameField = new JTextField(30);
        xcordField = new JMyNumberTextField(3);
        ycordField = new JMyNumberTextField(3);

        occu = new JComboBox<>();

        Dimension txtdim = nameField.getPreferredSize();
        txtdim.width = 90;
        nameField.setMinimumSize(txtdim);
        xcordField.setMinimumSize(txtdim);
        ycordField.setMinimumSize(txtdim);

        workingR = new JRadioButton("working");
        defectR = new JRadioButton("defect");
        offlineR = new JRadioButton("offline");
        statusG = new ButtonGroup();


        okBtn = new JButton("Add");

        //setup radioB
        statusG.add(workingR);
        statusG.add(defectR);
        statusG.add(offlineR);
        workingR.setSelected(true);

        workingR.setActionCommand("0");
        defectR.setActionCommand("def1ect");
        offlineR.setActionCommand("offline");

        //setup combobox
        DefaultComboBoxModel<String> occuModel = new DefaultComboBoxModel<>();
        occuModel.addElement("high");
        occuModel.addElement("medium");
        occuModel.addElement("low");
        occuModel.addElement("undefined");
        occu.setModel(occuModel);
        occu.setSelectedIndex(0);
        occu.setPreferredSize(new Dimension(110,20));

        //submit
        okBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int x = xcordField.getNumber();
                int y = ycordField.getNumber();
                String status = statusG.getSelection().getActionCommand();
                String occup = (String)occu.getSelectedItem();

                FormEvent ev = new FormEvent(this, name, x, y, status, occup);
                if(formListener != null){
                    formListener.formEvent(ev);
                }
            }
        });

        Border inner = BorderFactory.createTitledBorder("add Node");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        setComponents();
    }

    private void setComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //first ROW

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,0);
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField,gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(xcordLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(xcordField, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(ycordLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ycordField, gc);

        gc.gridy++;
        gc.weighty = 0.05;
        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Status: "), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(workingR, gc);

        //next row
        gc.gridy++;
        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(defectR, gc);

        //next row
        gc.gridy++;
        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(offlineR, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Occupany: "), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(occu, gc);

        //last ROW
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);



    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

}
