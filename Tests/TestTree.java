package Test;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import Mihail.Tree;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TestTree {

    Map<Integer, Object> rtTree;
    Map<Integer, Object> testTree;

    private static final Object OBJ = new Object[0];

    @Before
    public void before() {
        rtTree = new TreeMap<>();
        testTree = new Tree<>();
    }

    @Test
    public void testRandomFill() {
        Random r = new Random();
        int length = 5 + r.nextInt(996);
        for(int i = 0; i < length; i++) {
            int key = r.nextInt(1000);
            rtTree.put(key, OBJ);
            testTree.put(key, OBJ);
        }
        assertIterableEquals(rtTree.keySet(), testTree.keySet(), "Тест на рандомное заполнение и последующую проверку провалился");
    }

    @Test
    public void testBigFill() {
        int[] val = {
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (int) (Integer.MAX_VALUE*0.9f),
                (int) (Integer.MIN_VALUE*0.9f),
                (int) (Integer.MAX_VALUE*0.7f),
                (int) (Integer.MIN_VALUE*0.7f),
                (int) (Integer.MAX_VALUE*0.2f),
                (int) (Integer.MIN_VALUE*0.2f)
        };
        for(int v : val) {
            rtTree.put(v, OBJ);
            testTree.put(v, OBJ);
        }
        assertIterableEquals(rtTree.keySet(), testTree.keySet(), "Тест на заполнение большими ключами провалился");
    }

}