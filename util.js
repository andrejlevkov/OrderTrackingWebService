function add_order(args){
    return {
        orderId: 1
    }
}
function delete_order(args){
    return {success:true};
}
function change_location(args){
    return {
        success: true
    }
}
function leave_review(args){
    return {success:true};
}

module.exports = {
    add_order: add_order,
    delete_order: delete_order,
    change_location: change_location,
    leave_review: leave_review
}