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

    public static void RIH(CDR cdr, int typeOfService,int typeOfVoice) throws SQLException {
        //rate the service that user consumed by the help of billingDB
        // 1- Aggregate User Consumption from DB [if exit (in new bill cycle case)]
        // 2- Know his Rateplan and each units
        // 3- Subtract his consumption from his Rateplan
        // 4-
        //========================================================
        //1- Check the contract table and check units based on service
        //2- rate the service (Units based on service) & LE

        List<RatePlane> uRatePlane =SiteDAO.instanceData.getRatePlane(cdr.getRatePlan_id());

        //voice service = 1, sms service = 2, data service = 3,roaming service = 4
        switch (typeOfService){
            case 1:
                switch (typeOfVoice){
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

        RLH(cdr);
    }

    public static void RLH(CDR cdr) throws SQLException {
        //check if user has any free units and re rate the service
        //look in the additional_sp column
        //submit to db consumption table
        SiteDAO.instanceData.setConsumption(cdr);

    }
}
