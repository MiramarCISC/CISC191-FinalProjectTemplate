<%@ page import="edu.sdccd.cisc191.template.ViewStartScreen" %>
<html>
<head>
    <title>Saved Schedules for Homework Tracker</title>
</head>
<body>
<h2>Stored Schedules:</h2>
    <% out.println(getSavedSchedule()); %>
</body>
</html>