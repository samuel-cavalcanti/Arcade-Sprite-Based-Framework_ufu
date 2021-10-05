package spriteframework.gameResource;

import java.util.HashMap;

public class GameResources {

    private static GameResources singleton;
    private final HashMap<String, Object> resources;

    private GameResources() {
        resources = new HashMap<>();
    }

    public static GameResources getInstance() {
        if (singleton == null)
            singleton = new GameResources();

        return singleton;
    }

    public void add(String id, Object resource) {
        if (resources.get(id) != null)
            throw new GameResourcesException("ID must be unique" + id);

        resources.put(id, resource);
    }

    public Object get(String id) {
        Object r = resources.get(id);
        if (r == null)
            throw new GameResourcesException("Resource not exist  Resource ID: " + id);

        return r;
    }

    public Object remove(String id) {
        Object resource = resources.remove(id);
        if (resource == null)
            throw new GameResourcesException("Resource not exist  Resource ID: " + id);

        return resource;
    }

    public void replace(String id, Object o) {

        Object result = resources.replace(id, o);
        if (result == null)
            resources.put(id, o);
    }


}
