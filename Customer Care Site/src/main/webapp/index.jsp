<%@ page import="com.example.customer_care_app.modules.SiteDAO" %>
<%@ page import="com.example.customer_care_app.modules.Users" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.example.customer_care_app.modules.RatePlane" %>
<%@ page import="com.example.customer_care_app.modules.ServicePackage" %>
<%@include file="/header.html" %>


<div class="content pt-5">
    <div class="pb-5">
        <div class="row g-5">
            <div class="col-12 col-xxl-6">
                <div class="mb-8">
                    <h2 class="mb-2">Customer Care Dashboard</h2>
                </div>
                <div class="row align-items-center g-4">
                    <div class="col-12 col-md-auto">
                        <div class="d-flex align-items-center"><img src="assets/img/icons/illustrations/4.png" alt=""
                                                                    height="46" width="46">
                            <div class="ms-3">
                                <%
                                    try {
                                        List<Users> users =SiteDAO.instanceData.getUsers();
                                %>
                                <h4 class="mb-0"><%=users.size()%> users</h4>
                                <%
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                %>
                                <p class="text-800 fs--1 mb-0">Active user</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-auto">
                        <div class="d-flex align-items-center"><img src="assets/img/icons/illustrations/2.png" alt=""
                                                                    height="46" width="46">
                            <div class="ms-3">
                                <%
                                    try {
                                        List<RatePlane> ratePlanes =SiteDAO.instanceData.getRatePlane();
                                %>
                                <h4 class="mb-0"><%=ratePlanes.size()%> rateplanes</h4>
                                <%
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-auto">
                        <div class="d-flex align-items-center"><img src="assets/img/icons/illustrations/3.png" alt=""
                                                                    height="46" width="46">
                            <div class="ms-3">
                                <%
                                    try {
                                        List<ServicePackage> servicePackages =SiteDAO.instanceData.getServicePackage();
                                %>
                                <h4 class="mb-0"><%=servicePackages.size()%> service packages</h4>
                                <%
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="/footer.jsp" %>
