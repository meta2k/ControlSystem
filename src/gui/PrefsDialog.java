package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {

    private JButton okBtn;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private JTextField userField;
    private JPasswordField passField;
    private SpinnerNumberModel spinnerModel;
    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent) {
        super(parent, "Preferences", false);

        okBtn = new JButton("OK");
        cancelButton = new JButton("Cancel");
        userField = new JTextField(10);
        passField = new JPasswordField(10);

        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);

        passField.setEchoChar('*');



        layoutControls();


        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer port = (Integer) portSpinner.getValue();
                String user = userField.getText();
                char[] password = passField.getPassword();
                if(prefsListener != null){
                    prefsListener.prefeferencesSet(user, new String(password), port);
                }

                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(320, 250);
        setLocationRelativeTo(parent);

    }

    public void setPrefsListener(PrefsListener listener){
        this.prefsListener = listener;
    }

    public void setDefaults(String user, String password, int port){
        userField.setText(user);
        passField.setText(password);
        portSpinner.setValue(port);
    }

    private void layoutControls(){

        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
        Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");

        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));

        controlsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        //FIRST ROW

        Insets rightPadding = new Insets(0,0,0,15);
        Insets noPadding = new Insets(0,0,0,0);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("User: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        controlsPanel.add(userField, gc);

        // next row
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Password: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(passField, gc);


        // next row
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Port: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(portSpinner, gc);

        // Buttons Panel
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okBtn);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        gc.insets = noPadding;
        okBtn.setPreferredSize(btnSize);

        //add sub panels to dialog
        setLayout(new BorderLayout());
        add(controlsPanel,BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
