package store.BLL;

import store.DAL.Common;
import store.DAO.DataBaseBridge;
import store.modal.CheckOutModal;
import store.modal.Product;
import store.modal.ProductList;

public class CalculateBLL implements Bll {
	CheckOutModal c;

	public CalculateBLL() {
		c = new CheckOutModal();
		// TODO Auto-generated constructor stub
	}

	@Override
	public CheckOutModal getCheckOutDetails(ProductList pl) {
		// TODO Auto-generated method stub
		c.setSubTotal(pl.sum());
		DataBaseBridge dao = new DataBaseBridge();
		Common commonDALObject = dao.getCommonDAL();
		double baseShip = commonDALObject.getBaseShippingAmount(c.getSubTotal());
		
		//add baseshipping charges getting from database
		c.setShipping_charges(baseShip);
		
		c.setShipping_tax(getShippingTax(pl, baseShip, c.getSubTotal()));
		
		double total = c.getSubTotal() + c.getShipping_tax() + c.getShipping_charges();
		c.setTotal(total);
		return c;
	}

	private double getShippingTax(ProductList pl, double baseShip, double subTotal) {
		// TODO Auto-generated method stub
		double cummulativeTax = 0;
		for (Product p : pl) {
			double shippingChargeForTheProduct =((p.getPrice() * p.getQty()) / subTotal)*baseShip;
			
//			System.out.println("\n\n\n"+p);
//			System.out.println("price * qty                        "+(p.getPrice()*p.getQty()));
//			System.out.println("\nSubtotal                         "+subTotal);
//			System.out.println("\nprice_qty  / subtotal            "+(p.getPrice()*p.getQty()*100)/subTotal+"%");
//			System.out.println("\nshippingCharge for the product   "+shippingChargeForTheProduct);
//			System.out.println("\nTax                              "+(shippingChargeForTheProduct * p.getGST()/100));
			cummulativeTax += shippingChargeForTheProduct * p.getGST()/100;
			
		}
//		System.out.println("\nCummulativeTAX                      : "+cummulativeTax);
		return cummulativeTax;
	}

}
