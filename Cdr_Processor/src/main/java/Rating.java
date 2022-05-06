import modules.CDR;
import modules.RatePlane;
import modules.SiteDAO;

import java.sql.SQLException;
import java.util.List;

public class Rating {

    public static void FIH(CDR cdrData) throws SQLException {
        //knows the type of the service then pass it to RIH
        int serviceType = 0;
        int netIdentification = 0;

        /*
            voice service = 1
            sms service = 2
            data service = 3
            roaming service = 4
         */
        /*
            on_net = 1
            cross_net = 2
         */
        if (cdrData.getService_id() == 1) {
            serviceType = 1;
            if (cdrData.getTerminated_msisdn().regionMatches(0, "011", 0, 3)) {
                netIdentification = 1;
                System.out.println("I'm onNet ");
            } else {
                netIdentification = 2;
                System.out.println("I'm crossNet ");
            }
        } else if (cdrData.getService_id() == 2) {
            serviceType = 2;
        } else if (cdrData.getService_id() == 3) {
            serviceType = 3;
        } else if (cdrData.getService_id() == 4) {
            serviceType = 4;
        } else {
            try {
                throw new Exception("Service Not Exist !!, Check the CDR generator");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        RIH(cdrData, serviceType, netIdentification);
    }

    public static void RIH(CDR cdr, int typeOfService, int typeOfVoice) throws SQLException {
        //rate the service that user consumed by the help of billingDB
        // 1- Aggregate User Consumption from DB [if exit (in new bill cycle case)]
        // 2- Know his Rateplan and each units
        // 3- Subtract his consumption from his Rateplan
        // 4-
        //========================================================
        //1- Check the contract table and check units based on service
        //2- rate the service (Units based on service) & LE

        List<RatePlane> uRatePlane = SiteDAO.instanceData.getRatePlane(cdr.getRatePlan_id());

        //voice service = 1, sms service = 2, data service = 3,roaming service = 4
        switch (typeOfService) {
            case 1:
                switch (typeOfVoice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }

        CCH(cdr);
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
        String Service_type = SiteDAO.instanceData.getService(cdr.getService_id());
        int FreeU = SiteDAO.instanceData.getAddFreeUnits(cdr.getSource_msisdn());
        RatePlane currentRatePlan = SiteDAO.instanceData.getRatePlane(cdr.getRatePlan_id()).get(0);
        String str=serviceTypeMapping(Service_type);

        int oldDuration = cdr.getDuration();
        int nFree = 0, price = 0;

        if (FreeU != -1) {
            if (cdr.getRate() == 0) {
                //duration
                if (nDuration == 0)  nDuration=oldDuration;
                // use nduration
                if (nDuration > FreeU) nDuration = nDuration - FreeU;
                else nFree = FreeU - nDuration;

            } else {
                if (oldDuration > FreeU) {
                    nDuration = oldDuration - FreeU;
                    price=getPrice(currentRatePlan,Service_type);
                    cdr.setRate(price * nDuration);
                    nDuration = 0;
                } else {
                    nFree = FreeU - oldDuration;
                    cdr.setRate(0);
                }
            }
        }
        int units= SiteDAO.instanceData.getUnits(cdr.getSource_msisdn(),str);
        units =units-nDuration;
        SiteDAO.instanceData.setUnits(cdr.getSource_msisdn(),str,units,nFree);
        SiteDAO.instanceData.setRTX(cdr);
    }
    private static String serviceTypeMapping(String Service_type){
        String str =null;
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
        return str;
    }
    private static int getPrice(RatePlane ratePlane ,String Service_type){

        int price = 0;
        switch (Service_type) {
            case "voice":
            case "cross_voice":
                price = ratePlane.getAdditional_minutes_service();
                break;
            case "sms":
                price = ratePlane.getAdditional_sms_service();
                break;
            case "data":
                price = ratePlane.getAdditional_data_service();
                break;
            case "roaming":
                price = ratePlane.getAdditional_roaming_service();
                break;
        }
        return price;
    }
}
