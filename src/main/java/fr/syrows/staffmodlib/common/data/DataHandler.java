package fr.syrows.staffmodlib.common.data;

public interface DataHandler<T> {

    void save(T player);

    void clear(T player);

    void restore(T player);
}
