package com.example.beautysalon.controllers;

import com.example.beautysalon.common.Response;
import com.example.beautysalon.dto.GoodsRequestDTO;
import com.example.beautysalon.dto.GoodsResponseDTO;
import com.example.beautysalon.services.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GoodsController {
    final private GoodsService goodsService;

    @PutMapping("admin/goods/edit/{id}")
    public Response<GoodsResponseDTO> goodsEdit(@PathVariable(value = "id") Long id, @RequestBody GoodsRequestDTO goodsDTO) throws Exception {
        return Response.createSuccessfulResponseEntity(goodsService.editGoodsById(id, goodsDTO));
    }

    @GetMapping("admin/goods/get/{id}")
    public Response<GoodsResponseDTO> findByGoodsId(@PathVariable(value = "id") Long id) throws Exception {
        return Response.createSuccessfulResponseEntity(goodsService.findGoodsById(id));
    }

    @PostMapping("admin/goods/add")
    public Response<GoodsResponseDTO> goodsAdd(@RequestBody GoodsRequestDTO registrDTO) throws Exception {
        return Response.createSuccessfulResponseEntity(goodsService.addGoods(registrDTO));
    }
    @DeleteMapping("admin/goods/id/{id}/delete")
    public Response<GoodsResponseDTO> removeGoods(@PathVariable(value = "id") Long id) throws Exception {
        return Response.createSuccessfulResponseEntity(goodsService.removeGoods(id));
    }

}
