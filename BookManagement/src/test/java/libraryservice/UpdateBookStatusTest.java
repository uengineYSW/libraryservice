
package libraryservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;

import javax.inject.Inject;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import libraryservice.domain.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public class UpdateBookStatusTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateBookStatusTest.class);
   
   @Autowired
   private MessageCollector messageCollector;
   
   @Autowired
   private ApplicationContext applicationContext;

   @Autowired
   ObjectMapper objectMapper;

   @Autowired
   private MessageVerifier<Message<?>> messageVerifier;

   @Autowired
   public BookRepository repository;

   @Test
   @SuppressWarnings("unchecked")
   public void test0() {

      //given:  
      Book entity = new Book();

      entity.setBookId("BOOK-001");
      entity.setTitle("소설 제목");
      entity.setIsbn([object Object]);
      entity.setAuthor("홍길동");
      entity.setPublisher("출판사 이름");
      entity.setCategory("FICTION");
      entity.setStatus("AVAILABLE");

      repository.save(entity);

      //when:  
      try {


      
         UpdateBookStatus command = new UpdateBookStatusCommand();

         command.setBookId("BOOK-001");
         command.setStatus("BORROWED");

         entity.UpdateBookStatus(command);


           

         //then:
         this.messageVerifier.send(MessageBuilder
                .withPayload(entity)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build(), "libraryservice");

         Message<?> receivedMessage = this.messageVerifier.receive("libraryservice", 5000, TimeUnit.MILLISECONDS);
         assertNotNull("Resulted event must be published", receivedMessage);

         String receivedPayload = (String) receivedMessage.getPayload();

         BookStatusUpdated outputEvent = objectMapper.readValue(receivedPayload, BookStatusUpdated.class);


         LOGGER.info("Response received: {}", outputEvent);
         
         assertEquals(outputEvent.getBookId(), "BOOK-001");
         assertEquals(outputEvent.getStatus(), "BORROWED");

      } catch (JsonProcessingException e) {
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }
   @Test
   @SuppressWarnings("unchecked")
   public void test1() {

      //given:  
      Book entity = new Book();

      entity.setBookId("BOOK-002");
      entity.setTitle("학술 도서");
      entity.setIsbn([object Object]);
      entity.setAuthor("이몽룡");
      entity.setPublisher("학술 출판사");
      entity.setCategory("ACADEMIC");
      entity.setStatus("DISCARDED");

      repository.save(entity);

      //when:  
      try {


      
         UpdateBookStatus command = new UpdateBookStatusCommand();

         command.setBookId("BOOK-002");
         command.setStatus("BORROWED");

         entity.UpdateBookStatus(command);


           

         //then:
         this.messageVerifier.send(MessageBuilder
                .withPayload(entity)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build(), "libraryservice");

         Message<?> receivedMessage = this.messageVerifier.receive("libraryservice", 5000, TimeUnit.MILLISECONDS);
         assertNotNull("Resulted event must be published", receivedMessage);

         String receivedPayload = (String) receivedMessage.getPayload();

         BookStatusUpdated outputEvent = objectMapper.readValue(receivedPayload, BookStatusUpdated.class);


         LOGGER.info("Response received: {}", outputEvent);
         
         assertEquals(outputEvent.getBookId(), "BOOK-002");
         assertEquals(outputEvent.getStatus(), "N/A");

      } catch (JsonProcessingException e) {
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }

}
