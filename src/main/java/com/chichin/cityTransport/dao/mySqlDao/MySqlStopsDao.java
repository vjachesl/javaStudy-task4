package com.chichin.cityTransport.dao.mySqlDao;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.dao.interfaces.StopsDao;
import com.chichin.cityTransport.entity.Stop;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.chichin.cityTransport.dao.factory.DaoJdbcUtil.close;

/**
 * Created by viacheslav on 13.06.15.
 */
public class MySqlStopsDao implements StopsDao {
    private static final Logger LOG = Logger.getLogger(MySqlStopsDao.class);

    private DaoFactory daoFactory;
    public MySqlStopsDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static final String FIND_ALL_STOPS ="SELECT * FROM city_transport_db.stops";

    private static final String FIND_ALL_ROUTE_STOPS ="SELECT * FROM city_transport_db.stops\n" +
            "\tJOIN city_transport_db.routes_has_stops ON\n" +
            "\t city_transport_db.stops.STOP_ID = city_transport_db.routes_has_stops.stops_STOP_ID\n" +
            "\t WHERE routes_ROUTE_ID=(?) order by STOPS_ORDER_BY_ROUTE";

    private static final String ADD_NEW_STOP ="INSERT INTO city_transport_db.stops \n" +
            "\t( STOP_ID, STOP_NAME_EN, STOP_NAME_RU, STOP_LAT, STOP_LONG)\n" +
            "\t values (?, ?, ?, ?, ?)";

    private static final String ADD_ROUTE_STOP ="INSERT INTO city_transport_db.routes_has_stops\n" +
            "\t (routes_ROUTE_ID, stops_STOP_ID, STOPS_ORDER_BY_ROUTE) values( ? , ? , ?)";

    private static final String DELETE_ROUTE_STOP =" DELETE FROM city_transport_db.routes_has_stops\n" +
            "\t WHERE routes_ROUTE_ID=(?) AND stops_STOP_ID=(?)";

    private static final String DELETE_STOP_BY_ID =" DELETE FROM city_transport_db.stops\n" +
            "\t WHERE STOP_ID=(?)";

    public List<Stop> getAllStops() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Stop> stops = new ArrayList<Stop>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_STOPS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stops.add(map(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        LOG.info("list of stops was formed");
        return stops;
    }

    public Stop getStop(int stopId) {
        LOG.info("finding stop "+stopId );
        List<Stop> stops = getAllStops();
        for (Stop st : stops) if (st.STOP_ID()==stopId) {
            LOG.info("stop was found");
            return st;
        }
        LOG.info("stop was not found");
        return null;
    }

    public List<Stop> getRouteStops(int routeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Stop> stops = new ArrayList<Stop>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_ROUTE_STOPS);
            preparedStatement.setInt(1, routeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stops.add(map(resultSet));
            }
        } catch (SQLException ex) {
            LOG.warn("was caused an exception during query executing");
            ex.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        LOG.info("list of stops was formed");
        return stops;
     }

    public int addNewStop(Stop stop) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_STOP);
            preparedStatement.setInt(1, stop.STOP_ID());
            preparedStatement.setString(2, stop.getNameEn());
            preparedStatement.setString(3, stop.getNameRu());
            preparedStatement.setDouble(4, stop.getGeoPoint().getLat());
            preparedStatement.setDouble(5, stop.getGeoPoint().getLng());
            int result = preparedStatement.executeUpdate();
            LOG.info("stop was added with result"+result);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.warn("was caused an exception during query executing");
            return 0;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public int assignStopOnRoute(int stopId, int routeId, int orderByRoute) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(ADD_ROUTE_STOP);
            preparedStatement.setInt(1, routeId);
            preparedStatement.setInt(2, stopId);
            preparedStatement.setInt(3, orderByRoute);
            int result = preparedStatement.executeUpdate();
            LOG.info("stop was assigned on route with result" + result);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.warn("was caused an exception during query executing");
            return 0;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public int removeStopFromRoute(int stopId, int routeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ROUTE_STOP);
            preparedStatement.setInt(1, routeId);
            preparedStatement.setInt(2, stopId);
            int result = preparedStatement.executeUpdate();
            LOG.info("stop was removed from route with result" + result);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.warn("was caused an exception during query executing");
            return 0;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public int removeStop(int stopId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_STOP_BY_ID);
            preparedStatement.setInt(1, stopId);
            int result = preparedStatement.executeUpdate();
            LOG.info("delete stop was complete without exeption and result " + result);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.warn("deleting stop was caused exeption");
            return 0;
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
    }

    private Stop map(ResultSet resultSet) throws SQLException {
        return new Stop(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5));
    }
}
