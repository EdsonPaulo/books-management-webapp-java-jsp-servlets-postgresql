package ucan.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ucan.conection.DBConnection;

/**
 *
 * @author edsonpaulo
 */
public class HtmlObj {

    public HtmlObj() {
    }

    public static String generateHomeSectionLink(String label, String url) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<div class=\"col m-3\">");
        htmlBuilder.append("<a href=\"" + url + "\"");
        htmlBuilder.append(" class=\"h2 d-flex border rounded btn btn-light p-2 justify-content-center align-items-center\"");
        htmlBuilder.append(" style=\"width: 100%; height: 150px\" > ");
        htmlBuilder.append("<h4>" + label + "</h4>");
        htmlBuilder.append("</a></div>");
        return htmlBuilder.toString();
    }

    public String getSelectBox(DBConnection conn, String tableName, String fieldName, int optionSelected) {
        StringBuilder htmlCombo = new StringBuilder();

        htmlCombo.append("<select name=\"")
                .append(fieldName)
                .append("\" id=\"").append(fieldName)
                .append("\" class=\"form-control\" >");
        htmlCombo.append("<option value=\"\">Selecione</option>\n");

        try {
            ResultSet resultSet = conn.getConnection().createStatement().executeQuery("SELECT * FROM " + tableName);

            while (resultSet.next()) {
                htmlCombo.append("<option value=\"" + resultSet.getInt(1) + "\" ")
                        .append(resultSet.getInt(1) == optionSelected ? "selected>" : ">")
                        .append(resultSet.getString(2)).append("</option>");
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        htmlCombo.append("</select>");
        return htmlCombo.toString();
    }

    // Check if an array includes a item
    private boolean containElement(int[] collections, int element) {
        for (int item : collections) {
            if (item == element) {
                return true;
            }
        }
        return false;
    }

    public String getMultipleSelectBox(DBConnection conn, String tableName, String fieldName, int[] optionsSelected) {
        StringBuilder htmlCombo = new StringBuilder();
        String query = "SELECT * FROM " + tableName + ";";

        htmlCombo.append("<select multiple class=\"selectpicker\" title=\"Marque as opções\" data-width=\"100%\" name=\"")
                .append(fieldName).append("\" id=\"")
                .append(fieldName)
                .append("\" class=\"form-control\" >");

        try {
            ResultSet resultSet = conn.getConnection().createStatement().executeQuery(query);

            while (resultSet.next()) {
                htmlCombo.append("<option value=\"" + resultSet.getInt(1) + "\" ")
                        .append(containElement(optionsSelected, resultSet.getInt(1)) ? "selected>" : ">")
                        .append(resultSet.getString(2))
                        .append("</option>");
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        htmlCombo.append("</select>");
        return htmlCombo.toString();
    }

    public String getMultipleSelectWithTableJoin(DBConnection conn, String tableName1, String tableName2, String fieldName, int[] optionsSelected) {
        StringBuilder htmlCombo = new StringBuilder();
        String query = "SELECT pk_" + tableName1 + ", nome FROM " + tableName1 + " INNER JOIN " + tableName2 + " ON fk_" + tableName2 + " = pk_" + tableName2 + ";";

        htmlCombo.append("<select multiple class=\"selectpicker\" title=\"Marque as opções\" data-width=\"100%\"  name=\"")
                .append(fieldName)
                .append("\" id=\"")
                .append(fieldName)
                .append("\" class=\"form-control\" >");

        try {
            ResultSet resultSet = conn.getConnection().createStatement().executeQuery(query);

            while (resultSet.next()) {
                htmlCombo.append("<option value=\"" + resultSet.getInt(1) + "\" ")
                        .append(containElement(optionsSelected, resultSet.getInt(1)) ? "selected>" : ">")
                        .append(resultSet.getString(2)).append("</option>");
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        htmlCombo.append("</select>");
        return htmlCombo.toString();
    }

}
