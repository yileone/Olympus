<%@page import="org.openxava.view.View"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.google.gson.*"%>
<%@page import="fusioncharts.FusionCharts"%>
<%@page import="org.openxava.*"%>
<%@page import="com.jayktec.persistencia.BdManager"%>
<%@page import="com.jayktec.traductor.ToJson"%>
<%@page import="com.jayktec.controlador.Constantes"%>
<%@page import="com.jayktec.controlador.Constantes.CampoRegistro"%>
<%@ include file="../xava/imports.jsp"%>

<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>
<%

String viewObject = request.getParameter("viewObject");
viewObject = (viewObject == null || viewObject.equals(""))?"xava_view":viewObject;
View view = (org.openxava.view.View) context.get(request,viewObject);
	   
String sensor = (String)view.getValue("sensor.oid");
String origen = (String)view.getValue("origen.oid");
Boolean habil = (Boolean)view.getValue("habil");
try{
	
	ToJson tjMsLine = new ToJson(origen,sensor,habil); 		
	String grafico = BdManager.buscarJson(tjMsLine.crearJson());
	FusionCharts mslineChat = new FusionCharts("mscolumn2d", // chartType
				"chart1", // chartId
				"90%", "600", // chartWidth, chartHeight
				"chart", // chartContainer
				"json", // dataFormat
				grafico//dataSource
		);
%>
	<div id="chart"></div>
	<input type="button" value="Actualizar Pagina" onclick="window.location='/Afrodita/m/analisisEstadistico'">
	<%=mslineChat.render()%>
<%
}
catch(Exception e){
	%>
	 	<div class='ox-errors-wrapper'>
		<table id="ox_Afrodita_SignIn__errors_table1">
	<%
	if(sensor==null || sensor.equals("")){
	%>

			<tr><td class='ox-errors'>
				<div class='ox-message-box'>
					<i class="mdi mdi-close" style="cursor: pointer;" onclick="$(this).parent().fadeOut()"></i>
							<xava:message key="validaSensor"/>
				</div>
			</td></tr>

	<%		
	
	}	
	if(origen==null || origen.equals("")){
	%>

			<tr><td class='ox-errors'>
				<div class='ox-message-box'>
					<i class="mdi mdi-close" style="cursor: pointer;" onclick="$(this).parent().fadeOut()"></i>
						<xava:message key="validaOrigen"/>
				</div>
			</td></tr>

	<%		
	
	}
	%>		
	</table>
	</div>
<% 
}
		
%>
		
