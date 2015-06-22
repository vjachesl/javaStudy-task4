package com.chichin.cityTransport.dao.mySqlDao;

import static com.chichin.cityTransport.dao.factory.DaoJdbcUtil.*;
import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.dao.interfaces.RouteDao;
import com.chichin.cityTransport.entity.Route;
import com.chichin.cityTransport.entity.TransportTypes;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by viacheslav on 13.06.15.
 */
public class MySqlRouteDao implements RouteDao {
    private static final Logger LOG = Logger.getLogger(MySqlRouteDao.class);
    private DaoFactory daoFactory;

    private static final String FIND_ALL_ROUTES ="SELECT * FROM city_transport_db.routes";
    private static final String ADD_NEW_ROUTE ="INSERT INTO city_transport_db.routes \n" +
            "\t( ROUTE_ID, ROUTE_NAME_EN, ROUTE_NAME_RU, ROUTE_TRANSPORT_TYPE)\n" +
            "\t values (?, ?, ?, ?)";
    private static final String DELETE_ROUTE_BY_ID ="DELETE FROM city_transport_db.routes where city_transport_db.routes.ROUTE_ID=(?)";

    public MySqlRouteDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Route> getAllRoutes() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Route> routes = new ArrayList<Route>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_ROUTES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(map(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        LOG.info("list of routes was formed");
        return routes;
    }

    public Route getRoute(int routeId) {
        LOG.info("finding route "+routeId );
       List<Route> routes = getAllRoutes();
        for (Route ro : routes) if (ro.ROUTE_ID()==routeId) {
            LOG.info("route was found");
            return ro;
        }
        LOG.info("route was not found");
        return null;
    }

    public int addNewRoute(Route route) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_ROUTE);
            preparedStatement.setInt(1, route.ROUTE_ID());
            preparedStatement.setString(2, route.ROUTE_NAME_EN());
            preparedStatement.setString(3, route.ROUTE_NAME_RU());
            preparedStatement.setString(4, String.valueOf(route.ROUTE_TRANSPORT_TYPE()));
            int result = preparedStatement.executeUpdate();
            LOG.info("adding new route was complete without exeption and result "+result);
            return result;
            } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.warn("adding new route was caused exeption");
            return 0;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public int removeRoute(int routeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ROUTE_BY_ID);
            preparedStatement.setInt(1, routeId);
           int result = preparedStatement.executeUpdate();
            LOG.info("delete route was complete without exeption and result "+result);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.warn("deleting route was caused exeption");
            return 0;
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
    }

    private Route map(ResultSet resultSet) throws SQLException {
        return new Route(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3),TransportTypes.valueOf(resultSet.getString(4)));
    }
}
