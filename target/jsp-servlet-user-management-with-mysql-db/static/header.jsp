<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title><%= application.getInitParameter("WebAppName") %> - Home</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/font-awesome.min.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/shop-homepage.css" />">

</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="<%= application.getInitParameter("WebAppContextPath") %>"> <%= application.getInitParameter("WebAppName") %> </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="login">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="register">Register</a>
          </li>
          
          
          <% if(session.getAttribute("name") != null){ %>

            <li class="nav-item">
              <a class="nav-link" href="#">Hello <%= session.getAttribute("name") %></a>
            </li>

            <li class="nav-item">
              <a class="nav-link" href="logout">Logout</a>
            </li>
          <% } %>

        </ul>
      </div>

    </div>
  </nav>