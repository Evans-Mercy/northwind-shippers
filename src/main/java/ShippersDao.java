import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

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
}

