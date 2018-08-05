package com.sergon146.core.repository;

import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.repository.ExchangeRepository;
import com.sergon146.core.api.ApiService;

import io.reactivex.Observable;

public class ExchangeRepositoryImpl implements ExchangeRepository {

    private final ApiService apiService;

    public ExchangeRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<ExchangeRate> getExchangeRate(String in, String out) {
        String url = in + "_" + out;
        return apiService.getPage(url).map(outPut ->
                new ExchangeRate(in, out, outPut.string()));
    }
}
