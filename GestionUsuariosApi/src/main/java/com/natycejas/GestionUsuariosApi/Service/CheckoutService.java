package com.natycejas.GestionUsuariosApi.Service;

import com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder.CheckoutRequestDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder.CheckoutResponseDTO;

public interface CheckoutService {
    CheckoutResponseDTO checkout(CheckoutRequestDTO request);
}