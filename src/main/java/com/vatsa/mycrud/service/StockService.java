package com.vatsa.mycrud.service;

import java.util.List;

import com.vatsa.mycrud.exception.ResourceNotFoundException;
import com.vatsa.mycrud.model.Stock;
import com.vatsa.mycrud.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks(){
        return this.stockRepository.findAll();
    }

    // get Stock ny id
    
    public Stock getStockByID(Long stockId) throws ResourceNotFoundException {
        return stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId) );

    }

    // save Stock
    public Stock createStock(Stock stock){
        return this.stockRepository.save(stock);

    }
    
    // update Stock
    public Stock updateStock(Long stockId, Stock stockDetails) throws ResourceNotFoundException{
        Stock stock = stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId) );
         
        stock.setValue(stockDetails.getValue());
        return this.stockRepository.save(stock);
    }
    
    // delete Stock 

    public void deleteStock(Long stockId) throws ResourceNotFoundException{
        Stock stock = stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId) );

        this.stockRepository.delete(stock);
        
    }
}