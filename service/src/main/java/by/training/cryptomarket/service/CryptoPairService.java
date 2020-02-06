package by.training.cryptomarket.service;

import by.training.cryptomarket.entity.mapping.TraidingCouple;

import java.util.List;

public interface CryptoPairService {

    List<TraidingCouple> getAllPairs() throws Exception;
    List<TraidingCouple> getActivePairs() throws Exception;
    void togglePair(final String id) throws Exception;
}
