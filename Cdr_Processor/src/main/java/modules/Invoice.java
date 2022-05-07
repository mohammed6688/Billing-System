package modules;

import java.util.List;

public class Invoice {
    int uid;
    int totalFee;
    List<contractCons> uContractCons;

    public Invoice(int uid, int totalFee, List<contractCons> uContractCons) {
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

    public List<contractCons> getuContractCons() {
        return uContractCons;
    }

    public void setuContractCons(List<contractCons> uContractCons) {
        this.uContractCons = uContractCons;
    }
}
