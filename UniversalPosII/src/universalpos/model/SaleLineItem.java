package universalpos.model;
public class SaleLineItem 
{
	private Product product;
	private int qnty;
	public SaleLineItem(Product product,int qnty)
	{
		this.product = product;
		this.qnty = qnty;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQnty() {
		return qnty;
	}
	public void setQnty(int qnty) {
		this.qnty = qnty;
	}
}

