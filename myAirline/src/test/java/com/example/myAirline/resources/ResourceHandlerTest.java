package com.example.myAirline.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.lowagie.text.DocumentException;


@SpringBootTest
@AutoConfigureMockMvc
public class ResourceHandlerTest {

    /**
     * Should convert the test.html into the correct string.
     * 
     * @throws IOException if test.html is not found.
     */
    @Test
    void testHtmlToString() throws IOException {

        String testStr = ResourceHandler.htmlToString(ResourceHandler.HTML_ATTACHMENTS_PATH + "test.html", "!");  
          
        String actualHtml = "This is for the ResourceHandlerTest class. Please don't change the text!";

        assertEquals(actualHtml, testStr);
    }


    /**
     * Should put the test String into a pdf and store it at: ./outputResources/attachments/test.pdf.
     * 
     * @throws IOException
     * @throws DocumentException
     */
    @Test
    void testStringToPdf() throws IOException, DocumentException {

        String path = ResourceHandler.PDF_ATTACHMENTS_PATH + "test.pdf";
        ResourceHandler.stringToPdf("This is the pdf text", ResourceHandler.PDF_ATTACHMENTS_PATH, "test.pdf");

        assertTrue(new File(path).exists());
    }
}