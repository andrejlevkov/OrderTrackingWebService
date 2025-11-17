var soap = require('soap');
var express = require('express');
var fs = require('fs');
var https = require('https');
const orderController = require('./controllers/OrderController');
const ratingController = require('./controllers/RatingController');
const userController = require('./controllers/UserController');

var serviceObject = {
    OrderService: {
        OrderServicePort:{
            AddOrder: orderController.add_order,
            DeleteOrder: orderController.delete_order,
            ChangeOrderLocation: orderController.change_order_location,
            AddRating: ratingController.add_rating,
            RegisterUser: userController.register,
            Login: userController.login,
            DeleteUser: userController.delete_user
        }
    }
};

var xml = fs.readFileSync('orderservice.wsdl', 'utf8');

var app = express();

const options = {
    key: fs.readFileSync("./ssl/localhost-key.pem"),
    cert: fs.readFileSync("./ssl/localhost.pem")
};

const server = https.createServer(options, app);

app.get('/', (req, res) => {
    res.send('Hello World');
});

var port = 8080;
// app.listen(port, () => {
//     console.log("Server listening on port " + port);
//     var wsdl_path = '/wsdl';
//     soap.listen(app, wsdl_path, serviceObject, xml);
//     console.log("Check http://localhost:" + port + wsdl_path +"?wsdl to see if the service is working");
// });
server.listen(port, () => {
    console.log("Server listening on port " + port);
    var wsdl_path = '/orderservice';
    soap.listen(app, wsdl_path, serviceObject, xml);
    console.log("Check https://localhost:" + port + wsdl_path +"?wsdl to see if the service is working");
});