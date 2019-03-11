package com.rizalmovic.repositories;

import com.rizalmovic.models.Product;
import com.rizalmovic.models.ProductMeta;
import com.rizalmovic.models.SourceEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product repository test.")
class ProductRepositoryTest {

    private static final CategoryRepository categoryRepository = new CategoryRepository();
    private static final ProductRepository repository = new ProductRepository();

    @Test
    public void shouldAddProductWithMeta() {

        ProductMeta meta = new ProductMeta();
        meta.setKey("description");
        meta.setValue("Nokia X Dual SIM smartphone was launched in February 2014. The phone comes with a 4.00-inch touchscreen display with a resolution of 480x800 pixels at a pixel density of 233 pixels per inch (ppi). Nokia X Dual SIM is powered by a 1GHz dual-core Qualcomm Snapdragon S4 Play processor. It comes with 512MB of RAM.");

        Product product = new Product();
        product.setName("Nokia X");
        product.setSku("NOKIA-X");
        product.setSource(SourceEnum.IMPORT);
        product.setCategory(categoryRepository.find(1));

        product.getMetas().add(meta);

        assertNotNull(repository.save(product));
    }
}