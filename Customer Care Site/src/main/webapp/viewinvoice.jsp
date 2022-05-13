<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.customer_care_app.modules.SiteDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.example.customer_care_app.modules.ServicePackage" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.customer_care_app.modules.RatePlane" %>
<%@ page import="modules.Bill_Info" %>


<%@include file="/header.html" %>
<%  int NID=-1;
if (request.getParameter("NID")!=null) {
    NID = Integer.parseInt(request.getParameter("NID"));
}
if (NID != -1){
    int total_fees=0;
    try {
        List<Bill_Info> AllBills = SiteDAO.instanceData.generateBillsForUser(NID);
        String Username =SiteDAO.instanceData.getUser(String.valueOf(NID)).get(0).getU_name();
%>
<div class="content pt-5">
    <div class="pb-5">
        <div class="row g-5">
            <div class="col-12 col-xxl-6">
                <div class="mb-8">
                    <h1 class="mb-2">Monthly Invoice</h1>
                </div>
                <div class="mb-8">
                    <h2 class="mb-2">Customer name </h2>
                    <h4 class="mb-2"><%=Username%></h4>
                </div>

                <form action="viewinvoice.jsp" class="row g-3" novalidate="">

                    <div id="tableExample2"
                         data-list="{&quot;valueNames&quot;:[&quot;msisdn&quot;,&quot;voice on net&quot;,&quot;CrossNet&quot;,&quot;Roaming&quot;,&quot;Data&quot;,&quot;SMS&quot;,&quot;fee&quot;],&quot;page&quot;:5,&quot;pagination&quot;:true}">
                        <div class="table-responsive scrollbar">
                            <table class="table table-bordered table-striped fs--1 mb-0">
                                <thead class="bg-200 text-900">
                                <tr>
                                    <th>/</th>
                                    <th class="sort desc" data-sort="msisdn">msisdn</th>
                                    <th class="sort" data-sort="OnNet">voice on net</th>
                                    <th class="sort" data-sort="CrossNet">voice cross net</th>
                                    <th class="sort" data-sort="Roaming">roaming</th>
                                    <th class="sort" data-sort="Data">Data</th>
                                    <th class="sort" data-sort="SMS">SMS</th>
                                    <th class="sort" data-sort="totalFees">fee</th>
                                </tr>
                                </thead>
                                <tbody class="list">
                                <%
                                    for (Bill_Info billInfo : AllBills) {
                                %>
                                <tr>
                                    <td></td>
                                    <td class="msisdn"><%=billInfo.getMsisdn()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td>consumed units</td>
                                    <td></td>
                                    <td class="OnNet"><%=billInfo.getExtraConsumedVoice()%>
                                    </td>
                                    <td class="CrossNet"><%=billInfo.getExtraConsumedCross()%>
                                    </td>
                                    <td class="Roaming"><%=billInfo.getExtraConsumedRoaming()%>
                                    </td>
                                    <td class="Data"><%=billInfo.getExtraConsumedData()%>
                                    </td>
                                    <td class="SMS"><%=billInfo.getExtraConsumedSMS()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Rated Prices</td>
                                    <td></td>
                                    <td class="OnNet"><%=billInfo.getRatedExtraVoice()%>
                                    </td>
                                    <td class="CrossNet"><%=billInfo.getRatedExtraCross()%>
                                    </td>
                                    <td class="Roaming"><%=billInfo.getRatedExtraRoaming()%>
                                    </td>
                                    <td class="Data"><%=billInfo.getRatedExtraData()%>
                                    </td>
                                    <td class="SMS"><%=billInfo.getRatedExtraSMS()%>
                                    </td>
                                    <td class="totalFees"><%=billInfo.getExtraFees()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td> monthly fees</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="totalFees"><%=billInfo.getMonthlyFees()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td> Total fees</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="totalFees"><%=billInfo.getTotalFees()%> <% total_fees +=billInfo.getTotalFees();%>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                                <tr>
                                    <td>overall fees</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="totalFees"><%=total_fees %>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="d-flex justify-content-center mt-3">
                            <button class="btn btn-sm btn-falcon-default me-1 disabled" type="button" title="Previous"
                                    data-list-pagination="prev" disabled="">
                                <svg class="svg-inline--fa fa-chevron-left fa-w-10" aria-hidden="true" focusable="false"
                                     data-prefix="fas" data-icon="chevron-left" role="img"
                                     xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg="">
                                    <path fill="currentColor"
                                          d="M34.52 239.03L228.87 44.69c9.37-9.37 24.57-9.37 33.94 0l22.67 22.67c9.36 9.36 9.37 24.52.04 33.9L131.49 256l154.02 154.75c9.34 9.38 9.32 24.54-.04 33.9l-22.67 22.67c-9.37 9.37-24.57 9.37-33.94 0L34.52 272.97c-9.37-9.37-9.37-24.57 0-33.94z"></path>
                                </svg><!-- <span class="fas fa-chevron-left"></span> Font Awesome fontawesome.com -->
                            </button>
                            <ul class="pagination mb-0">
                                <li class="active">
                                    <button class="page" type="button" data-i="1" data-page="5">1</button>
                                </li>
                                <li>
                                    <button class="page" type="button" data-i="2" data-page="5">2</button>
                                </li>
                                <li>
                                    <button class="page" type="button" data-i="3" data-page="5">3</button>
                                </li>
                            </ul>
                            <button class="btn btn-sm btn-falcon-default ms-1" type="button" title="Next"
                                    data-list-pagination="next">
                                <svg class="svg-inline--fa fa-chevron-right fa-w-10" aria-hidden="true"
                                     focusable="false" data-prefix="fas" data-icon="chevron-right" role="img"
                                     xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg="">
                                    <path fill="currentColor"
                                          d="M285.476 272.971L91.132 467.314c-9.373 9.373-24.569 9.373-33.941 0l-22.667-22.667c-9.357-9.357-9.375-24.522-.04-33.901L188.505 256 34.484 101.255c-9.335-9.379-9.317-24.544.04-33.901l22.667-22.667c9.373-9.373 24.569-9.373 33.941 0L285.475 239.03c9.373 9.372 9.373 24.568.001 33.941z"></path>
                                </svg><!-- <span class="fas fa-chevron-right"></span> Font Awesome fontawesome.com -->
                            </button>
                        </div>
                    </div>

                </form>
                    <%
                    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }else {
%>
                <div class="content pt-5">
                    <div class="pb-5">
                        <div class="row g-5">
                            <div class="col-12 col-xxl-6">
                                <div class="mb-8">
                                    <h2 class="mb-2">please enter the National id</h2>
                                </div>
                                <form action="viewinvoice.jsp" class="row g-3" novalidate="">
                    <%----------------------------------------------------------------------------------------%>
                    <div class="col-md-4">

                        <label class="form-label" for="validationCustom01">National ID</label>
                        <input name="NID" class="form-control" id="validationCustom01">
                        <div class="valid-feedback">Looks good!</div>
                    </div>
                </form>
                <%
                    }
                %>
<%@include file="/footer.jsp" %>