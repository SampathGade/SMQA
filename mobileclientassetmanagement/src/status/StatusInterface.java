package mobileclientassetmanagement.src.status;

public interface StatusInterface {
    void assign(Object entity, Object owner);
    void unAssign(Object entity);

    void markAsExpired(Object entity);
}
