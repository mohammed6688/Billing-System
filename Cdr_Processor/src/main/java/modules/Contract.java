package modules;

public class Contract {
    int contract_id;
    int msisdn;
    int rateplane_id;
    int userid;
    int additional_sp;
    int current_voice;
    int current_cross_voice;
    int current_data;
    int current_sms;
    int current_roaming;
    int discount;

    public Contract(int contract_id, int msisdn, int rateplane_id, int userid, int additional_sp, int current_voice, int current_cross_voice, int current_data, int current_sms, int current_roaming, int discount) {
        this.contract_id = contract_id;
        this.msisdn = msisdn;
        this.rateplane_id = rateplane_id;
        this.userid = userid;
        this.additional_sp = additional_sp;
        this.current_voice = current_voice;
        this.current_cross_voice = current_cross_voice;
        this.current_data = current_data;
        this.current_sms = current_sms;
        this.current_roaming = current_roaming;
        this.discount = discount;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(int msisdn) {
        this.msisdn = msisdn;
    }

    public int getRateplane_id() {
        return rateplane_id;
    }

    public void setRateplane_id(int rateplane_id) {
        this.rateplane_id = rateplane_id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAdditional_sp() {
        return additional_sp;
    }

    public void setAdditional_sp(int additional_sp) {
        this.additional_sp = additional_sp;
    }

    public int getCurrent_voice() {
        return current_voice;
    }

    public void setCurrent_voice(int current_voice) {
        this.current_voice = current_voice;
    }

    public int getCurrent_cross_voice() {
        return current_cross_voice;
    }

    public void setCurrent_cross_voice(int current_cross_voice) {
        this.current_cross_voice = current_cross_voice;
    }

    public int getCurrent_data() {
        return current_data;
    }

    public void setCurrent_data(int current_data) {
        this.current_data = current_data;
    }

    public int getCurrent_sms() {
        return current_sms;
    }

    public void setCurrent_sms(int current_sms) {
        this.current_sms = current_sms;
    }

    public int getCurrent_roaming() {
        return current_roaming;
    }

    public void setCurrent_roaming(int current_roaming) {
        this.current_roaming = current_roaming;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
