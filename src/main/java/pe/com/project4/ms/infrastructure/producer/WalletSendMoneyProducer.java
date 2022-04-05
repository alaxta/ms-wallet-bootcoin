package pe.com.project4.ms.infrastructure.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.infrastructure.event.SendMoneyEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletSendMoneyProducer {
    private final KafkaTemplate<String, SendMoneyEvent> kafkaTemplate;

    public void sendMoneyEvent(SendMoneyEvent sendMoneyEvent) {
        log.info("==> Producing message {}", sendMoneyEvent.toString());
        kafkaTemplate.send("WALLETBC-SEND-MONEY", sendMoneyEvent);
    }

}
