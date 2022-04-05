package pe.com.project4.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project4.ms.application.SendMoneyWalletBootCoinUseCase;
import pe.com.project4.ms.application.repository.WalletBootCoinRepository;
import pe.com.project4.ms.domain.WalletBootCoin;
import pe.com.project4.ms.infrastructure.event.SendMoneyEvent;
import pe.com.project4.ms.infrastructure.producer.WalletSendMoneyProducer;
import pe.com.project4.ms.infrastructure.rest.request.SendMoneyRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMoneyWalletBootCoinService implements SendMoneyWalletBootCoinUseCase {

    private final WalletBootCoinRepository walletBootCoinRepository;
    private final WalletSendMoneyProducer walletSendMoneyProducer;

    @Override
    public Mono<WalletBootCoin> sendMoney(SendMoneyRequest sendMoneyRequest) {
        log.info("<---- Estoy dentro de sendMoney{}", sendMoneyRequest.toString());
        Mono<WalletBootCoin> walletBootCoinSenderMono = walletBootCoinRepository.findByPhoneNumber(sendMoneyRequest.getWalletAccountSenderId())
                .switchIfEmpty(Mono.error(new RuntimeException("Esta cuenta no existe!")))
                .filter(walletBootCoin -> sendMoneyRequest.getAmount().compareTo(walletBootCoin.getBalance()) <= 0)
                .doOnNext(w -> log.info("se imprime luego de verificar el saldo {}", w))
                .switchIfEmpty(Mono.error(new RuntimeException("Saldo Insuficiente!!!!!")));

        Mono<WalletBootCoin> walletBootCoinReceiverMono = walletBootCoinRepository.findByPhoneNumber(sendMoneyRequest.getWalletAccountReceiverId())
                .switchIfEmpty(Mono.error(new RuntimeException("Esta cuenta no existe!")));

        return Mono.zip(walletBootCoinSenderMono, walletBootCoinReceiverMono, (walletBootCoinSender, walletBootCoinReceiver) -> {
            BigDecimal money = sendMoneyRequest.getAmount();
            walletBootCoinSender.debitMoney(money);
            walletBootCoinReceiver.creditMoney(money);
            return Stream.of(walletBootCoinSender, walletBootCoinReceiver);
        }).flatMapMany(walletBootCoins -> {
            walletSendMoneyProducer.sendMoneyEvent(new SendMoneyEvent(sendMoneyRequest));//publicar evento
            return walletBootCoinRepository.saveAll(Flux.fromStream(walletBootCoins));
        }).filter(walletBootCoins -> walletBootCoins.getPhoneNumber().equals(sendMoneyRequest.getWalletAccountSenderId()))
                .elementAt(0);
    }
}
