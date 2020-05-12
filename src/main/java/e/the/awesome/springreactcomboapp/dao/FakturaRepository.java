package e.the.awesome.springreactcomboapp.dao;

import e.the.awesome.springreactcomboapp.model.faktury.Faktura;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakturaRepository extends PagingAndSortingRepository<Faktura, Integer> {
}
