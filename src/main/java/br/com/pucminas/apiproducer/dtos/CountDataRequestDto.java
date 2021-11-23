package br.com.pucminas.apiproducer.dtos;

import br.com.pucminas.apiproducer.enums.AreasEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountDataRequestDto {
    private Long count;
    private AreasEnum area;
}
