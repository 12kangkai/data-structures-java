package datastructures.hash;

import datastructures.hash.examples.MyHashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test

    void testPutAndGet() {

        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("a", 1);

        map.put("b", 2);

        assertEquals(1, map.get("a"));

        assertEquals(2, map.get("b"));

    }

    @Test

    void testUpdateValue() {

        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("a", 1);

        map.put("a", 100);

        assertEquals(100, map.get("a"));

        assertEquals(1, map.size());

    }

    @Test

    void testRemove() {

        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("a", 1);

        map.put("b", 2);

        assertEquals(1, map.remove("a"));

        assertNull(map.get("a"));

        assertEquals(1, map.size());

    }

    @Test

    void testRemoveNotExists() {

        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("a", 1);

        assertNull(map.remove("x"));

        assertEquals(1, map.size());

    }

    @Test

    void testContainsKey() {

        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("a", null);

        assertTrue(map.containsKey("a")); // ✅ 关键测试

    }

    @Test

    void testNullKey() {

        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put(null, 10);

        assertEquals(10, map.get(null));

        assertTrue(map.containsKey(null));

        assertEquals(10, map.remove(null));

        assertFalse(map.containsKey(null));

    }

    @Test

    void testResize() {

        MyHashMap<Integer, String> map = new MyHashMap<>();

        for (int i = 0; i < 100; i++) {

            map.put(i, "value" + i);

        }

        assertEquals(100, map.size());

        for (int i = 0; i < 100; i++) {

            assertEquals("value" + i, map.get(i));

        }

    }

    @Test

    void testHashCollision() {

        MyHashMap<BadHashKey, String> map = new MyHashMap<>();

        BadHashKey k1 = new BadHashKey(1);

        BadHashKey k2 = new BadHashKey(2);

        BadHashKey k3 = new BadHashKey(3);

        map.put(k1, "A");

        map.put(k2, "B");

        map.put(k3, "C");

        assertEquals("A", map.get(k1));

        assertEquals("B", map.get(k2));

        assertEquals("C", map.get(k3));

        assertEquals(3, map.size());

        map.remove(k2);

        assertNull(map.get(k2));

        assertEquals("A", map.get(k1));

        assertEquals("C", map.get(k3));

    }

    // ✅ 强制哈希冲突

    static class BadHashKey {

        private final int value;

        BadHashKey(int value) {

            this.value = value;

        }

        @Override

        public int hashCode() {

            return 1;

        }

        @Override

        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || obj.getClass() != this.getClass()) return false;

            BadHashKey other = (BadHashKey) obj;

            return this.value == other.value;

        }

    }

}