package e.the.awesome.springreactcomboapp.dao;

import e.the.awesome.springreactcomboapp.model.faktury.Odberatel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdberatelRepository extends PagingAndSortingRepository<Odberatel, Integer> {
}
