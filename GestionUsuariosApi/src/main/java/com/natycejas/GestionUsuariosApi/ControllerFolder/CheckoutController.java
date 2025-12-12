package com.natycejas.GestionUsuariosApi.ControllerFolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder.CheckoutRequestDTO;
import com.natycejas.GestionUsuariosApi.DTOFolder.CheckoutDtosFolder.CheckoutResponseDTO;
import com.natycejas.GestionUsuariosApi.Service.CheckoutService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios/ventas")
@Tag(name = "Checkout", description = "Proceso de compra y registro de ventas")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Operation(summary = "Checkout de compra",
        description = "Valida usuario por RUN, descuenta stock por ítems, registra la venta y devuelve resumen.")
    @ApiResponse(responseCode = "200", description = "Compra registrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CheckoutResponseDTO.class)))
    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponseDTO> checkout(@RequestBody CheckoutRequestDTO request) {
        CheckoutResponseDTO response = checkoutService.checkout(request);
        return ResponseEntity.ok(response);
    }
}