package com.acmesoft.stock.model;

import com.acmesoft.stock.SerializationException;
import com.acmesoft.stock.model.serialization.protobuf.StockQuoteBuffer;
import com.acmesoft.stock.model.serialization.protobuf.StockQuoteResponseBuffer;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockQuote implements Serializable {

    String symbol;

    Date date;

    Double open;

    Double high;

    Double low;

    Double close;

    Long volume;

    Double adjustedClose;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(Double adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public static StockQuoteResponseBuffer toProtocolBuffer(List<StockQuote> quotes) {
        StockQuoteResponseBuffer.Builder responseBuilder = StockQuoteResponseBuffer.newBuilder();

        for (StockQuote quote : quotes) {
            responseBuilder.addResult(StockQuoteBuffer.newBuilder()
                    .setAdjustedClose(quote.getAdjustedClose())
                    .setClose(quote.getClose())
                    .setDate(quote.getDate().getTime())
                    .setHigh(quote.getHigh())
                    .setLow(quote.getLow())
                    .setOpen(quote.getOpen())
                    .setSymbol(quote.getSymbol())
                    .setVolume(quote.getVolume()).build());
        }
        return responseBuilder.build();
    }

    public static List<StockQuote> listOfStockQuotesFromBytes(byte[] bytes) {
        List<StockQuoteBuffer> buffer;
        try {
            buffer = StockQuoteResponseBuffer.parseFrom(bytes).getResultList();
        } catch (InvalidProtocolBufferException e) {
            throw new SerializationException(e);
        }

        List<StockQuote> quotes = new ArrayList<StockQuote>();

        for (StockQuoteBuffer stockQuoteBuffer : buffer) {
            StockQuote stockQuote = new StockQuote();
            stockQuote.setClose(stockQuoteBuffer.getClose());
            stockQuote.setDate(new Date(stockQuoteBuffer.getDate()));
            stockQuote.setHigh(stockQuoteBuffer.getHigh());
            stockQuote.setOpen(stockQuoteBuffer.getOpen());
            stockQuote.setSymbol(stockQuoteBuffer.getSymbol());
            stockQuote.setVolume(stockQuoteBuffer.getVolume());
            stockQuote.setAdjustedClose(stockQuoteBuffer.getAdjustedClose());
            stockQuote.setLow(stockQuoteBuffer.getLow());
            quotes.add(stockQuote);
        }

        return quotes;
    }


}
