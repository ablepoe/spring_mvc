<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.enetity.SpecialDeal" %>
<%@ page import="com.enetity.DepartFrom" %>
<%@ page import="com.enetity.ArriveAt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to the Flight Booking Service</h1>
<p>We have the following specials now:</p>
<ul>
	<c:forEach items="${specials}" var="special">
	<li>
		${special.departFrom.name} - 
		${special.arriveAt.name} from
		$${special.cost}
	</li>
	</c:forEach>
</ul>
</body>
</html>