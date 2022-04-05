package pe.com.project4.ms.infrastructure.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.com.project4.ms.application.repository.WalletBootCoinRepository;
import pe.com.project4.ms.domain.WalletBootCoin;
import pe.com.project4.ms.infrastructure.persistence.model.WalletBootCoinDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class WalletBootCoinReactiveMongoRepository implements WalletBootCoinRepository {

    private final IWalletBootCoinReactiveMongoRepository walletBootCoinReactiveMongoRepository;

    @Override
    public Mono<WalletBootCoin> save(WalletBootCoin walletBootCoin) {
        log.info("==> Dentro del repository {}", walletBootCoin);
        return walletBootCoinReactiveMongoRepository.save(new WalletBootCoinDao(walletBootCoin))
                .map(WalletBootCoinDao::towalletBootCoin)
                .doOnNext(result -> log.info("==> Despues del map {}", result));
    }

    @Override
    public Mono<WalletBootCoin> findByPhoneNumber(String phone) {
        log.info("==> llega a buscar {}", phone);
        return walletBootCoinReactiveMongoRepository
                .findByPhoneNumber(phone)
                .map(WalletBootCoinDao::towalletBootCoin)
                .doOnNext(result -> log.info("==> llega a encontrar {}", result));
    }

    @Override
    public Flux<WalletBootCoin> saveAll(Flux<WalletBootCoin> walletAccounts) {
        return walletBootCoinReactiveMongoRepository.saveAll(walletAccounts.map(WalletBootCoinDao::new))
                .map(WalletBootCoinDao::towalletBootCoin)
                .doOnNext(result -> log.info("==> Despues del map {}", result));
    }


}
