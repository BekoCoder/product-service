package uz.pdp.productservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Kategoriya bo'yicha ma'lumotlar")
public class CategoryDto {
    @Schema(description = "Kategoriya nomi")
    private String name;
}
