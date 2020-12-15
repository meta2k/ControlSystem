package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import controller.Controller;
import gui.util.NodeFileFilter;


public class MainFrame extends JFrame {

    private FormPanel formPanel;
    private TablePanel tablePanel;
    private ToolBar toolBar;
    private Preferences prefs;

    private PrefsDialog prefsDialog;
    private CityMapDialog cityMapDialog;

    private JFileChooser fileChooser;

    private Controller controller;


    public MainFrame() {
        super("Control System");

        prefsDialog = new PrefsDialog(this);
        cityMapDialog = new CityMapDialog();

        controller = new Controller();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();

        prefs = Preferences.userRoot().node("db");

        tablePanel.setData(controller.getNodes());
        tablePanel.setNodeTableListener(new NodeTableListener(){
           public void rowDeleted(int row){
                controller.removeNode(row);
           }
        });

        prefsDialog.setPrefsListener(new PrefsListener(){

            @Override
            public void prefeferencesSet(String user, String password, int port) {
                prefs.put("user", user);
                prefs.put("password", password);
                prefs.put("port", new Integer(port).toString());
            }
        });
        String user = prefs.get("user", "");
        String password = prefs.get("password", "");
        Integer port = prefs.getInt("port", 3306);

        prefsDialog.setDefaults(user, password, port);

        toolBar = new ToolBar(tablePanel.getTable());

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new NodeFileFilter());

        setJMenuBar(setupMenu());
        setSize(800, 600);
        setMinimumSize(new Dimension(500, 400));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);

        formPanel.setFormListener(new FormListener() {
            @Override
            public void formEvent(FormEvent e) {
                controller.addNode(e);
                tablePanel.refresh();
            }
        });
    }

    public JMenuBar setupMenu() {
        JMenuBar menuBar = new JMenuBar();

        //file
        JMenu file = new JMenu("File");
        JMenuItem importItem = new JMenuItem("Import Data...");
        JMenuItem exportItem = new JMenuItem("Export Data...");
        JMenuItem exitItem = new JMenuItem("Exit");
        file.add(importItem);
        file.add(exportItem);
        file.addSeparator();
        file.add(exitItem);

        //window
        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");

        JMenuItem prefsItem = new JMenuItem("Preferences...");
        JMenuItem showFormItem = new JCheckBoxMenuItem("Node Form");
        showFormItem.setSelected(true);

        JMenuItem showToolBar = new JCheckBoxMenuItem("Toolbar");
        showToolBar.setSelected(true);

        windowMenu.add(prefsItem);
        windowMenu.add(showMenu);
        showMenu.add(showFormItem);
        showMenu.add(showToolBar);

        menuBar.add(file);
        menuBar.add(windowMenu);

        //mnemonics
        file.setMnemonic(KeyEvent.VK_F);
        windowMenu.setMnemonic(KeyEvent.VK_W);
        exitItem.setMnemonic(KeyEvent.VK_X);

        prefsItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                prefsDialog.setVisible(true);
            }
        });

        exportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        importItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });


        showFormItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }

        });

        showToolBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                toolBar.setVisible(menuItem.isSelected());
            }
        });

        return menuBar;
    }


}
