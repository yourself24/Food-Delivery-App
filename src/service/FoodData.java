package service;

import model.MenuItem;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Class for MenuItems</p>
 */
public class FoodData extends FileWriter {
    private static final String MENU_FILE = "data/menu.ser";

    public static boolean addProduct(MenuItem menuItem) {
        Set<MenuItem> menuList = FoodData.loadProducts();

        return menuList.add(menuItem) && writeObject(menuList, MENU_FILE);
    }
    /**
     modifying product
     */
    public static boolean modifyProduct(MenuItem menuItem) {
        Set<MenuItem> menuList = FoodData.loadProducts();

        if (!menuList.remove(menuItem)) {
            return false;
        }

        menuList.add(menuItem);

        return writeObject(menuList, MENU_FILE);
    }
    /**
     removing product
     */
    public static boolean removeProduct(MenuItem menuItem) {
        Set<MenuItem> menuList = FoodData.loadProducts();

        if (!menuList.remove(menuItem)) {
            return false;
        }

        return writeObject(menuList, MENU_FILE);
    }
    /**
     adding product
     */
    public static boolean addProducts(HashSet<MenuItem> productSet) {
        return writeObject(productSet, MENU_FILE);
    }

    @SuppressWarnings("unchecked")
    public static Set<MenuItem> loadProducts() {
        Set<MenuItem> availableProducts;

        availableProducts = (HashSet<MenuItem>) FileWriter.readObject(MENU_FILE);

        if (availableProducts == null) {
            availableProducts = new HashSet<>();
        }

        return availableProducts;
    }
}
