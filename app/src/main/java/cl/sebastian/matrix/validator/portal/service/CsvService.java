package cl.sebastian.matrix.validator.portal.service;

import cl.sebastian.matrix.validator.model.Matrix;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("csvService")
public class CsvService implements Serializable {

    private static final long serialVersionUID = 1089479203538504704L;
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvService.class);

    public List<Matrix> validate(File file) {
        List<Matrix> matrices = new ArrayList<>();
        try {
            final CSVParser parser = new CSVParserBuilder().withSeparator('|').withIgnoreQuotations(true).build();
            CSVReaderBuilder builder = new CSVReaderBuilder(new FileReader(file));
            try (CSVReader csvReader = builder.withCSVParser(parser).build()) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    Matrix matriz = new Matrix(line[0]);
                    matrices.add(matriz);
                }
            }
        } catch (Exception e) {
            matrices = new ArrayList<>();
            LOGGER.error("Error al construir el listado: {}", e.getMessage());
            LOGGER.debug("Error al construir el listado: {}", e.getMessage(), e);
        }
        return matrices;
    }

}
