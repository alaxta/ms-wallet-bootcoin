package pe.com.project4.ms.infrastructure.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMoneyRequest {
    private String walletAccountSenderId;
    private String walletAccountReceiverId;
    private BigDecimal amount;
}
