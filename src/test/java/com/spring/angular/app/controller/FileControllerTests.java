package com.spring.angular.app.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class FileControllerTests {

}
