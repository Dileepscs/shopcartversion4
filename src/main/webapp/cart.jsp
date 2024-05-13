<%@ page import="java.util.List"%>
<%@ page import="store.modal.CartItems"%>
<%@ page import="store.DAL.ProductsDAL"%>
<%@ page import="store.modal.Product"%>
<%-- <%@ page import="model.Cart"%> --%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<style>
body {
	
	min-height: 100vh;
}
</style>
<html>
<head>

	<title>Shopping Cart</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
	<link rel="stylesheet" herf="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://checkout.razorpay.com/v1/checkout.js"></script>

</head>
<body class="bg-dark">
	<div class="px-4 px-lg-0">
		<!-- For demo purpose -->
		<div class="container text-white py-5 text-center">
			<h1 class="display-4">
				<Strong>Shopping Cart</Strong>
			</h1>
			<p class="lead mb-0">Shop With Us!</p>
		</div>
		<!-- End -->
		<div class="pb-5">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

						<!-- Shopping cart table -->
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th scope="col" class="border-0 bg-light">
											<div class="p-2 px-3 text-uppercase">Product</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase">Price</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase">Quantity</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase">Remove</div>
										</th>
									</tr>
								</thead>
								<tbody>
									<%
										ProductsDAL productsDAL = new ProductsDAL();
									List<CartItems> cartitems = (ArrayList<CartItems>) session.getAttribute("cartitems");
									if (cartitems != null && cartitems.isEmpty() == false) {
										for (CartItems item : cartitems) {
											Product product = productsDAL.getProductById(item.getId()); // Fetch product details
									%>
									<tr id="row_<%=item.getId()%>">
										<th scope="row" class="border-0">
											<div class="p-2">
												<img src="<%=product.getImage()%>" alt="" height="150"
													width="70" class="img-fluid rounded shadow-sm">
												<div class="ml-3 d-inline-block align-middle">
													<h5 class="mb-0">
														<a href="#" class="text-dark d-inline-block align-middle"><%=product.getTitle()%></a>
													</h5>
												</div>
											</div>
										</th>
										<td class="border-0 align-middle">
											<strong id="price_<%=item.getId()%>" class="quantity-price">
												$<%= String.format("%.2f",product.getPrice()*item.getQuantity()) %>
											</strong>
										</td>
										<td class="border-0 align-middle">
											<div class="quantity-btns">
												<button class="decrease-btn bg-dark text-white rounded-circle font-weight-bold" data-pid="<%=product.getId()%>">-</button>
												<span id="quantity_<%=product.getId()%>" class="quantity-value"><%=item.getQuantity()%></span>
												<button class="increase-btn bg-dark text-white rounded-circle font-weight-bold" data-pid="<%=product.getId()%>">+</button>
											</div>
										</td>
										<td class="border-0 align-middle">
											<%
												String fun = "removeItem("+item.getId()+")";
											%>
											<h6 onclick="<%= fun %>" class="text-dark p-1" value="<%= item.getId() %>">
												<i class="fa fa-trash w-50 m-1 p-2" style="font-size: 24px;"></i>
											</h6>
										</td>
									</tr>

									<%
										}
									} else {
									%>
										<p>no items here</p>
									<%
										}
									%>
									<tr>
										<td class="border-0 align-middle">
											<h5 class="m-2 p-3">
												<strong>TOTAL AMOUNT  :  </strong>
												<strong id="total">0.00</strong>
											</h5>
											<button type="button" class="btn btn-dark rounded-pill mt-3 py-2 btn-block w-50" id="checkout-btn">Procced to Checkout</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- End -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function removeItem(pid) {
        console.log(pid);
        $.ajax({
            type : "POST",
            url : "RemoveServlet",
            data : {
                "pid" : pid
            },
            success : function(response) {
                //console.log(response);
                $('#row_' + pid).remove();
                alert("item removed");
            }
        });
        //getTotalAmount();
        location.reload();
    }
	
	function getTotalAmount() {
	    var totalPrice = 0;
	    $('.quantity-price').each(function() {
	        var price = parseFloat($(this).text().replace('$', ''));
	        totalPrice += price;
	    });
	    
	    $('#total').text('$' + totalPrice.toFixed(2));
	    console.log('Total price calculated successfully:', totalPrice.toFixed(2));
	}

	$("#checkout-btn").click(function(){
	    location.href="checkout";
	});
	$(document).ready(function() {
	    $(".increase-btn").click(function() {
	        var pid = $(this).data("pid");
	        console.log("pid : "+pid);
	        $.ajax({
	            url: "IncreaseQuantityController",
	            method: "GET",
	            data: { pid: pid },
	            success: function(data) {
	                var quantityElement = $("#quantity_" + pid);
	                var priceElement = $("#price_" + pid);
	                var Unitprice = parseFloat(priceElement.text().replace("$", ""))/parseInt(quantityElement.text());
	                var newQuantity = parseInt(quantityElement.text()) + 1;
	                quantityElement.text(newQuantity);
	                var newPrice = Unitprice * newQuantity;
	                priceElement.text("$" + newPrice.toFixed(2));
	            }
	        });
	        //getTotalAmount();
	        location.reload();
	    });

	    $(".decrease-btn").click(function() {
	        var pid = $(this).data("pid");
	        console.log("pid : "+pid);
	        $.ajax({
	            url: "DecreaseQuantityController",
	            method: "GET",
	            data: { pid: pid },
	            success: function(data) {
	                var quantityElement = $("#quantity_" + pid);
	                var priceElement = $("#price_" + pid);
	                var Unitprice = parseFloat(priceElement.text().replace("$", ""))/parseInt(quantityElement.text());
	                var newQuantity = parseInt(quantityElement.text()) - 1;
	                if (newQuantity > 0) {
	                    quantityElement.text(newQuantity);
	                    // var price = parseFloat(priceElement.text().replace("$", ""));
	                    var newPrice = Unitprice * newQuantity;
	                    priceElement.text("$" + newPrice.toFixed(2));
	                }else{
	                    //alert("Item removed successfully");
	                    removeItem(pid);
	                }
	            }
	        });
	        //getTotalAmount();
	        location.reload();
	    });
	    getTotalAmount();
	});   
	</script>

</body>
</html>