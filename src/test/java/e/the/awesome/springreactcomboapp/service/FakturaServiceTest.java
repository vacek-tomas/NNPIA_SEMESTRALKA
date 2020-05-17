package e.the.awesome.springreactcomboapp.service;


import e.the.awesome.springreactcomboapp.SpringReactComboAppApplication;
import e.the.awesome.springreactcomboapp.dao.FakturaRepository;
import e.the.awesome.springreactcomboapp.model.faktury.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {SpringReactComboAppApplication.class})
public class FakturaServiceTest {

    @MockBean
    private FakturaRepository fakturaRepository;

    @Autowired
    private FakturaService fakturaService;

    @Test
    void findByYearTest() {
        List<Faktura> mockList = new ArrayList<>();
        Faktura faktura = new Faktura();
        faktura.setEvidencniCislo("120");
        faktura.setCenaCelkem(50000);
        faktura.setDatumUzp(LocalDate.of(2020, 1, 1));
        Faktura faktura2 = new Faktura();
        faktura2.setEvidencniCislo("220");
        faktura2.setCenaCelkem(40000);
        faktura2.setDatumUzp(LocalDate.of(2020, 1, 31));
        Faktura faktura3 = new Faktura();
        faktura3.setEvidencniCislo("320");
        faktura3.setCenaCelkem(10000);
        faktura3.setDatumUzp(LocalDate.of(2020, 1, 31));

        mockList.add(faktura);
        mockList.add(faktura2);
        mockList.add(faktura3);

        when(fakturaRepository.findByDatumUzpBetween(LocalDate.of(2020,1,1), LocalDate.of(2020,12,31))).thenReturn(mockList);
        List<FakturaMonthInfo> list = fakturaService.findByYear(2020);

       assertEquals(list.get(0).getTotal(), 100000.0);
    }

    @Test
    void findByIdTest() {
        Faktura mockFaktura = new Faktura();
        mockFaktura.setEvidencniCislo("120");
        mockFaktura.setVariabilniSymbol(120);
        mockFaktura.setDatumVystaveni(LocalDate.of(2020, 1, 1));
        mockFaktura.setDatumSplatnosti(LocalDate.of(2020, 1, 15));
        mockFaktura.setDatumUzp(LocalDate.of(2020, 1, 15));

        Odberatel odberatel = new Odberatel();
        odberatel.setId(1);
        odberatel.setFirma("Firma");
        odberatel.setMesto("Pardubice");

        mockFaktura.setOdberatel(odberatel);

        Set<PolozkaFaktury> polozkyFaktury = new HashSet<>();
        PolozkaFaktury polozkaFaktury = new PolozkaFaktury();
        polozkaFaktury.setPopis("práce");
        polozkaFaktury.setCenaCelkem(20000);
        polozkyFaktury.add(polozkaFaktury);

        mockFaktura.setPolozkyFaktury(polozkyFaktury);
        mockFaktura.setCenaCelkem(20000);

        when(fakturaRepository.findById(1)).thenReturn(Optional.of(mockFaktura));

        FakturaIM fakturaIM = fakturaService.findById(1);

        assertEquals(fakturaIM.getEvidencniCislo(), mockFaktura.getEvidencniCislo());
        assertEquals(fakturaIM.getOdberatelId(), mockFaktura.getOdberatel().getId());
        assertEquals(fakturaIM.getPolozkyFaktury().stream().mapToDouble(i -> i.getCenaCelkem()).sum(), mockFaktura.getCenaCelkem());
    }
}
