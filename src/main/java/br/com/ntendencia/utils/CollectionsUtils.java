package br.com.ntendencia.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionsUtils {
    private CollectionsUtils() {
    }

    public static <T> List<T> getListFromIterable(Iterable<T> itr) {
        List<T> cltn = new ArrayList<>();
        itr.forEach(cltn::add);

        return cltn;
    }
}
