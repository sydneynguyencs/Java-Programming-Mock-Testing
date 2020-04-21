package ch.zhaw.prog2.shoppinglist_product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testen der Methode getTotalCosts aus der Klasse Shopping
 *
 * @author bles
 */
class ShoppingListTest {
    private ShoppingList shoppingList;
    // Ansatz: Die Produkte selbst mocken (für testGetTotalCostsWithProductMock())
    // PriceService wird nicht mehr verwendet

    @BeforeEach
    void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        shoppingList = new ShoppingList();
    }

    @Test
    void testGetTotalCostsWithProductMock() {
    }

}
