package test.com.hmrc.checkout;

import java.math.BigDecimal;

import main.com.hmrc.checkout.ItemNotFoundException;
import main.com.hmrc.checkout.ShoppingCart;
import main.com.hmrc.checkout.ShoppingCart.SHOPPINGCARTITEMS;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ShoppingCartTest {
	
	private final SHOPPINGCARTITEMS apple = SHOPPINGCARTITEMS.APPLE;
	private final SHOPPINGCARTITEMS orange = SHOPPINGCARTITEMS.ORANGE;
	private final BigDecimal priceOfApple = new BigDecimal(0.60);
	private final BigDecimal priceOfOrange = new BigDecimal(0.25);
	private String[] validCheckeOutItems = {"APPLE", "APPLE", "ORANGE", "APPLE"}; 
	private String[] inValidCheckeOutItems = {"APPLE", "APPLE", "KIWI", "PASSION-FRUIT"};
	
	private ShoppingCart shoppingCart;
	
	@Before
	public void setUp(){
		shoppingCart = new ShoppingCart();
	}
	
	@Test
	public void testTheItemsEligibleForCheckOut() {
		Assert.assertNotNull(shoppingCart);
		Assert.assertEquals(SHOPPINGCARTITEMS.values().length, 2);
		Assert.assertTrue(SHOPPINGCARTITEMS.valueOf("APPLE").equals(apple));
		Assert.assertTrue(SHOPPINGCARTITEMS.valueOf("ORANGE").equals(orange));
	}
	
	@Test
	public void testThePriceAgainstEligibleItems(){
		Assert.assertNotNull(shoppingCart);
		Assert.assertTrue(shoppingCart.getPriceList().get(SHOPPINGCARTITEMS.APPLE).equals(priceOfApple));
		Assert.assertTrue(shoppingCart.getPriceList().get(SHOPPINGCARTITEMS.ORANGE).equals(priceOfOrange));
	}
	
	@Test 
 	public void testTheTotalPriceOfItemsWhichAreEligibleToCheckOut() throws ItemNotFoundException {
		Assert.assertNotNull(shoppingCart);			
		Assert.assertEquals(Double.valueOf(2.05D), shoppingCart.checkOutItems(validCheckeOutItems));
	}
	
	@Test 
 	public void testTheTotalPriceOfCheckOutItemsWhichAreEligibleButWithWrongCalculation() throws ItemNotFoundException {
		Assert.assertNotNull(shoppingCart);			
		Assert.assertNotSame(2.05D+1D, shoppingCart.checkOutItems(validCheckeOutItems));
	}	
	
	@Test
	public void testTheCheckOutItemsWhichHasInvalidItemInBasketToThrowInvalidItemException() {
		Assert.assertNotNull(shoppingCart);				
		try {
			shoppingCart.checkOutItems(inValidCheckeOutItems);
		}
		catch(ItemNotFoundException inf) {
			Assert.assertEquals(inf.getMessage(), ShoppingCart.INVALID_ITEM_MESSAGE);
		}		
	}

}
