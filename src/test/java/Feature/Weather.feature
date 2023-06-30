Feature: Payment verification


  Scenario: Shop products based on  information
Given I Launch Weather Shopper website
When I Identify the temperature and read info
Then Choose the moisturiser for low temperature and add products to cart
And choose suncreen for high temperature and add products to cart
And Verify the cart value and initiate a payment
And make payment and perform payment verification
And Close the browser
