package gui;

import model.Node;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NodeTableModel extends AbstractTableModel {


    private List<Node> nodes;

    private String[] colNames = {"id", "name", "xcord", "ycord", "status", "occu", "adjecent"};

    public NodeTableModel(){};

    public void setData(List<Node> nodes) {
        this.nodes = nodes;
    }


    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public int getRowCount() {
        return nodes.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Node node = nodes.get(rowIndex);

        switch (columnIndex){
            case 0:
                return node.getId();
            case 1:
                return node.getName();
            case 2:
                return node.getX();
            case 3:
                return node.getY();
            case 4:
                return node.getStatus();
            case 5:
                return node.getOccu();
            case 6:
                return node.getAdjecent().size();
        }
        return null;

    }
}
