package com.training.vladilena.model.dao;

import java.util.List;

/**
 * The {@code GenericDao} interface is used for implement
 * the main CRUD operations
 *
 * @author Vladlena Ushakova
 */
public interface GenericDao<T> extends AutoCloseable {
    /**
     * Method to insert {@link T}
     *
     * @param entity this {@code entity} will be inserted
     * @return returns {@code true} if {@link T} was inserted succeed
     * or else {@code false}
     */
    boolean create(T entity);

    /**
     * Method return {@link T} which find by {@code id}
     *
     * @param id it indicates an {@link T} {@code id} that you want to return
     * @return return {@link T} by {@code id}
     */
    T findById(long id);

    /**
     * Method to get all {@link T}
     *
     * @return return {@link List} of all {@link T}
     */
    List<T> findAll();

    /**
     * Method update {@link T}
     *
     * @param entity the {@code entity} will be updated if it already exists
     * @return returns {@code true} if the {@code entity} was updated
     * or else {@code false}
     */
    boolean update(T entity);

    /**
     * Method delete {@link T}
     *
     * @param id {@code entity} with this {@code id} will be deleted
     * @return returns {@code true} if delete was succeed
     * or else {@code false}
     */
    boolean delete(long id);
}