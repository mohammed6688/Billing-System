import modules.CDR;
import modules.Contract;
import modules.RatePlane;
import modules.SiteDAO;

import java.sql.SQLException;
import java.util.List;

public class Rating {

    public static void FIH(CDR cdrData) throws SQLException {
        //knows the type of the service then pass it to RIH
        String serviceType = "";

        /*
            voice service = 1
            sms service = 2
            data service = 3
            roaming service = 4
            voice cross service = 5
         */
        /*
            on_net = 1
            cross_net = 2
         */
        if (cdrData.getService_id() == 1) {
            serviceType = "voice";
        } else if (cdrData.getService_id() == 2) {
            serviceType = "cross";
        } else if (cdrData.getService_id() == 3) {
            serviceType = "data";
        } else if (cdrData.getService_id() == 4) {
            serviceType = "sms";
        } else if (cdrData.getService_id() == 5) {
            serviceType = "roaming";
        }else {
            try {
                throw new Exception("Service Not Exist !!, Check the CDR generator");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        RIH(cdrData, serviceType);
    }

    public static void RIH(CDR cdr, String typeOfService) throws SQLException {
        //rate the service that user consumed by the help of billingDB
        //========================================================
        //1- Check the contract table and check units based on service
        //2- rate the service (Units based on service) & LE

        RatePlane uRatePlane = null;
        List<RatePlane> ratePlanes =SiteDAO.instanceData.getRatePlane(cdr.getRatePlan_id());
        Contract contract = SiteDAO.instanceData.getContract(cdr.getTerminated_msisdn());
        for (RatePlane ratePlane:ratePlanes){
            if (ratePlane.getId()==cdr.getRatePlan_id()){
                uRatePlane = ratePlane;
            }
        }

        if (uRatePlane==null){
            System.out.println("the ratePlane id is wrong");
            return;
        }
        switch (typeOfService){
            case "voice":
                break;
            case "cross":
                break;
            case "data":
                break;
            case "sms":
                int smsCount = cdr.getDuration();
                int availableSms = uRatePlane.getSms_service();
                int restSms = availableSms - smsCount;
                if (contract==null){
                    System.out.println("the contract not found");
                    return;
                }
                if (restSms>0 || restSms == 0){
                    cdr.setRate(0);
                }else {

                }
                CCH(cdr);
                break;
            case "roaming":
                break;
            default:
                break;
        }

        //CCH(cdr,typeOfVoice);
    }

    public static void CCH(CDR cdr) throws SQLException {
        Integer discount = SiteDAO.instanceData.getDiscount(cdr.getSource_msisdn());
        int OldRate = cdr.getRate();
        int OldDuration = cdr.getDuration();
        int NewRate = 0, NewDuration = 0;
        if (discount == -1) {
            RLH(cdr, NewDuration);
        } else {
            if (OldRate != 0) {
                NewRate = OldRate * (1 - (discount / 100));
                cdr.setRate(NewRate);
            } else {
                NewDuration = OldDuration * (1 - (discount / 100));
            }
            RLH(cdr, NewDuration);
        }
    }

    public static void RLH(CDR cdr, Integer nDuration) throws SQLException {
        //check if user has any free units and re rate the service
        //look in the additional_sp column
        //submit to db consumption table
        String Service_type = SiteDAO.instanceData.getService(cdr.getService_id());
        int oldDuration = cdr.getDuration();
        int nFree = 0;
        String str=null;
        int FreeU = SiteDAO.instanceData.getAddFreeUnits(cdr.getSource_msisdn());
        if (FreeU == -1) {

            // add data to db
        } else {
            if (cdr.getRate() == 0) {
                //duration
                if (nDuration == 0) {
                    //use cdr.duration
                    if (oldDuration > FreeU) {
                        nDuration = oldDuration - FreeU;
                    } else if (oldDuration <= FreeU) {
                        nFree = FreeU - oldDuration;
                    }
                } else {
                    // use nduration
                    if (nDuration > FreeU) {
                        nDuration = nDuration - FreeU;
                    } else if (oldDuration <= FreeU) {
                        nFree = FreeU - nDuration;
                    }
                }
            } else {
                // rate
                // rating again
                // duratin ? service type ? additional service ?
                // duration > free units  duration - freeunitsd =?
                // new rate = ? * additiopnal
                // duration < fre u      free units - duration =?
                // current add sp =?
                if (oldDuration > FreeU) {
                    nDuration = oldDuration - FreeU;

                    RatePlane currentRatePlan = SiteDAO.instanceData.getRatePlane(cdr.getRatePlan_id()).get(0);
                    int price = 0;
                    switch (Service_type) {
                        case "voice":
                        case "cross_voice":
                            price = currentRatePlan.getAdditional_minutes_service();
                            break;
                        case "sms":
                            price = currentRatePlan.getAdditional_sms_service();
                            break;
                        case "data":
                            price = currentRatePlan.getAdditional_data_service();
                            break;
                        case "roaming":
                            price = currentRatePlan.getAdditional_roaming_service();
                            break;
                    }
                    cdr.setRate(price * nDuration);
                    nDuration = 0;
                } else if (oldDuration <= FreeU) {
                    nFree = FreeU - oldDuration;
                    cdr.setRate(0);
                }
            }
        }
        //add to db
        // add to contract database
        switch (Service_type){
            case "voice":
                str="current_voice";
                break;
            case "cross_voice":
                str= "current_cross_voice";
                break;
            case "sms":
                str="current_sms";
                break;
            case "data":
                str="current_data";
                break;
            case "roaming":
                str= "current_roaming";
                break;
        }
        int units= SiteDAO.instanceData.getUnits(cdr.getSource_msisdn(),str);
        units =units-nDuration;
        SiteDAO.instanceData.setUnits(cdr.getSource_msisdn(),str,nDuration,nFree);
        SiteDAO.instanceData.setRTX(cdr);
    }
}
