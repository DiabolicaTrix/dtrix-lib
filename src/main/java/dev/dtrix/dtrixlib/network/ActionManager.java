package dev.dtrix.dtrixlib.network;


import java.util.HashMap;

public class ActionManager {

    private static ActionManager instance;

    private HashMap<Integer, ICallback> callbackMap;

    private ActionManager() {
        this.callbackMap = new HashMap<>();
    }

    public void addCallback(int id, ICallback callback) {
        this.callbackMap.put(id, callback);
    }

    public void triggerCallback(int id) {
        if(!this.callbackMap.containsKey(id))
            return;
        this.callbackMap.get(id).run();
        this.callbackMap.remove(id);
    }

    public static ActionManager getInstance() {
        if(instance == null)
            instance = new ActionManager();
        return instance;
    }
}
