package pe.com.project4.ms.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.project4.ms.domain.WalletBootCoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("walletBCAccounts")
public class WalletBootCoinDao {
    @Id
    private String id;
    private BigDecimal balance;
    private String phoneNumber;
    private  String modePayment;
    private String codeTransfer;
    private LocalDateTime createdAt;

    public WalletBootCoinDao(WalletBootCoin walletBootCoin) {
        id = walletBootCoin.getId();
        balance = walletBootCoin.getBalance();
        phoneNumber = walletBootCoin.getPhoneNumber();
        modePayment = walletBootCoin.getModePayment();
        codeTransfer = walletBootCoin.getCodeTransfer();
        createdAt = walletBootCoin.getCreatedAt();
    }

    public WalletBootCoin towalletBootCoin() {
        WalletBootCoin walletBootCoin = new WalletBootCoin();
        walletBootCoin.setId(id);
        walletBootCoin.setBalance(balance);
        walletBootCoin.setPhoneNumber(phoneNumber);
        walletBootCoin.setModePayment(modePayment);
        walletBootCoin.setCodeTransfer(codeTransfer);
        walletBootCoin.setCreatedAt(createdAt);
        return walletBootCoin;
    }
}
