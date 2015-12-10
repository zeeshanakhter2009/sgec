/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgec.dbmanager;

/**
 *
 * @author ZA
 */
import com.sgec.model.ChildRegistration;
import com.sgec.model.DeathRegistration;
import com.sgec.model.User;
import com.sgec.utility.ReturnStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Zeeshan
 */
public class DBOperations {

    private static final Logger log = Logger.getLogger(DBOperations.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

    /**
     * ************************************
     *
     * @param userName
     * @param password
     * @return
     */
    public ReturnStatus getUserAccount(String userName, String password) {

        Connection con = null;
        PreparedStatement pst = null;
        ReturnStatus returnStatus = new ReturnStatus();
        DbManager db = DbManager.getInstance();
        User user = new User();
        try {

            String query = "SELECT\n"
                    + "  `pkUserId`,\n"
                    + "  `userName`,\n"
                    + "  `password`,\n"
                    + "  `userType`,\n"
                    + "  `firstName`,\n"
                    + "  `lastName`,\n"
                    + "  `email`,\n"
                    + "  `contact`"
                    + "FROM  `users`\n"
                    + "WHERE userName = ? AND password = ? LIMIT 1;";

            con = (Connection) db.getJNDIConnection();
            pst = con.prepareStatement(query);
            pst.setString(1, userName);
            pst.setString(2, password);
            int count = 0;
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                user.setPkUserId(rs.getString("pkUserId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("userType"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setContact(rs.getString("contact"));
                count++;
            }
            if (count > 0) {
                returnStatus.setMessage("Record Fetch Successfully");
                returnStatus.setErrorCode(1);
                returnStatus.setStatusCode("Y");
                //returnStatus.setObjectList((List<Object>) list);
                returnStatus.setObject(user);
            } else {
                returnStatus.setErrorCode(0);
                returnStatus.setStatusCode("N");
                returnStatus.setMessage("Record not Fetch Successfully");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            returnStatus.setExceptionMessage(e.getMessage());
            e.printStackTrace();
            log.debug("Exception :: ", e);
        } finally {
            try {
                pst.close();
                db.closeJNDIConnection(con);
            } catch (Exception ex) {
                returnStatus.setExceptionMessage(ex.getMessage());
                ex.printStackTrace();
                log.debug("Exception :: ", ex);
            }
        }
        return returnStatus;
    }

    /**
     * ************************************
     *
     * @param userName
     * @param password
     * @return
     */
    public ReturnStatus insertChildRegistration(ChildRegistration childRegistration) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet keys = null;
        int key = 0;
        ReturnStatus returnStatus = new ReturnStatus();
        DbManager db = DbManager.getInstance();

        try {

            String query = "INSERT INTO  birthdetails "
                    + "(motherName,motherNationality,DateofBirth, fatherName,fatherNationality,"
                    + "grandFatherName, grandMotherName,weight,height, skinColor,eyeColor,\n"
                    + "country, city,hospital,createdBy ,status,createdDate)"
                    + "VALUES "
                    + "(?,?,? ,?,?,? ,?,?,? ,?,?,? ,?,?,? ,'1',NOW())";

            con = (Connection) db.getJNDIConnection();

            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            log.debug("insertChildRegistration ::::::: \n " + query);

            pst.setString(1, childRegistration.getMotherName());
            pst.setString(2, childRegistration.getMotherNationality());
            pst.setString(3, childRegistration.getDateofBirth());
            pst.setString(4, childRegistration.getFatherName());
            pst.setString(5, childRegistration.getFatherNationality());
            pst.setString(6, childRegistration.getGrandFatherName());
            pst.setString(7, childRegistration.getGrandMotherName());
            pst.setDouble(8, childRegistration.getWeight());
            pst.setDouble(9, childRegistration.getHeight());
            pst.setString(10, childRegistration.getSkinColor());
            pst.setString(11, childRegistration.getEyeColor());
            pst.setString(12, childRegistration.getCountry());
            pst.setString(13, childRegistration.getCity());
            pst.setString(14, childRegistration.getHospital());
            pst.setString(15, childRegistration.getCreatedBy());

            int out = pst.executeUpdate();
            keys = pst.getGeneratedKeys();

            while (keys.next()) {
                key = keys.getInt(1);
            }

            if (out != 0) {
                childRegistration.setBirthDetailsId(key);
                log.debug("Record Saved");
                returnStatus.setErrorCode(1);
                returnStatus.setStatusCode("Y");
                returnStatus.setObject(childRegistration);
                returnStatus.setLastInsertedId(key);
            } else {
                log.debug("Record not Saved");
                returnStatus.setErrorCode(0);
                returnStatus.setStatusCode("N");
                returnStatus.setLastInsertedId(key);
            }

        } catch (Exception e) {
            returnStatus.setExceptionMessage(e.getMessage());
            e.printStackTrace();
            log.debug("Exception :: ", e);
        } finally {
            try {
                pst.close();
                db.closeJNDIConnection(con);
            } catch (Exception ex) {
                returnStatus.setExceptionMessage(ex.getMessage());
                ex.printStackTrace();
                log.debug("Exception :: ", ex);
            }
        }
        return returnStatus;
    }

    /**
     * **
     *
     * @param deathRegistration
     * @return
     */
    public ReturnStatus insertDeathRegistration(DeathRegistration deathRegistration) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet keys = null;
        int key = 0;
        ReturnStatus returnStatus = new ReturnStatus();
        DbManager db = DbManager.getInstance();

        try {

            String query = "INSERT INTO deathdetails "
                    + "(citizenId, country, city, placeOfDeath, address, hospitalName, other, "
                    + "DateOfDeath, ReasonOfDeath, diseaseName, accidentDetails, status, createdDate, modifiedDate, createdBy) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '1', NOW(), NOW(), ?);";

            con = (Connection) db.getJNDIConnection();

            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            log.debug("insertDeathRegistration ::::::: \n " + query);

            pst.setInt(1, deathRegistration.getCitizenId());
            pst.setString(2, deathRegistration.getCountry());
            pst.setString(3, deathRegistration.getCity());
            pst.setString(4, deathRegistration.getPlaceOfDeath());
            pst.setString(5, deathRegistration.getAddress());
            pst.setString(6, deathRegistration.getHospitalName());
            pst.setString(7, deathRegistration.getOther());
            pst.setString(8, deathRegistration.getDateOfDeath());
            pst.setString(9, deathRegistration.getReasonOfDeath());
            pst.setString(10, deathRegistration.getDiseaseName());
            pst.setString(11, deathRegistration.getAccidentDetails());
            pst.setString(12, deathRegistration.getCreatedBy());

            int out = pst.executeUpdate();
            keys = pst.getGeneratedKeys();

            while (keys.next()) {
                key = keys.getInt(1);
            }

            if (out != 0) {
                deathRegistration.setDeathDetailsId(key);
                log.debug("Record Saved");
                returnStatus.setErrorCode(1);
                returnStatus.setStatusCode("Y");
                returnStatus.setObject(deathRegistration);
                returnStatus.setLastInsertedId(key);
            } else {
                log.debug("Record not Saved");
                returnStatus.setErrorCode(0);
                returnStatus.setStatusCode("N");
                returnStatus.setLastInsertedId(key);
            }

        } catch (Exception e) {
            returnStatus.setExceptionMessage(e.getMessage());
            e.printStackTrace();
            log.debug("Exception :: ", e);
        } finally {
            try {
                pst.close();
                db.closeJNDIConnection(con);
            } catch (Exception ex) {
                returnStatus.setExceptionMessage(ex.getMessage());
                ex.printStackTrace();
                log.debug("Exception :: ", ex);
            }
        }
        return returnStatus;
    }
    
    
    public ReturnStatus deleteDeathRegistration(String deathRegistrationId) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet keys = null;
        int key = 0;
        ReturnStatus returnStatus = new ReturnStatus();
        DbManager db = DbManager.getInstance();

        try {

            String query = "UPDATE deathdetails SET status='0', modifiedDate = NOW() WHERE deathDetailsId = ? ;";

            con = (Connection) db.getJNDIConnection();

            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            log.debug("deleteDeathRegistration ::::::: \n " + query);

            pst.setString(1, deathRegistrationId);

            int out = pst.executeUpdate();
            keys = pst.getGeneratedKeys();

            while (keys.next()) {
                key = keys.getInt(1);
            }

            if (out != 0) {
               
                log.debug("Record Deleted");
                returnStatus.setErrorCode(1);
                returnStatus.setStatusCode("Y");
                returnStatus.setLastInsertedId(key);
            } else {
                log.debug("Record not Saved");
                returnStatus.setErrorCode(0);
                returnStatus.setStatusCode("N");
                returnStatus.setLastInsertedId(key);
            }

        } catch (Exception e) {
            returnStatus.setExceptionMessage(e.getMessage());
            e.printStackTrace();
            log.debug("Exception :: ", e);
        } finally {
            try {
                pst.close();
                db.closeJNDIConnection(con);
            } catch (Exception ex) {
                returnStatus.setExceptionMessage(ex.getMessage());
                ex.printStackTrace();
                log.debug("Exception :: ", ex);
            }
        }
        return returnStatus;
    }
    
