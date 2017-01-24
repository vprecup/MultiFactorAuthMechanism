<#include "base.ftl">

<#macro page_body>
<div class="row login-container">
	<div class="col-sm-4 col-sm-offset-4">
		<div id="login">
			<form action="/iLibrary/login" method="POST">
				<img src="views/img/logo.png" class="logo">
			  <div class="form-group">
			    <label>Username*</label>
				<input class="form-control required" type="text" name="username" id="username">
			  </div>
			  <div class="form-group">
			    <label>Password*</label>
				<input class="form-control required" type="password" name="password" id="password">
			  </div>
			  <button type="submit" class="btn btn-success form-control">Log in</button>
			</form>
		</div>
	</div>
</div>
</#macro>

<@display_page/>