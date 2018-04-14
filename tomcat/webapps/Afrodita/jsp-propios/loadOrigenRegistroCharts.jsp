<%@page import="org.openxava.view.View"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.google.gson.*"%>
<%@page import="fusioncharts.FusionCharts"%>
<%@page import="org.openxava.*"%>
<%@page import="org.openxava.actions.ImportDataAction"%>

<%@ include file="../xava/imports.jsp"%>

<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>
<%
String viewObject = request.getParameter("viewObject");
viewObject = (viewObject == null || viewObject.equals(""))?"xava_view":viewObject;
View view = (org.openxava.view.View) context.get(request,viewObject);
/*System.out.println("loadOrigenRegistroCharts  JSP PROPIO-->"+view.getModelName());
System.out.println("loadOrigenRegistroCharts  JSP PROPIO--> origen "+view.getValue("origen.oid"));
System.out.println("loadOrigenRegistroCharts  JSP PROPIO--> sensor "+view.getValue("sensor.oid"));
*/
%>

<table>
<tr>
<th align='left' class="<%=style.getLabel()%>">
<xava:message key="introducir_origen"/>
</th>
<td><xava:descriptionsList reference="origen" /></td>
</tr>
<tr>
<th align='left' class="<%=style.getLabel()%>">
<xava:message key="introducir_sensor"/>
</th>
<td><xava:descriptionsList reference="sensor"/></td>
</tr>
<tr>
<th align='right' class="<%=style.getLabel()%>">
<xava:message key="diaHabil"/>
</th>
<td><xava:editor property="habil" editable="true"/></td>
</tr>
<tr>
<!-- <th align='right' class="<%=style.getLabel()%>">
<xava:message key="graficarDatosReducido"/>
</th>
<td><xava:editor property="reducido" editable="true"/></td>
</tr>
 -->
</table>





