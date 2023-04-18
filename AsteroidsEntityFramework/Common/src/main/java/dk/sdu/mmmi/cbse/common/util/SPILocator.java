package dk.sdu.mmmi.cbse.common.util;

<<<<<<< Updated upstream
import java.util.*;

public class SPILocator {

    private static final Map<Class, ServiceLoader> loaderMap = new HashMap<Class, ServiceLoader>();


    private SPILocator(){

=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class SPILocator {

    @SuppressWarnings("rawtypes")
    private static final Map<Class, ServiceLoader> loadermap = new HashMap<Class, ServiceLoader>();

    private SPILocator() {
>>>>>>> Stashed changes
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> locateAll(Class<T> service) {
<<<<<<< Updated upstream
        ServiceLoader<T> loader = loaderMap.get(service);
=======
        ServiceLoader<T> loader = loadermap.get(service);
>>>>>>> Stashed changes

        boolean printStatement = false;

        if (loader == null) {
            loader = ServiceLoader.load(service);
<<<<<<< Updated upstream
            loaderMap.put(service,loader);
=======
            loadermap.put(service, loader);
>>>>>>> Stashed changes
            printStatement = true;
        }

        List<T> list = new ArrayList<T>();

        if (loader != null) {
            try {
                for (T instance : loader) {
                    list.add(instance);
                }
<<<<<<< Updated upstream
            } catch (ServiceConfigurationError serviceError){
=======
            } catch (ServiceConfigurationError serviceError) {
>>>>>>> Stashed changes
                serviceError.printStackTrace();
            }
        }

        if (printStatement) {
            System.out.println("Found " + list.size() + " implementations for interface: " + service.getName());
        }

        return list;
    }
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
}
