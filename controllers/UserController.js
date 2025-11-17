const {genSaltSync, hashSync, compareSync} = require("bcrypt");
const pool = require('../config/database');

// credentials za account
// email: andrejlevkov@gmail.com
// pass: andrej146

function createUser(args, callback){
    var firstName = args.user.firstName;
    var lastName = args.user.lastName;
    var email = args.user.email;
    var username = args.user.username;
    var password = args.user.password;

    const salt = genSaltSync(10);
    password = hashSync(password, salt);
    
    var sql = 'INSERT INTO users(firstName, lastName, email, username, password) VALUES (?, ?, ?, ?, ?)';

    pool.query(sql, [firstName, lastName, email, username, password], (err, result, fields) => {
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
                message: "User Added Successfully"
            });
        }
        else{
            return callback({
                success: 0,
                message: "Failed to Add New User"
            }); 
        }
    });
}
function getUserByEmail(args, callback){
    var email = args.email
    pool.query(
        "SELECT * FROM users WHERE email = ?",
        [email],
        (error, results, fields) => {
            if(error) return callback(error);
            return callback(null, results[0]);
        }
    );
}

function login(args, callback){
    getUserByEmail(args, (err, results) =>{
        if(err){
            console.log(err);
        }
        if(!results){
            return callback({
                success: 0,
                data: "Invalid login attempt"
            });
        }
        const result = compareSync(args.password, results.password);
        if(result){
            results.password = undefined;
            // const jsontoken = sign({result: results}, "qwe1234", {
            //     expiresIn: "1h"
            // });
            callback(null,{
                success: 1,
                message: "successfully logged in",
                data: results
            });
        }
        else{
            return callback({
                success: 0,
                message: "Invalid email or password",
                data: null
            });
        }
    });
}

function delete_user(args, callback){
    getUserByEmail(args, (err, results) =>{
        if(err){
            console.log(err);
        }
        if(!results){
            return callback({
                success: 0,
                data: "Invalid login attempt"
            });
        }
        const result = compareSync(args.password, results.password);
        if(result){
            pool.query(
                "DELETE FROM users WHERE email = ? and password = ?",
                [args.email, results.password],
                (error, results, fields) => {
                    if(error) return callback(error);
                    return callback({
                        success: 1,
                        message: "User deleted successfully"
                    });
                }
            );
        }
        else{
            return callback({
                success: 0,
                message: "Invalid email or password"
            });
        }
    });
}

module.exports = {
    register: createUser,
    login: login,
    delete_user: delete_user
};