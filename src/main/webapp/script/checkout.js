$(document).ready(function() {
    $('.coupon').hide();

	// $('.coupon-success').show();
    $('#coupon-btn').click(function(){
        var code = $('#coupon-code').val();
        var code = code.toUpperCase();
        // alert("your code is "+code);
        $.ajax({
            url: 'checkcoupon',
            method: 'GET',
            data:{ code : code},
            success: function(data) {
                // Handle successful response
                console.log('Data received:', data);
                console.log(typeof data);
                $('.coupon').hide();
                if(data.status){
                    $('.coupon-success').show();
                    var total = parseFloat($('#total').text().replace('$', ''));
                    var discountAmt = total*(data.discount*0.01);
                    var netAmt = total-discountAmt;
                    var netAmt = netAmt.toFixed(2);
                    var discountAmt = discountAmt.toFixed(2);
                    $('#total').text("$"+netAmt);
                    $('#discount-amount').text("$"+discountAmt);
                    console.log(discountAmt);
                }else{
                    location.reload();
                    $('.coupon-error').show();
                }
            },
            error: function(xhr, status, error) {
                // Handle errors
                console.error('Error:', error);
            }
        });
        
    });

    var razorpayBtn = $('#razorpayBtn');
    razorpayBtn.on('click', function () {
        var amt = parseFloat($('#total').text().replace('$', ''));
        var amt = amt.toFixed(2);        
        var options = {
            "key": "rzp_test_KAquipQkjmJ0v2",
            "amount": Number(amt) * 100 * 80, // amount in paise
            "currency": "INR",
            "name": "Ekart Shopping",
            "description": "Shopping App",
            "image": "https://your-website.com/logo.png",
            "handler": function (response) {
                // Handle success response
                alert('Payment successful your order ID : ' + response.razorpay_payment_id);
                $.ajax({
                    url: 'showBill', // URL of the server-side script or API endpoint
                    method: 'GET', // HTTP method (e.g., 'GET', 'POST', 'PUT', 'DELETE')
                    data: {
                        payment_id : response.razorpay_payment_id
                    },
                    success: function(response) {
                        console.log(response);
                        location.href= response.url;
                    },
                    error: function(xhr, status, error) {
                        // Callback function to handle errors
                        alert("Something went wrong to download your Invoive please try again later...")
                        console.error('Error:', error);
                    }
                });
                
            },
            "prefill": {
                "name": "K Dileep",
                "email": "customer@example.com",
                "contact": "9346237652"
            },
            "theme": {
                "color": "#007bff"
            }
        };

        var rzp = new Razorpay(options);
        rzp.open();
    });
});
