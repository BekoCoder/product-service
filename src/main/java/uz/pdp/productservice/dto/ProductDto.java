package uz.pdp.productservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Mahsulotlar bo'yicha ma'lumotlar")
public class ProductDto {
    @Schema(description = "Mahsulot id si")
    private Long id;

    @Schema(description = "Mahsulot nomi")
    private String name;

    @Schema(description = "Mahsulot tavsifi")
    private String description;

    @Schema(description = "Mahsulot narxi")
    private Double price;

    @Schema(description = "Mahsulot miqdori")
    private Double quantity;
}
