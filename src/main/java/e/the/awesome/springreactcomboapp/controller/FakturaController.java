package e.the.awesome.springreactcomboapp.controller;

import e.the.awesome.springreactcomboapp.model.ApiResponse;
import e.the.awesome.springreactcomboapp.model.User;
import e.the.awesome.springreactcomboapp.model.UserDto;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaDto;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaIM;
import e.the.awesome.springreactcomboapp.model.faktury.FakturaPagingDto;
import e.the.awesome.springreactcomboapp.service.FakturaService;
import e.the.awesome.springreactcomboapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/invoices")
public class FakturaController {

    private final FakturaService fakturaService;

    public FakturaController(FakturaService fakturaService) {
        this.fakturaService = fakturaService;
    }

    @GetMapping
    public ApiResponse<FakturaPagingDto> listFaktura(@RequestParam(defaultValue = "0") int pageNo,
                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                  @RequestParam(defaultValue = "true") boolean sortAsc){
        return new ApiResponse<>(HttpStatus.OK.value(), "Invoice list fetched successfully.",fakturaService.findAll(pageNo, pageSize, sortBy, sortAsc));
    }

    @GetMapping("/{id}")
    public ApiResponse<FakturaIM> getById(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",fakturaService.findById(id));
    }

    @PostMapping
    public ApiResponse<FakturaDto> save(@RequestBody FakturaIM faktura){
        return new ApiResponse<>(HttpStatus.OK.value(), "Invoice saved successfully.",fakturaService.save(faktura));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        fakturaService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Invoice deleted successfully.", null);
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> update(@PathVariable int id, @RequestBody FakturaIM faktura) {
        faktura.setId(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Invoice updated successfully.",fakturaService.update(faktura));
    }
}
