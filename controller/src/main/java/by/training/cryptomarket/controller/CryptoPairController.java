package by.training.cryptomarket.controller;

import by.training.cryptomarket.entity.mapping.TraidingCouple;
import by.training.cryptomarket.service.CryptoPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/cryptopairservice")
@RestController
public class CryptoPairController {

    @Autowired
    CryptoPairService cryptoPairService;

    @RequestMapping(path = "/getallpairs")
    public List<TraidingCouple> getAllPairs() throws Exception {

        return cryptoPairService.getAllPairs();
    }


    @RequestMapping(path = "/getactivepairs")
    public List<TraidingCouple> getActivePairs() throws Exception {
        return cryptoPairService.getActivePairs();
    }



    @RequestMapping(path = "/togglepair")
    public Integer togglePair(@RequestParam(value = "id", required = true)String id) throws Exception {
        cryptoPairService.togglePair(id);
        return 42;
    }


}
