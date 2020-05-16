package e.the.awesome.springreactcomboapp.service.impl;

import ch.qos.logback.classic.jmx.MBeanUtil;
import e.the.awesome.springreactcomboapp.dao.FakturaRepository;
import e.the.awesome.springreactcomboapp.dao.OdberatelRepository;
import e.the.awesome.springreactcomboapp.dao.PolozkaFakturyRepository;
import e.the.awesome.springreactcomboapp.model.User;
import e.the.awesome.springreactcomboapp.model.faktury.*;
import e.the.awesome.springreactcomboapp.service.FakturaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service(value = "fakturaService")
public class FakturaServiceImpl implements FakturaService {

    @Autowired
    FakturaRepository fakturaRepository;

    @Autowired
    OdberatelRepository odberatelRepository;

    @Autowired
    PolozkaFakturyRepository polozkaFakturyRepository;

    @Override
    public FakturaDto save(FakturaIM fakturaIM) {

        Faktura faktura = new Faktura();
        BeanUtils.copyProperties(fakturaIM, faktura);
        faktura.setOdberatel(odberatelRepository.findById(fakturaIM.getOdberatelId()).get());
        faktura.setCenaCelkem(fakturaIM.getPolozkyFaktury().stream().map(i -> i.getCenaCelkem()).reduce(0.0, Double::sum));
        Faktura finalFaktura = faktura;
        faktura.setPolozkyFaktury(fakturaIM.getPolozkyFaktury().stream().map(i -> new PolozkaFaktury(i.getId(), i.getPopis(), i.getCenaZaJednotku(), i.getJednotka(), i.getMnozstvi(),i.getCenaCelkem(), finalFaktura)).collect(Collectors.toSet()));
        faktura = fakturaRepository.save(faktura);
        polozkaFakturyRepository.saveAll(faktura.getPolozkyFaktury());
        FakturaDto fakturaDto = new FakturaDto();
        BeanUtils.copyProperties(faktura, fakturaDto);
        fakturaDto.setOdberatelFirma(faktura.getOdberatel().getFirma());
        return fakturaDto;
    }

    @Override
    public FakturaDto update(FakturaIM fakturaIM) {
        Optional<Faktura> faktura = fakturaRepository.findById(fakturaIM.getId());
        if(faktura.isPresent()) {
            BeanUtils.copyProperties(fakturaIM, faktura.get());

            if(faktura.get().getOdberatel().getId() != fakturaIM.getOdberatelId())
            {
                faktura.get().setOdberatel(odberatelRepository.findById(fakturaIM.getOdberatelId()).get());
            }
            faktura.get().setCenaCelkem(fakturaIM.getPolozkyFaktury().stream().map(i -> i.getCenaCelkem()).reduce(0.0, Double::sum));
            fakturaRepository.save(faktura.get());
            polozkaFakturyRepository.deleteAll(faktura.get().getPolozkyFaktury().stream().filter(i -> !fakturaIM.getPolozkyFaktury().stream().map(j -> j.getId()).collect(Collectors.toSet()).contains(i.getId())).collect(Collectors.toSet()));
            PolozkaFaktury polozkaFaktury;
            for (PolozkaFakturyDto polozkaFakturyDto: fakturaIM.getPolozkyFaktury()  ) {
                if(faktura.get().getPolozkyFaktury().stream().map(i -> i.getId()).collect(Collectors.toList()).contains(polozkaFakturyDto.getId())){
                    polozkaFaktury = polozkaFakturyRepository.findById(polozkaFakturyDto.getId()).get();
                }
                else{
                    polozkaFaktury = new PolozkaFaktury();
                    polozkaFaktury.setFaktura(faktura.get());
                }
                BeanUtils.copyProperties(polozkaFakturyDto, polozkaFaktury, "id");
                polozkaFakturyRepository.save(polozkaFaktury);
            }
            FakturaDto fakturaDto = new FakturaDto();
            BeanUtils.copyProperties(faktura.get(),fakturaDto);
            fakturaDto.setOdberatelFirma(faktura.get().getOdberatel().getFirma());
            return fakturaDto;
        }
        else{
            throw new NullPointerException("Invoice not exists");
        }
    }

    @Override
    public void delete(int id) {
        fakturaRepository.deleteById(id);
    }

    @Override
    public FakturaIM findById(int id) {
        Optional<Faktura> optionalFaktura = fakturaRepository.findById(id);
        if(optionalFaktura.isPresent()){
            FakturaIM fakturaIM = new FakturaIM();
            BeanUtils.copyProperties(optionalFaktura.get(), fakturaIM);
            fakturaIM.setOdberatelId(optionalFaktura.get().getOdberatel().getId());
            fakturaIM.setPolozkyFaktury(optionalFaktura.get().getPolozkyFaktury().stream().map(i -> new PolozkaFakturyDto(i.getId(),i.getPopis(),i.getCenaZaJednotku(),i.getJednotka(),i.getMnozstvi(),i.getCenaCelkem())).collect(Collectors.toList()));
            return  fakturaIM;
        }
        else{
            return null;
        }
    }

    @Override
    public List<FakturaMonthInfo> findByYear(int year) {


        List<Faktura> faktury = fakturaRepository.findByDatumUzpBetween(LocalDate.of(year,1,1), LocalDate.of(year,12,31));
        Collections.sort(faktury, (o1, o2) -> o1.getDatumUzp().compareTo(o2.getDatumUzp()));
        List<FakturaMonthInfo> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++)
        {
            LocalDate start = LocalDate.of(year, i, 1);
            LocalDate end = i != 12 ? LocalDate.of(year, i + 1, 1) : LocalDate.of(year + 1, 1, 1);
            list.add(new FakturaMonthInfo(i, faktury.stream().filter(f -> f.getDatumUzp().compareTo(start) >= 0 && f.getDatumUzp().compareTo(end) < 0).mapToDouble(f -> f.getCenaCelkem()).sum()));
        }

        return list;
    }

    @Override
    public FakturaPagingDto findAll(int pageNo, int pageSize, String sortBy, boolean sortAsc) {
        Pageable paging = PageRequest.of(pageNo, pageSize, sortAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Page<Faktura> pagedResult = fakturaRepository.findAll(paging);

        if(pagedResult.hasContent()){
            return new FakturaPagingDto((pagedResult.getContent().stream().map(i -> new FakturaDto(i.getId(),i.getEvidencniCislo(),i.getVariabilniSymbol(),i.getDatumVystaveni(), i.getDatumUzp(), i.getDatumSplatnosti(),i.getOdberatel().getFirma(), i.getCenaCelkem())).collect(Collectors.toList())), ((int) pagedResult.getTotalElements()));
        }
        else{
            return new FakturaPagingDto();
        }

    }
}
