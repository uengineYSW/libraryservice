package libraryservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import libraryservice.domain.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanStatusUpdatePolicyTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(LoanStatusUpdatePolicyTest.class);
   
   @Autowired
   private MessageCollector messageCollector;
   @Autowired
   private ApplicationContext applicationContext;

   @Autowired
   private MessageVerifier<Message<?>> messageVerifier;

   @Autowired
   public BookRepository repository;

   @Test
   @SuppressWarnings("unchecked")
   public void test0() {

      //given:
   Book entity = new Book();

      entity.setBookId("example_book_id");
      entity.setTitle("예시 제목");
      entity.setAuthor("예시 작가");
      entity.setPublisher("예시 출판사");
      entity.setCategory("FICTION");
      entity.setStatus("AVAILABLE");
      entity.setIsbn("example_isbn");

      repository.save(entity);

      //when:  
      
      LoanCreated event = new LoanCreated();

      event.setLoanId(1L);
      event.setMember([object Object]);
      event.setLoanPeriod([object Object]);
      event.setLoanDate("2022-01-01");
      event.setReturnDueDate("2022-01-31");
      event.setStatus("IN_PROGRESS");
      event.setBookId("example_book_id");
   
   
   BookManagementApplication.applicationContext = applicationContext;

      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      try {
         this.messageVerifier.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader("type", event.getEventType())
                .build(), "libraryservice");

         //then:
         Message<?> receivedMessage = this.messageVerifier.receive("libraryservice", 5000, TimeUnit.MILLISECONDS);
         assertNotNull("Resulted event must be published", receivedMessage);

         String receivedPayload = (String) receivedMessage.getPayload();
         BookStatusUpdated outputEvent = objectMapper.readValue(receivedPayload, BookStatusUpdated.class);


         LOGGER.info("Response received: {}", outputEvent);

         assertEquals(outputEvent.getBookId(), "example_book_id");
         assertEquals(outputEvent.getStatus(), "BORROWED");


      } catch (JsonProcessingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }

}
