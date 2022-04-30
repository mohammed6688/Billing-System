<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.customer_care_app.modules.SiteDAO" %>
<%@ page import="java.sql.SQLException" %>

<%@include file="/header.html" %>
<%
    String mode = request.getParameter("mode");
    int val=0;
    if (mode!=null&&mode.equals("adduser")){
        String national_id = request.getParameter("national_id");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        try {
            val = SiteDAO.instanceData.addUser(national_id,name,age,address);
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
                    <h2 class="mb-2">Add New User</h2>
                </div>
                <form action="addcustomer.jsp" class="row g-3" novalidate="">
                    <input type="hidden" value="adduser" name="mode"/>
                    <div class="col-md-4"><label class="form-label" for="validationCustom01">User National Id</label> <input
                            name="national_id" class="form-control" id="validationCustom01" required="">
                        <div class="valid-feedback">Looks good!</div>
                    </div>
                    <div class="col-md-4"><label class="form-label" for="validationCustom02">User Name</label> <input
                            name="name" class="form-control" id="validationCustom02" required="">
                        <div class="valid-feedback">Looks good!</div>
                    </div>
                  <div class="col-md-4"><label class="form-label" for="validationCustom03">Age</label> <input
                          name="age" class="form-control" id="validationCustom03" required="">
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="col-md-4"><label class="form-label" for="validationCustom04">Address</label> <input
                          name="address" class="form-control" id="validationCustom04" required="">
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                    <%
                        if (val==1){
                    %>
                      <h4>customer Added successfully</h4>
                          <%
                        }else if (val==-1){
                    %>
                      <h4>error while adding customer</h4>
                          <%
                        }
                    %>

                    <div class="col-12">
                        <button class="btn btn-primary" type="submit">Add User</button>
                    </div>
                </form>

<%@include file="/footer.jsp" %>