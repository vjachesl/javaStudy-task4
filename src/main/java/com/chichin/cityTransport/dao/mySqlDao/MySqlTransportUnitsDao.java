package com.chichin.cityTransport.dao.mySqlDao;

import static com.chichin.cityTransport.dao.factory.DaoJdbcUtil.*;
import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.dao.interfaces.TransportUnitsDao;
import com.chichin.cityTransport.entity.TransportTypes;
import com.chichin.cityTransport.entity.TransportUnit;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by viacheslav on 13.06.15.
 */
public class MySqlTransportUnitsDao implements TransportUnitsDao {
    private static final Logger LOG = Logger.getLogger(MySqlTransportUnitsDao.class);

    private DaoFactory daoFactory;

    private static final String FIND_ALL_TRANSPORT_UNITS ="SELECT * FROM city_transport_db.transport_units";
    private static final String FIND_TRANSPORT_UNITS_FROM_ROUTE ="SELECT * FROM city_transport_db.transport_units WHERE ROUTE_ID=(?)";
    private static final String FIND_TRANSPORT_UNITS_WITH_NULL_ROUTE ="SELECT * FROM city_transport_db.transport_units WHERE ROUTE_ID IS null;";
    private static final String ADD_TRANSPORT_UNIT ="INSERT INTO city_transport_db.transport_units \n" +
           "\t (UNIT_ID, MODEL_NAME_EN, MODEL_NAME_RU, TRANSPORT_TYPE, ROUTE_ID)\n" +
           "\t values (?, ?, ?, ?)";
    private static final String UPDATE_TRANSPORT_UNIT_TO_ROUTE ="UPDATE city_transport_db.transport_units\n" +
            "\t SET ROUTE_ID=(?) WHERE UNIT_ID=(?)";

    private static final String REMOVE_TRANSPORT_UNIT_FROM_ROUTE ="UPDATE city_transport_db.transport_units\n" +
            "\t SET ROUTE_ID= null WHERE UNIT_ID=(?)";

    private static final String DELETE_TRANSPORT_UNIT_BY_ID =" DELETE FROM city_transport_db.transport_units\n" +
            "\t WHERE UNIT_ID=(?)";


    public MySqlTransportUnitsDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public TransportUnit getTransportUnit(int unitId) {
        LOG.info("finding unit "+unitId );
        List<TransportUnit> transportUnits = getAllTransportUnits();
        for (TransportUnit tu : transportUnits) if (tu.UNIT_ID()==unitId) {
            LOG.info("unit was found");
            return tu;
        }
        LOG.info("unit was not found");
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
        LOG.info("list of Units was formed");
        return transportUnits;
    }

    public List<TransportUnit> getRouteTransportUnits(int routeId) {
        if (routeId==0) return getZeroTransportUnits();
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
            LOG.warn("was caused an exception during query executing");
            ex.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        LOG.info("list of units from route "+routeId+" was formed");
        return transportUnits;
    }

    private List<TransportUnit> getZeroTransportUnits() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TransportUnit> transportUnits = new ArrayList<TransportUnit>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(FIND_TRANSPORT_UNITS_WITH_NULL_ROUTE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transportUnits.add(map(resultSet));
            }
        } catch (SQLException ex) {
            LOG.warn("was caused an exception during query executing");
            ex.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        LOG.info("list of units from route zero was formed");
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
            int result = preparedStatement.executeUpdate();
            LOG.info("unit "+unit.UNIT_ID()+" was added with result"+ result);
            return result;
        } catch (SQLException ex) {
            LOG.warn("was caused an exception during query executing");
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
            int result = preparedStatement.executeUpdate();
            LOG.info("unit "+unitId+" was assigned to route"+routeId+" with result "+ result);
            return result;
        } catch (SQLException ex) {
            LOG.warn("was caused an exception during query executing");
            ex.printStackTrace();
            return 0;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public int removeTransportUnitFromRoute(int unitId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(REMOVE_TRANSPORT_UNIT_FROM_ROUTE);
            preparedStatement.setInt(1, unitId);
            int result = preparedStatement.executeUpdate();
            LOG.info("unit "+unitId+" was assigned to route null with result "+ result);
            return result;
        } catch (SQLException ex) {
            LOG.warn("was caused an exception during query executing");
            ex.printStackTrace();
            return 0;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public int removeTransportUnit(int unitId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_TRANSPORT_UNIT_BY_ID);
            preparedStatement.setInt(1, unitId);
            int result = preparedStatement.executeUpdate();
            LOG.info("unit "+unitId+" was deleted" + result);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.warn("deleting unit was caused exeption");
            return 0;
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
    }

    private TransportUnit map(ResultSet resultSet) throws SQLException {
        return new TransportUnit(resultSet.getInt(1),
                resultSet.getString(2),resultSet.getString(3),
                TransportTypes.valueOf(resultSet.getString(4)),
                resultSet.getInt(5));
    }
}
