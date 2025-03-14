package Engine;

import java.util.HashMap;
import java.util.Map;

public class Input {

    public enum KeyState {
        None,
        Down,
        Hold,
        Up
    }

    public static class Key {
        private final int key;
        private KeyState state = KeyState.None;

        public Key(int key){
            this.key = key;
        }

        public KeyState GetState(){
            return state;
        }

        public void Update() {
            switch(state){
                case None, Up:
                    if(Window.GetKey(key))
                        state = KeyState.Down;
                    else
                        state = KeyState.None;
                    break;
                case Down, Hold:
                    if(Window.GetKey(key))
                        state = KeyState.Hold;
                    else
                        state = KeyState.Up;
                    break;
            }
        }
    }

    private static final Map<Integer, Key> keyStates = new HashMap<>();

    public static boolean GetKey(int key){
        if(keyStates.containsKey(key))
            return keyStates.get(key).GetState() == KeyState.Down || keyStates.get(key).GetState() == KeyState.Hold;
        keyStates.put(key, new Key(key));
        return false;
    }

    public static boolean GetKeyDown(int key){
        if(keyStates.containsKey(key))
            return keyStates.get(key).GetState() == KeyState.Down;
        keyStates.put(key, new Key(key));
        return false;
    }

    public static boolean GetKeyUp(int key){
        if(keyStates.containsKey(key))
            return keyStates.get(key).GetState() == KeyState.Up;
        keyStates.put(key, new Key(key));
        return false;
    }

    public static KeyState GetKeyState(int key){
        if(keyStates.containsKey(key))
            return keyStates.get(key).GetState();
        keyStates.put(key, new Key(key));
        return KeyState.None;
    }

    public static void Update() {
        for(Key key : keyStates.values()){
            key.Update();
        }
    }

    public static void KeyCallback(int key) {
        if(!keyStates.containsKey(key))
            keyStates.put(key, new Key(key));
    }
}
