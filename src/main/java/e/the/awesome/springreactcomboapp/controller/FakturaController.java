package e.the.awesome.springreactcomboapp.controller;

import e.the.awesome.springreactcomboapp.model.ApiResponse;
import e.the.awesome.springreactcomboapp.model.SortingDto;
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

@RestController
@RequestMapping("/invoices")
public class FakturaController {

    private final FakturaService fakturaService;

    public FakturaController(FakturaService fakturaService) {
        this.fakturaService = fakturaService;
    }

    @PostMapping
    public ApiResponse<FakturaPagingDto> listFaktura(@RequestParam(defaultValue = "0") int pageNo,
                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                  @RequestBody List<SortingDto> sortingDtoList){
        return new ApiResponse<>(HttpStatus.OK.value(), "Invoice list fetched successfully.",fakturaService.findAll(pageNo, pageSize, sortingDtoList));
    }

    @GetMapping("/{id}")
    public ApiResponse<FakturaIM> getById(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Invoice fetched successfully.",fakturaService.findById(id));
    }

    @GetMapping("/between/{year}")
    public ApiResponse<FakturaIM> getByYear(@PathVariable int year){
        return new ApiResponse<>(HttpStatus.OK.value(), "Invoice info fetched successfully.",fakturaService.findByYear(year));
    }

    @PostMapping("/save")
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
