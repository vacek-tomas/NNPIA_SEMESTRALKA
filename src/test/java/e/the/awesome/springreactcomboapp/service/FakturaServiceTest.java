package e.the.awesome.springreactcomboapp.service;


import e.the.awesome.springreactcomboapp.SpringReactComboAppApplication;
import e.the.awesome.springreactcomboapp.dao.FakturaRepository;
import e.the.awesome.springreactcomboapp.model.faktury.Faktura;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaMonthInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

}
