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
    private DBConnection connection;

    public HtmlObj() {  }

    public String Tabela1(DBConnection c, String tabela, String nome) throws Exception {
        String sqlQuery = "SELECT * FROM " + tabela + ";";
        String htmlElements = "<select name=\"" + nome + "\" id=\"" + nome + "\" class=\"form-control\">";
        statement = c.getConnection().createStatement();

        ResultSet resultados = statement.executeQuery(sqlQuery);

        while (resultados.next()) {
            htmlElements += "<option value=\"" + resultados.getInt(1) + "\">" + resultados.getString(2) + " </option>";
        }
        htmlElements += "</select> ";

        statement.close();
        return htmlElements;
    }

    public String select(String tabela, String nome, int id) {
        StringBuilder htmlElements = new StringBuilder();
        try {
            connection = new DBConnection();
            statement = connection.getConnection().createStatement();

            htmlElements.append("<select name=\"").append(nome).append("\" id=\"").append(nome).append("\" class=\"form-control\">\n");

            htmlElements.append("<option value=\"\">-- Selecione uma opção --</option>\n");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tabela + " ;");

            while (resultSet.next()) {
                String temp = "<option value=\"" + resultSet.getInt(1) + "\" ";

                if (resultSet.getInt(1) == id) {
                    temp += " selected >";
                } else {
                    temp += " > ";
                }

                temp += resultSet.getString(2) + "</option>\n";

                htmlElements.append(temp);
            }

            resultSet.close();
            statement.close();
            connection.getConnection().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        htmlElements.append("</select>");
        return htmlElements.toString();
    }

    public String getElement(String tabela, String id) {
        String valor = "";
        try {
            statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tabela + " WHERE id = " + id + " ;");

            while (resultSet.next()) {
                valor = resultSet.getString(2);
            }
            resultSet.close();
            statement.close();
            connection.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return valor.trim();
    }

    public String getSelectBox(String nomeTabela, String nomeCampo) {
        StringBuilder htmlCombo = new StringBuilder();
        htmlCombo.append("<select name=\"").append(nomeCampo).append("\" id=\"").append(nomeCampo).append("\" class=\"form-control\" >");
        
        htmlCombo.append("<option value=\"\"> -- Selecione uma opção -- </option>\n");
        try {
            DBConnection conexao1 = new DBConnection();
            statement = conexao1.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + nomeTabela);

            while (resultSet.next()) {
                htmlCombo.append("<option value=\"").append(resultSet.getInt(1)).append("\">").append(resultSet.getString(2)).append("</option>");
            }
            resultSet.close();
            statement.close();
            conexao1.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        htmlCombo.append("</select>");
        return htmlCombo.toString();
    }
}
