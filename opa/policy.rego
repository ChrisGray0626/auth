package pers.ruizhi.auth

allow_post_review {
    input.action == "post_review"
    check_prouduct_bought(input.user_id, input.product_id)
}

allow_raise_support_request {
    input.action == "raise_support_request"
    check_prouduct_bought(input.user_id, input.product_id)
    not check_product_returned(input.user_id, input.product_id)
}

# Check whether the product was bought by user
check_prouduct_bought(user_id, product_id) {
    some order in input.orders
    order.user_id == user_id
    order.product_id == product_id
    order.status == "purchased"
}

# Check whether the product was returned by user
check_product_returned(user_id, product_id) {
    some order in input.orders
    order.user_id == user_id
    order.product_id == product_id
    order.status == "returned"
}
