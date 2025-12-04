import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShippersDao {
    //add Shipper
    //get all shippers
    //update phone
    //delete
    private BasicDataSource dataSource;

    String url = "jdbc:mysql://localhost:3306/northwind";
    String userName = "root";
    String password = "yearup";

    public ShippersDao() {
        this.dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
    }

    //INSERT
    public long insert(Shipper shipper) {
        String sql = "INSERT INTO shippers (CompanyName, Phone) VALUES (?,?)";
        long generatedKey = -1;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, shipper.getCompanyName());
            preparedStatement.setString(2, shipper.getPhone());

            int rows = preparedStatement.executeUpdate();
            System.out.printf("Rows updated %d\n", rows);

            //result with primary key
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    generatedKey = keys.getLong(1);
                    System.out.printf("New Shipper ID: %d\n" + generatedKey);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedKey;
    }

    //GET ALL SHIPPERS
    public List<Shipper> getAllShippers() {
        List<Shipper> shippers = new ArrayList<>();
        String sql = "SELECT CompanyName, Phone FROM shippers";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("CompanyName");
                String phone = resultSet.getString("Phone");
                shippers.add(new Shipper(name, phone));
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return shippers;
    }
}

