package main.com.hmrc.checkout;

import java.math.BigDecimal;
import java.util.EnumMap;

public class ShoppingCart {
	
	public static final String INVALID_ITEM_MESSAGE = "Invalid Item Checked Out";
	public static enum SHOPPINGCARTITEMS {APPLE, ORANGE};
	
	private EnumMap<SHOPPINGCARTITEMS, BigDecimal> priceList;
	
	public ShoppingCart() {
		priceList = new EnumMap<SHOPPINGCARTITEMS, BigDecimal>(SHOPPINGCARTITEMS.class);
		priceList.put(SHOPPINGCARTITEMS.APPLE, new BigDecimal(0.60));
		priceList.put(SHOPPINGCARTITEMS.ORANGE, new BigDecimal(0.25));
	}
	
	public Double checkOutItems (String[] checkOutItems) throws ItemNotFoundException  {
		BigDecimal totalPrice = new BigDecimal(0.0);
		for(String item : checkOutItems) {
			try {
				if(priceList.containsKey(SHOPPINGCARTITEMS.valueOf(item))) {
					totalPrice = totalPrice.add(getPriceList().get(SHOPPINGCARTITEMS.valueOf(item)));
				}
			}
			catch(IllegalArgumentException iae) {
				throw new ItemNotFoundException(INVALID_ITEM_MESSAGE);
			}
		}
		totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		return totalPrice.doubleValue();
		
	}

	public EnumMap<SHOPPINGCARTITEMS, BigDecimal> getPriceList() {
		return priceList;
	}	

}
