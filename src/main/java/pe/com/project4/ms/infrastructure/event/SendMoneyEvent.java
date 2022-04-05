package pe.com.project4.ms.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project4.ms.infrastructure.rest.request.SendMoneyRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMoneyEvent {
    private String walletAccountSenderId;
    private String walletAccountReceiverId;
    private BigDecimal amount;
    private String modePayment;
    private String codeTransfer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime occurredAt;

    public SendMoneyEvent(SendMoneyRequest sendMoneyRequest) {
        walletAccountSenderId = sendMoneyRequest.getWalletAccountSenderId();
        walletAccountReceiverId = sendMoneyRequest.getWalletAccountReceiverId();
        amount = sendMoneyRequest.getAmount();
        modePayment = "Transfer";
        codeTransfer = String.format("C-%04d",Integer.parseInt(sendMoneyRequest.getWalletAccountReceiverId().substring(2,4)+1));
        occurredAt = LocalDateTime.now();
    }
}
