package entrega1.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class LectorCSV {
    public static Iterable<CSVRecord> leerCSV(String ruta) {

        try {
            Reader reader = new FileReader(ruta);
            CSVParser parser = CSVFormat.DEFAULT
                    .withHeader()
                    .withSkipHeaderRecord()
                    .parse(reader);
            return parser.getRecords();
        }
        catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo CSV: " + ruta, e);
        }
    }

}
