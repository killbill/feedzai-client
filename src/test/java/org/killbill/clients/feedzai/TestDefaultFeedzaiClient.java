package org.killbill.clients.feedzai;


import com.google.common.collect.ImmutableList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class TestDefaultFeedzaiClient {

    private DefaultFeedzaiClient client;

    private Random random;


    @BeforeClass(groups = "slow")
    public void beforeTest() {
        final String apiKey = System.getProperty("org-kill-bill.clients.feedzai.api.key");
        Assert.assertNotNull(apiKey, "Need to specify client API KEY");
        client = new DefaultFeedzaiClient(true, apiKey);
        random = new Random();

    }

    @Test(groups = "slow")
    public void testScorePayment() throws FeedzaiClientException {

        final String paymentId = "test_pay_" + random.nextLong();
        final PaymentRequest request = new PaymentRequest("test_cli", 20, "72.21.91.19", paymentId, null, null, null);
        final PaymentResponse result = client.scorePayment(request);

        final PreviousPaymentResponse previous = client.getPreviousPayment(paymentId);
        Assert.assertEquals(previous.getPayment().getAmount(), request.getAmount());
        Assert.assertEquals(previous.getPayment().getId(), request.getId());
        Assert.assertEquals(previous.getPayment().getIp(), request.getIp());
        Assert.assertEquals(previous.getPayment().getUserId(), request.getUserId());

        Assert.assertEquals(previous.getScore().getScore(), result.getScore());
        Assert.assertEquals(previous.getScore().getBaseRisk(), result.getBaseRisk());
    }

    @Test(groups = "slow")
    public void testLabelPayment() throws FeedzaiClientException {

        final String paymentId = "test_pay_" + random.nextLong();
        final PaymentRequest request = new PaymentRequest("test_cli", 20, "72.21.91.19", paymentId, null, null, null);
        client.scorePayment(request);

        client.labelPreviousPayment(paymentId, new LabelRequest("fraud"));

        final PreviousPaymentResponse previous = client.getPreviousPayment(paymentId);
        Assert.assertEquals(previous.getLabel(), "fraud");
    }


    @Test(groups = "slow")
    public void testHistoricalPayments() throws FeedzaiClientException {

        final String paymentId1 = "test_historical_pay_" + random.nextLong();
        final PaymentRequestWithTimestamp request1 = new PaymentRequestWithTimestamp("test_historical_cli", 20, "72.21.91.19", paymentId1, System.currentTimeMillis());

        final String paymentId2 = "test_historical_pay_" + random.nextLong();
        final PaymentRequestWithTimestamp request2 = new PaymentRequestWithTimestamp("test_historical_cli", 20, "72.21.91.19", paymentId2, System.currentTimeMillis());

        final String paymentId3 = "test_historical_pay_" + random.nextLong();
        final PaymentRequestWithTimestamp request3 = new PaymentRequestWithTimestamp("test_historical_cli", 20, "72.21.91.19", paymentId3, System.currentTimeMillis());

        final List<HistoricalPayments.HistoricalPayment> payments = new ImmutableList.Builder<HistoricalPayments.HistoricalPayment>()
                .add(new HistoricalPayments.HistoricalPayment(request1, "ok"))
                .add(new HistoricalPayments.HistoricalPayment(request2, "ok"))
                .add(new HistoricalPayments.HistoricalPayment(request3, "fraud"))
                .build();
        client.sendHistoricalPayments(new HistoricalPayments(payments));

        /*
        Seems like they don't show up, just for training purpose:
        "You can upload one or more historic payments (previously labeled), in bulk. These payments will not be scored but are crucial in building historic profiles of your business."

        final PreviousPaymentResponse previous1 = client.getPreviousPayment(paymentId1);
        Assert.assertEquals(previous1.getPayment().getIp(), paymentId1);
        Assert.assertEquals(previous1.getLabel(), "ok");

        final PreviousPaymentResponse previous2 = client.getPreviousPayment(paymentId2);
        Assert.assertEquals(previous2.getPayment().getIp(), paymentId2);
        Assert.assertEquals(previous2.getLabel(), "ok");

        final PreviousPaymentResponse previous3 = client.getPreviousPayment(paymentId3);
        Assert.assertEquals(previous3.getPayment().getIp(), paymentId3);
        Assert.assertEquals(previous3.getLabel(), "fraud");
        */
    }

    @Test(groups = "slow")
    public void testBlockUser() throws FeedzaiClientException {

        final String userId = "test_block_user_" + random.nextLong();
        client.blockUser(userId, new StatusRequest("blocked"));

        /* Broken...
        final UserStatusResponse result = client.getUserStatus(userId);
        Assert.assertEquals(result.getUserId(), userId);
        Assert.assertEquals(result.getStatus(), "blocked");
        */
    }


    @Test(groups = "slow")
    public void testSendUserAction() throws FeedzaiClientException {

        final String userId = "test_block_user_" + random.nextLong();
        final UserActionRequest request = new UserActionRequest(userId, "72.21.91.20", "login", "my_item", System.currentTimeMillis(), "target");
        client.sendUserAction(request);
    }
}
