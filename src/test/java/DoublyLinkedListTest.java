import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class DoublyLinkedListTest {

    @Test
    public void testAdd() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1, 4, 2, 8);

        assertEquals(4, list.size());
    }

    @Test
    public void testSize() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1, 4);

        assertEquals(2, list.size());
    }

    @Test
    public void testIsEmpty() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1, 3, 5, 7, 9);

        assertFalse(list::isEmpty);

        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    public void testClear() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(2);

        assertEquals(1, list.size());

        list.clear();

        assertTrue(list.isEmpty());

        assertEquals(0, list.size());
    }

    @Test
    public void testPeekFirst() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(8, 5, 3);

        assertEquals(8, list.peekFirst());
    }

    @Test
    public void testAddFirst() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1, 3);
        list.addFirst(5);

        assertEquals(5, list.peekFirst());
    }

    @Test
    public void testPeekLast() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(2, 4, 6);

        assertEquals(6, list.peekLast());

        list.add(3);

        assertEquals(3, list.peekLast());
    }

    @Test
    public void testAddLast() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1, 3, 5, 9);
        list.addLast(17);

        assertEquals(17, list.peekLast());

        list.addLast(12);

        assertEquals(12, list.peekLast());
    }

    @Test
    public void testRemoveFirst() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(8, 6, 4, 2, 0);

        assertEquals(8, list.removeFirst());

        assertEquals(6, list.removeFirst());
    }

    @Test
    public void testRemoveLast() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(8, 6, 4, 2, -2);

        assertEquals(-2, list.removeLast());

        assertEquals(2, list.removeLast());
    }

    @Test
    public void testRemoveAt() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(8, 6, 4, -2, 2, 10);

        assertEquals(4, list.removeAt(2));

        assertEquals(-2, list.removeAt(2));

        assertEquals(4, list.size());
    }

    @Test
    public void test_RemoveAt_ThrowsIndexOutOfBoundsException() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1, 3, 5, 8);

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(4));
    }

    @Test
    public void testRemove() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(8, 4, 6, 9, 10);

        assertTrue(list.remove(6));

        assertFalse(list.remove(2));

        assertTrue(list.remove(4));

        assertEquals(3, list.size());
    }

    @Test
    public void testIndexOf() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(10, 4, 8, 6, 2);

        assertEquals(2, list.indexOf(8));

        assertEquals(3, list.indexOf(6));
    }

    @Test
    public void testContains() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(2, -1, 0, 8, 10);

        assertFalse(list.contains(9));

        assertTrue(list.contains(-1));
    }

    @Test
    public void testGetAt() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(2, -1, 0, 8, 10);

        assertEquals(0, list.getAt(2));

        assertEquals(10, list.getAt(4));
    }

    @Test
    public void testIterator() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(8, 6, 4, 2, 1);

        int index = 0;

        for (Integer item : list) {
            assertEquals(item, list.getAt(index++));
        }
    }

    @Test
    public void testToString() {
        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(5, 3, 7, 11, 9);

        assertEquals("[5, 3, 7, 11, 9]", list.toString());

        final DoublyLinkedList<String> strList = new DoublyLinkedList<>();
        strList.add("Lorem", "Ipsum", "Dolor", "Met");

        assertEquals("[Lorem, Ipsum, Dolor, Met]", strList.toString());
    }
}
