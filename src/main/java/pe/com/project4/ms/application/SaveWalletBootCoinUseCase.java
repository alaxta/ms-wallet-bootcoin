package pe.com.project4.ms.application;

import pe.com.project4.ms.domain.WalletBootCoin;
import pe.com.project4.ms.infrastructure.rest.request.CreateAccountRequest;
import reactor.core.publisher.Mono;

public interface SaveWalletBootCoinUseCase {
    Mono<WalletBootCoin> createAccount(CreateAccountRequest createAccountRequest);
}
