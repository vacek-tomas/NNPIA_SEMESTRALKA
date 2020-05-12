package e.the.awesome.springreactcomboapp.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import e.the.awesome.springreactcomboapp.dao.FakturaRepository;
import e.the.awesome.springreactcomboapp.dao.OdberatelRepository;
import e.the.awesome.springreactcomboapp.model.faktury.*;
import e.the.awesome.springreactcomboapp.service.OdberatelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "odberatelService")
public class OdberatelServiceImpl implements OdberatelService {

    @Autowired
    OdberatelRepository odberatelRepository;

    @Autowired
    FakturaRepository fakturaRepository;

    @Override
    public OdberatelDto save(OdberatelIM odberatelIM) {

        Odberatel odberatel = new Odberatel();
        BeanUtils.copyProperties(odberatelIM, odberatel);

        odberatel = odberatelRepository.save(odberatel);

        OdberatelDto odberatelDto = new OdberatelDto();
        BeanUtils.copyProperties(odberatel, odberatelDto);

        return odberatelDto;
    }

    @Override
    public OdberatelDto update(int id, OdberatelIM odberatelIM) {
        Odberatel odberatel = odberatelRepository.findById(id).get();
        BeanUtils.copyProperties(odberatelIM, odberatel);

        odberatelRepository.save(odberatel);

        OdberatelDto odberatelDto = new OdberatelDto();
        BeanUtils.copyProperties(odberatel, odberatelDto);

        return odberatelDto;
    }

    @Override
    public void delete(int id) throws SQLException, NullPointerException {
        Optional<Odberatel> optionalOdberatel = odberatelRepository.findById(id);
        if(!optionalOdberatel.isPresent()){
            throw  new NullPointerException("Odberatel not exists");
        }
        if(fakturaRepository.findByOdberatel_Id(id).size() != 0){
            throw new SQLException("Odberatel has invoices");
        }
        odberatelRepository.delete(optionalOdberatel.get());
    }

    @Override
    public OdberatelIM findById(int id) throws NullPointerException {
        Optional<Odberatel> optionalOdberatel = odberatelRepository.findById(id);
        if(!optionalOdberatel.isPresent()){
            throw new NullPointerException("Odberatel not exist.");
        }

        OdberatelIM odberatelIM = new OdberatelIM();
        BeanUtils.copyProperties(optionalOdberatel.get(), odberatelIM);

        return odberatelIM;
    }

    @Override
    public OdberatelPagingDto findAll(int pageNo, int pageSize, String sortBy, boolean sortAsc) {
        Pageable paging = PageRequest.of(pageNo, pageSize, sortAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        Page<Odberatel> pagedResult = odberatelRepository.findAll(paging);

        if(pagedResult.hasContent()){
            return new OdberatelPagingDto((pagedResult.getContent().stream().map(i -> new OdberatelDto(i.getId(),i.getFirma(),i.getIc(), i.getPsc(), i.getMesto(),i.getUlice(), i.getCisloPopisne())).collect(Collectors.toList())), ((int) pagedResult.getTotalElements()));
        }
        else{
            return new OdberatelPagingDto();
        }
    }
}
