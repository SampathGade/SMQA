package mobileclientassetmanagement.src.handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class HandlerFactory
{
    private static final String HANDLER = "handler.";
    public static Handler instantiateHandler(String handlerName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> handlerClassName = Class.forName(HANDLER+handlerName); return (Handler) handlerClassName.getDeclaredConstructor().newInstance();
    }

    public static Handler instantiateHandlerWithScanner(String handlerName, Scanner scanner) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> handlerClassName = Class.forName(HANDLER+handlerName);
        Constructor<?> constructor = handlerClassName.getConstructor(Scanner.class);
        return (Handler) constructor.newInstance(scanner);
    }
}
