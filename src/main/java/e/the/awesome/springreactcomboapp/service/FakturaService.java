package e.the.awesome.springreactcomboapp.service;

import e.the.awesome.springreactcomboapp.model.User;
import e.the.awesome.springreactcomboapp.model.UserDto;
import e.the.awesome.springreactcomboapp.model.faktury.Faktura;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaDto;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaIM;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaPagingDto;

import java.util.List;

public interface FakturaService {

    FakturaDto save(FakturaIM fakturaIM);
    FakturaDto update(FakturaIM fakturaIM);
    void delete(int id);
    FakturaIM findById(int id);
    FakturaPagingDto findAll(int pageNo, int pageSize, String sortBy, boolean sortAsc);



}
