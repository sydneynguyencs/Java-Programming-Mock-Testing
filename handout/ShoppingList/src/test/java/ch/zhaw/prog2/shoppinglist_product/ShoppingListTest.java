package ch.zhaw.prog2.shoppinglist_product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testen der Methode getTotalCosts aus der Klasse Shopping
 *
 * @author bles
 */
class ShoppingListTest {
    private ShoppingList shoppingList;
    // Ansatz: Die Produkte selbst mocken (für testGetTotalCostsWithProductMock())
    // PriceService wird nicht mehr verwendet
    @Mock
    private ProductMigros milk;
    private ProductMigros salad;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shoppingList = new ShoppingList();
        milk = mock(ProductMigros.class);
        salad = mock(ProductMigros.class);

        when(milk.getProductId()).thenReturn("MilkId");
        when(milk.getName()).thenReturn("Milk");
        when(milk.getQuantity()).thenReturn(3);
        when(milk.getPrice()).thenReturn(1.50);

        when(salad.getProductId()).thenReturn("SaladId");
        when(salad.getName()).thenReturn("Salad");
        when(salad.getQuantity()).thenReturn(2);
        when(salad.getPrice()).thenReturn(3.0);

        /*
        getProductId()
        getName()
        getQuantity()
        getPrice()
        */
    }

    @Test
    void testGetTotalCostsWithProductMock() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);

        double expectedTotalCost = (double) milk.getQuantity() * milk.getPrice() + (double) salad.getQuantity() * salad.getPrice();
        assertEquals(expectedTotalCost, shoppingList.getTotalCosts());
    }

}
