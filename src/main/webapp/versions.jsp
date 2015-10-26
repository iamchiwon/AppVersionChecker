<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.iamchiwon.appversionchecker.Versions"%>
<%@ page import="com.iamchiwon.appversionchecker.AppVersion"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.List"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
	integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
	integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
	crossorigin="anonymous"></script>

<title>Registered Apps</title>
</head>
<body>
	<div class="container">

		<h1>Registered versions</h1>
		<%
			List<AppVersion> apps = ObjectifyService.ofy().load().type(AppVersion.class).list();
			if (apps.isEmpty()) {
		%>
		<li class='noapp'>no apps registered</li>
		<%
			} else {
		%>
		<table class="table">
			<tr>
				<th>#</th>
				<th>App Key</th>
				<th>Latest Version</th>
			</tr>
			<%
				int index = 1;
					for (AppVersion app : apps) {
						String appkey = app.appkey;
						String appver = app.version;
			%>
			<tr>
				<td class='index'><%=index%></td>
				<td class='appkey'><%=appkey%></td>
				<td class='appver'><%=appver%></td>
			</tr>
			<%
				index++;
					}
			%>
		</table>
		<%
			}
		%>


		<br /> <br /> <a
			href="https://github.com/iamchiwon/AppVersionChecker" class="btn">
			<img src="https://github.com/fluidicon.png"
			style="width: 20px; height: 20px;">
			github.com/iamchiwon/AppVersionChecker
		</a>

	</div>

</body>
</html>