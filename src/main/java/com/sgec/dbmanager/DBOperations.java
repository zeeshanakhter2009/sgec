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

}
