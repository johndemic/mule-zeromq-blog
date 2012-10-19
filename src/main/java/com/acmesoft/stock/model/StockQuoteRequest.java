package com.acmesoft.stock.model;

import com.acmesoft.stock.SerializationException;
import com.acmesoft.stock.model.serialization.protobuf.StockQuoteRequestBuffer;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Date;


public class StockQuoteRequest {

    String symbol;

    Date startDate;

    Date endDate;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public byte[] toProtocolBufferAsBytes() {
        return StockQuoteRequestBuffer.newBuilder()
                .setSymbol(symbol)
                .setStart(startDate.getTime())
                .setEnd(endDate.getTime()).build().toByteArray();
    }

    public static StockQuoteRequest fromProtocolBuffer(StockQuoteRequestBuffer buffer) {

        StockQuoteRequest request = new StockQuoteRequest();
        request.setSymbol(buffer.getSymbol());
        request.setStartDate(new Date(buffer.getStart()));
        request.setEndDate(new Date(buffer.getEnd()));

        return request;
    }

    public static StockQuoteRequest fromBytes(byte[] bytes) {

        StockQuoteRequestBuffer buffer;

        try {
            buffer = StockQuoteRequestBuffer.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            throw new SerializationException(e);
        }

        StockQuoteRequest request = new StockQuoteRequest();
        request.setSymbol(buffer.getSymbol());
        request.setStartDate(new Date(buffer.getStart()));
        request.setEndDate(new Date(buffer.getEnd()));

        return request;
    }
}
