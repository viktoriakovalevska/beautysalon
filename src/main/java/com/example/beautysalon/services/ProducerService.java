package com.example.beautysalon.services;

import com.example.beautysalon.dto.ProducerRequestDTO;
import com.example.beautysalon.dto.ProducerResponseDTO;
import com.example.beautysalon.entities.Producer;
import com.example.beautysalon.repositories.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository producerRepository;

    private ProducerResponseDTO saveProducer(Producer producer) throws Exception {
        return new ProducerResponseDTO(producerRepository.save(producer));
    }

    public ProducerResponseDTO addProducer(ProducerRequestDTO producerRequestDTO) throws Exception {
        Producer producer = Producer.builder()
                .name(producerRequestDTO.getName())
                .country(producerRequestDTO.getCountry())
                .build();
        return saveProducer(producer);
    }

    public ProducerResponseDTO editProducerById(String id, ProducerRequestDTO producerDTO) throws Exception {
        Producer producer = getProducerById(id);
        producer.setName(producerDTO.getName());
        producer.setCountry(producerDTO.getCountry());
        return new ProducerResponseDTO(producerRepository.save(producer));
    }

    public Producer getProducerById(String id) throws Exception {
        return producerRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("producer with id %s is not found in DB", id)));
    }

    public ProducerResponseDTO findProducerById(String id) {
        return producerRepository.findById(id).map((ProducerResponseDTO::new))
                .orElse(new ProducerResponseDTO());
    }

    public List<ProducerResponseDTO> getProducerByPartName(String partName) {
        return producerRepository.findByNameContainingIgnoreCase(partName).stream()
                .map(val -> new ProducerResponseDTO(val))
                .toList();
    }

    public ProducerResponseDTO removeProducer(String id) throws Exception {
        ProducerResponseDTO producerResponseDTO = new ProducerResponseDTO(getProducerById(id));
        producerRepository.deleteById(id);
        return producerResponseDTO;
    }

}
