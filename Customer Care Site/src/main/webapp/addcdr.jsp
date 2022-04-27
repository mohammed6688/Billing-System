<%@ page import="java.sql.Date" %>
<%@ page import="com.example.customer_care_app.modules.CDR" %>
<%@ page import="com.example.customer_care_app.fileManager.CsvHandler" %>
<%@include file="/header.html" %>

<%
  String mode = request.getParameter("mode");
  if (mode!=null&&mode.equals("addcdr")){
    String msisdn = request.getParameter("msisdn");
    String service_id = request.getParameter("service_id");
    String rp_id = request.getParameter("rp_id");

    String str = "2015-03-31";
    Date date = Date.valueOf(str);
    CDR cdr = new CDR(1, msisdn, "01147964655", date, 20, Integer.parseInt(rp_id), Integer.parseInt(service_id), 3, 600);
    CsvHandler cv = new CsvHandler();
    cv.writeDataLineByLine(cdr);
  }
%>
<div class="content pt-5">
    <div class="pb-5">
        <div class="row g-5">
            <div class="col-12 col-xxl-6">
                <div class="mb-8">
                    <h2 class="mb-2">Add New CDR</h2>
                    <h5 class="text-700 fw-semi-bold">Add new unprocessed cdr</h5>
                </div>


                <form action="addcdr.jsp" class="row g-3 ">
                    <input type="hidden" value="addcdr" name="mode"/>
                    <div class="col-md-4"><label class="form-label">MSISDN</label> <input
                            name="msisdn" class="form-control" placeholder="01126489485" required="">
                        <div class="valid-feedback">Looks good!</div>
                    </div>
                    <div class="col-md-4"><label class="form-label">RatePlane id</label> <input
                            name="rp_id" class="form-control" placeholder="1" required="">
                        <div class="valid-feedback">Looks good!</div>
                    </div>
                    <div class="col-md-4"><label class="form-label">Service Id</label> <input
                            name="service_id" class="form-control" placeholder="ex..1,2,3,4" required="">
                        <div class="valid-feedback">Looks good!</div>
                    </div>
                    <div class="col-12">
                        <button class="btn btn-primary" type="submit">Submit CDR</button>
                    </div>
                </form>


                <%@include file="/footer.jsp" %>
