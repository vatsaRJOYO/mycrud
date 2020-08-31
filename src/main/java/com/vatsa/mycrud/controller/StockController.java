package com.vatsa.mycrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.vatsa.mycrud.exception.ResourceNotFoundException;
import com.vatsa.mycrud.model.Stock;
import com.vatsa.mycrud.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class StockController {
    
    @Autowired
    private StockService stockServive;
    
    // get Stock 
    @GetMapping("stocks")
    public List<Stock> getAllStocks(){
        return this.stockServive.getAllStocks();
    }

    // get Stock by id
    @GetMapping("/stocks/{id}")
    @ResponseBody
    public Stock getStockByID(@PathVariable(value="id") Long stockId) throws ResourceNotFoundException { //ResponseEntity<Stock> 
        Stock stock = stockServive.getStockByID(stockId);
        return stock;  // return ResponseEntity.ok().body(stock);
    }

    // save Stock
    @PostMapping("stocks")
    public Stock createStock(@RequestBody Stock stock){
        return this.stockServive.createStock(stock);

    }
    
    // update Stock
    @PutMapping("stocks/{id}")
    @ResponseBody
    public Stock updateStock(@PathVariable(value = "id") Long stockId, @Valid @RequestBody Stock stockDetails) throws ResourceNotFoundException{
        Stock stock = this.stockServive.updateStock(stockId, stockDetails);
        return stock;  //ResponseEntity.ok(stock);

    }
    
    // delete Stock 
    
    @DeleteMapping("stocks/{id}")
    @ResponseBody
    public Map<String, Boolean> deleteStock(@PathVariable(value = "id") Long stockId ) throws ResourceNotFoundException{
        this.stockServive.deleteStock(stockId);
        Map <String, Boolean> response = new HashMap<>();
        response.put("Deleted", true);
        return response;
    }

}