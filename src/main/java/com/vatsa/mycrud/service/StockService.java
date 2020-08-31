package com.vatsa.mycrud.service;

import java.util.List;

import com.vatsa.mycrud.exception.ResourceNotFoundException;
import com.vatsa.mycrud.model.Stock;
import com.vatsa.mycrud.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class StockService {
    
    @Autowired
    private StockRepository stockRepository;

    @Cacheable(value = "stocks", key = "#root.args[0]")
    public List<Stock> getAllStocks(String logToken){
        try {
            return this.stockRepository.findAll();
        } catch (Exception e) {
            log.info("Retrieve all stocks failed with exception {}, logToken: {}",e.getMessage(), logToken);
            throw new RuntimeException(e.getMessage() + " logToken: " + logToken , e) ;
        }
    }

    // get Stock ny id
    @Cacheable(value = "stocks", key = "#root.args[0]")
    public Stock getStockByID(Long stockId, String logToken) throws ResourceNotFoundException {
        //System.out.println("getting");
        try {
            return stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId + " logToken: " + logToken) );            
        } catch (Exception e) {
            log.info("Retrieve stock failed with exception {}, logToken: {}",e.getMessage(), logToken);
            throw e;
        }

    }

    // save Stock
    public Stock createStock(Stock stock, String logToken){
        try {
            return this.stockRepository.save(stock);            
        } catch (Exception e) {
            log.info("Create stock failed with exception {}, logToken: {}", e.getMessage(), logToken);
            throw new RuntimeException(e.getMessage()+ " logToken: " + logToken, e );
        }
        

    }
    
    // update Stock
    @CachePut(value = "stocks", key = "#root.args[0]")
    public Stock updateStock(Long stockId, Stock stockDetails, String logToken) throws ResourceNotFoundException{
        try {
            Stock stock = stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId + " logToken: " + logToken) );
            stock.setValue(stockDetails.getValue());
            return this.stockRepository.save(stock);
    
        } catch (Exception e) {
            log.info("Update stock failed with exception {}, logToken: {}", e.getMessage(), logToken);
            throw e;
        }
    }
    
    // delete Stock 
    @CacheEvict(value = "stocks", key = "#root.args[0]")
    public void deleteStock(Long stockId, String logToken) throws ResourceNotFoundException{
        try {
            Stock stock = stockRepository.findById(stockId).orElseThrow(()-> new ResourceNotFoundException("Stock Not Found with ID:  " + stockId + " logToken: " + logToken) );            
            this.stockRepository.delete(stock);

        } catch (Exception e) {
            log.info("Delete stock failed with exception {}, logToken: {}", e.getMessage(), logToken);
            throw e;
        }
        
    }
}