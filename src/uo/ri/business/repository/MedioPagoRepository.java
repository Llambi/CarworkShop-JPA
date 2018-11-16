package uo.ri.business.repository;

import uo.ri.model.MedioPago;

import java.util.List;

public interface MedioPagoRepository extends Repository<MedioPago> {

    /**
     * @param id of the client
     * @return a list with all the payment means owned by the client
     */
    List<MedioPago> findPaymentMeansByClientId(Long id);

    /**
     * @param id of the Invoice
     * @return a list with all the payment means owned by the client
     */
    List<MedioPago> findPaymentMeansByInvoiceId(Long id);
}

