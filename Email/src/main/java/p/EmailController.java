package p;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class EmailController {

    @RequestMapping("/emailcontroller.com/{email}/{token}")
    public EmailSender requester(@PathVariable String email, @PathVariable String token) {
        return new EmailSender(email, token);
    }
}
