package e.the.awesome.springreactcomboapp.model.faktury;

import java.util.List;

public class OdberatelPagingDto {
    private List<OdberatelDto> odberatele;
    private int totalCount;

    public OdberatelPagingDto(List<OdberatelDto> odberatele, int totalCount) {
        this.odberatele = odberatele;
        this.totalCount = totalCount;
    }

    public OdberatelPagingDto() {

    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<OdberatelDto> getOdberatele() {
        return odberatele;
    }

    public void setOdberatele(List<OdberatelDto> odberatele) {
        this.odberatele = odberatele;
    }
}
