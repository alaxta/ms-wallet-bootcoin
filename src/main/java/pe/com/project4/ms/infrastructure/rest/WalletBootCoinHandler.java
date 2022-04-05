package pe.com.project4.ms.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.project4.ms.application.SaveWalletBootCoinUseCase;
import pe.com.project4.ms.application.SendMoneyWalletBootCoinUseCase;
import pe.com.project4.ms.application.repository.WalletBootCoinRepository;
import pe.com.project4.ms.domain.WalletBootCoin;
import pe.com.project4.ms.infrastructure.rest.request.CreateAccountRequest;
import pe.com.project4.ms.infrastructure.rest.request.SendMoneyRequest;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletBootCoinHandler {

    private final SaveWalletBootCoinUseCase saveWalletBootCoinService;
    private final SendMoneyWalletBootCoinUseCase sendMoneyWalletBootCoinUseCase;
    private final WalletBootCoinRepository walletBootCoinRepository;

    public Mono<ServerResponse> postWalletBootCoin(ServerRequest serverRequest) {
        log.info("==> BC LLego");
        return serverRequest.bodyToMono(CreateAccountRequest.class)
                .map(saveWalletBootCoinService::createAccount)
                .flatMap(respuesta -> this.toServerResponse(respuesta, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> getBootCoinPhone(ServerRequest request) {
        Mono<WalletBootCoin> BootCoin = request.queryParam("phoneNumber")
                .map(walletBootCoinRepository::findByPhoneNumber)
                .orElseGet(Mono::empty);
        return this.toServerResponse(BootCoin, HttpStatus.OK);
    }

    public Mono<ServerResponse> postTransaction(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SendMoneyRequest.class)
                .map(sendMoneyWalletBootCoinUseCase::sendMoney)
                .flatMap(walletBootCoin -> this.toServerResponse(walletBootCoin, HttpStatus.CREATED));
    }

    private Mono<ServerResponse> toServerResponse(CorePublisher<WalletBootCoin> BootCoinMono, HttpStatus status) {
        log.info("==> Antes de responder {} " + BootCoinMono.toString());
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BootCoinMono, WalletBootCoin.class);
    }

}
