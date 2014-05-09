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
        String SQL = "INSERT INTO litteratur (id, titel, forfattare, "
                + "sprak, utgivningsar, tillganglighet, kopieringsbart, isbn) VALUES (" + insertData + ")";
        int numberOfChanges;
        System.out.println(SQL);
        numberOfChanges = sql.update(SQL);

        return numberOfChanges;
    }

    public ArrayList listLitt() {
        String SQL = "SELECT * FROM litteratur";
        ArrayList<String> resultat = new ArrayList<>();
        System.out.println(SQL);
        resultat = sql.query(SQL);

        return resultat;
    }

}
