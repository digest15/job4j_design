package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleMapTest {

    /* put */
    @Test
    public void whenPutThenGetEqualsKey() {
        SimpleMap<MockObject, Integer> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), 123);
        assertEquals(Integer.valueOf(123), map.get(new MockObject(1, 1)));
    }

    @Test
    public void whenDoublePutSameKey() {
        SimpleMap<MockObject, Integer> map = new SimpleMap<>();
        MockObject mockKey = new MockObject(1, 1);
        map.put(mockKey, 123);
        assertFalse(map.put(mockKey, 456));
    }

    @Test
    public void whenDoublePutEqualsKey() {
        SimpleMap<MockObject, Integer> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), 123);
        assertFalse(map.put(new MockObject(1, 1), 456));
    }

    @Test
    public void whenDoublePutDistinctKey() {
        SimpleMap<MockObject, Integer> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), 123);
        assertTrue(map.put(new MockObject(2, 2), 456));
    }

    @Test
    public void whenDoublePutDistinctKeyWithEqualsHasCode() {
        SimpleMap<MockObject, Integer> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), 123);
        assertFalse(map.put(new MockObject(1, 2), 456));
    }

    @Test
    public void hashTableMustBeGrow() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        IntStream.range(0, 32).forEach(k -> map.put(k, k));
    }

    @Test
    public void putAndGetNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(null, 123);
        assertSame(123, map.get(null));
    }

    @Test
    public void whenPutNullDoubleTimes() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(null, 1);
        assertFalse(map.put(null, 2));
    }

    /* get */
    @Test
    public void whenGetFromEmptyMap() {
        assertNull(new SimpleMap<String, Integer>().get("123"));
    }

    @Test
    public void getForNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(123, 123);
        assertNull(map.get(null));

        map.put(null, 123);
        assertEquals(Integer.valueOf(123), map.get(null));
    }

    @Test
    public void whenGetBySameKey() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        MockObject mockKey = new MockObject(1, 1);
        String value = "123";
        map.put(mockKey, value);
        assertSame(value, map.get(mockKey));
    }

    @Test
    public void whenGetByEqualsKey() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        String value = "123";
        map.put(new MockObject(1, 1), value);
        assertSame(value, map.get(new MockObject(1, 1)));
    }

    @Test
    public void whenGetByDistinctKey() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), "value");
        assertNull(map.get(new MockObject(2, 2)));
    }

    @Test
    public void whenGetByDistinctKeyWithEqualsHasCode() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), "value");
        assertNull(map.get(new MockObject(1, 2)));
    }

    @Test
    public void whenGetByKeyWithDistinctHasCodeButEqualsState() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), "value");
        assertNull(map.get(new MockObject(2, 1)));
    }

    /* remove */
    @Test
    public void whenRemoveFromEmptyMap() {
        assertFalse(new SimpleMap<Integer, Integer>().remove(123));
    }

    @Test
    public void removeForNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(123, 123);
        assertFalse(map.remove(null));

        map.put(null, 123);
        assertTrue(map.remove(null));
    }

    @Test
    public void whenRemoveBySameKey() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        MockObject mockKey = new MockObject(1, 1);
        map.put(mockKey, "123");
        assertTrue(map.remove(mockKey));
    }

    @Test
    public void whenRemoveByEqualsKey() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), "value");
        assertTrue(map.remove(new MockObject(1, 1)));
    }

    @Test
    public void whenRemoveByDistinctKey() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), "value");
        assertFalse(map.remove(new MockObject(2, 2)));
    }

    @Test
    public void whenRemoveByDistinctKeyWithEqualsHasCode() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), "value");
        assertFalse(map.remove(new MockObject(1, 2)));
    }

    @Test
    public void whenRemoveByKeyWithDistinctHasCodeButEqualsState() {
        SimpleMap<MockObject, String> map = new SimpleMap<>();
        map.put(new MockObject(1, 1), "value");
        assertFalse(map.remove(new MockObject(2, 1)));
    }

    /* iterator */
    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertFalse(map.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        assertEquals(Integer.valueOf(1), map.iterator().next());
        assertEquals(Integer.valueOf(1), map.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIterator() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        map.put(1, 1);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIterator() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(0, 0);
        Iterator<Integer> iterator = map.iterator();
        map.remove(0);
        iterator.next();
    }

    private static class MockObject {
        final int hashCode;
        final int state;

        public MockObject(int hashCode, int state) {
            this.hashCode = hashCode;
            this.state = state;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof MockObject) {
                return state == ((MockObject) obj).state;
            }
            return false;
        }
    }

}