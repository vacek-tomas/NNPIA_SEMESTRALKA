package e.the.awesome.springreactcomboapp.dao;

import e.the.awesome.springreactcomboapp.model.faktury.PolozkaFaktury;
import org.springframework.data.repository.CrudRepository;

public interface PolozkaFakturyRepository extends CrudRepository<PolozkaFaktury, Integer> {
}
