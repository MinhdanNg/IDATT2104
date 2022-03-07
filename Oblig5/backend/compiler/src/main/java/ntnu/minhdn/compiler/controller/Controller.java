package ntnu.minhdn.compiler.controller;

import ntnu.minhdn.compiler.model.Code;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequestMapping("/compiler")
public class Controller{

    @PostMapping()
    public String compile(@RequestBody Code code){
        String compilation = "";
        return compilation;
    }
}

