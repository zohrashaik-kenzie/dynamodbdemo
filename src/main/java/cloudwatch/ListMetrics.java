package cloudwatch;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.model.ListMetricsRequest;
import com.amazonaws.services.cloudwatch.model.ListMetricsResult;
import com.amazonaws.services.cloudwatch.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ListMetrics {


    @Autowired
    private  AmazonCloudWatch buildCloudWatch;

    public List<Metric> getMetrics() {

        String name = "NetworkOut";
        String namespace = "AWS/EC2";

        ListMetricsRequest request = new ListMetricsRequest()
                .withMetricName(name)
                .withNamespace(namespace);

        boolean done = false;
        List<Metric> metricList = new ArrayList<>();
        while (!done) {
            ListMetricsResult response = buildCloudWatch.listMetrics(request);
            metricList.addAll(response.getMetrics());
            for (Metric metric : response.getMetrics()) {
                System.out.printf(
                        "Retrieved metric %s", metric.getDimensions());
            }

            request.setNextToken(response.getNextToken());

            if (response.getNextToken() == null) {
                done = true;
            }
        }
        return metricList;
    }
}


