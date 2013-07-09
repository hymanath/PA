<%@ page import="com.itgrids.partyanalyst.web.action.UserSessionStorageAction"%>


<html>
...
  Active Sessions : <%= UserSessionStorageAction.getActiveSessions() %>
...
</html>