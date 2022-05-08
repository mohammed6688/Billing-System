<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.customer_care_app.modules.SiteDAO" %>
<%@ page import="java.sql.SQLException" %>

<%@include file="/header.html" %>
<%
    String mode = request.getParameter("mode");
    int val=0;
    if (mode != null && mode.equals("addsp")) {
        String type = request.getParameter("rb");
        String units = request.getParameter("units");
        try {
            val =SiteDAO.instanceData.addServicePackage(type, units);
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
                    <h2 class="mb-2">Add New Service package</h2>
                </div>
                <form action="addsp.jsp" class="row g-3" novalidate="">
                    <input type="hidden" value="addsp" name="mode"/>
                    <div class="col-md-4"><label class="form-label" for="validationCustom01">Units</label> <input
                            name="units" class="form-control" id="validationCustom01" required="">
                        <div class="valid-feedback">Looks good!</div>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" id="voice" type="radio" value="voice" name="rb">
                        <label class="form-check-label" for="voice">Voice</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" id="cross_voice" type="radio" value="cross_voice" name="rb">
                        <label class="form-check-label" for="cross_voice">Cross Voice</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" id="data" type="radio" name="rb" value="data" >
                        <label class="form-check-label" for="data">Data</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" id="sms" type="radio" name="rb" value="sms" >
                        <label class="form-check-label" for="sms">Sms</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" id="roaming" type="radio" name="rb" value="roaming">
                        <label class="form-check-label" for="roaming">Roaming</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" id="free" type="radio" name="rb" value="free">
                        <label class="form-check-label" for="free">Free Units</label>
                    </div>

                    <%
                        if (val==1){
                    %>
                    <h4>service package Added successfully</h4>
                    <%
                        }else if (val==-1){
                    %>
                    <h4>error while adding service package</h4>
                    <%
                        }
                    %>
                    <div class="col-12">
                        <button class="btn btn-primary" type="submit">Add Service Package</button>
                    </div>
                </form>

<%@include file="/footer.jsp" %>