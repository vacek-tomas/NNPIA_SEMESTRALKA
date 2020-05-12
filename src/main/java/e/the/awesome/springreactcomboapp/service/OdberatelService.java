package e.the.awesome.springreactcomboapp.service;

import e.the.awesome.springreactcomboapp.model.faktury.*;

import java.sql.SQLException;

public interface OdberatelService {
    OdberatelDto save(OdberatelIM odberatelIM);
    OdberatelDto update(int id, OdberatelIM odberatelIM);
    void delete(int id) throws SQLException, NullPointerException;
    OdberatelIM findById(int id) throws NullPointerException;
    OdberatelPagingDto findAll(int pageNo, int pageSize, String sortBy, boolean sortAsc);
}
