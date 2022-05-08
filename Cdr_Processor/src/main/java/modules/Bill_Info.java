package modules;
public class Bill_Info {

    private String msisdn;
    private String rateplane;
    private int monthlyFees;

    private int extraConsumedCross;
    private int RatedExtraCross;

    private int extraConsumedVoice;
    private int RatedExtraVoice;

    private int extraConsumedData;
    private int RatedExtraData;

    private int extraConsumedSMS;
    private int RatedExtraSMS;

    private int extraConsumedRoaming;
    private int RatedExtraRoaming;

    private int extraFees;
    private int totalFees;


    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getRateplane() {
        return rateplane;
    }

    public void setRateplane(String rateplane) {
        this.rateplane = rateplane;
    }

    public int getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(int monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    public int getExtraConsumedVoice() {
        return extraConsumedVoice;
    }

    public void setExtraConsumedVoice(int extraConsumedVoice) {
        this.extraConsumedVoice = extraConsumedVoice;
    }

    public int getRatedExtraVoice() {
        return RatedExtraVoice;
    }

    public void setRatedExtraVoice(int ratedExtraVoice) {
        RatedExtraVoice = ratedExtraVoice;
    }

    public int getExtraConsumedCross() {
        return extraConsumedCross;
    }

    public void setExtraConsumedCross(int extraConsumedCross) {
        this.extraConsumedCross = extraConsumedCross;
    }

    public int getRatedExtraCross() {
        return RatedExtraCross;
    }

    public void setRatedExtraCross(int ratedExtraCross) {
        RatedExtraCross = ratedExtraCross;
    }

    public int getExtraConsumedData() {
        return extraConsumedData;
    }

    public void setExtraConsumedData(int extraConsumedData) {
        this.extraConsumedData = extraConsumedData;
    }

    public int getRatedExtraData() {
        return RatedExtraData;
    }

    public void setRatedExtraData(int ratedExtraData) {
        RatedExtraData = ratedExtraData;
    }

    public int getExtraConsumedSMS() {
        return extraConsumedSMS;
    }

    public void setExtraConsumedSMS(int extraConsumedSMS) {
        this.extraConsumedSMS = extraConsumedSMS;
    }

    public int getRatedExtraSMS() {
        return RatedExtraSMS;
    }

    public void setRatedExtraSMS(int ratedExtraSMS) {
        RatedExtraSMS = ratedExtraSMS;
    }


    public int getExtraConsumedRoaming() {
        return extraConsumedRoaming;
    }

    public void setExtraConsumedRoaming(int extraConsumedRoaming) {
        this.extraConsumedRoaming = extraConsumedRoaming;
    }

    public int getRatedExtraRoaming() {
        return RatedExtraRoaming;
    }

    public void setRatedExtraRoaming(int ratedExtraRoaming) {
        RatedExtraRoaming = ratedExtraRoaming;
    }

    public int getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(int totalFees) {
        this.totalFees = totalFees;
    }

    public int getExtraFees() {
        return extraFees;
    }

    public void setExtraFees(int extraFees) {
        this.extraFees = extraFees;
    }
}
