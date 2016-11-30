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
	private String[] validCheckeOutItems = {"ORANGE", "ORANGE", "ORANGE", "ORANGE", "ORANGE", "ORANGE", "APPLE", "APPLE", "ORANGE", "ORANGE", "ORANGE", "ORANGE"};
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
		Assert.assertEquals(Double.valueOf(2.35), shoppingCart.checkOutItems(validCheckeOutItems));
	}
	
	@Test 
 	public void testTheTotalPriceOfCheckOutItemsWhichAreEligibleButWithWrongCalculation() throws ItemNotFoundException {
		Assert.assertNotNull(shoppingCart);			
		Assert.assertNotSame(Double.valueOf(2.05+1), shoppingCart.checkOutItems(validCheckeOutItems));
	}	
	
	@Test
	public void testTheCheckOutItemsWhichHasInvalidItemInBasketToThrowInvalidItemException() {
		Assert.assertNotNull(shoppingCart);				
		try {			
			Assert.assertEquals("INVALID_ITEM", shoppingCart.checkOutItems(inValidCheckeOutItems)); 
		}
		catch(ItemNotFoundException inf) {
			Assert.assertEquals(inf.getMessage(), ShoppingCart.INVALID_ITEM_MESSAGE);
		}		
	}
	@Test
	public void testTheSimpleOfferAppliedAgainstTheItemAppleWorkAsExpected() throws ItemNotFoundException {
		Assert.assertNotNull(shoppingCart);
		validCheckeOutItems = new String[] {"APPLE", "APPLE"};
		Assert.assertEquals(Double.valueOf(0.60), shoppingCart.checkOutItems(validCheckeOutItems));
		validCheckeOutItems = new String[] {"APPLE", "APPLE", "APPLE"};
		Assert.assertEquals(Double.valueOf(0.60+0.60), shoppingCart.checkOutItems(validCheckeOutItems));
		validCheckeOutItems = new String[] {"APPLE", "APPLE", "APPLE", "APPLE"};
		Assert.assertEquals(Double.valueOf(0.60+0.60), shoppingCart.checkOutItems(validCheckeOutItems));
	}
	
	@Test
	public void testTheSimpleOfferAppliedAgainstTheItemOrangeWorkAsExpected() throws ItemNotFoundException {
		Assert.assertNotNull(shoppingCart);
		validCheckeOutItems = new String[] {"ORANGE", "ORANGE"};
		Assert.assertEquals(Double.valueOf(0.50), shoppingCart.checkOutItems(validCheckeOutItems));
		validCheckeOutItems = new String[] {"ORANGE", "ORANGE", "ORANGE"};
		Assert.assertEquals(Double.valueOf(0.50), shoppingCart.checkOutItems(validCheckeOutItems));
		validCheckeOutItems = new String[] {"ORANGE", "ORANGE", "ORANGE", "ORANGE"};
		Assert.assertEquals(Double.valueOf(0.75), shoppingCart.checkOutItems(validCheckeOutItems));
	}
	
	@Test
	public void testTheSimpleOffersWorkForTheCombinationOfCheckOutItemsAppleAndOrange() throws ItemNotFoundException{
		Assert.assertNotNull(shoppingCart);
		Assert.assertEquals(Double.valueOf(2.35), shoppingCart.checkOutItems(validCheckeOutItems));
		
	}

}
