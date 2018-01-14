package com.gbcorp.sivbeta;

/**
 * Created by GBCorp on 19/08/2017.
 */

public class FirstAidDetailModel {
    String CurrentDate;
    String FirstAidReason;
    String FirstAidDetail;
    String HospitalName;
    String HospitalFees;
    String Remarks;

    // Empty Constructor
    public FirstAidDetailModel()
    {

    }

    // Constructor
    public FirstAidDetailModel(
            String CurrentDate,
            String FirstAidReason,
            String FirstAidDetail,
            String HospitalName,
            String HospitalFees,
            String Remarks
    )
    {
        super();
        this.CurrentDate = CurrentDate;
        this.FirstAidReason = FirstAidReason;
        this.FirstAidDetail = FirstAidDetail;
        this.HospitalName = HospitalName;
        this.HospitalFees = HospitalFees;
        this.Remarks = Remarks;
    }

    // Getter and Setter Method
    public String getCurrentDate()
    {
        return CurrentDate;
    }
    public void setCurrentDate(String CurrentDate)
    {
        this.CurrentDate = CurrentDate;
    }

    public String getFirstAidReason()
    {
        return FirstAidReason;
    }
    public void setFirstAidReason(String FirstAidReason)
    {
        this.FirstAidReason = FirstAidReason;
    }

    public String getFirstAidDetail()
    {
        return FirstAidDetail;
    }
    public void setFirstAidDetail(String FirstAidDetail)
    {
        this.FirstAidDetail = FirstAidDetail;
    }

    public String getHospitalName()
    {
        return HospitalName;
    }
    public void setHospitalName(String HospitalName)
    {
        this.HospitalName = HospitalName;
    }

    public String getHospitalFees()
    {
        return HospitalFees;
    }
    public void setHospitalFees(String HospitalFees)
    {
        this.HospitalFees = HospitalFees;
    }

    public String getRemarks()
    {
        return Remarks;
    }
    public void setRemarks(String Remarks)
    {
        this.Remarks = Remarks;
    }


}
