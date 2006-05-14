package ar.com.survey.persistence.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.hibernate.HibernateException;
import org.hibernate.connection.ConnectionProvider;




/**
 * El provider para DBCP de hibernate 2.x no contempla todas las propiedades ni el mejor uso posible de DBCP.
 * Peor a�n, en Hibernate 3 se lo elimin�. El problema estaba en que DBCP iba mejorando y no hab�a inter�s en mantener todo el c�digo responsable
 * de pasar las propiedades desde Hibernate.properties hasta el provider mismo. Por lo tanto, se elimin� y que cada usario cree su propio provider custom para DBCP.
 * Esto es exactamente lo que esta clase hace. Su implementaci�n se basa en una clase propuesta en http://wiki.apache.org/jakarta-commons/DBCP/Hibernate.
 * La diferencia fundamental es que la original tiene c�digo para parsear las propiedades de hibernate de la sesi�n y adaptarlas a lo que espera
 * DBCP. Esta implementaci�n simplifica el procesamiento de propiedades buscando dbcp-cfg.properties y pas�ndolo entero al factory de DBCP. Esto permite:
 * <br>
 * <li>simplificar el c�digo, por consiguente hacerlo m�s mantenible</li>
 * <li>mantener separada la configuraci�n del pool</li>
 * <li>acompa�ar mejor la evoluci�n de DBCP</li>
 * <li>mantener varias configuraciones de pool sin tener que editar hibernate.properties o embeberlas en el SessionFactory de Hibernate.</li>
 *
 * Este provider no lee datos del entonor de Hibernate (hibernate.properties, etc.)
 * @author sesponda
 */
public class DBCPConnectionProvider implements ConnectionProvider {
    //~ Instance fields ----------------------------------------------------------------------------

    private BasicDataSource pool = null;

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Obtener una conection
     * @return connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {  
        return this.pool.getConnection();
    }

    /**
     * Cerrar el provider
     * @throws HibernateException
     */
    public void close() throws HibernateException {
        try {
            this.pool.close();
        } catch (SQLException e) {
            throw new HibernateException(e);
        }
    }
    /**
     * Cerrar una conection
     * @param conn
     * @throws SQLException
     */
    public void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
    /**
     * Configuracion de DBCP.
     *
     * @param props este par�metro se ignora
     * @exception HibernateException
     */
    public void configure(Properties props) throws HibernateException {
        try {
        	Properties prop = new Properties();
        	prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("pool.properties"));
            this.pool = (BasicDataSource) BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }
    /**
     * Does this connection provider support aggressive release of JDBC
     * connections and re-acquistion of those connections (if need be) later?
     * <p/>
     * This is used in conjunction with {@link org.hibernate.cfg.Environment.RELEASE_CONNECTIONS}
     * to aggressively release JDBC connections.  However, the configured ConnectionProvider
     * must support re-acquisition of the same underlying connection for that semantic to work.
     * <p/>
     * Typically, this is only true in managed environments where a container
     * tracks connections by transaction or thread.
     */
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}
}

