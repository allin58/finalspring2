package by.training.cryptomarket.daojdbctemplate;
import by.training.cryptomarket.entity.Entity;
import by.training.cryptomarket.exception.PersistentException;

import java.util.List;

/**
 * Interface for Dao.
 *
 * @author Nikita Karchahin
 * @param <Type> this is type of entity
 * @version 1.0
 */
public interface  Dao<Type extends Entity> {

    /**
     * This method creates a new record in a database.
     * @param entity entity
     * @return number of record in the database
     * @throws PersistentException PersistentException
     */
    Integer create(Type entity) throws PersistentException;


    /**
     * This method returns entity by id.
     * @param identity identity
     * @return entity
     * @throws PersistentException PersistentException
     */
    Type read(Integer identity) throws PersistentException;

    /**
     * This method returns all entity.
     * @return collection of entities
     * @throws PersistentException PersistentException
     */
    List<Type> read() throws PersistentException;

    /**
     * This method updates a record.
     * @param entity entity
     * @throws PersistentException PersistentException
     */
    void update(Type entity) throws PersistentException;

    /**
     * This method deletes a record.
     * @param identity identity
     * @throws PersistentException PersistentException
     */
    void delete(Integer identity) throws PersistentException;

}
