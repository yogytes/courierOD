package com.yogytes.rest.cod.types;

/**
 * Enumeration and state machine depicting status of consignment
 * 
 * @author yogytes {Yogesha K S}
 * 
 */

public enum Status {

    REQ_ACK("courier request acknowledged") {

        @Override
        Status transition(String state) {
            if (state.equals(Constants.SUCCESS))
                return PICKED_UP;
            return this;
        }

    },
    PICKED_UP("consignment picked up") {

        @Override
        Status transition(String state) {
            if (state.equals(Constants.SUCCESS))
                return IN_TRANSIT;
            return this;
        }
    },
    IN_TRANSIT("consignment in transit") {
        @Override
        Status transition(String state) {
            if (state.equals(Constants.SUCCESS))
                return DELIVERED;
            return FAILED;
        }
    },
    DELIVERED("courier delivered") {

        @Override
        Status transition(String state) {
            return this;
        }
    },
    FAILED("deliver failed") {

        @Override
        Status transition(String state) {
            return this;
        }
    };

    private String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isFinal(Status status){
        if(status.equals(Status.FAILED) || status.equals(DELIVERED))
            return true;
        
        return false;
    }
    abstract Status transition(String state);

}
