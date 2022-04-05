package pe.com.project4.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project4.ms.application.SaveWalletBootCoinUseCase;
import pe.com.project4.ms.application.repository.WalletBootCoinRepository;
import pe.com.project4.ms.domain.WalletBootCoin;
import pe.com.project4.ms.infrastructure.producer.WalletAccountCreatedProducer;
import pe.com.project4.ms.infrastructure.rest.request.CreateAccountRequest;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveWalletBootCoinService implements SaveWalletBootCoinUseCase {

    private final WalletBootCoinRepository walletBootCoinRepository;
    private final WalletAccountCreatedProducer walletAccountCreatedProducer;
//    private String codeTransfer = String.format("B-%04d",1+1);
    @Override
    public Mono<WalletBootCoin> createAccount(CreateAccountRequest createAccountRequest) {
        log.info("==> Entro al createAccount {} ", createAccountRequest);
        if (createAccountRequest.getPhoneNumber().length() == 9) {
            if (createAccountRequest.getDocumentNumber().length() <= 11) {
                return walletBootCoinRepository.findByPhoneNumber(createAccountRequest.getPhoneNumber())
                        .flatMap(accountFound -> Mono.error(new RuntimeException("==> El numero de celular ya esta registrado")))
                        .then(Mono.just(createAccountRequest))
                        .flatMap(createAccount -> walletBootCoinRepository.save(new WalletBootCoin(createAccount.getPhoneNumber(),String.format("C-%04d",Integer.parseInt(createAccount.getPhoneNumber().substring(2,4)+1))))
                                .map(walletBootCoin -> {
                                    walletAccountCreatedProducer.sendAccountCreate(createAccountRequest.toWalletAccountCreatedEvent());
                                    log.info("==> Request, {}", createAccountRequest);
                                    return walletBootCoin;
                                }));
            } else {
                log.warn("==> Ingrese Un Numero de documento Valido., {} " + "=>[" + createAccountRequest.getDocumentNumber() + "]");
            }
        } else {
            log.warn("==> Ingrese Un Numero de Celular Valido., {} " + "=>[" + createAccountRequest.getPhoneNumber() + "]");
        }
        return Mono.empty();
    }


}
