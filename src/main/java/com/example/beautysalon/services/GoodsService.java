package com.example.beautysalon.services;

import com.example.beautysalon.dto.GoodsRequestDTO;
import com.example.beautysalon.dto.GoodsResponseDTO;
import com.example.beautysalon.entities.Goods;
import com.example.beautysalon.repositories.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final ProducerService producerService;

    private GoodsResponseDTO saveGoods(Goods goods) throws Exception {
        validateUniqueFields(goods.getName());
        goods.setId(sequenceGeneratorService.generateSequence(Goods.class.getSimpleName()));
        return new GoodsResponseDTO(goodsRepository.save(goods));
    }
    public GoodsResponseDTO addGoods(GoodsRequestDTO goodsRequestDTO) throws Exception {
        Goods goods = Goods.builder()
                .name(goodsRequestDTO.getName())
                .baseMeasurement(goodsRequestDTO.getBaseMeasurement())
                .description(goodsRequestDTO.getDescription())
                .producer(producerService.getProducerById(goodsRequestDTO.getProducerId()))
                .build();
        return saveGoods(goods);
    }

    public void validateUniqueFields(String name) throws Exception {
        if (Objects.nonNull(goodsRepository.findByName(name))) {
            throw new Exception("goods name  is not unique");
        }
    }

    public GoodsResponseDTO editGoodsById(Long id, GoodsRequestDTO goodsDTO) throws Exception {
        validateUniqueFields(goodsDTO.getName());
        Goods goods = getGoodsById(id);
        goods.setName(goodsDTO.getName());
        goods.setDescription(goodsDTO.getDescription());
        goods.setMeasurement(goodsDTO.getMeasurement());
        goods.setProducer(producerService.getProducerById(goodsDTO.getProducerId()));
        goods.setBaseMeasurement(goodsDTO.getBaseMeasurement());
        return new GoodsResponseDTO(goodsRepository.save(goods));
    }

    public Goods getGoodsById(Long id) throws Exception {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("goods with %d is not found in DB", id)));
    }

    public GoodsResponseDTO findGoodsById(Long id) {
        return goodsRepository.findById(id).map((GoodsResponseDTO::new))
                .orElse(new GoodsResponseDTO());
    }

    public List<GoodsResponseDTO> getGoodsByPartName(String partName) {
        return goodsRepository.findByNameContainingIgnoreCase(partName).stream()
                .map(val -> new GoodsResponseDTO(val))
                .toList();
    }

    public GoodsResponseDTO removeGoods(Long id) throws Exception {
        GoodsResponseDTO goodsResponseDTO = new GoodsResponseDTO(getGoodsById(id));
        goodsRepository.deleteById(id);
        return goodsResponseDTO;
    }


}
