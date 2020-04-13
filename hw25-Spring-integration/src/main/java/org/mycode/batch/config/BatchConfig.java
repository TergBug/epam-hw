package org.mycode.batch.config;

import org.mycode.batch.model.Account;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.mail.SimpleMailMessageItemWriter;
import org.springframework.batch.item.mail.builder.SimpleMailMessageItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.persistence.EntityManagerFactory;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;

@Configuration
@EnableBatchProcessing
@Import(DatabaseConfig.class)
@PropertySource("email.properties")
public class BatchConfig {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;
    @Autowired
    private Environment environment;
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public BatchConfig(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(environment.getProperty("mail.smtp.host"));
        javaMailSender.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("mail.smtp.port"))));
        javaMailSender.setUsername(environment.getProperty("mail.username"));
        javaMailSender.setPassword(environment.getProperty("mail.password"));
        javaMailSender.setProtocol("smtp");
        Properties props = new Properties();
        props.put("mail.smtp.host", Objects.requireNonNull(environment
                .getProperty("mail.smtp.host")));
        props.put("mail.smtp.port", Objects.requireNonNull(environment
                .getProperty("mail.smtp.port")));
        props.put("mail.smtp.auth", Objects.requireNonNull(environment
                .getProperty("mail.smtp.auth")));
        props.put("mail.smtp.starttls.enable", Objects.requireNonNull(environment
                .getProperty("mail.smtp.starttls.enable")));
        Session session = Session.getInstance(props, null);
        javaMailSender.setSession(session);
        try {
            javaMailSender.testConnection();
        } catch (MessagingException e) {
            System.out.println("No connection");
        }
        return javaMailSender;
    }

    @Bean
    public Job job() {
        return jobs.get("job")
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return steps.get("step")
                .<Account, SimpleMailMessage>chunk(10)
                .reader(itemReader())
                .processor((Function<Account, SimpleMailMessage>) f -> {
                    if (f.getBalance() < 10) {
                        SimpleMailMessage mailMessage = new SimpleMailMessage();
                        mailMessage.setTo(f.getEmail());
                        mailMessage.setText("Dear " + f.getName() + ",\nYour balance is low (" + f.getBalance() + "$)");
                        return mailMessage;
                    }
                    return null;
                })
                .writer(itemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Account> itemReader() {
        return new JpaPagingItemReaderBuilder<Account>()
                .name("accountReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select a from Account a")
                .pageSize(5)
                .build();
    }

    @Bean
    public SimpleMailMessageItemWriter itemWriter() {
        return new SimpleMailMessageItemWriterBuilder()
                .mailSender(javaMailSender())
                .build();
    }
}
