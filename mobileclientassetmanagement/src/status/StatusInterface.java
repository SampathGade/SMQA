package mobileclientassetmanagement.src.status;

public interface StatusInterface {
    void assign(Object entity, Object owner);
    void unAssign(Object entity);

    void markAsExpired(Object entity);
    void decommision(Object entity);
    void close(Object entity);
    void markAsPaid(Object entity);
    void push(Object entity);
}
