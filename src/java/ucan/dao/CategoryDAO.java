package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.CategoryModel;
import ucan.utils.DBConnection;

public class CategoryDAO {
    private DBConnection connection;

    public CategoryDAO() {

    }

    public void create(CategoryModel category) {
        String sql = "INSERT INTO categoria(nome) values(?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, category.getName());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public void update(CategoryModel category) {
        String sql = "UPDATE categoria SET nome = ? WHERE pk_categoria = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getCategoryId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public void delete(int categoryId) {
        String sql = "DELETE FROM categoria WHERE pk_categoria = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, categoryId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public List<CategoryModel> getAll() {
        String sql = "SELECT * FROM categoria";

        List<CategoryModel> categoryList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                CategoryModel category = new CategoryModel();
                category.setCategoryId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

                categoryList.add(category);
            }
            ps.close();
            resultSet.close();

            return categoryList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public CategoryModel getCategoryById(int categoryId) {
        String sql = "SELECT * FROM categoria WHERE pk_categoria = ?";

        try {
            CategoryModel category = new CategoryModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, categoryId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                category.setCategoryId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return category;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
        return null;
    }
}
