package ar.com.survey.persistence;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.com.survey.persistence.hibernate.HibernateUtil;

public class DBHelper {
	
	private static Connection getConn() {
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		return HibernateUtil.getSessionFactory().getCurrentSession().connection();
	}
    public static int countRecords(Table table) {
        int result = -1;
        try {
            Connection con = getConn();
            PreparedStatement pstmt = con.prepareStatement("select count(*) from "+table);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            result = rs.getInt(1);
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
