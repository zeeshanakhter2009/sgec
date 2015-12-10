<%
    //allow access only if session exists
    String user = (String) session.getAttribute("user");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userName = cookie.getValue();
            }
            if (cookie.getName().equals("JSESSIONID")) {
                sessionID = cookie.getValue();
            }
        }
    }
%>

<div class="navbar-wrapper">
    <div class="container-fluid">
        <nav class="navbar navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" style="font-size: 20px;font-weight: bold; " href="home.jsp">SGEC</a>

                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="childRegistration.jsp" class=""><strong>Hospital</strong></a></li>
                        <li class="active"><a href="deathRegistration.jsp" class=""><strong>Town Hall</strong></a></li>
                        <li class="active"><a href="#" class=""><strong>Citizen</strong></a></li>
                    </ul>
                    <ul class="nav navbar-nav pull-right">
                        <li class=""><a href="#">Hi <strong><%=userName%>,</strong> </a></li>
                        <li class=""><a href="LogoutServlets"><strong>Logout</strong></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>