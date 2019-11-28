package raivio.kaappo.snake.misc;

import java.util.Objects;

public class Pair <K, V> {
    final protected K key;
    final protected V value;

    public Pair (K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey () {
        return key;
    }

    public V getValue () {
        return value;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return key.equals(pair.key) &&
                value.equals(pair.value);
    }

    @Override
    public int hashCode () {
        return Objects.hash(key, value);
    }
}