package pe.com.project4.ms.infrastructure.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.project4.ms.domain.DocumentType;
import pe.com.project4.ms.infrastructure.event.WalletAccountCreatedEvent;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

    private String names;
    private String paternalSurname;
    private String maternalSurname;
    private String documentNumber;
    private DocumentType documentType;
    private String phoneNumber;
    private String email;

    public WalletAccountCreatedEvent toWalletAccountCreatedEvent() {
        log.info("BC llego aqui");
        WalletAccountCreatedEvent walletAccountCreatedEvent = new WalletAccountCreatedEvent();
        walletAccountCreatedEvent.setNames(names);
        walletAccountCreatedEvent.setPaternalSurname(paternalSurname);
        walletAccountCreatedEvent.setMaternalSurname(maternalSurname);
        walletAccountCreatedEvent.setDocumentNumber(documentNumber);
        walletAccountCreatedEvent.setDocumentType(documentType);
        walletAccountCreatedEvent.setPhoneNumber(phoneNumber);
        walletAccountCreatedEvent.setEmail(email);
        return walletAccountCreatedEvent;
    }
}
