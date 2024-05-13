package store.modal;

public class Product {

	private int id;
	private String title;
	private String category;
	private double gst;
	private String image;
	private double price;
	private double MRP;
	private double price_without_gst;
	private double offer;
	private double discount;
	private int qty;

	public Product(int pid, String ptitle, String pcat, String pimag, Double pprice, double GST) {
		// TODO Auto-generated constructor stub
		this.id = pid;
		this.title = ptitle;
		this.category = pcat;
		this.image = pimag;
		this.price = pprice;
		gst = GST;
		System.out.println("productss....");
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getGST() {
		return gst;
	}

	public void setGST(double gst) {
		this.gst = gst;
	}

	public double getMRP() {
		return MRP;
	}

	public void setMRP(double mRP) {
		MRP = mRP;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println("\n--------------------------------------------------");
		System.out.println("\n pid : "+id);
		System.out.println("\n name : "+title);
		System.out.println("\n price : "+price);
		System.out.println("\n Quantity : "+qty);
		System.out.println("\n GST        : "+gst);
		return "\n";
	}

	public double getPrice_without_gst() {
		return price_without_gst;
	}

	public void setPrice_without_gst(double price_without_gst) {
		this.price_without_gst = price_without_gst;
	}
}
