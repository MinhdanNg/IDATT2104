package ntnu.minhdn.compiler.controller;

import ntnu.minhdn.compiler.model.Code;
import ntnu.minhdn.compiler.service.Compilation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequestMapping("/compiler")
public class Controller{
    Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping()
    public String compile(@RequestBody Code code) throws IOException {
        logger.info("Request from client received");
        Compilation compiler = new Compilation();
        return compiler.compile(code.getCode());
    }
}

