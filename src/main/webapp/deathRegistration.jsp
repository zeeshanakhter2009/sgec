<%@page import="java.util.List"%>
<%@page import="com.sgec.model.ChildRegistration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="dbOperations" class="com.sgec.dbmanager.DBOperations" ></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="favicon.ico">

        <title>SGEC-Login</title>

        <!-- Bootstrap core CSS -->
        <link href="dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="dist/css/signin.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="assets/js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="dist/js/jquery.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>
    </head>

    <body>

        <jsp:include page="menu.jsp" ></jsp:include>


        <div   class="container">

            </br>
            </br>
            <!--            <div class="row">
                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">-->
            <div class="row">
                <div class="col-lg-12 text-center">
                    <form role="form">
                        <h2>Death <small>Registration.</small></h2>
                        <hr class="colorgraph">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="citizenId" id="citizenId" class="form-control input-lg" placeholder="Citizen ID" tabindex="1">
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="hospitalName" id="hospitalName" class="form-control input-lg" placeholder="Hospital Name" tabindex="2">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="country" id="country" class="form-control input-lg" placeholder="Country" tabindex="3">
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="city" id="city" class="form-control input-lg" placeholder="City" tabindex="4">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="address" id="address" class="form-control input-lg" placeholder="Address" tabindex="5">
                        </div>
                        <div class="form-group">
                            <input type="text" name="other" id="other" class="form-control input-lg" placeholder="Other" tabindex="6">
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="dateOfDeath" id="dateOfDeath" class="form-control input-lg" placeholder="Date Of Death" tabindex="7">
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="reasonOfDeath" id="reasonOfDeath" class="form-control input-lg" placeholder="Reason Of Death" tabindex="8">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="diseaseName" id="diseaseName" class="form-control input-lg" placeholder="Disease Name" tabindex="9">
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="accidentDetails" id="accidentDetails" class="form-control input-lg" placeholder="Accident Details" tabindex="10">
                                </div>
                            </div>
                        </div>

                        <hr class="colorgraph">
                        <div class="row">
                            <div class="col-xs-12 col-md-6"><input type="submit" value="Submit" class="btn btn-primary btn-block btn-lg" tabindex="11"></div>
                            <div class="col-xs-12 col-md-6"><input type="reset" value="Cancel" class="btn btn-success btn-block btn-lg"/> </div>
                        </div>
                    </form>
                </div>
            </div>
            

            </br>
            </br>

            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">
                        Registered Deaths List
                    </h1>
                    <!--                    <h3 class="text-center">
                                            Resize the browser screen to see how the table changes
                                        </h3>-->
                </div>
                <div id="no-more-tables">
                    <table class="col-md-12 table-bordered table-striped table-condensed cf">
                        <thead class="cf">
                            <tr>
                                <th  class="numeric">
                                    Sr.No 
                                </th><th>Mother Name 
                                </th><th>Mother Nationality 
                                </th><th>Date of Birth 
                                </th><th>Father Name 
                                </th><th>Father Nationality 
                                </th><th>God Father Name
                                </th><th>God Mother Name
                                </th><th class="numeric">Weight
                                </th><th class="numeric">Height
                                </th><th>SkinColor
                                </th><th>EyeColor
                                </th><th>Country
                                </th><th>City
                                </th>
                                <th>Hospital
                                </th>
                                <th>Action
                                </th>
<!--                                <th>status
                                </th><th>createdDate
                                </th><th>modifiedDate
                                </th><th>createdBy</th>-->

                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<ChildRegistration> childRegistrationList = dbOperations.getChildRegistrationList();
                                //   System.out.println("Record fetch=== " + uploadedFiles.size());
                                for (int i = 0; i < childRegistrationList.size(); i++) {%>

                            <tr>
                                <td data-title="Sr.No"><%=childRegistrationList.get(i).getBirthDetailsId() %>
                                </td>
                                <td data-title="Mother Name"><%=childRegistrationList.get(i).getMotherName()%>
                                <td data-title="Mother Nationality"><%=childRegistrationList.get(i).getMotherNationality()%>
                                </td> <td data-title="Date OF Birth"><%=childRegistrationList.get(i).getDateofBirth()%>
                                </td> <td data-title="Father Name"><%=childRegistrationList.get(i).getFatherName()%>
                                </td> <td data-title="Father Nationality"><%=childRegistrationList.get(i).getFatherNationality()%>
                                </td> <td data-title="God Father Name"><%=childRegistrationList.get(i).getGrandFatherName()%>
                                </td> <td data-title="God Mother Name"><%=childRegistrationList.get(i).getGrandMotherName()%>
                                </td> <td data-title="Weight"><%=childRegistrationList.get(i).getWeight()%>
                                </td> <td data-title="Height"><%=childRegistrationList.get(i).getHeight()%>
                                </td> <td data-title="Skin Color"><%=childRegistrationList.get(i).getSkinColor()%>
                                </td> <td data-title="Eye Color"><%=childRegistrationList.get(i).getEyeColor()%>
                                </td> <td data-title="Country"><%=childRegistrationList.get(i).getCountry()%>
                                </td> <td data-title="City"><%=childRegistrationList.get(i).getCity()%>
                                </td> 
                                <td data-title="Hospital"><%=childRegistrationList.get(i).getHospital()%>
                                </td> 
                                <td data-title="Hospital"><a href="#" >Edit</a> <a href="#" >Delete</a>
                                </td> 
<!--                                <td data-title="Status"><%=childRegistrationList.get(i).getStatus()%>
                                </td> <td data-title="Register Date"><%=childRegistrationList.get(i).getCreatedDate()%>
                                </td> <td data-title="Updated Name"><%=childRegistrationList.get(i).getModifiedDate()%>
                                </td> <td data-title="Register By"><%=childRegistrationList.get(i).getCreatedBy()%></td>-->
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <p class="bg-success" style="padding:10px;margin-top:20px"><small><a href="home.jsp" target="_blank">(SGEC)</a> Centralization of Civil Status Management System.</small></p>
            </div>  



        </div>



        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
