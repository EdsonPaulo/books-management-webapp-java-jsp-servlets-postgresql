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

    private Statement statement;

    public HtmlObj() {
    }

    public String getSelectBox(DBConnection conn, String nomeTabela, String nomeCampo) {
        StringBuilder htmlCombo = new StringBuilder();
        htmlCombo.append("<select name=\"").append(nomeCampo).append("\" id=\"").append(nomeCampo)
                .append("\" class=\"form-control\" >");

        htmlCombo.append("<option value=\"\">Selecione uma opção</option>\n");
        try {
            statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + nomeTabela);

            while (resultSet.next()) {
                htmlCombo.append("<option value=\"").append(resultSet.getInt(1)).append("\">")
                        .append(resultSet.getString(2)).append("</option>");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        htmlCombo.append("</select>");
        return htmlCombo.toString();
    }

    public String getTableRow(DBConnection conn, String nomeTabela) {
        StringBuilder htmlBuilder = new StringBuilder();
        try {
            statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + nomeTabela);

            while (resultSet.next()) {
                htmlBuilder.append("<tr>");
                htmlBuilder.append("<th scope=\"row\">" + resultSet.getInt(1) + "</th>");
                htmlBuilder.append("<td>" + resultSet.getString(2) + "</td>");
                htmlBuilder.append("</tr>");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return htmlBuilder.toString();
    }
}
