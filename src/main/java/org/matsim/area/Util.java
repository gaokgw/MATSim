package org.matsim.area;


        import com.pb.common.datafile.TableDataFileReader;
        import com.pb.common.datafile.TableDataSet;

        import org.apache.log4j.Logger;


        import java.io.*;
        import java.nio.file.Path;
        import java.nio.file.Paths;

        import static java.lang.System.exit;



/**
 * Created by carlloga on 4/19/2017.
 */
public class Util {

    static Logger logger = Logger.getLogger(Util.class);

    public static TableDataSet readCSVfile (String fileName) {
        // read csv file and return as TableDataSet
        File dataFile = new File(fileName);
        TableDataSet dataTable;
        boolean exists = dataFile.exists();
        if (!exists) {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            System.out.println("Current relative path is: " + s);
            logger.error("File not found: " + dataFile.getAbsolutePath());
            exit(1);
        }
        try {
            TableDataFileReader reader = TableDataFileReader.createReader(dataFile);
            dataTable = reader.readFile(dataFile);
            reader.close();
        } catch (Exception e) {
            logger.error("Error reading file " + dataFile);
            throw new RuntimeException(e);
        }
        return dataTable;
    }


}
