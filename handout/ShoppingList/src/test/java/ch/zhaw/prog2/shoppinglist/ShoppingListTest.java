package ch.zhaw.prog2.shoppinglist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingListTest {
	private ShoppingList shoppingList;
	private PriceService priceService;
    private final Product milk = new Product("MilkId", "Milk", 3);
    private final Product salad = new Product("SaladId", "Salad", 2);

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testGetProducts() {
    }

    @Test
    void testAddProduct() {
    }

    @Test
    void testGetTotalCosts() {
    }

    private void addMilkAndSaladToShoppingList() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);
    }

}
