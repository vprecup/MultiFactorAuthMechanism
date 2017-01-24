<#include "base.ftl">

<#macro page_body>
<div class="row login-container">
	<div class="col-sm-4 col-sm-offset-4">
		<div id="login">
			<form action="/iLibrary/access" method="POST">
				<img src="views/img/logo.png" class="logo">
				<label>Verifica email-ul si introdu codul primit aici pentru a putea accesa contul</label>
				<div class="form-group">
					<label>Token</label>
					<input class="form-control required" type="text" name="token" id="token">
				</div>
			  <button type="submit" class="btn btn-success form-control">Submit</button>
			</form>
		</div>
	</div>
</div>
</#macro>

<@display_page/>