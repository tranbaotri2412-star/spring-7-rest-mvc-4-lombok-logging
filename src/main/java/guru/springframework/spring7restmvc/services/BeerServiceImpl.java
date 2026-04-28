package guru.springframework.spring7restmvc.services;

import ch.qos.logback.core.util.StringUtil;
import guru.springframework.spring7restmvc.model.Beer;
import guru.springframework.spring7restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;
    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1).beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE).upc("123456")
                .quantityOnHand(122)
                .price(new BigDecimal("12.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1).beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .quantityOnHand(392)
                .price(new BigDecimal("11.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1).beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .quantityOnHand(144)
                .price(new BigDecimal("13.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(),beer1);
        beerMap.put(beer2.getId(),beer2);
        beerMap.put(beer3.getId(),beer3);
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setBeerStyle(beer.getBeerStyle());
        existing.setQuantityOnHand(beer.getQuantityOnHand());
        existing.setPrice(beer.getPrice());
        existing.setUpc(beer.getUpc());
        existing.setUpdateDate(LocalDateTime.now());

        beerMap.put(existing.getId(), existing);
    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())) {
            existing.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }
    }

    @Override
    public List<Beer> listBeers() {
        return new ArrayList<>(beerMap.values());
    }
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return beerMap.get(id);
    }
    @Override
    public Beer saveNewBeer(Beer beer) {
        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }
}
