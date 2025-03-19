package Game.Map;

import Engine.Application;

public class CenterRight extends Zone {

    public CenterRight() {
        super("CenterRight", "centerRightBG.PNG");
    }

    @Override
    public void addEntities() {
        System.out.println("CenterRight zone entities added.");
    }

    @Override
    public void removeEntities() {
        System.out.println("CenterRight zone entities removed.");
    }
}
