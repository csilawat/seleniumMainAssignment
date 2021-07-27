import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ReadPDFANdVerifyItTest {

    WebDriver driver = null;

    @Test
    public void verifyContentInPDf() throws IOException {
        String url = "http://www.pdf995.com/samples/pdf.pdf";
        driver.get(url);

        String pdfContent = readPdfContent(url);

        Assert.assertTrue(pdfContent.contains("The Pdf995 Suite offers the following features"));
        Assert.assertTrue(pdfContent.contains("World Wide Web"));
        Assert.assertTrue(pdfContent.contains("Virtual Reality Modeling Language (VRML) is a language"));

    }

    public static String readPdfContent(String url) throws IOException {

        URL pdfUrl = new URL(url);
        InputStream in = pdfUrl.openStream();
        BufferedInputStream bf = new BufferedInputStream(in);
        PDDocument doc = PDDocument.load(bf);
        int numberOfPages = getPageCount(doc);
        System.out.println("The total number of pages " + numberOfPages);
        String content = new PDFTextStripper().getText(doc);
        doc.close();

// TODO read data line by line

//        try (PDDocument document = PDDocument.load(new File("PDF.pdf"))) {
//
//            document.getClass();
//
//            if (!document.isEncrypted()) {
//
//                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//                stripper.setSortByPosition(true);
//
//                PDFTextStripper tStripper = new PDFTextStripper();
//
//                String pdfFileInText = tStripper.getText(document);
//                // System.out.println("Text:" + st);
//
//                // split by whitespace
//                String lines[] = pdfFileInText.split("\\r?\\n");
//                List<String> pdfLines = new ArrayList<>();
//                StringBuilder sb = new StringBuilder();
//                for (String line : lines) {
//                    System.out.println(line);
//                    pdfLines.add(line);
//                    sb.append(line + "\n");
//                }
//                return sb.toString();
//            }
//
//        }


        return content;
    }

    public static int getPageCount(PDDocument doc) {
        //get the total number of pages in the pdf document
        int pageCount = doc.getNumberOfPages();

        return pageCount;

    }

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        System.out.println("before test");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() throws InterruptedException {

        //  Thread.sleep(3000);
        driver.quit();
    }

}
