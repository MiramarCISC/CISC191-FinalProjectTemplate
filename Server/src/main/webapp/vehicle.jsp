<%@page
language="java"
contentType="application/json;charset=UTF-8"
import="edu.sdccd.cisc191.template.VehicleResponse"
errorPage = "error.jsp"
%><%@ page import="edu.sdccd.cisc191.template.VehicleRequest"%><%
VehicleResponse vehicleResponse = new VehicleResponse(new VehicleRequest(Integer.parseInt(request.getParameter("year")),request.getParameter("make"),request.getParameter("model")),43000,23000,5,4,null);
out.print(VehicleResponse.toJSON(vehicleResponse));
%>