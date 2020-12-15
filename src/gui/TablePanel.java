package gui;

import model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class TablePanel extends JPanel {

    private JTable table;
    private NodeTableModel tableModel;
    private JPopupMenu popup;
    private NodeTableListener nodeTableListener;

    public TablePanel() {

        tableModel = new NodeTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("Delete node");
        popup.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(table, e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(nodeTableListener != null){
                    nodeTableListener.rowDeleted(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }
            }
        });


        this.setLayout(new BorderLayout());

        this.add(new JScrollPane(table), BorderLayout.CENTER);

    }

    public JTable getTable() {
        return this.table;
    }

    public void setData(List<Node> nodes) {
        tableModel.setData(nodes);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }

    public void setNodeTableListener(NodeTableListener listener){
        this.nodeTableListener = listener;
    }


}
