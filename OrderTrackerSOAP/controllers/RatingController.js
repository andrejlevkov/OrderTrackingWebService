const pool = require('../config/database');

function add_rating(args, callback){
    var rating = args.rating.ratingVal;
    var orderId = args.rating.orderId;
    var userId = args.rating.userId;

    sql = "INSERT INTO ratings(rating, orderId, userId) VALUES (?, ?, ?)";

    pool.query(sql, [rating, orderId, userId], (err, results, fields) => {
        if(err){
            console.log(err);
            return callback({
                success: 0,
                message: "Server Failure"
            });
        }
        if(results.affectedRows != 0){
            callback(null, {
                success: 1,
                message: "Rating Added Successfully"
            });
        }
        else{
            return callback({
                success: 0,
                message: "Failed to Add New Rating"
            }); 
        }
    });
    
}

module.exports = {
    add_rating: add_rating
};