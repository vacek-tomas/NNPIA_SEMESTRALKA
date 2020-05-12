package e.the.awesome.springreactcomboapp.model.faktury;

import java.util.List;

public class FakturaPagingDto {
    private List<FakturaDto> faktury;
    private int totalCount;

    public FakturaPagingDto(List<FakturaDto> faktury, int totalCount) {
        this.faktury = faktury;
        this.totalCount = totalCount;
    }

    public FakturaPagingDto() {

    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<FakturaDto> getFaktury() {
        return faktury;
    }

    public void setFaktury(List<FakturaDto> faktury) {
        this.faktury = faktury;
    }
}
