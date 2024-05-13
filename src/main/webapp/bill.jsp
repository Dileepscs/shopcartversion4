<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="store.modal.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
      rel="stylesheet"
      id="bootstrap-css"
    />
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>INVOICE</title>
</head>
<body>
		<%
        	BillDetails bill = (BillDetails) session.getAttribute("bill");
        	ProductList products = bill.getProducts();
        	CouponDetails coupon = bill.getCoupon();
        	CheckOutModal checkoutdetails = bill.getDelivery();
        	Customer customer = bill.getCustomer();
        %>
	<div class="container">
    <div class="row">
      <div class="col-xs-12">
        <div class="invoice-title">
          <h2>Invoice</h2>
          <h3 class="pull-right">ORDER ID :  ORD#<%=bill.getOrderId() %></h3>
        </div>
        <hr />
        
        <div class="row">
          <div class="col-xs-6">
            <address>
              <strong>Billed To:</strong><br />
              <%= customer.getCname() %><br />
              Pennant technology pvt ltd<br />
              Hill No. 2<br />
              Vizag,Madhuwada
            </address>
          </div>
          <div class="col-xs-6 text-right">
            <address>
              <!-- <strong>Shipped To:</strong><br />
                Jane Smith<br />
                1234 Main<br />
                Apt. 4B<br />
                Springfield, ST 54321 -->
            </address>
          </div>
        </div>
        <div class="row">
          <div class="col-xs-6">
            <address>
              <strong>Payment ID:</strong><br />
              <%= bill.getBillNo() %>
              Visa ending **** 4242<br />
            </address>
          </div>
          <div class="col-xs-6 text-right">
            <address>
              <strong>Order Date:</strong><br />
              <%= bill.getBillDate() %><br /><br />
            </address>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title"><strong>Order summary</strong></h3>
          </div>
          <div class="panel-body">
            <div class="table-responsive">
              <table class="table table-condensed">
                <thead>
                  <tr>
                    <td><strong>Item</strong></td>
                    <td class="text-center"><strong>Price</strong></td>
                    <td class="text-center"><strong>MRP</strong></td>
                    <td class="text-center"><strong>GST</strong></td>
                    <td class="text-center"><strong>Discount</strong></td>
                    <td class="text-center"><strong>Quantity</strong></td>
                    <td class="text-right"><strong>Total</strong></td>
                  </tr>
                </thead>
                <tbody>
                  <!-- foreach ($order->lineItems as $line) or some such thing here -->
             		<%
             	     for(Product p : products){
             			
             		%>
                  
                  
                  <tr>
                    <td>
                     	<%= p.getTitle() %>
                    </td>
                    <td class="text-center">$<%= String.format("%.2f",p.getPrice_without_gst()) %></td>
                    <td class="text-center">$<%=p.getMRP() %></td>
                    <td class="text-center">$<%=String.format("%.2f",(p.getGST()*0.01*p.getPrice_without_gst())) %></td>
                    <td class="text-center">$<%=String.format("%.2f",p.getDiscount()) %></td>
                    <td class="text-center"><%=p.getQty() %></td>
                    <%
                    	double subtotal;
                    %>
                    <td class="text-right">$<%=String.format("%.2f",((p.getPrice()-p.getDiscount())*p.getQty())) %></td>
                  </tr>
                  <%
             	     }
                  %>
                  <tr>
                    <td class="thick-line"></td>
                    <td class="thick-line"></td>
                    <td class="thick-line"></td>
                    <td class="thick-line"></td>
                    <td class="thick-line"></td>
                    <td class="thick-line text-left">
                      <strong>Subtotal</strong>
                    </td>
                    <td class="thick-line text-right">$<%= checkoutdetails.getSubTotal() %></td>
                  </tr>
                  <tr>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line text-left">
                      <strong>Shipping and Handling</strong>
                    </td>
                    <td class="no-line text-right">$<%= (checkoutdetails.getShipping_charges()) %></td>
                  </tr>
                  <tr>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line text-left">
                      <strong>Tax</strong>
                    </td>
                    <td class="no-line text-right">$<%= (checkoutdetails.getShipping_tax()) %></td>
                  </tr>
                  <tr>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line text-left">
                      <strong>Total</strong>
                    </td>
                    <td class="no-line text-right">$<%= String.format("%.2f",(checkoutdetails.getTotal())) %></td>
                  </tr>
                </tbody>
              </table>
            </div>
            
          </div>
        </div>
      </div>
      
    </div>
 	<button class="btn btn-primary d-flex justofy-content-center" onclick="print()">PRINT BILL</button>
  </div>
</body>
</html>