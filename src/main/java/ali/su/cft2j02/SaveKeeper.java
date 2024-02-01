package ali.su.cft2j02;

import java.util.HashMap;
import java.util.Map;

public class SaveKeeper {
    private final Map<String, Save<?>> saves = new HashMap<>();

    public Save<?> getSave(String description) {
        return saves.get(description);
    }

    public void putSave(Save<?> save) {
        saves.put(save.getSaveDescription(), save);
    }

    @Override
    public String toString() {
        return "SaveKeeper{" +
                "saves=" + saves +
                '}';
    }
}
