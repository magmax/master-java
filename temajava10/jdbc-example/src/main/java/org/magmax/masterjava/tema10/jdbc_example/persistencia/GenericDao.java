package org.magmax.masterjava.tema10.jdbc_example.persistencia;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao <T, PK extends Serializable>{
    PK create(T newInstance) throws SQLException;

    T read(PK id) throws SQLException;

    void update(T transientObject) throws SQLException;

    void delete(T persistentObject) throws SQLException;

    List<T> findAll() throws SQLException;
}
