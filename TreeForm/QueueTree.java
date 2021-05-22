package Tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;

public class QueueTree <T> implements Queue<T> {

    private QueueElement first, last;
    private int size = 0;

    private class QueueElement{
        private final T value;
        private QueueElement next;
        private QueueElement prev;
        private QueueElement(T value, QueueElement next, QueueElement prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private class QueueIterator implements Iterator<T> {

        private final QueueElement starter = new QueueElement(null, first, null);
        private QueueElement current = starter;
        private boolean removed = false;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            removed = false;
            return current.value;
        }

        @Override
        public void remove() {
            if(current == starter || removed)
                throw new RuntimeException("?????????? ?????????? ???????? ?? ???????? ???????");
            if(current == first) {
                first = current.next;
                if(first != null) first.prev = null;
                removed = true;
            }
            if(current == last) {
                last = current.prev;
                if(last != null) last.next = null;
                removed = true;
            }
            if(!removed) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                removed = true;
            }
            size--;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(T v : this)
            if(Objects.equals(o, v)) return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] ar = new Object[size];
        Iterator<T> it = iterator();
        for(int i = 0; i < ar.length && i < size; i++)
            ar[i] = it.next();
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> E[] toArray(E[] a) {
        if(size < a.length)
            return (E[]) toArray();
        Iterator<T> it = iterator();
        for(int i = 0; i < a.length && i < size; i++)
            a[i] = (E) it.next();
        return null;
    }

    @Override
    public boolean remove(Object o) {
        Iterator<T> it = iterator();
        while(it.hasNext()) {
            T v = it.next();
            if(Objects.equals(o, v)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object cv : c)
            if(contains(cv)) return true;
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T cv : c)
            add(cv);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean f = true;
        for(Object cv : c)
            if(!remove(cv)) f = false;
        return f;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        removeIf(v -> !c.contains(v));
        return true;
    }

    @Override
    public void clear() {
        Iterator<T> it = iterator();
        while(it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    @Override
    public synchronized boolean add(T e) {
        QueueElement el = new QueueElement(e, null, last);
        if(isEmpty())
            first = el;
        else
            last.next = el;
        last = el;
        size++;
        return true;
    }

    @Override
    public boolean offer(T e) {
        return add(e);
    }

    @Override
    public synchronized T remove() {
        Iterator<T> it = iterator();
        T v = it.next();
        it.remove();
        return v;
    }

    @Override
    public T poll() {
        if(isEmpty())
            return null;
        return remove();
    }

    @Override
    public synchronized T element() {
        return first.value;
    }

    @Override
    public T peek() {
        if(isEmpty())
            return null;
        return element();
    }

}
