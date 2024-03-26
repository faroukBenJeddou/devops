package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class testStock {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddStock() {
        // Given
        Stock stock = new Stock(); // Create a sample stock object
        when(stockRepository.save(stock)).thenReturn(stock);

        // When
        Stock savedStock = stockService.addStock(stock);

        // Then
        assertNotNull(savedStock); // Check that the saved stock is not null
        assertEquals(stock, savedStock); // Check that the saved stock is the same as the input stock
        verify(stockRepository, times(1)).save(stock); // Verify that the save method of the repository was called once with the correct argument
    }

    @Test
    void testRetrieveStock() {
        // Given
        Long id = 1L;
        Stock stock = new Stock(); // Create a sample stock object
        when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        // When
        Stock retrievedStock = stockService.retrieveStock(id);

        // Then
        assertNotNull(retrievedStock); // Check that the retrieved stock is not null
        assertEquals(stock, retrievedStock); // Check that the retrieved stock is the same as the expected stock
        verify(stockRepository, times(1)).findById(id); // Verify that the findById method of the repository was called once with the correct argument
    }

    @Test
    void testRetrieveAllStock() {
        // Given
        List<Stock> stocks = new ArrayList<>(); // Create a list of sample stocks
        stocks.add(new Stock());
        stocks.add(new Stock());
        when(stockRepository.findAll()).thenReturn(stocks);

        // When
        List<Stock> retrievedStocks = stockService.retrieveAllStock();

        // Then
        assertNotNull(retrievedStocks); // Check that the retrieved list of stocks is not null
        assertEquals(stocks.size(), retrievedStocks.size()); // Check that the retrieved list has the same size as the expected list
        verify(stockRepository, times(1)).findAll(); // Verify that the findAll method of the repository was called once
    }
}
