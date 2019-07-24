package cl.sebastian.matrix.validator.portal.jsf;

import cl.sebastian.matrix.validator.model.Matrix;
import cl.sebastian.matrix.validator.portal.service.CsvService;
import cl.sebastian.matrix.validator.portal.utils.FacesUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author seba
 */
@Component
@Scope("view")
@Qualifier("homeBean")
public class HomeBean implements Serializable {

    private static final long serialVersionUID = 4823682893215700992L;

    private UploadedFile file;
    private List<Matrix> matrixes = null;

    @Resource(name = "csvService")
    private transient CsvService csvService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeBean.class);

    @PostConstruct
    public void initBean() {
        this.matrixes = new ArrayList<>();
    }

    public void upload() {
        try {
            if (file != null) {
                FacesUtils.info(String.format("Archivo '%s' subido exitosamente", file.getFileName()));

                File temporal = File.createTempFile(UUID.randomUUID().toString(), ".csv");
                FileUtils.writeByteArrayToFile(temporal, file.getContents());
                this.matrixes = csvService.validate(temporal);
            }
        } catch (Exception e) {
            this.matrixes = new ArrayList<>();
            LOGGER.error("Error al subir archivo: {}", e.getMessage());
            LOGGER.debug("Error al subir archivo: {}", e.getMessage(), e);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Matrix> getMatrixes() {
        return matrixes;
    }

    public void setMatrixes(List<Matrix> matrixes) {
        this.matrixes = matrixes;
    }
}
