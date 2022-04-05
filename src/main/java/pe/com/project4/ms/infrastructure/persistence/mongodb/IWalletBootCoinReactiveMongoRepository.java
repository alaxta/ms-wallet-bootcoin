package pe.com.project4.ms.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.project4.ms.infrastructure.persistence.model.WalletBootCoinDao;
import reactor.core.publisher.Mono;

public interface IWalletBootCoinReactiveMongoRepository extends ReactiveMongoRepository<WalletBootCoinDao, String> {
    Mono<WalletBootCoinDao> findByPhoneNumber(String phoneNumber);
}
