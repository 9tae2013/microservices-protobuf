package it.me.tae.customer.contract;

import it.me.tae.customer.model.CustomerProto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "account-service")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/customer/{customerId}")
    Accounts getAccounts(@PathVariable("customerId") Integer customerId);

}