package cloudwatch;

import com.amazonaws.services.cloudwatch.model.Metric;
import com.spring.dynamodb.entity.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cloudwatch")
public class CloudWatchController {
    @GetMapping("/getmetrics")
    public List<Metric> getMetrics() {
       ListMetrics metrics = new ListMetrics();
       return metrics.getMetrics();
    }
}
