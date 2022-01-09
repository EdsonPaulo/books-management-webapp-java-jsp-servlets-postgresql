package ucan.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ucan.conection.DBConnection;

/**
 *
 * @author edsonpaulo
 */
public class Helpers {

    public static LocalDateTime stringToDateTime(String value, boolean defaultTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(defaultTime ? value + " 00:00" : value, formatter);
    }

    public static LocalDateTime stringToDateTime(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(value, formatter);
    }

    public static int getIdOfLastRow(String table) {
        String pKey = "pk_" + table;
        String sql = "SELECT "+ pKey + " FROM " + table + " ORDER BY " + pKey + " DESC LIMIT 1";
        int elementId = 0;
        try {
            DBConnection connection = new DBConnection();
            ResultSet resultSet = connection.getConnection().prepareStatement(sql).executeQuery();

            while (resultSet.next()) {
                elementId = resultSet.getInt(1);
            }
            resultSet.close();
            connection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elementId;
    }
}
