const pool = require('../config/database');

function padTwoDigits(number) {
    return number.toString().padStart(2, "0");
}
  
function formatDate(date) {
    return (
      [
        date.getFullYear(),
        padTwoDigits(date.getMonth() + 1),
        padTwoDigits(date.getDate()),
      ].join("-") +
      " " +
      [
        padTwoDigits(date.getHours()+1),
        padTwoDigits(date.getMinutes()),
        padTwoDigits(date.getSeconds()),
      ].join(":")
    );
}

function add_order(args, callback){
    var location  = args.location;
    var sql = "INSERT INTO orders(status, location, created, departure, collected) VALUES (?, ?, ?, ?, ?)";
    var now = formatDate(new Date());

    pool.query(sql, ["se priprema", location, now, null, null], (err, result) => {
        if(err){
            console.log(err);
            return callback({
                success: 0,
                message: "Server Failure"
            });
        }
        // console.log(result);
        if(result.affectedRows != 0){
            callback(null, {
                success: 1,
                message: "Order Added Successfully"
            });
        }
        else{
            return callback({
                success: 0,
                message: "Failed to Add New Order"
            }); 
        }
    });
}
function delete_order(args, callback){
    var orderId = args.orderId;
    var sql = "DELETE FROM orders WHERE orderId = ?";

    pool.query(sql, [orderId], (err, result) => {
        if(err){
            console.log(err);
            return callback({
                success: 0,
                message: "Server Failure"
            });
        }
        if(result.affectedRows != 0){
            callback(null, {
                success: 1,
                message: "Order Deleted Successfully"
            });
        }
        else{
            return callback({
                success: 0,
                message: "Failed to Delete Order"
            });
        }
        
    });
}

function change_order_location(args, callback){
    var location = args.location;
    var orderId = args.orderId;

    var sql = "UPDATE orders SET location = ? where orderId = ?";

    pool.query(sql, [location, orderId], (err, result) => {
        if(err){
            console.log(err);
            return callback({
                success: 0,
                message: "Server Failure"
            });
        }
        if(result.affectedRows != 0){
            callback(null, {
                success: 1,
                message: "Order Location Changed Successfully"
            });
        }
        else{
            return callback({
                success: 0,
                message: "Failed to Change Order Location"
            });
        }
        
    });
}

module.exports = {
    add_order: add_order,
    delete_order: delete_order,
    change_order_location: change_order_location
}