package com.chichin.cityTransport.dao.mySqlDao;

import static com.chichin.cityTransport.dao.factory.DaoJdbcUtil.*;
import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.dao.interfaces.TransportUnitsDao;
import com.chichin.cityTransport.entity.TransportTypes;
import com.chichin.cityTransport.entity.TransportUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by viacheslav on 13.06.15.
 */
public class MySqlTransportUnitsDao implements TransportUnitsDao {
    private DaoFactory daoFactory;

    private static final String FIND_ALL_TRANSPORT_UNITS ="SELECT * FROM city_transport_db.transport_units";
    private static final String FIND_TRANSPORT_UNITS_FROM_ROUTE ="SELECT * FROM city_transport_db.transport_units WHERE ROUTE_ID=(?)";
    private static final String ADD_TRANSPORT_UNIT ="INSERT INTO city_transport_db.transport_units \n" +
           "\t (UNIT_ID, MODEL_NAME_EN, MODEL_NAME_RU, TRANSPORT_TYPE, ROUTE_ID)\n" +
           "\t values (?, ?, ?, ?, ?)";
    private static final String UPDATE_TRANSPORT_UNIT_TO_ROUTE ="UPDATE city_transport_db.transport_units\n" +
            "\t SET ROUTE_ID=(?) WHERE UNIT_ID=(?)";



    public MySqlTransportUnitsDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public TransportUnit getTransportUnit(int unitId) {
        List<TransportUnit> transportUnits = getAllTransportUnits();
        for (TransportUnit tu : transportUnits) if (tu.UNIT_ID()==unitId) return tu;
        return null;
    }

    public List<TransportUnit> getAllTransportUnits() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TransportUnit> transportUnits = new ArrayList<TransportUnit>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_TRANSPORT_UNITS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transportUnits.add(map(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return transportUnits;
    }

    public List<TransportUnit> getRouteTransportUnits(int routeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TransportUnit> transportUnits = new ArrayList<TransportUnit>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(FIND_TRANSPORT_UNITS_FROM_ROUTE);
            preparedStatement.setInt(1, routeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transportUnits.add(map(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return transportUnits;
    }

    public int addNewTransportUnit(TransportUnit unit) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(ADD_TRANSPORT_UNIT);
            preparedStatement.setInt(1, unit.UNIT_ID());
            preparedStatement.setString(2, unit.MODEL_NAME_EN());
            preparedStatement.setString(3, unit.MODEL_NAME_RU());
            preparedStatement.setString(4, String.valueOf(unit.TRANSPORT_TYPE()));
            preparedStatement.setInt(5, (unit.getRoute() == null ? null : unit.getRoute().ROUTE_ID()));
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public int assignTransportUnitOnRoute(int unitId, int routeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TRANSPORT_UNIT_TO_ROUTE);
            preparedStatement.setInt(1, routeId);
            preparedStatement.setInt(2, unitId);
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public int removeTransportUnitFromRoute(int unitId) {
       return assignTransportUnitOnRoute(unitId, 0);
    }

    private TransportUnit map(ResultSet resultSet) throws SQLException {
        return new TransportUnit(resultSet.getInt(1),
                resultSet.getString(2),resultSet.getString(3),
                TransportTypes.valueOf(resultSet.getString(4)),
                daoFactory.getRouteDao().getRoute(resultSet.getInt(5)));
    }
}
