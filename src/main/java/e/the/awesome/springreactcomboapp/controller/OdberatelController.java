package e.the.awesome.springreactcomboapp.controller;

import e.the.awesome.springreactcomboapp.model.ApiResponse;
import e.the.awesome.springreactcomboapp.model.UserDto;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaDto;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaIM;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaPagingDto;
import e.the.awesome.springreactcomboapp.model.faktury.OdberatelIM;
import e.the.awesome.springreactcomboapp.service.FakturaService;
import e.the.awesome.springreactcomboapp.service.OdberatelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/subscribers")
public class OdberatelController {

    private final OdberatelService odberatelService;

    public OdberatelController(OdberatelService odberatelService) {
        this.odberatelService = odberatelService;
    }

    @GetMapping
    public ApiResponse<FakturaPagingDto> listOdberatel(@RequestParam(defaultValue = "0") int pageNo,
                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                  @RequestParam(defaultValue = "true") boolean sortAsc){
        return new ApiResponse<>(HttpStatus.OK.value(), "Subscriber list fetched successfully.",odberatelService.findAll(pageNo, pageSize, sortBy, sortAsc));
    }

    @GetMapping("/{id}")
    public ApiResponse<FakturaIM> getById(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Subscriber fetched successfully.",odberatelService.findById(id));
    }

    @GetMapping("/startsWith")
    public ApiResponse<FakturaIM> getByFirma(@RequestParam String firma){
        return new ApiResponse<>(HttpStatus.OK.value(), "Subscriber fetched successfully.",odberatelService.findByFirmaStartsWith(firma));
    }

    @PostMapping
    public ApiResponse<FakturaDto> save(@RequestBody OdberatelIM odberatelIM){
        return new ApiResponse<>(HttpStatus.OK.value(), "Subscriber saved successfully.",odberatelService.save(odberatelIM));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        try{
        odberatelService.delete(id);
        }
        catch (Exception e){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Subscriber deleted successfully.", null);
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> update(@PathVariable int id, @RequestBody OdberatelIM odberatelIM) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Subscriber updated successfully.",odberatelService.update(id, odberatelIM));
    }
}
