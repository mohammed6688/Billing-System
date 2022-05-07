package modules;

public class ContractCons {
    String msisdn;
    String serviceType;
    int fee;

    public ContractCons(String msisdn, String serviceType, int fee) {
        this.msisdn = msisdn;
        this.serviceType = serviceType;
        this.fee = fee;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
