<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="store.modal.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
	<link rel="stylesheet"	herf="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js" />
	<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body class=" bg-dark ">
	<div class="px-4 d-flex justify-content-center align-items-center" style="height: 100vh;">
		<!-- For demo purpose -->
		<!-- End -->
		<div class="pb-5">
			<div class="container">
				<!-- <div class="row">
					<div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

					</div>
				</div> -->
				<div class="row py-5 p-4 bg-white rounded shadow-sm">
					<div class="col-lg-6">
						<div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Coupon code</div>
						<div class="p-4">
							<p class="font-italic mb-4">If you have a coupon code, please enter it in the box below</p>
							<div class="input-group mb-4 border rounded-pill p-2">
								<input type="text" placeholder="Apply coupon" name="coupon-code" id="coupon-code" aria-describedby="button-addon3" maxlength="10" class="form-control border-0 text-uppercase font-weight-bold">
								<div class="input-group-append border-0">
									<button id="coupon-btn" type="button" class="btn btn-dark px-4 rounded-pill">
										<i class="fa fa-gift mr-2"></i>Apply coupon
									</button>
								</div>
							</div>
							<div class="text-success font-weight-bold coupon coupon-success">Coupon applied successfully</div>
							<div class="text-danger font-weight-bold coupon coupon-error">Invalid Coupon or coupon expired</div>
							
						</div>
						<div
							class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Instructions
							for seller</div>
						<div class="p-4">
							<p class="font-italic mb-4">If you have some information for
								the seller you can leave them in the box below</p>
							<textarea name="" cols="30" rows="2" class="form-control"></textarea>
						</div>
					</div>
					<div class="col-lg-6">
						<div
							class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order summary</div>
						<div class="p-4">
							<p class="font-italic mb-4">Shipping and additional costs are calculated based on values you have entered.</p>
							<% 
								CheckOutModal ch = (CheckOutModal)request.getAttribute("checkoutDetails");
									
							%>
							
							<ul class="list-unstyled mb-4">
								<li class="d-flex justify-content-between py-3 border-bottom">
									<strong class="text-muted ">Order Subtotal </strong>
									<strong class="totalCost">$<%= String.format("%.2f",ch.getSubTotal()) %></strong>
								</li>
								<li class="d-flex justify-content-between py-3 border-bottom">
									<strong class="text-muted">Shipping and handling</strong>
									<strong >$<%= String.format("%.2f", ch.getShipping_charges()) %></strong>
								</li>
								<li class="d-flex justify-content-between py-3 border-bottom">
									<strong class="text-muted">Shipping Tax</strong>
									<strong>$<%= String.format("%.2f", ch.getShipping_tax())%></strong>
								</li>
								<li class="d-flex justify-content-between py-3 border-bottom">
									<strong class="text-muted">Discount Amount</strong>
									<strong class="text-success" id="discount-amount">$<%= String.format("%.2f", 0.0)%></strong>
								</li>
								<li class="d-flex justify-content-between py-3 border-bottom">
									<strong	class="text-muted">Total</strong>
									<h5 id="total" class="font-weight-bold totalCost ">$<%= String.format("%.2f", ch.getTotal())%></h5>
								</li>
							</ul>
						<a href="#" id="razorpayBtn" class="btn btn-dark rounded-pill py-2 btn-block">Procceed to Payamount</a>
							<!--	<form id="paymentForm">
    <input type="text" name="amount" placeholder="Enter Amount (in paise)">
    <button type="submit">Pay with Razorpay</button>
</form> -->
								
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript" src="./script/checkout.js"></script>
</body>
</html>