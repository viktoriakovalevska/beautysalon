package com.example.beautysalon.services;

import com.example.beautysalon.dto.OfferRequestDTO;
import com.example.beautysalon.dto.OfferResponseDTO;
import com.example.beautysalon.entities.Offer;
import com.example.beautysalon.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    private OfferResponseDTO saveOffer(Offer offer) throws Exception {
        return new OfferResponseDTO(offerRepository.save(offer));
    }

    public OfferResponseDTO addOffer(OfferRequestDTO offerRequestDTO) throws Exception {
        Offer offer = Offer.builder()
                .name(offerRequestDTO.getName())
                .period(Duration.ofMinutes(offerRequestDTO.getPeriod()))
                .build();
        return saveOffer(offer);
    }

    public Offer getOfferById(String id) throws Exception {
        return offerRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("offer with id %s is not found in DB", id)));
    }

    public OfferResponseDTO editOfferById(String id, OfferRequestDTO offerRequestDTO) throws Exception {
        Offer offer = getOfferById(id);
        offer.setName(offerRequestDTO.getName());
        offer.setPeriod(Duration.ofMinutes(offerRequestDTO.getPeriod()));
        return new OfferResponseDTO(offerRepository.save(offer));
    }

    public OfferResponseDTO findOfferById(String id) {
        return offerRepository.findById(id).map(OfferResponseDTO::new)
                .orElse(new OfferResponseDTO());
    }

    public List<OfferResponseDTO> getOfferByPartName(String partName) {
        return offerRepository.findByNameContainingIgnoreCase(partName).stream()
                .map(val -> new OfferResponseDTO(val))
                .toList();
    }

    public List<OfferResponseDTO> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(val -> new OfferResponseDTO(val))
                .toList();
    }

    public OfferResponseDTO removeOffer(String id) throws Exception {
        OfferResponseDTO offerResponseDTO = new OfferResponseDTO(getOfferById(id));
        offerRepository.deleteById(id);
        return offerResponseDTO;
    }

}
