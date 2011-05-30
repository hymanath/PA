<%@ page import="java.lang.management.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<mead>
</head>
<body style="font-family: Verdana, Arial, Helvetica, sans-serif;">


<%
Iterator iter = ManagementFactory.getMemoryPoolMXBeans().iterator();
while (iter.hasNext())
{
MemoryPoolMXBean item = (MemoryPoolMXBean) iter.next();
%>
<b><%= item.getName() %></b><br />
<b>Type</b> <%= item.getType() %><br />
<b>Usage</b> <%= item.getUsage() %><br />
<b>Peak Usage</b> <%= item.getPeakUsage() %><br />
<b>Collection Usage</b> <%= item.getCollectionUsage() %><br />
<br /><br />

<%
}

%>
</body>
</html
