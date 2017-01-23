# MultiFactorAuthMechanism

Brief description of the subject
In a world with handheld "supercomputers", classical security mechanisms and authentication methods are not enough any more. Password hashes can be broken in no time. Thus, there is a higher and higher demand for enforcing systems security. 
Throughout this project, we would like to study and demonstrate a potential two-factor authentication mechanism which  uses the normal username & password scheme (something that the user knows) for the first factor, and a one-time token generated code sent to his device (something that the user possesses) as the second factor. 

We will demonstrate the Two-Factor Authentication (2FA) mechanism through a Java-enabled website, which communicates with an orchestrator service that (1) coordinates the one-time token generation and validation through a WebAPI (.NET) service and (2) sends a message / email to the user through a Java Sending Service. As this 2nd factor of authentication is pluggable, the orchestrator service together with its 2 dependencies are configurable through a lightweight configuration portal.

A brief description of the system components follows:
	- Web Server: Host Website / 2FA Runner - Java Web App [Alex] 
		- Enables the following scenarios:
			1. Register new user (email, password, phone number)
			2. Sign in flow - contains 2 pages
				1) User name + password validation
				2) One time token validation -> sends a "generate token" request to the orchestrator service
			3. Successful authentication page.
	- User Data: Host Website Data Store - MySQL DB accessed using Hibernate [Alex] 
	- Orchestrator Service - Java RESTful service [Mihai]
		- Forwards token generation calls
		- Exposes the following endpoints:
			1. GenerateToken - GET (mail address)
			2. ValidateToken - GET (mail address + token - parameters are forwarded through query string)
			3. GetConfigurationOptions - GET
				a. Token generation service address
				b. Sending service address
				c. On Test Service, it calls the above-mentioned endpoints' GetConfigurationOptions and returns the options back to the caller
			4. [optional] SetConfiguration - POST (key + value; de ex. { "serverPort": "443" } )
	- Token Service - .NET -> WebAPI deployed in Azure [Vlad] 
		- SSL connection (optional)
		- Exposes the following endpoints: 
			1. GenerateToken - GET 
			2. ValidateToken - GET
			3. GetConfigurationOptions - GET
				a. Token expiration interval
				b. Token length
			4. SetConfiguration - POST
	- Sending service - Java service [Nelly]
		- Endpoints:
			1. SendEmail - POST (params: recipient-string/validEmailAddress, emailContent-string/html)
			2. GetConfigurationOptions - GET (returns the configurations options for this service):
				a. Email template
				b. Server address (SMTP)
				c. Server port
			3. [optional] SetConfiguration - POST (key + value; de ex. { "serverPort": "443" } )
	- Configuration portal - Java Web App
		- Through this portal the user is able to configure the 3 services
			§ This web server will dynamically show the configuration options based on the GetConfigurationOptions interface to the orchestrator, token and sender services.
			
