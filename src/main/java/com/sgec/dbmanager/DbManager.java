package com.sgec.dbmanager;

import com.sgec.exception.ConnectionNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author Zeeshan
 */
public class DbManager {

    private static DbManager db = null;
    private static final Logger log = Logger.getLogger(DbManager.class);
    private DataSource UTLIST_DB_DS = null;
    private DataSource CALLREJECT_DB_DS = null;
    private DataSource MOBIMUSIC_DB_DS = null;

    private DbManager() {
        try {
            Context envContext = (Context) new InitialContext().lookup("java:/comp/env");
            UTLIST_DB_DS = (DataSource) envContext.lookup("jdbc/sgec");
//            CALLREJECT_DB_DS = (DataSource) envContext.lookup("jdbc/callrejectDB");
//            MOBIMUSIC_DB_DS = (DataSource) envContext.lookup("jdbc/musicstoreDB");
        } catch (Exception ex) {
            log.error("Error initializing DBManager", ex);
        }
    }

    public static DbManager getInstance() {
        if (db == null) {
            db = new DbManager();
        }
        return db;
    }

    public Connection getJNDIConnection() throws ConnectionNotFoundException {
        Connection result = null;
        try {
            if (UTLIST_DB_DS == null) {
                Context envContext = (Context) new InitialContext().lookup("java:/comp/env");
                UTLIST_DB_DS = (DataSource) envContext.lookup("jdbc/sgec");
            }
            result = UTLIST_DB_DS.getConnection();
        } catch (NamingException ex) {
            throw new ConnectionNotFoundException(ex);
        } catch (SQLException ex) {
            throw new ConnectionNotFoundException(ex);
        }
        return result;
    }

    public void closeJNDIConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Error closing conn", e);
            }
        }
    }

    public String getUserTicket(int logindetailId) {

        String stream_ticket = "";
        Connection con = null;

        try {
            con = getJNDIConnection();

            String query = "SELECT   ticket  FROM  userdetail where logindetailId = ?";
            //       System.out.println("getUserTicket  Query :::::::::::: " + query);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, logindetailId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                stream_ticket = rs.getString(1);
            }
            rs.close();
            pst.close();

        } catch (Exception ex) {
            log.error("getUserTicket(" + logindetailId + ")", ex);
        } finally {
            closeJNDIConnection(con);
        }
        return stream_ticket;
    }

    public String getFilePath(String contentId) {

        String filePath = "";
        Connection con = null;

        try {
            con = getJNDIConnection();

            String query = "SELECT   filePath  FROM  contentdetails where sqId =?";
            //       System.out.println("getUserTicket  Query :::::::::::: " + query);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, contentId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                filePath = rs.getString(1);
                filePath = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
            }
            rs.close();
            pst.close();

        } catch (Exception ex) {
            log.error("getFilePath(" + filePath + ")", ex);
        } finally {
            closeJNDIConnection(con);
        }
        return filePath;
    }

    public String updateUserTicket(int logindetailId, String md5Ticket) {

        Connection con = null;
        String returnStatus = "";
        PreparedStatement preparedStatement = null;
        try {
            String updateTableSQL = "update userdetail set ticket = ? , modifieddate = NOW()  where logindetailId = ?";
            con = getJNDIConnection();
            preparedStatement = con.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, md5Ticket);
            preparedStatement.setInt(2, logindetailId);

            int out = preparedStatement.executeUpdate();
            if (out > 0) {
                //          System.out.println("Record Updated");
                returnStatus = "0";
            } else {
                returnStatus = "-100";
                //             System.out.println("Record not Updated");
            }
        } catch (Exception ex) {
            returnStatus = "-100";
            log.error("updateUserTicket(" + logindetailId + "," + md5Ticket + ")", ex);
        } finally {
            closeJNDIConnection(con);
        }
        return returnStatus;
    }
}
