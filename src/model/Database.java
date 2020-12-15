package model;

import javax.xml.crypto.Data;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;

public class Database {

    private Connection con;

    private final String NODE_ID = "ID";
    private final String NODE_NAME = "Name";
    private final String NODE_X = "xCord";
    private final String NODE_Y = "yCord";
    private final String NODE_STAT = "Status";
    private final String NODE_OCCU = "Occupany";
    private final String NODE_ADJ = "Adjecent";

    private final String DB_NAME = "nodes";
    private final String URL = "jdbc:derby:" + DB_NAME + ";create=true";
    private final String CREATESQL = String.format(
            "CREATE TABLE %s(%s INTEGER, %s VARCHAR(100), %s INTEGER, "
                    + "%s INTEGER, %s VARCHAR(50), %s VARCHAR(50, %s VARCHAR(50)",
            DB_NAME, NODE_ID, NODE_NAME, NODE_X, NODE_Y, NODE_STAT, NODE_OCCU, NODE_ADJ);

    private Model model;

    public Database(Model model) {
        this.model = model;
    }

    public void connect() throws Exception {
        System.out.println("trying to connect");
        if (con != null) {
            return;
        }
        con = DriverManager.getConnection(URL);
        try {
            con.createStatement().executeUpdate(CREATESQL);
            System.out.println("connected");
        } catch (SQLException e) {
            System.err.println("Could not create DB, already exists ...");
        }
    }


    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("can't close connection");
                e.printStackTrace();
            }
        }
    }

    public void load() throws SQLException{


    }

    public void remove(int id) throws SQLException{



    }
    public void save() throws SQLException{


    }



}
