package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    private int sentCount;
    private String lastDelivered;

    // Template Method: alurnya tetap, detail tiap kanal bisa berbeda.
    @Override
    public final boolean send(CustomerContact contact, String message) {
        if (contact == null || message == null || message.isBlank()) return false;
        String recipient = resolveRecipient(contact);
        if (recipient == null || recipient.isBlank()) return false;
        String prepared = prepare(message);
        if (prepared == null || prepared.isBlank()) return false;
        boolean delivered = deliver(recipient, prepared);
        if (delivered) {
            sentCount++;
            lastDelivered = prepared;
        }
        return delivered;
    }

    protected abstract String resolveRecipient(CustomerContact contact);
    protected String prepare(String message) { return message; }
    protected abstract boolean deliver(String recipient, String message);

    public int getSentCount() { return sentCount; }
    public String getLastDelivered() { return lastDelivered; }
}
