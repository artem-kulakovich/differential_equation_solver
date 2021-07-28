package by.bntu.fitr.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static Properties properties = new Properties();


    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(properties.getProperty("db.url")
                    , properties.getProperty("db.username")
                    , properties.getProperty("db.password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void insert(Connection connection, String sql, Object... parameters) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setParameters(preparedStatement, parameters);
            preparedStatement.execute();

        }
    }

    public static <T> T select(Connection connection, ResultSetHandler<T> resultSetHandler, String sql, Object... parameters) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setParameters(preparedStatement, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetHandler.handle(resultSet);
        }
    }


    public static void setParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i + 1, parameters[i]);
        }
    }

    public static void loadProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Artyom\\IdeaProjects\\course_work\\resources\\connection.properties");
        properties.load(fileInputStream);
    }
}
