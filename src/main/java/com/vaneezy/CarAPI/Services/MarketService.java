package com.vaneezy.CarAPI.Services;

import com.vaneezy.CarAPI.DAO.MarketDAO.MarketDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class MarketService {

    private final MarketDAO marketDAO;

    public List<Market> getAllMarkets() {
        return marketDAO.getAll();
    }

    public void createMarket(Market market) {
        marketDAO.save(market);
    }

    public void deleteMarket(Long id) {
        marketDAO.delete(id);
    }
}
