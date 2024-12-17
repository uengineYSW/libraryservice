
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
public class ReturnLoanTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(ReturnLoanTest.class);
   
   @Autowired
   private MessageCollector messageCollector;
   
   @Autowired
   private ApplicationContext applicationContext;

   @Autowired
   ObjectMapper objectMapper;

   @Autowired
   private MessageVerifier<Message<?>> messageVerifier;

   @Autowired
   public LoanRepository repository;

   @Test
   @SuppressWarnings("unchecked")
   public void test0() {

      //given:  
      Loan entity = new Loan();

      entity.setLoanId(1001L);
      entity.setMember([object Object]);
      entity.setBookReference([object Object]);
      entity.setLoanPeriod([object Object]);
      entity.setLoanDate("2023-10-01");
      entity.setReturnDueDate("2023-10-15");
      entity.setStatus("LOANED");

      repository.save(entity);

      //when:  
      try {


      
         ReturnLoan command = new ReturnLoanCommand();

         command.setLoanId(1001L);
         command.setReturnDate("2023-10-10");

         entity.ReturnLoan(command);


           

         //then:
         this.messageVerifier.send(MessageBuilder
                .withPayload(entity)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build(), "libraryservice");

         Message<?> receivedMessage = this.messageVerifier.receive("libraryservice", 5000, TimeUnit.MILLISECONDS);
         assertNotNull("Resulted event must be published", receivedMessage);

         String receivedPayload = (String) receivedMessage.getPayload();

         LoanReturned outputEvent = objectMapper.readValue(receivedPayload, LoanReturned.class);


         LOGGER.info("Response received: {}", outputEvent);
         
         assertEquals(outputEvent.getLoanId(), 1001L);
         assertEquals(outputEvent.getReturnDate(), "2023-10-10");
         assertEquals(outputEvent.getStatus(), "RETURNED");

      } catch (JsonProcessingException e) {
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }

}
