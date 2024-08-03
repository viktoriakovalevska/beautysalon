package com.example.beautysalon.controllers;

import com.example.beautysalon.common.Response;
import com.example.beautysalon.dto.OfferRequestDTO;
import com.example.beautysalon.dto.OfferResponseDTO;
import com.example.beautysalon.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @PostMapping("admin/offer/add")
    public Response<OfferResponseDTO> offerAdd(@RequestBody OfferRequestDTO requestDTO) throws Exception {
        return Response.createSuccessfulResponseEntity(offerService.addOffer(requestDTO));
    }

    @PutMapping("admin/offer/edit/{id}")
    public Response<OfferResponseDTO> offerEdit(@PathVariable(value = "id") String id, @RequestBody OfferRequestDTO offerRequestDTO) throws Exception {
        return Response.createSuccessfulResponseEntity(offerService.editOfferById(id, offerRequestDTO));
    }

    @GetMapping("admin/offer/get/{id}")
    public Response<OfferResponseDTO> findByOfferId(@PathVariable(value = "id") String id) throws Exception {
        return Response.createSuccessfulResponseEntity(offerService.findOfferById(id));
    }

    @GetMapping("admin/offers/get/name")
    public Response<List<OfferResponseDTO>> findOfferByPartName(@RequestParam(value = "partname") String partName) {
        return Response.createSuccessfulResponseEntity(offerService.getOfferByPartName(partName));
    }

    @GetMapping("admin/offer/get/all")
    public Response<List<OfferResponseDTO>> findAllOffer() {
        return Response.createSuccessfulResponseEntity(offerService.getAllOffers());
    }

    @DeleteMapping("admin/offer/id/{id}/delete")
    public Response<OfferResponseDTO> removeOffer(@PathVariable(value = "id") String id) throws Exception {
        return Response.createSuccessfulResponseEntity(offerService.removeOffer(id));
    }

}
