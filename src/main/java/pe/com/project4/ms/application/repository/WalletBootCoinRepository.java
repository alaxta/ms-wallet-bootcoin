package pe.com.project4.ms.application.repository;

import pe.com.project4.ms.domain.WalletBootCoin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WalletBootCoinRepository {
    Mono<WalletBootCoin> save(WalletBootCoin walletBootCoin);

    Mono<WalletBootCoin> findByPhoneNumber(String phone);

    Flux<WalletBootCoin> saveAll(Flux<WalletBootCoin> walletAccounts);
}
