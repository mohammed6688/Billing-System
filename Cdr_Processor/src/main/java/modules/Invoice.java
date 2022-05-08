package modules;

import java.util.List;

public class Invoice {
    int uid;
    int totalFee;
    List<ContractCons> uContractCons;

    public Invoice(int uid, int totalFee, List<ContractCons> uContractCons) {
        this.uid = uid;
        this.totalFee = totalFee;
        this.uContractCons = uContractCons;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public List<ContractCons> getuContractCons() {
        return uContractCons;
    }

    public void setuContractCons(List<ContractCons> uContractCons) {
        this.uContractCons = uContractCons;
    }
}
