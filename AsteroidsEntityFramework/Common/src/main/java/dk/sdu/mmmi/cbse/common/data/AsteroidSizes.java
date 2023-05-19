package dk.sdu.mmmi.cbse.common.data;

import java.util.HashMap;
import java.util.Map;

public class AsteroidSizes {

    private final int life = 3;
    private final int defaultSize = 20;

    private Map<Integer, Integer> sizes;

    public AsteroidSizes(){
        sizes = new HashMap<>();
    }

    private void setMap(){
        for (int i = life; i >= 0; i--) {
            sizes.put(i, defaultSize/(life-i+1));
        }
    }

    public int getSize(int life){
        if (life < 1){
            return 0;
        }
        setMap();
        return sizes.get(life);
    }

}
