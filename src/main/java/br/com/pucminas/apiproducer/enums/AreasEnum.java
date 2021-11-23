package br.com.pucminas.apiproducer.enums;

import br.com.pucminas.apiproducer.exceptions.AreasException;
import br.com.pucminas.apiproducer.exceptions.StatusErrorException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;
import static br.com.pucminas.apiproducer.constants.ApiConstants.MSG_ERROR_AREA_INVALID;
import static br.com.pucminas.apiproducer.utils.Helpers.validateIntegerEquals;
import static br.com.pucminas.apiproducer.utils.Helpers.validateStringEquals;

@Getter
@AllArgsConstructor
public enum AreasEnum {

    MEIO_AMBIENTE(1, "Meio Ambiente"),
    SAUDE(2, "Saúde"),
    FUNDO_SOCIAL(3,"Fundo Social"),
    OBRAS(4, "Obras"),
    COMUNICACAO(5, "Comunicação"),
    ASSISTENCIA_SOCIAL(6,"Assistência Social"),
    EDUCACAO(7, "Educação"),
    ADMINISTRACAO(8, "Administração"),
    JUSTICA_E_CIDADANIA(9, "Justiça e Cidadania"),
    GOVERNO_E_GESTAO_ESTRATEGICA(10, "Governo e Gestão Estratégica" ),
    FAZENDA(11,"Fazenda"),
    SANEAMENTO(12, "Saneamento"),
    DEFESA_CIVIL(13, "Defesa Civil"),
    CULTURA(14,"Cultura"),
    SEGURANCA_E_MOBILIDADE(15, "Segurança e Mobilidade"),
    PLANEJAMENTO(16,"Planejamento"),
    TURISMO(17, "Turismo"),
    LICITACAO(18, "Licitação"),
    AGRICULTURA(19, "Agricultura"),
    ESPORTES(20, "Esportes"),
    INDUSTRIA(21,"Industria"),
    OUVIDORIA(22, "Ouvidoria");

    private Integer codigo;
    private String nome;


    public static Optional<AreasEnum> fromCodigo(Integer codigo){
        return Stream.of(AreasEnum.values())
                .filter(area -> area.getCodigo().equals(codigo))
                .findFirst();
    }

    @JsonCreator
    public static AreasEnum decode(Object value){

        if(value instanceof  Integer){
            return fromCodigo((Integer) value)
                    .orElseThrow(() -> new AreasException(MSG_ERROR_AREA_INVALID));

        } else if(value instanceof String){
            return fromString((String) value);

        } else {
            throw new AreasException(MSG_ERROR_AREA_INVALID);
        }
    }

    public static AreasEnum fromString(String value){

        if (StringUtils.isNumeric(value)) {
           return Stream.of(AreasEnum.values())
                    .filter(val -> validateIntegerEquals(val.getCodigo(), value))
                    .findFirst()
                    .orElseThrow(() -> new StatusErrorException(MSG_ERROR_AREA_INVALID));
        } else {
            return Stream.of(AreasEnum.values())
                    .filter(val -> validateStringEquals(val.name().replace("_"," ")
                            .toLowerCase(Locale.ROOT), value))
                    .findFirst()
                    .orElseThrow(() -> new StatusErrorException(MSG_ERROR_AREA_INVALID));
        }
    }


}
