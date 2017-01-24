<#macro common_page_head>
  <meta charset="utf-8">
  <title>MyStruts</title>
  <link rel="stylesheet" type="text/css" href="views/assets/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="views/css/myapp.css">
</#macro>

<#macro common_page_footer>
  <script  src="views/js/jquery.js"></script>
  <script  src="views/assets/bootstrap/js/bootstrap.js"></script>
  <script  src="views/js/myapp.js"></script>
</#macro>

<#macro page_header>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/iLibrary/">MyApp-Struts</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Help</a></li>
        <li class="dropdown">
          
          <#if out['session']?? && out['session'].getAttribute("user")?? && out['session'].getAttribute("user")?has_content>
             <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi, ${out['session'].getAttribute("user")}<span class="caret"></span></a>
          <#else>
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account <span class="caret"></span></a>
          </#if>
          
          <ul class="dropdown-menu">
            <#if out['session']?? && out['session'].getAttribute("user")?? && out['session'].getAttribute("user")?has_content>
            <li><a href="/iLibrary/profile">Profile</a></li>
            <li><a href="/iLibrary/logout">Sign out</a></li>
            <#else>
            <li><a href="/iLibrary/login">Login</a></li>
            <li><a href="/iLibrary/signup">Register</a></li>
            </#if>
            
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
</#macro>

<#macro page_head>
  <@common_page_head/>
</#macro>

<#macro page_footer>
  <@common_page_footer/>
</#macro>

<#macro page_body>

</#macro>

<#macro display_page>
  <!doctype html>
  <html>
    <head> 
      <@page_head/> 
    </head>
    <body>
      <@page_header/> 
      <@page_body/>
    <footer>
      <@page_footer/>
    </footer>
    </body>
  </html>
</#macro>