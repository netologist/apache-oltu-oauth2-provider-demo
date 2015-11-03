apache-oltu-oauth2-provider-demo
================================

Apache Oltu Provider Server Demo (Oauth 2.0)


Request Examples
==
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


