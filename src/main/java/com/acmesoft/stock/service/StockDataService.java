package com.acmesoft.stock.service;

import com.acmesoft.stock.model.StockQuote;
import com.acmesoft.stock.model.StockQuoteRequest;
import org.mule.api.annotations.param.Payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StockDataService {

    static Double getRandomDouble() {
        Random r = new Random();
        return 1.0 + (50.0 - 1.0) * r.nextDouble();
    }

    public List<StockQuote> getQuote(@Payload StockQuoteRequest request) {

        List<StockQuote> quotes = new ArrayList<StockQuote>();

        for (int i = 0; i < 5; i++) {
            StockQuote quote = new StockQuote();
            quote.setAdjustedClose(getRandomDouble());
            quote.setClose(getRandomDouble());
            quote.setDate(new Date());
            quote.setLow(getRandomDouble());
            quote.setHigh(getRandomDouble());
            quote.setOpen(getRandomDouble());
            quote.setSymbol(request.getSymbol());
            quote.setVolume(getRandomDouble().longValue());

            quotes.add(quote);
        }

        return quotes;
    }
}
