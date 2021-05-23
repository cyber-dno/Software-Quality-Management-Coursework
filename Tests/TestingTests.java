package Test;

import java.util.TreeMap;

import org.junit.Before;

public class TestingTests extends TestTree {
    @Override
    @Before
    public void before() {
        rtTree = new TreeMap<Integer, Object>();
        testTree = new TreeMap<Integer, Object>();
    }
}
