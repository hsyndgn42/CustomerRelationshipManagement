package com.hd.crm.crm.common;

public class ExceptionMessageBuilder {

    public String generateCustomerNotFoundMessage(Long customerId) {
        return "Customer[customerId= " + customerId + " ] can't be found";
    }

    public String generateAccountNotFoundMessage(Long accountId) {
        return "Account[accountId= " + accountId + " ] can't be found";
    }
}
