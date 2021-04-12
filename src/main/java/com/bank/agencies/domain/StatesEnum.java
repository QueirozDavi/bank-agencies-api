package com.bank.agencies.domain;

public enum StatesEnum {

    ACRE("AC", "Acre"),
    ALAGOAS("AL", "Alagoas"),
    AMAPA("AP", "Amap\u00E1"),
    AMAZONAS("AM", "Amazonas"),
    BAHIA("BA", "Bahia"),
    CEARA("CE", "Cear\u00E1"),
    DISTRITO_FEDERAL("DF", "Distrito Federal"),
    ESPIRITO_SANTO("ES", "Esp\u00EDrito Santo"),
    GOIAS("GO", "Goi\u00E1s"),
    MARANHAO("MA", "Maranh\u00E3o"),
    MATO_GROSSO("MT", "Mato Grosso"),
    MATO_GROSSO_DO_SUL("MS", "Mato Grosso do Sul"),
    MINAS_GERAIS("MG", "Minas Gerais"),
    PARA("PA", "Par\u00E1"),
    PARAIBA("PB", "Para\u00EDba"),
    PARANA("PR", "Paran\u00E1"),
    PERNAMBUCO("PE", "Pernambuco"),
    PIAUI("PI", "Piau\u00ED"),
    RIO_DE_JANEIRO("RJ", "Rio de Janeiro"),
    RIO_GRANDE_DO_NORTE("RN", "Rio Grande do Norte"),
    RIO_GRANDE_DO_SUL("RS", "Rio Grande do Sul"),
    RONDONIA("RO", "Rond\u00F4nia"),
    RORAIMA("RR", "Roraima"),
    SANTA_CATARINA("SC", "Santa Catarina"),
    SAO_PAULO("SP", "S\u00E3o Paulo"),
    SERGIPE("SE", "Sergipe"),
    TOCANTINS("TO", "Tocantins");

    private final String value;
    private final String description;

    private StatesEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
