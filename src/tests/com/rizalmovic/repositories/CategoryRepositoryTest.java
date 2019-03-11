package com.rizalmovic.repositories;

import com.rizalmovic.models.Category;
import com.rizalmovic.models.Product;
import com.rizalmovic.models.ProductMeta;
import com.rizalmovic.models.SourceEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Category repository test.")
class CategoryRepositoryTest {

    private static final CategoryRepository repository = new CategoryRepository();

    @Test
    public void shouldCreateCategory() {
        Category category = new Category();
        category.setName("Electronics");
        assertNotNull(repository.save(category));
    }

    @Test
    public void shouldGetCategory() {
        assertNotNull(repository.find(1));
    }

    @Test
    public void shouldAddProduct() {
        Category category = repository.find(1);

        Product product = new Product();
        product.setName("Mobile Phone");
        product.setSku("PHN-001");
        product.setSource(SourceEnum.IMPORT);

        category.getProducts().add(product);
        assertNotNull(repository.save(category));
    }

    @Test
    public void shouldAddProductWithMetas() {
        Category category = repository.find(1);

        ProductMeta meta = new ProductMeta();
        meta.setKey("description");
        meta.setValue("The latest released of mobile phone.");

        Product product = new Product();
        product.setName("Mobile Phone");
        product.setSku("PHN-002");
        product.setSource(SourceEnum.IMPORT);
        product.getMetas().add(meta);

        category.getProducts().add(product);
        assertNotNull(repository.save(category));
    }

    @Test
    public void shouldGetCategoryList() {
        assertTrue(repository.all().size() > 0);
    }
}