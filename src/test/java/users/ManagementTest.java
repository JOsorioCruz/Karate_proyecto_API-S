package users;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManagementTest {

    @Test
    void testParallel() {
        // Elimino el reporte previo
        File reportDir = new File("build/cucumber-html-reports");
        if (reportDir.exists()) {
            try {
                FileUtils.deleteDirectory(reportDir);
                System.out.println("Previous report directory deleted successfully.");
            } catch (Exception e) {
                System.err.println("Failed to delete the previous report directory: " + e.getMessage());
            }
        }

        Results results = Runner.path("classpath:users").outputCucumberJson(true).parallel(4);
        generateReport(results.getReportDir());
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("build"), "curso_karate");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}


//package users;
//
//import com.intuit.karate.Results;
//import com.intuit.karate.Runner;
//import net.masterthought.cucumber.Configuration;
//import net.masterthought.cucumber.ReportBuilder;
//import org.apache.commons.io.FileUtils;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class ManagementTest {
//
//    @Test
//    void testParallel(){
//        Results results = Runner.path("classpath:users").outputCucumberJson(true).parallel(4);
//        generateReport(results.getReportDir());
//    }
//
//    public static void generateReport(String karateOutputPath){
//        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
//        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
//        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
//        Configuration config = new Configuration(new File("build"), "curso_karate");
//        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
//        reportBuilder.generateReports();
//    }
//}
