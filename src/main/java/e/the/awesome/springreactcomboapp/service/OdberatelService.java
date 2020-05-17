package e.the.awesome.springreactcomboapp.service;

import e.the.awesome.springreactcomboapp.model.faktury.*;

import java.sql.SQLException;
import java.util.List;

public interface OdberatelService {
    OdberatelDto save(OdberatelIM odberatelIM);
    OdberatelDto update(int id, OdberatelIM odberatelIM);
    void delete(int id) throws SQLException, NullPointerException;
    OdberatelIM findById(int id) throws NullPointerException;
    OdberatelPagingDto findAll(int pageNo, int pageSize, String sortBy, boolean sortAsc);
    List<OdberatelVM> findByFirmaStartsWith(String firma);
    Odberatel findOdberatelById(int id) throws NullPointerException;

}
