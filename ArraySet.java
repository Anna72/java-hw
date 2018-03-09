package ru.ifmo.rain.pilyarchuk.arrayset;
import java.util.*;

public class ArraySet<E> extends AbstractSet<E> implements SortedSet<E> {
    private List<E> array;
    private Comparator<? super E> comparator = null;

    public ArraySet() {
        this(Collections.emptyList(), null);
    }

    public ArraySet(Comparator<? super E> comp) {
        this.array = new ArrayList<>();
        this.comparator = comp;
    }

    public ArraySet(Collection<? extends E> coll) {
        this(coll, null);
    }

    public ArraySet(Collection<? extends E> coll, Comparator<? super E> comp) {

        TreeSet<E> ts = new TreeSet<>(comp);
        ts.addAll(coll);
        this.array = new ArrayList<>(ts);
        this.comparator = ts.comparator();
    }

    public ArraySet(ArraySet<E> ss) {
        this.comparator = ss.comparator();
        this.array = new ArrayList<>(ss);
    }

    private ArraySet(List<E> arra, Comparator<? super E> comp, boolean pampam) {
        // this((Collection) arra, comp);
        this.comparator = comp;
        this.array = arra;
    }

    private int findToEl(E toElement, boolean inclusive) {
        int index = Collections.binarySearch(array, toElement, comparator);
        if (index < 0) {
            index = ~index - 1;
        } else if (!inclusive) {
            --index;
        }
        return index;
    }

    private int findFromEl(E fromElement, boolean inclusive) {
        int index = Collections.binarySearch(array, fromElement, comparator);
        if (index < 0) {
            index = ~index;
        } else if (!inclusive) {
            ++index;
        }
        return index;
    }


    @Override
    public int size() {
        return array == null ? 0 : array.size();
    }

    @Override
    public boolean contains(Object o) {
        return Collections.binarySearch(array, (E) o, comparator) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.unmodifiableList(array).iterator();
    }

    private E get(int i) {
        if (i >= size() || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        return array.get(i);
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    // sorted set
    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return headSet(toElement).tailSet(fromElement);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        int toI = findToEl(toElement, false) + 1;
        return new ArraySet<>(array.subList(0, toI), comparator, false);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        int fromI = findFromEl(fromElement, true);
        return new ArraySet<>(array.subList(fromI, array.size()), comparator, false);
    }

    @Override
    public E first() {
        try {
            return get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E last() {
        try {
            return get(size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }
}

 /* public  class DescendingList<E> extends ArrayList<E>  {
        private ArrayList<E> array;
        private boolean b;

        public DescendingList(ArrayList<E> arra) {
            array = arra;
        }

        @Override
        public E get(int index) {
            return b ? array.get(size() - 1 - index) : array.get(index);
        }

        @Override
        public int size() {
            return array.size();
        }

        public void reverse(){
          b = !b;
        }
    }*/

// descending set
 /*   @Override
    public NavigableSet<E> descendingSet() {
       /* return new ArraySet<>(array instanceof DescendingList ?
                ((DescendingList<E>) array).array :
                new DescendingList<>(array), Collections.reverseOrder(comparator));*/
//return new ArraySet<>(array, Collections.reverseOrder(comparator));
// return new ArraySet<>(new DescendingList<>(array), Collections.reverseOrder(comparator)  );
//}

    /*@Override
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }*/

    /* @Override
     public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
         int fromI = find_from_el(fromElement, fromInclusive);
         int toI = find_to_el(toElement, toInclusive) + 1;
         if (toI < fromI) {
             toI = fromI;
         }
         return new ArraySet<>(array.subList(fromI, toI), comparator);
     }
     @Override
     public NavigableSet<E> headSet(E toElement, boolean inclusive) {
         int toI = find_to_el(toElement, inclusive) + 1;
         return new ArraySet<>(array.subList(0, toI), comparator);
     }
     @Override
     public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
         int fromI = find_from_el(fromElement, inclusive);
         return new ArraySet<>(array.subList(fromI, array.size()), comparator);
     }
 */

    /*   @Override
    public E lower(E e) {
        int i = find_to_el(e, false);
        return i >= 0 ? array.get(i) : null;
    }

    @Override
    public E floor(E e) {
        int i = find_to_el(e, true);
        return i >= 0 ? array.get(i) : null;
    }

    @Override
    public E ceiling(E e) {
        int i = find_from_el(e, true);
        return i < array.size() ? array.get(i) : null;
    }

    @Override
    public E higher(E e) {
        int index = find_from_el(e, false);
        return index < array.size() ? array.get(index) : null;
    }

    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E pollLast() {
        throw new UnsupportedOperationException();
    }*/

