
var soap = require('soap');
var url = 'https://localhost:8080/wsdl?wsdl';

process.env["NODE_TLS_REJECT_UNAUTHORIZED"] = 0;
// Create client
soap.createClient(url, function (err, client) {
  if (err){
    // throw err;
    console.log("error creating soap client: " + err);
  }
  /* 
  * Parameters of the service call: they need to be called as specified
  * in the WSDL file
  */
  var args = {
    location: "test"
  };
  
  // call the service
  client.AddOrder(args, function (err, res) {
    if (err)
      console.log("error making request: "+ err);
      // print the service returned result
    console.log(res); 
  });
});