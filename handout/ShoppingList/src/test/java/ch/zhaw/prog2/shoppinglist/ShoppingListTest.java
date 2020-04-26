package ch.zhaw.prog2.shoppinglist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingListTest {
	private ShoppingList shoppingList;
	@Mock
	private PriceService priceService;
    private final Product milk = new Product("MilkId", "Milk", 3);
    private final Product salad = new Product("SaladId", "Salad", 2);

    @BeforeEach
    void setUp() throws Exception {
        shoppingList = new ShoppingList();
        priceService = mock(PriceService.class);
        MockitoAnnotations.initMocks(this);
        //alle methoden in der gemockten klasse
        when(priceService.getPrice(milk)).thenReturn(1.5);
        when(priceService.getPrice(salad)).thenReturn(3.0);
    }

    @Test
    void testGetProducts() {
        List<Product> list = new ArrayList<>();
        list.add(milk);
        list.add(salad);
        addMilkAndSaladToShoppingList();
        assertEquals(list, shoppingList.getProducts());
    }

    @Test
    void testAddProduct() {
        List<Product> list = new ArrayList<>();
        list.add(milk);
        list.add(salad);
        addMilkAndSaladToShoppingList();

        assertEquals(list.size(), shoppingList.getProducts().size());
        assertEquals(3, milk.getQuantity());
        assertEquals(2, salad.getQuantity());
    }

    @Test
    void testGetTotalCosts() {
        //mock PriceService
        //Anzahl Anfragen an PriceService testen

        addMilkAndSaladToShoppingList();
        shoppingList.setPriceService(priceService);

        double expectedTotalCost = 3 * priceService.getPrice(milk) + 2 * priceService.getPrice(salad);
        assertEquals(expectedTotalCost, shoppingList.getTotalCosts());
    }

    private void addMilkAndSaladToShoppingList() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);
    }

}
