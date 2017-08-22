/**
 * This is a class for processing Sales Information and maintaining sales record. 
 * 
 *
 */
public class Sale {

	// public field log to store messages and product details
	public SaleRecord log;

	// Message parser helps to parse the incoming messages and obtain product sale information.
	private TextMessageParser messageParser;

	// Adjustment of product price is handled within this object e.g. add 20p, subtract 20p, etc.
	private AdjustPrice adjustPrice;

	// This holds the product details
	private Product product;


	// Constructor
	public Sale() {
		log = new SaleRecord();
		this.messageParser = new TextMessageParser();
	}

	/** Process notices and appends product information to the relevant product in the
	 *  salelog.
	 * @param saleNotice
	 * @return [Boolean] false on empty string and invalid message and true on the rest.
	 */
	public boolean processNotification(String saleNotice) {
		// Process the given message

		messageParser.parseMessage(saleNotice);

		// Get the product type e.g 'apple'
		String productType = messageParser.getProductType();

		// Check if product type is empty return false and do nothing.
		if (productType.isEmpty()) {
			return false;
		}

		//Returns an existing product else returns a new Product object
		this.product = log.getProduct(productType);

		// Prepare the product details for adjustment
		this.adjustPrice = new AdjustPrice(product);

		// Set the product details from the parsed message
		this.product.setProductQuantity(messageParser.getProductQuantity());
		this.product.setTotalQuantity(messageParser.getProductQuantity());
		this.product.setProductPrice(messageParser.getProductPrice());
		this.product.setAdjustmentOperator(messageParser.getOperatorType());

		// Set the total value of the product.
		setProductTotalPrice();

		// Set the sale log reports
		log.setNormalReports(saleNotice);

		// Update the product with the new details
		log.updateProduct(product);

		return true;
	}

	/** Set or Append Total product price based on any adjustment if given.
	 * Also appends the log for adjustments made.
	 * 
	 */
	private void setProductTotalPrice() {
		double adjustedPrice;
		double productValue;

		if (!product.getAdjustmentOperator().isEmpty()) {
			adjustedPrice = adjustPrice.getAdjustedPrice();
			log.setAdjustmentReports(adjustPrice.adjustmentReport());
			product.setTotalPrice(adjustedPrice);
		} else {
			productValue = product.calculatePrice(product.getProductQuantity(), product.getProductPrice());
			product.appendTotalPrice(productValue);
		}
	}

}		
//}
