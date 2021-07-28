package by.bntu.fitr.model.main_logic;

import by.bntu.fitr.entity.Equation;
import by.bntu.fitr.jdbc.JDBCUtils;
import by.bntu.fitr.jdbc.ResultSetHandler;
import by.bntu.fitr.jdbc.ResultSetHandlerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Generator {


    public static Equation getEquation() {

        try {
            JDBCUtils.loadProperties();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        List<Equation> equationList = null;


        try (Connection connection = JDBCUtils.getConnection()) {
            ResultSetHandler<List<Equation>> listResultSetHandler =
                    ResultSetHandlerFactory.getListResultSet(ResultSetHandlerFactory.equationResultSetHandler);
            equationList = JDBCUtils.select(connection, listResultSetHandler, "SELECT * FROM equation");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        int random = (int) (Math.random() * equationList.size());
        return equationList.get(random);
    }
}
