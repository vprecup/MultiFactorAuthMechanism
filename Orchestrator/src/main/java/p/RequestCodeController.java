package p;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class RequestCodeController {

    @RequestMapping("/requestcode.com/{email}")
    public RequestCode requester(@PathVariable String email) {
        return new RequestCode(email);
    }
}