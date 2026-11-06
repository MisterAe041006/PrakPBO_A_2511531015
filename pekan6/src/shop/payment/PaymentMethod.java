package shop.payment;

public interface PaymentMethod {
    String getName();
    boolean pay(long amount);

    // T1: cara memperluas kontrak tanpa merusak implementasi lama.
    default long adminFee(long amount) { return 0; }
}
