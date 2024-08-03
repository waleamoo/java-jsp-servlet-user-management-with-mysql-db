<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title><%= application.getInitParameter("WebAppName") %> - Error</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/font-awesome.min.css" />" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/shop-homepage.css" />" />
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#"><%= application.getInitParameter("WebAppName") %></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

    </div>
  </nav>
<div class="container my-4">
    <div class="row">
        <div class="col-lg-3 offset-lg-3">
            <h2>Oops! Looks like you're lost</h2>
            <p><%= exception.getMessage() %></p>
        </div>
    </div>
</div>

<%@ include file="/static/footer.jsp" %>