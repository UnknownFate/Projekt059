package system;

import java.util.ArrayList;

/**
 *
 * @author Projekt059
 */
public class Logik {

    Sql sql = new Sql();

    public int regLitt(Litteratur litteratur) {
        String insertData = litteratur.toString();
        String columnNames = litteratur.getVariableNames();
        String SQL = "INSERT INTO litteratur (" + columnNames + ") VALUES (" + insertData + ")";

        int numberOfChanges;
        numberOfChanges = sql.update(SQL);

        return numberOfChanges;
    }

    public ArrayList listLitt() {
        String SQL = "SELECT * FROM litteratur";
        ArrayList<String> resultat = new ArrayList<>();
        resultat = sql.query(SQL);

        return resultat;
    }

    public ArrayList listPerson() {
        String SQL = "SELECT * FROM person";
        ArrayList<String> resultat = new ArrayList<>();
        resultat = sql.query(SQL);

        return resultat;
    }

    public int regPerson(Person person) {
        String insertData = person.toString();
        String columnNames = person.getVariableNames();
        String SQL1 = "INSERT INTO person (" + columnNames + ") VALUES (" + insertData + person.getRoll() + ")";
        String SQL2;
        int numberOfChanges;

        numberOfChanges = sql.update(SQL1);
        for (int i = 0; i < person.getBehorigheter().size(); i++) {
            SQL2 = "INSERT INTO personBehorighet (pnr, behorighet) VALUES (" + person.getPnr() + ", " + person.getBehorigheter().get(i) + ")";
            numberOfChanges = numberOfChanges + sql.update(SQL2);
        }

        return numberOfChanges;
    }

    public ArrayList listSkuld() {
        String SQL = "SELECT * FROM skuld";
        ArrayList<String> resultat = new ArrayList<>();
        resultat = sql.query(SQL);

        return resultat;
    }

    public ArrayList listLon() {
        String SQL = "SELECT * FROM lon";
        ArrayList<String> resultat = new ArrayList<>();
        resultat = sql.query(SQL);

        return resultat;
    }

    public int regSkuld(Skuld skuld) {
        int numberOfChanges;
        String insertData = skuld.toString();
        String columnNames = "skuldId, belopp, datum, lonId";
        String SQL = "INSERT INTO person (" + columnNames + ") VALUES (" + insertData + ")";

        numberOfChanges = sql.update(SQL);
        return numberOfChanges;
    }

    public int regLon(Lon lon) {
        String insertData = lon.toString();
        String columnNames = "lonId, pnr, datum";
        String SQL1 = "INSERT INTO lon (" + columnNames + ") VALUES (" + insertData + ")";
        String SQL2;

        int numberOfChanges;

        numberOfChanges = sql.update(SQL1);
        for (int i = 0; i < lon.getLitteratur().size(); i++) {
            SQL2 = "INSERT INTO lonLitteratur (litterturId, lonId) VALUES (" + lon.getLitteratur().get(i) + ", " + lon.getLonId() + ")";
            numberOfChanges = numberOfChanges + sql.update(SQL2);
        }

        return numberOfChanges;
    }
}
