package com.pm.billing_service.grpc;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount (billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> responseObserver) {
    //A StreamObserver is a grpc interface that allows incoming and outgoing streams of data in real time between the service and the client once the connection is established once.
        log.info("createBillingAccount request received {}", billingRequest.toString());

        // Business logic - e.g save to database, perform calculates etc.

        BillingResponse billingResponse = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(billingResponse); //sends response from grpc service to client
        responseObserver.onCompleted(); //response completed and ready to end cycle

    }
}
