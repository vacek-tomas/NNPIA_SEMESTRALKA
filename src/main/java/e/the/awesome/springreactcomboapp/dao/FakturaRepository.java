package e.the.awesome.springreactcomboapp.dao;

import e.the.awesome.springreactcomboapp.model.faktury.Faktura;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FakturaRepository extends PagingAndSortingRepository<Faktura, Integer> {
    List<Faktura> findByOdberatel_Id(int id);
}
