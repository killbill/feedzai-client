package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaymentRequest {

    // The unique user ID for the buyer, used in your system. Example: “af00-bc14-1245”
    private final String userId;
    // The payment’s amount in cents. Example: 11099 (represents $110.99)
    private final Integer amount;
    // The user device IP at purchase time. Example: “89.180.212.63”
    private final String ip;
    // The unique payment ID in your system. If omitted, we automatically generate one. Example: “124212-00245”
    private final String id;

    private final List<Item> items;

    private final String transactionType;
    private Boolean cardCvvPresent;

    private final String userEmail;
    private final String userFullname;
    private final Long userCreatedAt;

    private final String userGender;
    private final String userDateofbirth;
    private final String userPhone;
    private final String userAddressLine1;
    private final String userAddressLine2;

    private final String userZip;
    private final String userCity;
    private final String userRegion;
    private final String userCountry;

    private final String deviceId;

    private final String cardHash;
    private final String cardFullname;
    private final String cardExp;
    private final String cardCountry;
    private final Integer cardBin;
    private final Integer cardLast4;

    private final String billingPhone;
    private final String billingAddressLine1;
    private final String billingAddressLine2;
    private final String billingZip;
    private final String billingCity;
    private final String billingRegion;
    private final String billingCountry;

    private final String shippingFullname;
    private final String shippingPhone;
    private final String shippingAddressLine1;
    private final String shippingAddressLine2;
    private final String shippingZip;
    private final String shippingCity;
    private final String shippingRegion;
    private final String shippingCountry;

    private final String merchantId;
    private final Long merchantCreatedAt;
    private final Integer merchantMcc;
    private final String merchantEmail;
    private final String merchantCountry;

    private final String detailsUrl;
    private final Object userDefined;


    @JsonCreator
    public PaymentRequest(@JsonProperty("userId") String userId,
                          @JsonProperty("amount") Integer amount,
                          @JsonProperty("ip") String ip,
                          @JsonProperty("id") String id,
                          @JsonProperty("items") List<Item> items,
                          @JsonProperty("transactionType") String transactionType,
                          @JsonProperty("cardCvvPresent") Boolean cardCvvPresent,
                          @JsonProperty("userEmail") String userEmail,
                          @JsonProperty("userFullname") String userFullName,
                          @JsonProperty("userCreatedAt") Long userCreatedAt,
                          @JsonProperty("userGender") String userGender,
                          @JsonProperty("userDateofbirth") String userDateofbirth,
                          @JsonProperty("userPhone") String userPhone,
                          @JsonProperty("userAddressLine1") String userAddressLine1,
                          @JsonProperty("userAddressLine2") String userAddressLine2,
                          @JsonProperty("userZip") String userZip,
                          @JsonProperty("userCity") String userCity,
                          @JsonProperty("userRegion") String userRegion,
                          @JsonProperty("userCountry") String userCountry,
                          @JsonProperty("deviceId") String deviceId,
                          @JsonProperty("cardHash") String cardHash,
                          @JsonProperty("cardFullname") String cardFullname,
                          @JsonProperty("cardExp") String cardExp,
                          @JsonProperty("cardCountry") String cardCountry,
                          @JsonProperty("cardBin") Integer cardBin,
                          @JsonProperty("cardLast4") Integer cardLast4,
                          @JsonProperty("billingPhone") String billingPhone,
                          @JsonProperty("billingAddressLine1") String billingAddressLine1,
                          @JsonProperty("billingAddressLine2") String billingAddressLine2,
                          @JsonProperty("billingZip") String billingZip,
                          @JsonProperty("billingCity") String billingCity,
                          @JsonProperty("billingRegion") String billingRegion,
                          @JsonProperty("billingCountry") String billingCountry,
                          @JsonProperty("shippingFullname") String shippingFullname,
                          @JsonProperty("shippingPhone") String shippingPhone,
                          @JsonProperty("shippingAddressLine1") String shippingAddressLine1,
                          @JsonProperty("shippingAddressLine2") String shippingAddressLine2,
                          @JsonProperty("shippingZip") String shippingZip,
                          @JsonProperty("shippingCity") String shippingCity,
                          @JsonProperty("shippingRegion") String shippingRegion,
                          @JsonProperty("shippingCountry") String shippingCountry,
                          @JsonProperty("merchantId") String merchantId,
                          @JsonProperty("merchantCreatedAt") Long merchantCreatedAt,
                          @JsonProperty("merchantMcc") Integer merchantMcc,
                          @JsonProperty("merchantEmail") String merchantEmail,
                          @JsonProperty("merchantCountry") String merchantCountry,
                          @JsonProperty("detailsUrl") String detailsUrl,
                          @JsonProperty("userDefined") Object userDefined) {
        this.userId = userId;
        this.amount = amount;
        this.ip = ip;
        this.id = id;
        this.items = items;
        this.transactionType = transactionType;
        this.cardCvvPresent = cardCvvPresent;
        this.userEmail = userEmail;
        this.userFullname = userFullName;
        this.userCreatedAt = userCreatedAt;
        this.userGender = userGender;
        this.userDateofbirth = userDateofbirth;
        this.userPhone = userPhone;
        this.userAddressLine1 = userAddressLine1;
        this.userAddressLine2 = userAddressLine2;
        this.userZip = userZip;
        this.userCity = userCity;
        this.userRegion = userRegion;
        this.userCountry = userCountry;
        this.deviceId = deviceId;
        this.cardHash = cardHash;
        this.cardFullname = cardFullname;
        this.cardExp = cardExp;
        this.cardCountry = cardCountry;
        this.cardBin = cardBin;
        this.cardLast4 = cardLast4;
        this.billingPhone = billingPhone;
        this.billingAddressLine1 = billingAddressLine1;
        this.billingAddressLine2 = billingAddressLine2;
        this.billingZip = billingZip;
        this.billingCity = billingCity;
        this.billingRegion = billingRegion;
        this.billingCountry = billingCountry;
        this.shippingFullname = shippingFullname;
        this.shippingPhone = shippingPhone;
        this.shippingAddressLine1 = shippingAddressLine1;
        this.shippingAddressLine2 = shippingAddressLine2;
        this.shippingZip = shippingZip;
        this.shippingCity = shippingCity;
        this.shippingRegion = shippingRegion;
        this.shippingCountry = shippingCountry;
        this.merchantId = merchantId;
        this.merchantCreatedAt = merchantCreatedAt;
        this.merchantMcc = merchantMcc;
        this.merchantEmail = merchantEmail;
        this.merchantCountry = merchantCountry;
        this.detailsUrl = detailsUrl;
        this.userDefined = userDefined;
    }

    // For convenience... until we want to populate more fields ;-)
    public PaymentRequest(String userId,
                          Integer amount,
                          String ip,
                          String id,
                          String userFullName,
                          String cardCountry,
                          String billingCountry) {
        this(userId,
                amount,
                ip,
                id,
                null,
                null,
                null,
                null,
                userFullName,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                cardCountry,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                billingCountry,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }


    public String getUserId() {
        return userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getIp() {
        return ip;
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Boolean getCardCvvPresent() {
        return cardCvvPresent;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public Long getUserCreatedAt() {
        return userCreatedAt;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserDateofbirth() {
        return userDateofbirth;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserAddressLine1() {
        return userAddressLine1;
    }

    public String getUserAddressLine2() {
        return userAddressLine2;
    }

    public String getUserZip() {
        return userZip;
    }

    public String getUserCity() {
        return userCity;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getCardHash() {
        return cardHash;
    }

    public String getCardFullname() {
        return cardFullname;
    }

    public String getCardExp() {
        return cardExp;
    }

    public String getCardCountry() {
        return cardCountry;
    }

    public Integer getCardBin() {
        return cardBin;
    }

    public Integer getCardLast4() {
        return cardLast4;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingRegion() {
        return billingRegion;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public String getShippingFullname() {
        return shippingFullname;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public String getShippingAddressLine1() {
        return shippingAddressLine1;
    }

    public String getShippingAddressLine2() {
        return shippingAddressLine2;
    }

    public String getShippingZip() {
        return shippingZip;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingRegion() {
        return shippingRegion;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public Long getMerchantCreatedAt() {
        return merchantCreatedAt;
    }

    public Integer getMerchantMcc() {
        return merchantMcc;
    }

    public String getMerchantEmail() {
        return merchantEmail;
    }

    public String getMerchantCountry() {
        return merchantCountry;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public Object getUserDefined() {
        return userDefined;
    }

public static class Item {

    private final String itemId;
    private final Integer quantity;
    private final String name;
    private final Integer price;
    private final String url;

    @JsonCreator
    public Item(@JsonProperty("itemId") String itemId,
                @JsonProperty("quantity") Integer quantity,
                @JsonProperty("name") String name,
                @JsonProperty("price") Integer price,
                @JsonProperty("url") String url) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }
}
}