    /*******
     * 
     * @param childRegistrationId
     * @return 
     */
    public ReturnStatus deleteChildRegistration(String childRegistrationId) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet keys = null;
        int key = 0;
        ReturnStatus returnStatus = new ReturnStatus();
        DbManager db = DbManager.getInstance();

        try {

            String query = "UPDATE birthdetails SET status='0', modifiedDate = NOW() WHERE birthDetailsId = ? ;";

            con = (Connection) db.getJNDIConnection();

            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            log.debug("deleteDeathRegistration ::::::: \n " + query);

            pst.setString(1, childRegistrationId);

            int out = pst.executeUpdate();
            keys = pst.getGeneratedKeys();

            while (keys.next()) {
                key = keys.getInt(1);
            }

            if (out != 0) {
               
                log.debug("Record Deleted");
                returnStatus.setErrorCode(1);
                returnStatus.setStatusCode("Y");
                returnStatus.setLastInsertedId(key);
            } else {
                log.debug("Record not Saved");
                returnStatus.setErrorCode(0);
                returnStatus.setStatusCode("N");
                returnStatus.setLastInsertedId(key);
            }

        } catch (Exception e) {
            returnStatus.setExceptionMessage(e.getMessage());
            e.printStackTrace();
            log.debug("Exception :: ", e);
        } finally {
            try {
                pst.close();
                db.closeJNDIConnection(con);
            } catch (Exception ex) {
                returnStatus.setExceptionMessage(ex.getMessage());
                ex.printStackTrace();
                log.debug("Exception :: ", ex);
            }
        }
        return returnStatus;
    }

    public List<ChildRegistration> getChildRegistrationList() {
        Connection con = null;

        PreparedStatement ps = null;
        List<ChildRegistration> list = new ArrayList<ChildRegistration>();
        int count = 0;
        DbManager db = DbManager.getInstance();
        try {
            String query = " SELECT birthDetailsId,motherName,motherNationality,DateofBirth,fatherName,fatherNationality, \n"
                    + " grandFatherName,grandMotherName,weight,height,skinColor,eyeColor,country, \n"
                    + " city,hospital,status,createdDate,modifiedDate,createdBy \n"
                    + " FROM birthdetails where status='1' order by birthDetailsId desc";

            con = (Connection) db.getJNDIConnection();
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count++;
                ChildRegistration childRegistration = new ChildRegistration();
                int birthDetailsId = rs.getInt("birthDetailsId");
                String motherName = rs.getString("motherName");
                String fatherName = rs.getString("fatherName");
                String motherNationality = rs.getString("motherNationality");
                String fatherNationality = rs.getString("fatherNationality");
                String grandMotherName = rs.getString("grandMotherName");
                String grandFatherName = rs.getString("grandFatherName");
                String dateofBirth = rs.getString("dateofBirth");
                String weight = rs.getString("weight");
                String height = rs.getString("height");
                String skinColor = rs.getString("skinColor");
                String eyeColor = rs.getString("eyeColor");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String hospitalName = rs.getString("hospital");
                String createdBy = rs.getString("createdBy");
                String status = rs.getString("status");
                String createdDate = rs.getString("createdDate");
                String modifiedDate = rs.getString("modifiedDate");

                childRegistration.setBirthDetailsId(birthDetailsId);
                childRegistration.setMotherName(motherName);
                childRegistration.setFatherName(fatherName);
                childRegistration.setDateofBirth(dateofBirth);
                childRegistration.setCity(city);
                childRegistration.setCountry(country);
                childRegistration.setCreatedBy(createdBy);
                childRegistration.setEyeColor(eyeColor);
                childRegistration.setSkinColor(skinColor);
                childRegistration.setFatherNationality(fatherNationality);
                childRegistration.setMotherNationality(motherNationality);
                childRegistration.setGrandFatherName(grandFatherName);
                childRegistration.setGrandMotherName(grandMotherName);
                childRegistration.setHeight(Double.parseDouble(height));
                childRegistration.setWeight(Double.parseDouble(weight));
                childRegistration.setHospital(hospitalName);
                childRegistration.setStatus(status);
                childRegistration.setModifiedDate(modifiedDate);
                childRegistration.setCreatedDate(createdDate);

                // uploadDetails.toString();
                list.add(childRegistration);
            }
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            log.debug("Exception :: ", e);
        } finally {
            try {
                ps.close();
                db.closeJNDIConnection(con);
            } catch (Exception ex) {
                //  System.err.println(ex.getMessage());
                log.debug("Exception :: ", ex);
            }
        }
        //   System.out.println(count + "  ==count \n" + "List Size==== " + list.size());
        return list;
    }

    public List<DeathRegistration> getDeathRegistrationList() {
        Connection con = null;

        PreparedStatement ps = null;
        List<DeathRegistration> list = new ArrayList<DeathRegistration>();
        int count = 0;
        DbManager db = DbManager.getInstance();
        try {
            String query = "SELECT deathDetailsId, citizenId, country, city, placeOfDeath, address, hospitalName, other, DateOfDeath, ReasonOfDeath, diseaseName, "
                    + "accidentDetails, status, createdDate, modifiedDate, createdBy FROM deathdetails where status='1' order by deathDetailsId desc;";

            con = (Connection) db.getJNDIConnection();
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count++;
                DeathRegistration deathRegistration = new DeathRegistration();
               
                deathRegistration.setDeathDetailsId(rs.getInt("deathDetailsId"));
                deathRegistration.setCitizenId(rs.getInt("citizenId"));
                deathRegistration.setCountry(rs.getString("country"));
                deathRegistration.setCity(rs.getString("city"));
                deathRegistration.setPlaceOfDeath(rs.getString("placeOfDeath"));
                deathRegistration.setAddress(rs.getString("address"));
                deathRegistration.setHospitalName(rs.getString("hospitalName"));
                deathRegistration.setOther(rs.getString("other"));
                deathRegistration.setDateOfDeath(rs.getString("DateOfDeath"));
                deathRegistration.setReasonOfDeath(rs.getString("ReasonOfDeath"));
                deathRegistration.setDiseaseName(rs.getString("diseaseName"));
                deathRegistration.setAccidentDetails(rs.getString("accidentDetails"));
                deathRegistration.setStatus(rs.getString("status"));
                deathRegistration.setCreatedDate(rs.getString("createdDate"));
                deathRegistration.setModifiedDate(rs.getString("modifiedDate"));
                deathRegistration.setCreatedBy(rs.getString("createdBy"));
                
                // uploadDetails.toString();
                list.add(deathRegistration);
            }
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            log.debug("Exception :: ", e);
        } finally {
            try {
                ps.close();
                db.closeJNDIConnection(con);
            } catch (Exception ex) {
                //  System.err.println(ex.getMessage());
                log.debug("Exception :: ", ex);
            }
        }
        //   System.out.println(count + "  ==count \n" + "List Size==== " + list.size());
        return list;
    }

}
