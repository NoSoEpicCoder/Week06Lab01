<%@taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@taglib tagdir="/WEB-INF/tlds" prefix="saitTLD"%>

<saitTLD:debug>
    Remote Host: ${pageContext.request.remoteHost}<br>
    Session ID: ${pageContext.session.id}<br>    
</saittld:debug>
<tag:login/>