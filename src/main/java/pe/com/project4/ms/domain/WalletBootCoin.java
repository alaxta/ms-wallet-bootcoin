package pe.com.project4.ms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletBootCoin {
    private String id;
    private BigDecimal balance;
    private String phoneNumber;
    private String modePayment;
    private String codeTransfer;
    private LocalDateTime createdAt;

    public WalletBootCoin(String phoneNumber,String codeTransfer) {
        this.phoneNumber = phoneNumber;
        this.balance = BigDecimal.ZERO;
        this.modePayment = "transfer";
        this.codeTransfer = codeTransfer;
        this.createdAt = LocalDateTime.now();
    }

    public void debitMoney(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void creditMoney(BigDecimal amount) {
        balance = balance.add(amount);
    }

}
