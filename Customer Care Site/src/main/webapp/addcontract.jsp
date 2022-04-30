<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.customer_care_app.modules.SiteDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.example.customer_care_app.modules.RatePlane" %>
<%@ page import="java.util.List" %>

<%@include file="/header.html" %>
<%
    String mode = request.getParameter("mode");
    int val=0;

    if (mode!=null&&mode.equals("adduser")){
        String national_id = request.getParameter("national_id");
        String rateplane = request.getParameter("rateplane");
        String msisdn = request.getParameter("msisdn");
        try {
            val = SiteDAO.instanceData.addContract(national_id,rateplane,msisdn);
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
                    <h2 class="mb-2">Add New Contract</h2>
                </div>
                <form action="addcontract.jsp" class="row g-3" novalidate="">
                    <input type="hidden" value="adduser" name="mode"/>

                    <div class="col-md-4"><label class="form-label" for="validationCustom03">User National Id</label> <input
                            name="national_id" class="form-control" id="validationCustom03" required="">
                        <div class="valid-feedback">Looks good!</div>
                    </div>

                    <div class="col-md-4"><label class="form-label" for="validationCustom01">MSISDN</label> <input
                            name="msisdn" class="form-control" id="validationCustom01" required="">
                        <div class="valid-feedback">Looks good!</div>
                    </div>

                    <div class="col-md-4"><label class="form-label">Rate Plane</label>
                        <select name="rateplane" class="form-select" aria-label="Default select example">
                            <option selected="">Choose rate plane</option>
                            <%
                                List<RatePlane> ratePlanes = null;
                                try {
                                    ratePlanes = SiteDAO.instanceData.getRatePlane();
                                    for (RatePlane ratePlane: ratePlanes){
                            %>
                            <option value="<%=ratePlane.getId()%>"><%=ratePlane.getCommercial_name()%></option>
                            <%
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            %>
                        </select>
                        <div class="valid-feedback">Looks good!</div>
                    </div>

                    <%
                        if (val==1){
                    %>
                    <h4>contract Added successfully</h4>
                    <%
                    }else if (val==-1){
                    %>
                    <h4>error while adding contract</h4>
                    <%
                        }
                    %>
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">Add Contract</button>
                        </div>
                </form>

<%@include file="/footer.jsp" %>