<%@include file="../xava/imports.jsp"%>

<%-- To put your own text add entries in the i18n messages files of your project --%>

<%
String language = "es".equals(request.getLocale().getLanguage()) || "ca".equals(request.getLocale().getLanguage())?"es":"en";
%>

<jsp:forward page="/m/Regla"/>