package review.fixed;

import java.util.List;
import shop.model.Address;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.model.Product;
import shop.payment.PaymentMethod;
import shop.service.CheckoutService;
import shop.shipping.ExpressShipping;
import shop.shipping.ShippingService;

/** Contoh refactor mandiri berdasarkan gejala BadShop dalam modul, bukan salinan BadShop.java. */
public class BadShopRefactored {
    public static void main(String[] args) {
        Product snack = new PhysicalProduct("S-1", "Paket UMKM", 100_000, 1_000);
        Product promo = new PhysicalProduct("P-1", "Produk flash sale", 70_000, 1_000);
        List<Product> cart = List.of(snack, promo);
        CheckoutService checkout = new CheckoutService();
        ShippingService express = new ExpressShipping();
        System.out.println("Total cart: " + checkout.subtotal(cart));
        System.out.println("Ongkir Ekspres 2 kg: " + express.calculateCost(checkout.totalWeight(cart)));

        Order order1 = new Order("ORD-1", new Address("Jl. Sudirman 10", "Padang", "25121"));
        Order order2 = new Order("ORD-2", new Address("Jl. Sudirman 10", "Bukittinggi", "26111"));
        System.out.println(order1.getOrderId() + " -> " + order1.getShippingAddress().getStreet()
                + ", " + order1.getShippingAddress().getCity());
        System.out.println(order2.getOrderId() + " -> " + order2.getShippingAddress().getStreet()
                + ", " + order2.getShippingAddress().getCity());
        System.out.println("Harga flash sale: " + promo.getFinalPrice());

        PaymentMethod voucher = new PaymentMethod() {
            private long remaining = 120_000;
            @Override public String getName() { return "Voucher"; }
            @Override public boolean pay(long amount) {
                if (amount <= 0 || amount > remaining) return false;
                remaining -= amount;
                return true;
            }
        };
        boolean paid = voucher.pay(50_000);
        System.out.println(paid + " | sisa Rp" + (120_000 - 50_000));
        // Voucher tidak diperlakukan sebagai Shippable: tidak ada ship() yang melempar exception.
    }
}
