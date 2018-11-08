package other;

import java.util.ArrayList;
import java.util.List;

public class ForCheck {
    private static List<String> listProdInMind = new ArrayList();
    public static List<String> listProdInCart = new ArrayList();

    private static int totalPrice = 0;

    public static void clearVar (){
        listProdInCart.clear();
        listProdInMind.clear();
        totalPrice = 0;
    }

    public static void setListProdInCart(List<String> listProdInCart) {
        ForCheck.listProdInCart = listProdInCart;
    }

    public static int getTotalPriceFromCart() {
        return totalPriceFromCart;
    }

    public static void setTotalPriceFromCart(int totalPriceFromCart) {
        ForCheck.totalPriceFromCart = totalPriceFromCart;
    }

    private static int totalPriceFromCart = 0;

    public static int getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(int totalPrice) {
        ForCheck.totalPrice = totalPrice;
    }

    public static void setListProdInMind(List<String> listProdInMind) {
        ForCheck.listProdInMind = listProdInMind;
    }
    public static void addToListProdInCart(String prod) {
        ForCheck.listProdInMind.add(prod);
    }
    public static void AddToListProdInMind(String prod) {
        ForCheck.listProdInMind.add(prod);
    }

    public static List<String> getListProdInCart() {
        return listProdInCart;
    }

    public static List<String> getListProdInMind() {
        return listProdInMind;

    }
}
