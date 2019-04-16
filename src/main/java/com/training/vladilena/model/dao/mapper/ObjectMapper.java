package com.training.vladilena.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
/**
 * The {@code ObjectMapper} interface for mapping the entities
 *
 * @author Vladlena Ushakova
 */
public interface ObjectMapper <T> {
    /**
     * Method to parse {@link T} from {@link ResultSet}
     *
     * @return return {@link T}
     */
    T parseFromResultSet(ResultSet rs) throws SQLException;
    /**
     * Method to make {@link T} unique
     *
     * @param cache is a {@link Map} which contains unique entities
     * @param entity ia an {@code entity} which supposed to be unique
     * @return return {@link T} if it's not exist or link to the existed entity
     */
    T makeUnique(Map<Long, T> cache, T entity);
}
