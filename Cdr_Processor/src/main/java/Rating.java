import modules.CDR;
import modules.RatePlane;
import modules.SiteDAO;

import java.sql.SQLException;
import java.util.List;

public class Rating {

    public void FIH(CDR cdr) throws SQLException {
        //knows the type of the service then pass it to RIH
        RIH(cdr,"");
    }
    public void RIH(CDR cdr,String typeOfService) throws SQLException {
        //rate the service that user consumed by the help of billingDB
        List<RatePlane> ratePlane =SiteDAO.instanceData.getRatePlane(cdr.getRatePlan_Id());
        CCH(cdr);
    }
    public void CCH(CDR cdr) throws SQLException {

        RLH(cdr);
    }
    public void RLH(CDR cdr) throws SQLException{
        //check if user has any free units and re rate the service
        //look in the additional_sp column
        //submit to db consumption table
        SiteDAO.instanceData.setConsumption(cdr);

    }
}
