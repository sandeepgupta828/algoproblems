package sample.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.api.buster.model.BusterResponse;
import sample.api.buster.BusterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    private BusterService busterService;
    private TransactionRepository transactionRepository;
    private String apiKey;
    private ObjectMapper mapper;

    @Autowired
    public TransactionController(BusterService busterService, TransactionRepository transactionRepository) {
        this.busterService = busterService;
        this.apiKey = busterService.createKey(ImmutableMap.of("webhookUrl", "http://5f0c95f7.ngrok.io/webhooks")).getKey();
        this.transactionRepository = transactionRepository;
        this.mapper = new ObjectMapper();
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction()  {
        try {
            BusterResponse response = busterService.createTransaction(apiKey, ImmutableMap.of("referenceId", RandomStringUtils.random(32, true, true)));
            return new ResponseEntity(saveTransactionToDB(response), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        try {
            List<Transaction> allTransactions = new ArrayList<>();
            Iterable<Transaction> transactionIterable = transactionRepository.findAll();
            transactionIterable.forEach(iter -> allTransactions.add(iter));
            return new ResponseEntity(allTransactions, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/webhooks")
    public void updateTransactionStatus(@RequestBody Map<String, Object> transactionUpdateData) {
        if (transactionUpdateData != null && "TRANSACTION_UPDATE".equals(transactionUpdateData.get("type")) && transactionUpdateData.get("data") != null) {
            BusterResponse busterResponseWebHook = mapper.convertValue(transactionUpdateData.get("data"), BusterResponse.class);
            saveTransactionToDB(busterResponseWebHook);
        }
    }

    private Transaction saveTransactionToDB(BusterResponse response) {
        Transaction transaction = new Transaction(response.getReferenceId(), response.getId(), response.getStatus(), response.getCreated());
        try {
            transactionRepository.save(transaction);
        } catch (Exception ex) {
            System.out.println(ex);
            // execute save to some fallback location (e.g. SQS)
            // do not propagate error to caller as failing to save to DB is not an error
        }
        return transaction;
    }
}
