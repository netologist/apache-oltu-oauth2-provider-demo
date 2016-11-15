apache-oltu-oauth2-provider-demo
================================

Apache Oltu Provider Server Demo (Oauth 2.0)

For basic OAuth2 flow have a read of:
https://aaronparecki.com/2012/07/29/2/oauth2-simplified#web-server-apps

OAuth2 provides several grant types.  In this example we will be using Authorisation Code grant type which is used
 for apps running on a web server.
 
 

Request Examples
==
In the root folder, there is a postman collection OLTU.postmain_collection.json that you could use to try out
these requests.

Register:
--
Before you can begin the OAuth process, you must first register a new app with the service.
Register is used to onboard a client organisation's app to use the OAuth service.  
This registers the client name, url and redirect url with the OAuth service.

    curl -X POST http://localhost:8080/register -H "Content-Type:application/json" --data '{ type: "pull", client_name:"test-app", client_url:"localhost:8080", client_description:"example app", redirect_url:"localhost:8080" }'
    
    POST /register
    {
        type: "pull",
        client_name: "test-app",
        client_url: "localhost:8080",
        client_description: "example app",
        redirect_url: "localhost:8080/callback"
    }

    RESPONSE
    {
        "client_secret": "someclientsecret",
        "issued_at": "0123456789",
        "expires_in": 987654321,
        "client_id": "someclientid"
    }


Authorise 
--
After Registering, the first step of OAuth2 is to get authorisation for the user.

GET http://localhost:8080/auth?redirect_uri=/redirect&uri=/uri&state=state&scope=read_ekycclaims&response_type=code&client_id=clientid

response_type=code -- This is to signify a grant type of Authorisation Code.  This authorisation code(known_authz_code) will be sent in the 
redirect url back to the clients site.
  
Token
--
Next, using the token call, the clients backed server will need to exchange the Authorisation Code for a Access Token.
  
POST http://localhost:8080/token?redirect_uri=/redirect&grant_type=authorization_code&code=known_authz_code&client_id=test_id&client_secret=secret
 
RESPONSE  
{
  "access_token": "eed58ddc2e382d58c54a646f56a638a1",
  "id_token": "idTokenValue",
  "expires_in": 3600
}  


Validation of the access token
--
The access_token can be validated in 3 ways.

Resource body validation
--
POST http://localhost:8080/resource_body?access_token=access_token_expired

RESPONSE
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
        <title>Error 401 Unauthorized</title>
    </head>
    <body>
        <h2>HTTP ERROR 401</h2>
        <p>Problem accessing /resource_body. Reason:

            <pre>    Unauthorized</pre>
        </p>
        <hr>
        <a href="http://eclipse.org/jetty">Powered by Jetty:// 9.3.14.v20161028</a>
        <hr/>
    </body>
</html>

POST http://localhost:8080/resource_body?access_token=access_token_valid

RESPONSE 200 OK
access_token_valid

Resource header validation
--
This is probably the best method to validate the access_token.  access_token_valid value in the header
 is the token to be validated.

GET http://localhost:8080/resource_header
ADD header key: Authorization value: Bearer access_token_valid

RESPONSE 200 OK
access_token_valid

Resource query validation
--
GET http://localhost:8080/resource_query?access_token=access_token_valid

RESPONSE 200 OK
access_token_valid
