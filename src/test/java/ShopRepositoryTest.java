import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopRepositoryTest {

    ShopRepository products = new ShopRepository();
    Product product1 = new Product(1, "мороженое", 60);
    Product product2 = new Product(2, "хлеб", 40);
    Product product3 = new Product(3, "молоко", 50);
    Product product4 = new Product(4, "сливочное масло", 180);
    Product product5 = new Product(3, "масло", 180);

    @BeforeEach
    public void setup() {
        products.add(product1);
        products.add(product2);
        products.add(product3);
    }

    @Test
    public void shouldRemoveExistingElement() {
        int id = 3;

        Product[] expected = {product1, product2};
        Product[] actual = products.removeById(id);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveBecauseElementDoesNotExist() {
        int id = 7;

        Assertions.assertThrows(NotFoundException.class, () -> {
            products.removeById(id);
        });
    }

    @Test
    public void shouldAddElement() {
        products.add(product4);

        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = products.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddBecauseElementAlreadyExist() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            products.add(product5);
        });
    }
}