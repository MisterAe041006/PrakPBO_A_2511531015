package shop.service;

import java.util.List;
import shop.model.Product;
import shop.payment.PaymentMethod;
import shop.payment.RefundablePayment;
import shop.shipping.Shippable;
import shop.shipping.ShippingService;

public class CheckoutService {
    public long subtotal(List<Product> items) {
        long sum = 0;
        for (Product item : items) {
            sum += item.getFinalPrice();
        }
        return sum;
    }

    public int totalWeight(List<Product> items) {
        int grams = 0;
        for (Product item : items) {
            if (item instanceof Shippable shippable) {
                grams += shippable.getWeightGrams();
            }
        }
        return grams;
    }

    public long total(List<Product> items, ShippingService shipping) {
        return subtotal(items) + shipping.calculateCost(totalWeight(items));
    }

    // T1: total + biaya administrasi pembayaran.
    public long grandTotal(List<Product> items, ShippingService shipping,
                           PaymentMethod payment) {
        long amount = total(items, shipping);
        return amount + payment.adminFee(amount);
    }

    public boolean checkout(List<Product> items, ShippingService shipping,
                            PaymentMethod payment) {
        if (items == null || items.isEmpty()) return false;
        return payment.pay(grandTotal(items, shipping, payment));
    }

    public boolean refund(RefundablePayment payment, long amount) {
        return payment.refund(amount);
    }
}
