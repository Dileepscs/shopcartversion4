package store.modal;

public class CartItems {
	private int id;
	private int quantity;
	private double price;
	private String name;
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartItems(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;

	}
	
	public String display() {
		// TODO Auto-generated method stub
		String s = "[ "+this.id+" , "+this.quantity+" , "+ this.price+" ]";
		return s;
	}
}
