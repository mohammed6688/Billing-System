package modules;

public class CDR {
    private int id;
    private String source_msisdn,terminated_msisdn;
    private String timestamp;
    private int duration;
    private int rate;
    private int service_id;
    private int ratePlan_id;

    public CDR(){
        this.id = 0;
        this.source_msisdn = null;
        this.terminated_msisdn = null;
        this.timestamp = null;
        this.duration = 0;
        this.rate = 0;
        this.service_id = 0;
        this.ratePlan_id = 0;
    }
    public CDR(int id, String source_msisdn, String terminated_msisdn, String timestamp, int duration, int rate, int service_id, int ratePlan_id) {
        this.id = id;
        this.source_msisdn = source_msisdn;
        this.terminated_msisdn = terminated_msisdn;
        this.timestamp = timestamp;
        this.duration = duration;
        this.rate = rate;
        this.service_id = service_id;
        this.ratePlan_id = ratePlan_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource_msisdn() {
        return source_msisdn;
    }

    public void setSource_msisdn(String source_msisdn) {
        this.source_msisdn = source_msisdn;
    }

    public String getTerminated_msisdn() {
        return terminated_msisdn;
    }

    public void setTerminated_msisdn(String terminated_msisdn) {
        this.terminated_msisdn = terminated_msisdn;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getRatePlan_id() {
        return ratePlan_id;
    }

    public void setRatePlan_id(int ratePlan_id) {
        this.ratePlan_id = ratePlan_id;
    }
}
