package support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestEnvironment {

    private Map<String, String> productPriceMap;
    private Map<String, String> productQuantityMap;

    public TestEnvironment() {
        productPriceMap = new HashMap<>();
        productQuantityMap = new HashMap<>();
    }

    public void setProductPriceMap(Map<String, String> productPriceMap) {
        this.productPriceMap = productPriceMap;
    }

    public void setProductQuantityMap(Map<String, String> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }

    public Map<String, String> getProductPriceMap() {
        return this.productPriceMap;
    }

    public Map<String, String> getProductQuantityMap() {
        return this.productQuantityMap;
    }

}
