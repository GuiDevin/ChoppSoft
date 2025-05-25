import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {

    @GetMapping("/data")
    @ResponseBody
    public String getData() {
        return "<h1>Dados do Java</h1><p>Este é um exemplo de conexão entre Java e HTML.</p>";
    }
}
