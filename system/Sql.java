package system;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Projekt059
 */
public class Sql {

    Connection c;
    Statement s;
    ResultSet rs;

    public Sql() {
        c = createConnection();
        s = myCreateStatement(c);
    }

    //connect
    public Connection createConnection() {
        Connection localConn = null;

        try {
            //kollar om drivrutin finns
            Class.forName("org.sqlite.JDBC"); //Sqlite-drivrutin
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Couldn't find driver class:");
            cnfe.printStackTrace();
        }
        System.out.println("Driver found");

        try {
            //skapar uppkoppling
            localConn = DriverManager.getConnection("jdbc:sqlite:Databas");
        } catch (SQLException se) {
            System.out.println("Couldn't connect:"
                    + " print out a stack trace and exit.");
            se.printStackTrace();

        }
        //om allt gick som det ska
        if (localConn != null) {
            System.out.println("Successfully connected to the database!");
        } else {
            System.out.println("This should be impossible.");
        }

        return localConn;
    }

    //sql sats
    public Statement myCreateStatement(Connection c) {
        Statement s = null;

        try {
            //skapar statement med hjälp av uppkopplingen till databasen
            s = c.createStatement();
        } catch (SQLException se) {
            System.out.println("We got an exception while creating a statement:"
                    + "that probably means we're no longer connected.");
            System.out.println(se.getMessage());

        }
        return s;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    public ArrayList query(String query) {
        ResultSet rs = null;
        ArrayList<String> resultat = new ArrayList<>();
        try {
            rs = s.executeQuery(query);
            resultat = ResultSetToString(rs);
        } catch (SQLException se) {
            System.out.println("We got an exception while executing our query:"
                    + "that probably means our SQL is invalid");
            System.out.println(se.getMessage());

        }
        return resultat;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    public int update(String query) {
        int antal = 0;
        try {
            antal = s.executeUpdate(query);

        } catch (SQLException se) {
            System.out.println("We got an exception while executing our query: "
                    + "that probably means our SQL is invalid");
            System.out.println(se.getMessage());

        }
        return antal;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    //Gör resultset till en sträng
    public ArrayList ResultSetToString(ResultSet rs) {
        String temp = "";
        ArrayList<String> resultat = new ArrayList<>();
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();

            for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                if (i == 1) {
                    temp = rsMetaData.getColumnName(i);
                } else {
                    temp = temp + " " + rsMetaData.getColumnName(i);
                }

            }
            resultat.add(temp);

            while (rs.next()) {
                temp = "";
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    if (i == 1) {
                        temp = rs.getString(i);
                    } else {
                        temp = temp + " " + rs.getString(i);
                    }

                }

                resultat.add(temp);

            }
        } catch (SQLException se) {
            System.out.println("We got an exception while getting a result:this"
                    + " shouldn't happen: we've done something really bad.");
            System.out.println(se.getMessage());

        }
        return resultat;
    }
}
