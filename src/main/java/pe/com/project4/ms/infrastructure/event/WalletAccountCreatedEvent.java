package pe.com.project4.ms.infrastructure.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project4.ms.domain.DocumentType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletAccountCreatedEvent {
    private String names;
    private String paternalSurname;
    private String maternalSurname;
    private String documentNumber;
    private DocumentType documentType;
    private String phoneNumber;
    private String email;
}
