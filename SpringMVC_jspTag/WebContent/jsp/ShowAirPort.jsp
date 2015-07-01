<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.enetity.SpecialDeal" %>
<%@ page import="com.enetity.DepartFrom" %>
<%@ page import="com.enetity.ArriveAt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
ASDFGHJKL

<h1>Welcome to the Flight Booking Service</h1>
<p>We have the following specials now:</p>
<ul>
	<%
	List specials = (List)request.getAttribute("Specials");
	for(Iterator i = specials.iterator();i.hasNext();){
		SpecialDeal special = (SpecialDeal)i.next();
	%>	
	<li>
	<%=special.getDepartFrom().getName() %> - 
	<%=special.getArriveAt().getName() %> from
	$<%= special.getCost() %>
	</li>
	<%
	}
	%>
</ul>
</body>
</html>