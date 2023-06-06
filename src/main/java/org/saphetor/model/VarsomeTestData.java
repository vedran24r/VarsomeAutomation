package org.saphetor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class VarsomeTestData {
    private String variant, genome, phenotype, ageAtOnset, endpoint;

    public VarsomeTestData(String variant, String genome, String phenotype, String ageAtOnset, String endpoint) {
        this.variant = variant;
        this.genome = genome;
        this.phenotype = phenotype;
        this.ageAtOnset = ageAtOnset;
        this.endpoint = endpoint;
    }
}
