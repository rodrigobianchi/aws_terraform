package io.github.awsconect.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aws-conect/files")
public class AwsController {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private AmazonSQS amazonSQS;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AmazonSQSAsync amazonSQSAsync;

    /*
    @PostMapping("/note")
    public void note(@RequestParam String name, @RequestParam String content) {
        amazonS3.putObject("terraform-rodrigobianchi", name+".txt", content);
    }
    Comunicação com S3
     */

    /*
    @PostMapping("/note")
    public void note(@RequestParam String name, @RequestParam String content) {
        jdbcTemplate.update("insert into note (name, content) values (?, ?)", name, content);
    }
    Comunicação com RDS Postgres
     */

    @SqsListener("NOTE_QUEUE")
    public void consume(String message) {
        System.out.println("mensagem queue" + message);
    }

    @PostMapping("/note")
    public void note(@RequestParam String name, @RequestParam String content) {
        new QueueMessagingTemplate(amazonSQSAsync).convertAndSend("NOTE_QUEUE", content);
    }

    @GetMapping("/note")
    public void listQueues() {
        System.out.println(amazonSQS.listQueues());
    }

}
