package mobileclientassetmanagement.src.status;

public abstract class Status implements StatusInterface {

    @Override
    public void assign(Object entity, Object owner) {}

    @Override
    public void unAssign(Object entity) { }

    @Override
    public void markAsExpired(Object entity) {}

    @Override
    public void decommision(Object entity) { }

    @Override
    public void close(Object entity) {}
    @Override
    public void markAsPaid(Object entity) { }
    
    @Override
    public void approve(Object entity) { }
    
    @Override
    public void push(Object entity) {}

}
