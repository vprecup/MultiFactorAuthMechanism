<#include "base.ftl">

<!-- AICI FACEM NISTE ASSIGN-URI PENTRU A VERIFICA MAI USOR IN HTML DACA AVEM ERORI -->
<#assign firstnameError = "false">
<#assign lastnameError = "false">
<#assign usernameError = "false">
<#assign passwordError = "false">
<#assign cpasswordError = "false">
<#assign emailError = "false">

<#if out['errors']?? && out['errors']['firstname']?? && out['errors']['firstname']?has_content>
	<#assign firstnameError = "true">
</#if>
<#if out['errors']?? && out['errors']['lastname']?? && out['errors']['lastname']?has_content>
	<#assign lastnameError = "true">
</#if>
<#if out['errors']?? && out['errors']['username']?? && out['errors']['username']?has_content>
	<#assign usernameError = "true">
</#if>
<#if out['errors']?? && out['errors']['password']?? && out['errors']['password']?has_content>
	<#assign passwordError = "true">
</#if>
<#if out['errors']?? && out['errors']['cpassword']?? && out['errors']['cpassword']?has_content>
	<#assign cpasswordError = "true">
</#if>
<#if out['errors']?? && out['errors']['email']?? && out['errors']['email']?has_content>
	<#assign emailError = "true">
</#if>

<#macro page_body>
<div class="col-sm-12">
	<div class="col-sm-9">
	
	</div>
	<div class="col-sm-3">
		<div id="register">
			<form action="/iLibrary/signup" id="registerForm" method="POST">
			  	<div class="form-group">
			    	<label>First Name*</label>
			    	<#if firstnameError == "true">
						<span class="error">${out.errors.firstname[0]}</span>
					</#if>
					<span class="error" style="display:none;"></span>
					<input class="form-control required <#if firstnameError == 'true'>error</#if>" type="text" name="person.firstname" id="firstname">
			  	</div>
			  	<div class="form-group">
			    	<label>Last Name*</label>
			    	<#if lastnameError == "true">
						<span class="error">${out['errors']['lastname'][0]}</span>
					</#if>
					<span class="error" style="display:none;"></span>
					<input class="form-control required <#if lastnameError == 'true'>error</#if>" type="text" name="person.lastname" id="lastname">
			  	</div>
			  	<div class="form-group">
			    	<label>Username*</label>
			    	<#if usernameError == "true">
						<span class="error">${out['errors']['username'][0]}</span>
					</#if>
					<span class="error" style="display:none;"></span>
					<input class="form-control required <#if usernameError == 'true'>error</#if>" type="text" name="user.username" id="username">

			  	</div>
			  	<div class="form-group">
			    	<label>Email*</label>
			    	<#if emailError == "true">
						<span class="error">${out['errors']['email'][0]}</span>
					</#if>
					<span class="error" style="display:none;"></span>
					<input class="form-control required <#if emailError == 'true'>error</#if>" type="text" name="user.email" id="email">
			  	</div>
			  	<div class="form-group">
			    	<label>Password*</label>
			    	<#if passwordError == "true">
						<span class="error">${out['errors']['password'][0]}</span>
					</#if>
					<span class="error" style="display:none;"></span>
					<input class="form-control required <#if passwordError == 'true'>error</#if>" type="password" name="user.password" id="password">
			  	</div>
			  	<div class="form-group">
			    	<label>Confirm Password*</label>
			    	<#if cpasswordError == "true">
						<span class="error">${out['errors']['cpassword'][0]}</span>
					</#if>
					<span class="error" style="display:none;"></span>
					<input class="form-control required <#if cpasswordError == 'true'>error</#if>" type="password" name="cpassword" id="cpassword">
			  	</div>
			  	<div class="col-sm-12">
			  		<p>
			  			By clicking Sign up, you are indicating that you have read and agree to the <a href="/iLibrary/displayTS" target="_blank">Terms of Service</a> and <a href="/iLibrary/displayPP" target="_blank">Privacy Policy</a>.
			  		</p>
				</div>
			  <button type="submit" class="btn btn-success form-control">Sign up</button>
			</form>
		</div>
	</div>
</div>
</#macro>

<@display_page/>