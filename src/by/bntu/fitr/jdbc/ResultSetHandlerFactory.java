package by.bntu.fitr.jdbc;

import by.bntu.fitr.entity.Equation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerFactory {

    public static ResultSetHandler<Equation> equationResultSetHandler = new ResultSetHandler<Equation>() {
        @Override
        public Equation handle(ResultSet resultSet) throws SQLException {
            Equation equation = new Equation();
            equation.setId(resultSet.getInt("id"));
            equation.setEquation(resultSet.getString("eq"));
            equation.setLowerLim(resultSet.getDouble("lower_lim"));
            equation.setUpperLim(resultSet.getDouble("upper_lim"));
            equation.setStep(resultSet.getDouble("step"));
            equation.setInitY(resultSet.getDouble("init_y"));
            return equation;
        }
    };


    public static <T> ResultSetHandler<List<T>> getListResultSet(ResultSetHandler<T> oneRowResultSetHandler) throws SQLException {
        return new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet resultSet) throws SQLException {
                List<T> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(oneRowResultSetHandler.handle(resultSet));
                }
                return list;
            }
        };
    }
}
