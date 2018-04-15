<%@ page import="org.openxava.model.meta.MetaProperty" %>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>
<%
String propertyKey = request.getParameter("propertyKey");
MetaProperty p = (MetaProperty) request.getAttribute(propertyKey);
String [] fvalues = (String []) request.getAttribute(propertyKey + ".fvalue");
String fDate = fvalues[0];
String fTime = fvalues[1];
String fvalue = fDate + " " + fTime;
String align = p.isNumber()?"right":"left";

System.out.println("*****************************");
System.out.println(fTime);
System.out.println(editable);
System.out.println(label);
System.out.println(p.getDescription(request));
System.out.println(script);
boolean editable="true".equals(request.getParameter("editable"));
String disabled=editable?"":"disabled";
String script = request.getParameter("script");
boolean label = org.openxava.util.XavaPreferences.getInstance().isReadOnlyAsLabel();
if (editable || !label) {
%>
    <input name="<%=propertyKey%>" class=<%=style.getEditor()%>
    type="text" 
    title="<%=p.getDescription(request)%>"
    align='<%=align%>'
    maxlength="8" 
    size="8" 
    value="<%=fTime%>"
    <%=disabled%>
    <%=script%> />
<%
} else {
%>
<%=fTime%>&nbsp;    
<%
}
%>
<% if (!editable) { %>
    <input type="hidden" name="<%=propertyKey%>" value="<%=fvalue%>">
<% } %>