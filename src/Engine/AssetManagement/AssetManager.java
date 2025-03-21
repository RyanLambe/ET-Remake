package Engine.AssetManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    private static final ArrayList<Sprite> sprites = new ArrayList<>();
    private static final Map<String, Image> images = new HashMap<>();

    public static Sprite LoadSprite(String path) {
        Sprite newSprite = new Sprite();

        if (!images.containsKey(path)) {
            Image image = new Image(path);
            images.put(path, image);
            newSprite.AddFrame(image);
        } else {
            newSprite.AddFrame(images.get(path));
        }

        sprites.add(newSprite);
        return newSprite;
    }

    public static Sprite LoadAnimation(String... paths) {
        Sprite newSprite = new Sprite();

        for (String path : paths) {
            if (!images.containsKey(path)) {
                Image image = new Image(path);
                images.put(path, image);
                newSprite.AddFrame(image);
            } else {
                newSprite.AddFrame(images.get(path));
            }
        }

        sprites.add(newSprite);
        return newSprite;
    }

    public static ArrayList<Sprite> GetSprites() {
        return new ArrayList<>(sprites);
    }

    public static ArrayList<Image> GetImages() {
        return new ArrayList<>(images.values());
    }
}
