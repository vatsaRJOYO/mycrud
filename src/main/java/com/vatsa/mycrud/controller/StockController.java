package com.vatsa.mycrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.vatsa.mycrud.exception.ResourceNotFoundException;
import com.vatsa.mycrud.model.Stock;
import com.vatsa.mycrud.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class StockController {
    
    @Autowired
    private StockRepository stockRepository;
    
    // get Stock
    @GetMapping("stocks")
    public List<Stock> getAllStocks(){
        return this.stockRepository.findAll();
    }

    // get Stock ny id
    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> getStockByID(@PathVariable(value="id") Long stockId) throws ResourceNotFoundException {
        Stock stock = stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId) );

        return ResponseEntity.ok().body(stock);
    }

    // save Stock
    @PostMapping("stocks")
    public Stock createStock(@RequestBody Stock stock){
        return this.stockRepository.save(stock);

    }
    
    // update Stock
    @PutMapping("stocks/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable(value = "id") Long stockId, @Valid @RequestBody Stock stockDetails) throws ResourceNotFoundException{
        Stock stock = stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId) );
         
        stock.setValue(stockDetails.getValue());

        return ResponseEntity.ok(this.stockRepository.save(stock));

    }
    
    // delete Stock 
    @DeleteMapping("stocks/{id}")
    public Map<String, Boolean> deleteStock(@PathVariable(value = "id") Long stockId ) throws ResourceNotFoundException{
        Stock stock = stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId) );

        this.stockRepository.delete(stock);
        Map <String, Boolean> response = new HashMap<>();
        response.put("Deleted", true);
        return response;
    }

}