package pe.com.project4.ms.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WalletBootCoinRouter {
    private static final String WALLET_BOOTCOIN = "/wallet-bootcoin";
    private static final String WALLET_HOLDER_ACCOUNT = WALLET_BOOTCOIN + "/account";
    private static final String WALLET_HOLDER_TRANSACTION = WALLET_BOOTCOIN + "/transaction";

    @Bean
    public RouterFunction<ServerResponse> routes(WalletBootCoinHandler walletBootCoinHandler) {
        return route(POST(WALLET_HOLDER_ACCOUNT).and(accept(APPLICATION_JSON)), walletBootCoinHandler::postWalletBootCoin)
                .andRoute(POST(WALLET_HOLDER_TRANSACTION).and(accept(APPLICATION_JSON)), walletBootCoinHandler::postTransaction)
                .andRoute(GET(WALLET_BOOTCOIN).and(queryParam("phoneNumber", t -> true)), walletBootCoinHandler::getBootCoinPhone);
    }
}
