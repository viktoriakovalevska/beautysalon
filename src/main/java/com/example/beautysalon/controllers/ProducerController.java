package com.example.beautysalon.controllers;

import com.example.beautysalon.common.Response;
import com.example.beautysalon.dto.ProducerRequestDTO;
import com.example.beautysalon.dto.ProducerResponseDTO;
import com.example.beautysalon.services.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService producerService;

    @PostMapping("admin/producer/add")
    public Response<ProducerResponseDTO> producerAdd(@RequestBody ProducerRequestDTO registrDTO) throws Exception {
        return Response.createSuccessfulResponseEntity(producerService.addProducer(registrDTO));
    }

    @PutMapping("admin/producer/edit/{id}")
    public Response<ProducerResponseDTO> producerEdit(@PathVariable(value = "id") String id, @RequestBody ProducerRequestDTO producerRequestDTO) throws Exception {
        return Response.createSuccessfulResponseEntity(producerService.editProducerById(id, producerRequestDTO));
    }

    @GetMapping("admin/producer/get/{id}")
    public Response<ProducerResponseDTO> findByProducerId(@PathVariable(value = "id") String id) throws Exception {
        return Response.createSuccessfulResponseEntity(producerService.findProducerById(id));
    }
    @GetMapping("admin/producer/get/name")
    public  Response<List<ProducerResponseDTO>> findProducerByPartName(@RequestParam(value = "partname") String partName){
        return Response.createSuccessfulResponseEntity(producerService.getProducerByPartName(partName));
    }
    @DeleteMapping("admin/producer/id/{id}/delete")
    public Response<ProducerResponseDTO> removeProducer(@PathVariable(value = "id") String id) throws Exception {
        return Response.createSuccessfulResponseEntity(producerService.removeProducer(id));
    }
}
