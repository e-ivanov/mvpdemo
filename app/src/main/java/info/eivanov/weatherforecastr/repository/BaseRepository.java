package info.eivanov.weatherforecastr.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by killer on 9/2/17.
 */

public class BaseRepository<K, V> {
    protected final Map<K, V> cache = new HashMap<>();
}
