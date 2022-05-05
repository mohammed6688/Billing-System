import modules.CDR;
import modules.Contract;
import modules.RatePlane;
import modules.SiteDAO;

import java.sql.SQLException;
import java.util.List;

public class Rating {

    public static void FIH(CDR cdrData) throws SQLException {
        //knows the type of the service then pass it to RIH
        int serviceType = 0;

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
            serviceType = 1;
        } else if (cdrData.getService_id() == 2) {
            serviceType = 2;
        } else if (cdrData.getService_id() == 3) {
            serviceType = 3;
        } else if (cdrData.getService_id() == 4) {
            serviceType = 4;
        } else if (cdrData.getService_id() == 5) {
            serviceType = 5;
        }else {
            try {
                throw new Exception("Service Not Exist !!, Check the CDR generator");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        RIH(cdrData, serviceType);
    }

    public static void RIH(CDR cdr, int typeOfService) throws SQLException {
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

        //voice service = 1, sms service = 2, data service = 3,roaming service = 4
        switch (typeOfService){
            case 1: //voice
                break;
            case 2: //data
                break;
            case 3: //sms
                int smsCount = cdr.getDuration();
                int availableSms =uRatePlane.getSms_service();
                int restSms = availableSms - smsCount;
                if (contract==null){
                    System.out.println("the contract not found");
                    return;
                }
                if (restSms>0){
                    cdr.setRate(0);
                }else {

                }
                CCH(cdr);
                break;
            case 4: //roaming
                break;
            case 5: //cross voice
                break;
            default:
                break;
        }

        //CCH(cdr,typeOfVoice);
    }

    public static void CCH(CDR cdr) throws SQLException {

        RLH(cdr);
    }

    public static void RLH(CDR cdr) throws SQLException {
        //check if user has any free units and re rate the service
        //look in the additional_sp column
        //submit to db consumption table
        SiteDAO.instanceData.setConsumption(cdr);

    }
}
