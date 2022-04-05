package pe.com.project4.ms.application;

import pe.com.project4.ms.domain.WalletBootCoin;
import pe.com.project4.ms.infrastructure.rest.request.SendMoneyRequest;
import reactor.core.publisher.Mono;

public interface SendMoneyWalletBootCoinUseCase {
    Mono<WalletBootCoin> sendMoney(SendMoneyRequest sendMoneyRequest);
}
