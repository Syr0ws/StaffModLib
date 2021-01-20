package fr.syrows.staffmodlib.staffmod;

import fr.syrows.staffmodlib.events.ItemUseEvent;
import fr.syrows.staffmodlib.util.UseEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StaffModItemMetadata {

    private StaffModItem item;
    private Map<Method, Class<? extends ItemUseEvent>> listeners;

    public StaffModItemMetadata(StaffModItem item) {
        this.item = item;
        this.listeners = this.parseListeners();
    }

    public void handle(ItemUseEvent event) {

        for(Map.Entry<Method, Class<? extends ItemUseEvent>> entry : this.listeners.entrySet()) {

            Class<? extends ItemUseEvent> clazz = entry.getValue();

            if(!clazz.isInstance(event)) continue;

            try { entry.getKey().invoke(this.item, event);
            } catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
        }
    }

    private Map<Method, Class<? extends ItemUseEvent>> parseListeners() {

        Map<Method, Class<? extends ItemUseEvent>> listeners = new HashMap<>();

        Method[] methods = item.getClass().getDeclaredMethods();

        for(Method method : methods) {

            // Not an event method.
            if(!method.isAnnotationPresent(UseEvent.class)) continue;

            // TODO Throw an exception.
            if(method.getParameterCount() != 1) continue;

            Class<?> parameterClass = method.getParameterTypes()[0];

            // TODO Throw an exception.
            if(!ItemUseEvent.class.isAssignableFrom(parameterClass)) continue;

            @SuppressWarnings("unchecked")
            Class<? extends ItemUseEvent> eventClass = (Class<? extends ItemUseEvent>) parameterClass;

            listeners.put(method, eventClass);
        }
        return listeners;
    }

    public StaffModItem getItem() {
        return this.item;
    }

    public Map<Method, Class<? extends ItemUseEvent>> getListeners() {
        return Collections.unmodifiableMap(this.listeners);
    }
}
