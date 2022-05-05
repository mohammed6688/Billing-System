<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.customer_care_app.modules.SiteDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.example.customer_care_app.modules.ServicePackage" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.customer_care_app.modules.RatePlane" %>


<%@include file="/header.html" %>
<%
    String CommName,AddMin,AddSMS,AddData,AddRoam,fee,voice,SeCrossvoice,seData,seSMS,seRoam;
    CommName = request.getParameter("CommName");
    AddMin = request.getParameter("AddMin");
    AddSMS = request.getParameter("AddSMS");
    AddData = request.getParameter("AddData");
    AddRoam = request.getParameter("AddRoam");
    fee= request.getParameter("fee");

    voice= request.getParameter("voice");

    SeCrossvoice = request.getParameter("SeCrossvoice");
    seData  = request.getParameter("seData");
    seSMS = request.getParameter("seSMS");
    seRoam= request.getParameter( "seRoam");
  int val=0;
  if (CommName != null && AddMin!= null&& AddSMS!= null&& AddData!= null&& AddRoam!= null &&fee != null ) {
      RatePlane ratePlane = new RatePlane(0,CommName,Integer.parseInt(voice),Integer.parseInt(SeCrossvoice),Integer.parseInt(seData),Integer.parseInt(seSMS),Integer.parseInt(seRoam),Integer.parseInt(AddMin),Integer.parseInt(AddSMS),Integer.parseInt(AddData),Integer.parseInt(AddRoam),Integer.parseInt(fee));
    try {
      val =SiteDAO.instanceData.addRatePlan(ratePlane);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
%>
<div class="content pt-5">
  <div class="pb-5">
    <div class="row g-5">
      <div class="col-12 col-xxl-6">
        <div class="mb-8">
          <h2 class="mb-2">Add New Rate Plane</h2>
        </div>
        <form action="addrp.jsp" class="row g-3" novalidate="">
<%----------------------------------------------------------------------------------------%>
          <div class="col-md-4">

          <label class="form-label" for="validationCustom01">Commercial Name</label>
              <input name="CommName" class="form-control" id="validationCustom01" >
            <div class="valid-feedback">Looks good!</div>
          </div>
<%--    ------------------------------------------------------------------------     --%>
          <div class="col-md-4"><label class="form-label">Voice Service</label>
            <select name="voice" class="form-select" aria-label="Default select example">
              <option selected="">choose Units</option>
              <%
                List<ServicePackage> servicePackageList = null;
                try {
                  servicePackageList = SiteDAO.instanceData.getServicePackage(1);
                  for (ServicePackage servicePackage: servicePackageList){
              %>
              <option value="<%=servicePackage.getId()%>"><%=servicePackage.getUnits()%></option>
              <%
                  }
                } catch (SQLException e) {
                  throw new RuntimeException(e);
                }
              %>
            </select>
            <div class="valid-feedback">Looks good!</div>
          </div>

<%--          -----------------------------------------------------------------------         --%>
          <div class="col-md-4"><label class="form-label">cross voice Service</label>
            <select name="SeCrossvoice" class="form-select" aria-label="Default select example">
              <option selected="">choose Units</option>
              <%

                try {
                  servicePackageList = SiteDAO.instanceData.getServicePackage(2);
                  for (ServicePackage servicePackage: servicePackageList){
              %>
              <option value="<%=servicePackage.getId()%>"><%=servicePackage.getUnits()%></option>
              <%
                  }
                } catch (SQLException e) {
                  throw new RuntimeException(e);
                }
              %>
            </select>
            <div class="valid-feedback">Looks good!</div>
          </div>


<%--          -------------------------------------------------------------------------------%>
          <div class="col-md-4"><label class="form-label">data Service</label>
            <select name="seData" class="form-select" aria-label="Default select example">
              <option selected="">choose Units</option>
              <%

                try {
                  servicePackageList = SiteDAO.instanceData.getServicePackage(3);
                  for (ServicePackage servicePackage: servicePackageList){
              %>
              <option value="<%=servicePackage.getId()%>"><%=servicePackage.getUnits()%></option>
              <%
                  }
                } catch (SQLException e) {
                  throw new RuntimeException(e);
                }
              %>
            </select>
            <div class="valid-feedback">Looks good!</div>
          </div>

<%--          ------------------------------------------------------------------------------------%>
          <div class="col-md-4"><label class="form-label">sms Service</label>
            <select name="seSMS" class="form-select" aria-label="Default select example">
              <option selected="">choose Units</option>
              <%
                try {
                  servicePackageList = SiteDAO.instanceData.getServicePackage(4);
                  for (ServicePackage servicePackage: servicePackageList){
              %>
              <option value="<%=servicePackage.getId()%>"><%=servicePackage.getUnits()%></option>
              <%
                  }
                } catch (SQLException e) {
                  throw new RuntimeException(e);
                }
              %>
            </select>
            <div class="valid-feedback">Looks good!</div>
          </div>

          <%--          ------------------------------------------------------------------------------------%>
          <div class="col-md-4"><label class="form-label">roaming Service</label>
            <select name="seRoam" class="form-select" aria-label="Default select example">
              <option selected="">choose Units</option>
              <%

                try {
                  servicePackageList = SiteDAO.instanceData.getServicePackage(5);
                  for (ServicePackage servicePackage: servicePackageList){
              %>
              <option value="<%=servicePackage.getId()%>"><%=servicePackage.getUnits()%></option>
              <%
                  }
                } catch (SQLException e) {
                  throw new RuntimeException(e);
                }
              %>
            </select>
            <div class="valid-feedback">Looks good!</div>
          </div>

<%-----------------------------------------------------------------------------------------%>
          <div class="col-md-4">

            <label class="form-label" for="validationCustom01">Additional minutes</label> <input
                  name="AddMin" class="form-control" id="validationCustom01" >
            <div class="valid-feedback">Looks good!</div>
          </div>
<%--          ---------------------------------------------               --%>
          <div class="col-md-4">

            <label class="form-label" for="validationCustom01">Additional sms</label> <input
                  name="AddSMS" class="form-control" id="validationCustom01" >
            <div class="valid-feedback">Looks good!</div>
          </div>
          <%--          ---------------------------------------------               --%>
          <div class="col-md-4">

            <label class="form-label" for="validationCustom01">Additional data</label> <input
                  name="AddData" class="form-control" id="validationCustom01" >
            <div class="valid-feedback">Looks good!</div>
          </div>
          <%--          ---------------------------------------------               --%>
          <div class="col-md-4">

            <label class="form-label" for="validationCustom01">Additional roaming</label> <input
                  name="AddRoam" class="form-control" id="validationCustom01" >
            <div class="valid-feedback">Looks good!</div>
          </div>
          <%--          ---------------------------------------------               --%>
          <div class="col-md-4">

            <label class="form-label" for="validationCustom01">fee</label> <input
                  name="fee" class="form-control" id="validationCustom01" >
            <div class="valid-feedback">Looks good!</div>
          </div>
          <%--          ---------------------------------------------               --%>

          <div class="col-12">
            <button class="btn btn-primary" type="submit">Add Service Package</button>
          </div>
        </form>

<%@include file="/footer.jsp" %>