package e.the.awesome.springreactcomboapp.service;

import e.the.awesome.springreactcomboapp.Creator;
import e.the.awesome.springreactcomboapp.SpringReactComboAppApplication
import e.the.awesome.springreactcomboapp.model.faktury.Faktura
import e.the.awesome.springreactcomboapp.model.faktury.FakturaDto
import e.the.awesome.springreactcomboapp.model.faktury.FakturaIM;
import e.the.awesome.springreactcomboapp.model.faktury.Odberatel
import e.the.awesome.springreactcomboapp.model.faktury.PolozkaFakturyDto
import e.the.awesome.springreactcomboapp.service.FakturaService
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest

import java.time.LocalDate;

@SpringBootTest
public class FakturaServiceIntegrationTest {

    @Autowired
    private Creator creator;

    @Autowired
    private FakturaService fakturaService;

    int PrepareData() {
        Odberatel odberatel = new Odberatel(firma: "Tomáš Vacek");
        return creator.saveEntity(odberatel).id;

    }

    @Test
    void createTest() {
        int odberatelId = PrepareData();
        FakturaIM fakturaIM = new FakturaIM(evidencniCislo: "120", variabilniSymbol: 120, odberatelId: odberatelId, datumUzp: LocalDate.now(),datumSplatnosti: LocalDate.now(), datumVystaveni: LocalDate.now());
        List<PolozkaFakturyDto> polozky = new ArrayList<>();
        PolozkaFakturyDto polozkaFakturyDto = new PolozkaFakturyDto();
        polozkaFakturyDto.setPopis("Práce");
        polozkaFakturyDto.setCenaCelkem(50000);
        polozky.add(polozkaFakturyDto);
        fakturaIM.setPolozkyFaktury(polozky);

        FakturaDto fakturaDto = fakturaService.save(fakturaIM);

        Assert.assertEquals(fakturaDto.evidencniCislo, fakturaIM.evidencniCislo);
        Assert.assertEquals(fakturaDto.odberatelFirma, "Tomáš Vacek");
    }

}
