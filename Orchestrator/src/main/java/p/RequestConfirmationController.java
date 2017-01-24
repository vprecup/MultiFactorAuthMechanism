package p;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class RequestConfirmationController {

    @RequestMapping("/requestconfirmation/{token}")
    public RequestConfirmation requester(@PathVariable String token) {
        return new RequestConfirmation(token);
    }
}