/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.apertum.qsky.plugins.ws;

import java.net.URL;
import ru.apertum.qsky.plugins.ws.ws_client.CustomerEvents;
import ru.apertum.qsystem.common.exceptions.ServerException;
import ru.apertum.qsystem.server.ServerProps;

/**
 * Через этот класс используем коннектор до вебсервисов.
 * @author egorov
 */
public class SkyService {

    private final CustomerEvents customerEvents;

    private SkyService() {
        try {
            customerEvents = new CustomerEvents(new URL(ServerProps.getInstance().getProps().getSkyServerUrl()));
        } catch (Exception ex) {
            throw new ServerException("Impossible create access to web service. ", ex);
        }
    }

    private CustomerEvents getCustomerEvents() {
        return customerEvents;
    }

    public static CustomerEvents getInstance() {
        return CEHolder.INSTANCE.getCustomerEvents();
    }

    private static class CEHolder {

        private static final SkyService INSTANCE = new SkyService();
    }
}
