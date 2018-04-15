<%@ include file="../imports.jsp"%>
<%@ page import="org.openxava.model.meta.MetaProperty" %>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>
<%
String propertyKey = request.getParameter("propertyKey");
MetaProperty p = (MetaProperty) request.getAttribute(propertyKey);
String fvalues = (String) request.getAttribute(propertyKey + ".fvalue");
String align = p.isNumber()?"right":"left";
boolean editable="true".equals(request.getParameter("editable"));
String disabled=editable?"":"disabled";
String script = request.getParameter("script");
boolean label = org.openxava.util.XavaPreferences.getInstance().isReadOnlyAsLabel();
if (editable || !label) {
%>
    <input name="<%=propertyKey%>" class=<%=style.getEditor()%>
    type="time" max="23:59:59" min="00:00:00" step="1" 
    title="<%=p.getDescription(request)%>"
    align='<%=align%>'
    maxlength="8" 
    size="8" 
    value="<%=fvalues%>"
    <%=disabled%>
    <%=script%> />
<%
} else {
%>
<%=fvalues%>&nbsp;    
<%
}
%>
<% if (!editable) { %>
    <input type="hiddven" name="<%=propertyKey%>" value="<%=fvalues%>">
<% } %>
