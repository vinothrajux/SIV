package com.gbcorp.sivbeta;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by VinothJoshua on 8/3/2017.
 */

public class Utils {
    public static String userId;
    public static int instituteId;
    public static String userRole;
    public static JSONObject userData;
    public static JSONArray studentList;

    public Utils()
    {
    }
    public JSONObject getUserData()
    {
        return userData;
    }
    public void setUserData(JSONObject userdata)
    {
        userData = userdata;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userid)
    {
        userId = userid;
    }
    public String getUserRole()
    {
        return userRole;
    }
    public void setUserRole(String userrole)
    {
        userRole = userrole;
    }
    public int getInstituteId()
    {
        return instituteId;
    }
    public void setInstituteId(int instituteid)
    {
        instituteId = instituteid;
    }

   public String getApiHost(){
        return "192.168.43.195:8080";
   }
//    public String getApiHost(){
//        return "ec2-13-59-171-34.us-east-2.compute.amazonaws.com:8080";
//    }
    public void setPhotoGalleryStudentList(JSONArray studentListArray) {
        studentList = studentListArray;
    }
    public JSONArray getPhotoGalleryStudentList() {
        return studentList;
    }
    public void updatePhotoAccessForStudent(int position, int photoAccess) {
        try {
            studentList.getJSONObject(position).put("studentphotoaccess", photoAccess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    public String convertToDateFormat(String getdate){
        long l = Long.parseLong(getdate);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(l);
        String date = DateFormat.format("dd/MM/yyyy", cal).toString();
        return date;
    }
    public String getDayOfDate(String givendate){
        long l = Long.parseLong(givendate);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(l);
        String date = DateFormat.format("EEEE", cal).toString();
        return date;
    }
    public ArrayList<Item> fillData()
    {
        ArrayList<Item> data = new ArrayList<Item>();
        Utils utils = new Utils();
        JSONObject jsonObj = new JSONObject();
        jsonObj = utils.getUserData();
        try {
            String menulistStr = jsonObj.getString("menulist");
            JSONArray menuListArray = new JSONArray(menulistStr);
            JSONObject menuItem = new JSONObject();
            String menuName = "", FragmentName = "", iconName = "";
            for (int i = 0; i<menuListArray.length();i++){
                menuItem = null;
                menuName = "";
                FragmentName = "";
                iconName = "";
                menuItem = menuListArray.getJSONObject(i);
                menuName = menuItem.getString("menuitem");
                iconName = menuItem.getString("icon");
                FragmentName = menuItem.getString("fragmentname");
                data.add(new Item(menuName,iconName,FragmentName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        data.add(new Item("Change Password", "profile",".ChangePassword"));
        data.add(new Item("Feedback", "attendance",".ParentFeedBackFragment"));
        Log.e("dataid",data.toString());
        return data;
//        switch(getUserRole()){
//            case "MGMT":
//
//                data.add(new Item("Student Attendance",R.drawable.attendance,".AdmissionCounsellingFragment"));
//                data.add(new Item("Staff Attendance",R.drawable.staff_attendance,".AdmissionCounsellingFragment"));
//                data.add(new Item("School Fees Set",R.drawable.accounts1,".AdmissionCounsellingFragment"));
//                data.add(new Item("Transport Fees Set",R.drawable.accounts1,".AdmissionCounsellingFragment"));
//                data.add(new Item("Daily Fees Report",R.drawable.reports,".AdmissionCounsellingFragment"));
//                data.add(new Item("Admission Status",R.drawable.accounts,".AdmissionCounsellingFragment"));
//                data.add(new Item("Student Progress",R.drawable.performance,".AdmissionCounsellingFragment"));
//                data.add(new Item("Class Progress",R.drawable.performance,".AdmissionCounsellingFragment"));
//                data.add(new Item("Parent FeedBack",R.drawable.feedback,".AdmissionCounsellingFragment"));
//                data.add(new Item("Reports",R.drawable.reports,".AdmissionCounsellingFragment"));
//                data.add(new Item("View Feedbacks", R.drawable.attendance,".ViewFeedbackFragment"));
//
////                data.add(new Item("Admission Counselling",R.drawable.admission,".AdmissionCounsellingFragment"));
////                data.add(new Item("Admission Status", R.drawable.accounts,".AdmissionStatusFragment"));
////                data.add(new Item("College Fees Setting", R.drawable.accounts1,".CollegeFeesSettingFragment"));
////                data.add(new Item("Transport Fees Setting", R.drawable.accounts1,".TransportFeesSettingFragment"));
////                data.add(new Item("Hostel Fees Setting", R.drawable.accounts1,".HostelFeesSettingFragment"));
////                data.add(new Item("Food Fees Setting", R.drawable.accounts1,".HostelFeesSettingFragment"));
////                data.add(new Item("Staff Attendance", R.drawable.staff_attendance,".StaffAttendanceFragment"));
////                data.add(new Item("Student Attendance", R.drawable.attendance,".StudentAttendanceFragment"));
////                data.add(new Item("Staff Performance", R.drawable.performance,".StaffPerformanceFragment"));
////                data.add(new Item("Department Performance", R.drawable.performance,".DepartmentPerformanceFragment"));
////                data.add(new Item("Salary Calculation", R.drawable.salary,".SalaryCalculationFragment"));
////                data.add(new Item("Staff Requirement", R.drawable.admission,".StaffReqFragment"));
////                data.add(new Item("Locate Staff", R.drawable.locatestaff,"LocateStaffFragment"));
////                data.add(new Item("Admin Removal", R.drawable.adminremoval,".AdminRemovalFragment"));
////                data.add(new Item("Placement Performance", R.drawable.performance,".PlacementPerformFragment"));
////                data.add(new Item("Promote Staff", R.drawable.promotestaff,".PromoteStaffFragment"));
////                data.add(new Item("Relieve Staff", R.drawable.relievestaff,".RelieveStaffFragment"));
////                data.add(new Item("Approvals", R.drawable.approval,".ApprovalsFragment"));
////                data.add(new Item("Events", R.drawable.events,".EventsFragment"));
////                data.add(new Item("Transport Maintenance", R.drawable.transport,".TransportMaintenanceFragment"));
////                data.add(new Item("Hostel", R.drawable.hostel,".HostelManagementFragment"));
////                data.add(new Item("Reports", R.drawable.reports,".ReportsFragment"));
////                data.add(new Item("Sports Performance", R.drawable.sports,".SportsPerformFragment"));
//                break;
//
//            case "PRPL":
//                data.add(new Item("Staff Attendance", R.drawable.staff_attendance,".PrincStaffAttendFragment"));
//                data.add(new Item("Student Attendance", R.drawable.attendance,".PrincStudentAttendFragment"));
//                data.add(new Item("Staff Performance", R.drawable.performance,".PrincStaffPerfFragment"));
//                data.add(new Item("Department Performance", R.drawable.performance,".PrincDeptPerfFragment"));
//                data.add(new Item("Staff Workload", R.drawable.workload,".PrincStaffWorkloadFragment"));
//                data.add(new Item("Staff Requirement", R.drawable.admission,".PrincStaffReqFragment"));
//                data.add(new Item("Locate Staff", R.drawable.locatestaff,".PrincLocateStaffFragment"));
//                data.add(new Item("Hostel", R.drawable.hostel,".PrincHostelFragment"));
//                data.add(new Item("Placement Performance", R.drawable.performance,".PrincPlacementPerfFragment"));
//                data.add(new Item("Sports Performance", R.drawable.performance,".PrincSportPerfFragment"));
//                break;
//
//            //case "Office Admin":
//            case "OADM":
//                data.add(new Item("Admission", R.drawable.admission,".OffAdmissionFragment"));
//                data.add(new Item("Student Data", R.drawable.student,".OfficeStudentInfoUpdFragment"));
//                data.add(new Item("Staff Data", R.drawable.staff,".OfficeStaffInfoFragment"));
//                data.add(new Item("Fees Counter", R.drawable.feespayment,".OffFeeCounterFragment"));
//                data.add(new Item("Stores", R.drawable.stores,".OfficeStoresFragment"));
//                data.add(new Item("Transport", R.drawable.transport,".OffTransportFragment"));
//                data.add(new Item("Hostel", R.drawable.hostel,".OfficeHostelFragment"));
//                data.add(new Item("Exam", R.drawable.exam,".OfficeExamFragment"));
//                data.add(new Item("e-Governance", R.drawable.admission,".OffEGovernanceFragment"));
//                data.add(new Item("Events", R.drawable.events,".OffEventHandlerFragment"));
//                data.add(new Item("Circulars", R.drawable.circulars,".OffStenoCircularsFragment"));
//
//                break;
//
//            //case "Office Admission":
//            case "OADMSN":
//                //data.add(new Item("Application Sale", R.drawable.application,".ApplicationSaleViewFragment"));
//                data.add(new Item("Application Sale", R.drawable.application,".ApplicationSaleViewPlaySchoolFragment"));
//                data.add(new Item("Admission", R.drawable.admissionent,".AdmissionPlaySchoolViewFragment"));
//                data.add(new Item("Cash Counter", R.drawable.feespayment,".FeesPaymentPlaySchoolViewFragment"));
//                data.add(new Item("Transport Change", R.drawable.idcard,".TransportChangePlaySchoolViewFragment"));
//                data.add(new Item("Events Update", R.drawable.events,".EventsPlaySchoolViewFragment"));
//                data.add(new Item("Emergency Message", R.drawable.application,".EmergencyAlarmPlaySchoolFragment"));
//                data.add(new Item("Name List", R.drawable.application,".EmergencyAlarmPlaySchoolFragment"));
//                data.add(new Item("Staff Bio Data", R.drawable.personalinf,".StaffBioDataViewPlaySchoolFragment"));
//                data.add(new Item("Reports", R.drawable.reports,".StaffBioDataViewPlaySchoolFragment"));
//
//                //data.add(new Item("Admission Entry", R.drawable.admissionent,".AdmissionEntryFragment"));
//                //data.add(new Item("Follow up", R.drawable.follower,".ApplicationFollowFragment"));
//                //data.add(new Item("Reports", R.drawable.reports,".OffAdmissionReportFragment"));
//                break;
//
//            //case "Office Fees":
//            case "FEES":
//                data.add(new Item("Fees Payment",R.drawable.feespayment,".FeesPaymentFragment"));
//                data.add(new Item("Fees Payment PS",R.drawable.feespayment,".FeesPaymentPlaySchoolViewFragment"));
//                data.add(new Item("Exam Fees", R.drawable.examfees,".ExamFeesFragment"));
//                data.add(new Item("Follow up", R.drawable.follow,".FollowupFeesFragment"));
//                data.add(new Item("Reports", R.drawable.reports,".ReportFeeCounterFragment"));
//                break;
//
//            //case "Office Supdt":
//            case "OSUPDT":
//                data.add(new Item("Staff Personal PS", R.drawable.personalinf,".StaffBioDataViewPlaySchoolFragment"));
//                data.add(new Item("Staff Search", R.drawable.personalinf,".StaffSearchFragment"));
//                data.add(new Item("Staff Personal", R.drawable.personalinf,".StaffPersonalFragment"));
//                data.add(new Item("Student Personal", R.drawable.personalinf,".StudentPersonalInformationFragment"));
//                data.add(new Item("College ID", R.drawable.idcard,".CollegeIdCardFragment"));
//                data.add(new Item("Bonafide", R.drawable.admission,".BonafideFragment"));
//                data.add(new Item("TC", R.drawable.admission,".TcGenerateFragment"));
//                data.add(new Item("Name List", R.drawable.admission,".NameListFragment"));
//                data.add(new Item("First Aid", R.drawable.firstaid,".FirstAidEntryFragment"));
//                data.add(new Item("Certificate Submission", R.drawable.certificate,".CertificateSubmissionViewFragment"));
//                data.add(new Item("Scholarship", R.drawable.scholarship,".ScholarshipFragment"));
//                data.add(new Item("Staff Academic", R.drawable.academic,".StaffAcademicFragment"));
//                data.add(new Item("Willing Subjects", R.drawable.subject,".WillingSubjectsFragment"));
//                data.add(new Item("Staff Transport", R.drawable.transport,".StaffTransportFragment"));
//                data.add(new Item("Staff Attendance", R.drawable.staff_attendance,".OffSupdtStaffAttendanceFragment"));
//                data.add(new Item("Leave Entry", R.drawable.admission,".StaffLeaveEntryFragment"));
//                data.add(new Item("Nominal Entry", R.drawable.admission,".NominalEntryFragment"));
//                data.add(new Item("Equivalence Subject Entry", R.drawable.admission,".EquivalenceSubjectEntryFragment"));
//                data.add(new Item("Result Entry", R.drawable.admission,".ResultEntryFragment"));
//                data.add(new Item("Ex-2 Entry", R.drawable.admission,".Ex2EntryFragment"));
//                data.add(new Item("E-Gov Spell Entry", R.drawable.admission,".EgovSpellEntryFragment"));
//                data.add(new Item("B-Forms", R.drawable.admission,".BformFragment"));
//                data.add(new Item("B-Forms", R.drawable.admission,".BformActivity"));
//                data.add(new Item("Reports", R.drawable.reports,".OfficeSupdtReportFragment"));
//                break;
//
//            //case "Office Steno":
//            case "OSTN":
//                data.add(new Item("Events PS", R.drawable.fees,".EventsPlaySchoolViewFragment"));
//                data.add(new Item("Fees Events", R.drawable.fees,".FeesEventsActivity"));
//                data.add(new Item("Branch Events", R.drawable.branches,".BranchEventsActivity"));
//                data.add(new Item("Exam Events", R.drawable.exam,".ExamEventsActivity"));
//                data.add(new Item("Placement Events", R.drawable.placement,".PlacementEventsActivity"));
//                data.add(new Item("Sports Events", R.drawable.sports,".SportsEventsActivity"));
//                data.add(new Item("NSS Events", R.drawable.nss,".NSSEventsActivity"));
//                data.add(new Item("Management Events", R.drawable.events,".ManagementEventsActivity"));
//                data.add(new Item("Fees Circular", R.drawable.circulars,".FeesCircularActivity"));
//                data.add(new Item("Branch Circular", R.drawable.circulars,".BranchCircularActivity"));
//                data.add(new Item("Exam Circular", R.drawable.circulars,".ExamCircularActivity"));
//                data.add(new Item("Placement Circular", R.drawable.circulars,".PlacementCircularActivity"));
//                data.add(new Item("Sports Circular", R.drawable.circulars,".SportsCircularActivity"));
//                data.add(new Item("Management Circular", R.drawable.circulars,".ManagementCircularActivity"));
//                data.add(new Item("Reports", R.drawable.reports,".OfficeStenoReportsActivity"));
//                break;
//
//            //case "Office Stores":
//            case "OSTO":
//                data.add(new Item("Stores Vendor", R.drawable.vendor,".VendorDetailsFragment"));
//                data.add(new Item("Stores Purchase", R.drawable.stores,".PurchaseFragment"));
//                data.add(new Item("Stores Sales", R.drawable.sale,".SalesFragment "));
//                data.add(new Item("Reports", R.drawable.reports,".OfficeManagerReportsActivity"));
//
//                break;
//            //case "Office Transport" :
//            case "OTRAN" :
//                data.add(new Item("Transport ID", R.drawable.idcard,".TransportIdFragment"));
//                data.add(new Item("Transport Change PS", R.drawable.idcard,".TransportChangePlaySchoolViewFragment"));
//                data.add(new Item("Route Change", R.drawable.admission,".RouteChangeFragment"));
//                data.add(new Item("MTC Concession", R.drawable.admission,".MTCConcessionFragment"));
//                data.add(new Item("Train Concession", R.drawable.admission,".TrainConcessionFragment"));
//                data.add(new Item("Route Verification", R.drawable.admission,".RouteVerificationFragment"));
//                data.add(new Item("Fuel Filling", R.drawable.fuel,".FuelFillingFragment"));
//                data.add(new Item("Transport Insurance", R.drawable.admission,".TransportInsuranceFragment"));
//                data.add(new Item("Transport FC", R.drawable.admission,".TransportFCFragment"));
//                data.add(new Item("Driver Data", R.drawable.driver,".DriverDataFragment"));
//                data.add(new Item("Requirement Request", R.drawable.requiredkit,".TransRequirementReqFragment"));
//                data.add(new Item("Reports", R.drawable.reports,".TransReportFragment"));
//                break;
//
//
//
//            //case "Teaching Staff":
//            case "TSTAFF":
//                data.add(new Item("Home Work Entry", R.drawable.homework,".HomeWorkPlaySchoolViewFragment"));
//                data.add(new Item("Childs Pickup", R.drawable.pickup,".ChildPickupViewPlaySchoolFragment"));
//                data.add(new Item("Childs Progress", R.drawable.performance,".IndividualStudentProgressPlaySchoolViewFragment"));
//                data.add(new Item("Attendance Entry", R.drawable.attendance,".IndividualStudentProgressPlaySchoolViewFragment"));
//                data.add(new Item("Photo/Video Upload", R.drawable.photogallery,".PhotoGalleryUploadPlaySchoolFragment"));
////                data.add(new Item("Video Upload", R.drawable.videogallery,".IndividualStudentProgressPlaySchoolViewFragment"));
//
////                data.add(new Item("Student Attendance", R.drawable.attendance,".TeachstaffStudAttEntryFragment"));
////                data.add(new Item("Exam Mark Entry", R.drawable.admission,".TSExamMarkEntryFragment"));
////                data.add(new Item("View Internal Mark", R.drawable.internal,".TSViewInternalFragment"));
////                data.add(new Item("Time Table", R.drawable.timetable,".TSTimeTableViewerFragment"));
////                data.add(new Item("Home Work Entry", R.drawable.homework,".HomeWOrkEntryFragment"));
////                data.add(new Item("Requirements Request", R.drawable.admission,".MaterialRequirementRequestFragment"));
////                data.add(new Item("Leave Request", R.drawable.leaveletter,".StaffLeaveRequestFragment"));
////                data.add(new Item("Events", R.drawable.events,".StaffEventsFragment"));
////                data.add(new Item("Reports", R.drawable.reports,".StaffReportsFragment"));
////                data.add(new Item("Image Upload", R.drawable.reports,".PhotoGalleryUploadPlaySchoolFragment"));
//
//                break;
//
//
//            case "HOD":
//                data.add(new Item("Student Attendance", R.drawable.attendance,".StudentAttEntryActivity"));
//                data.add(new Item("Subject Entry", R.drawable.subject,".SubjectEntryActivity"));
//                data.add(new Item("Subject Allocation", R.drawable.subject,".SubjectAllocationActivity"));
//                data.add(new Item("Time Table", R.drawable.timetable,".TimeTableViewerActivity"));
//                data.add(new Item("Exam Time Table", R.drawable.exam,".ExamTimeTableActivity"));
//                data.add(new Item("Exam Mark Entry", R.drawable.exam,".ExamMarkEntryActivity"));
//                data.add(new Item("Question Set", R.drawable.question,".QuestionSetActivity"));
//                data.add(new Item("Attendance Entry Monitor", R.drawable.monitor,".AttEntryMonitorActivity"));
//                data.add(new Item("Student Remarks", R.drawable.admission,".StudentsRemarksActivity"));
//                data.add(new Item("Staff Leave Approval", R.drawable.approval,".StaffLeaveApprovalActivity"));
//                data.add(new Item("Home Work Entry", R.drawable.homework,".HomeWOrkEntryActivity"));
//                data.add(new Item("Requirements Request", R.drawable.admission,".MaterialRequirementRequestActivity"));
//                data.add(new Item("Leave Request", R.drawable.leaveletter,".StaffLeaveRequestActivity"));
//                data.add(new Item("Events", R.drawable.events,".StaffEventsActivity"));
//                data.add(new Item("Reports", R.drawable.reports,".StaffReportsActivity"));
//                break;
//
//            //case "Placement officer":
//            case "PLO":
//                data.add(new Item("Corporates Detail", R.drawable.corporates,".CorporateDetActivity"));
//                data.add(new Item("Campus Request", R.drawable.campusreq,".CampusReqActivity"));
//                data.add(new Item("Schedules", R.drawable.schedule,".SchedulesActivity"));
//                data.add(new Item("Followup", R.drawable.follow,".FollowupActivity"));
//                data.add(new Item("Seminars", R.drawable.seminars,".SeminarsActivity"));
//                data.add(new Item("Inplant Training", R.drawable.inplant,".InplantActivity"));
//                data.add(new Item("Job Links", R.drawable.joblink,".JobLinksActivity"));
//                data.add(new Item("Reports", R.drawable.reports,".PlacementReportsActivity"));
//
//                break;
//
//            //case "Sports":
//            case "SPRT":
//                data.add(new Item("Athelete Data", R.drawable.athlete,".AtheleteDataActivity"));
//                data.add(new Item("Tournament Data", R.drawable.tournament,".TournamentDataActivity"));
//                data.add(new Item("Kits Stock", R.drawable.stockkit,".KitsStockActivity"));
//                data.add(new Item("Kits Requirement", R.drawable.requiredkit,".KitsReqActivity"));
//                data.add(new Item("O.D.Form", R.drawable.leaveletter,".ODFormActivity"));
//                data.add(new Item("Reports", R.drawable.reports,".SportsReportsActivity"));
//
//                break;
//
//            //case "Library":
//            case "LIB":
//                data.add(new Item("Member Data", R.drawable.profile,".LibMemberDataFragment"));
//                data.add(new Item("Books Data", R.drawable.book,".LibBooksDataFragment"));
//                data.add(new Item("CD Data", R.drawable.cd,".LibCDDataFragment"));
//                data.add(new Item("Magazines Data", R.drawable.magazine,".MagazinesDataFragment"));
//                data.add(new Item("Books Purchase", R.drawable.purchase,".BooksPurchaseFragment"));
//                data.add(new Item("Books Availability", R.drawable.admission,".BooksAvailFragment"));
//                data.add(new Item("Issuing Books", R.drawable.admission,".IssuingBooksFragment"));
//                data.add(new Item("Returning Books", R.drawable.returnbook,".ReturningBooksFragment"));
//                data.add(new Item("Library Usage", R.drawable.admission,".LibraryUsageFragment"));
//                data.add(new Item("Reports", R.drawable.reports,".LibraryReportsfragment"));
//
//                break;
//
//            //case "Hostel":
//            case "HOS":
//                data.add(new Item("Student Data", R.drawable.student,".HostelStudDataFragment"));
//                data.add(new Item("Facilities Data", R.drawable.facility,".HostelFacilitiesDataFragment"));
//                data.add(new Item("Payment Data", R.drawable.feespayment,".HostelPayDataFragment"));
//                data.add(new Item("Leave Data", R.drawable.leaveletter,".HostelStudLeaveDataFragment"));
//                data.add(new Item("Visitor Data", R.drawable.visitor,".HostelVisitorDataFragment"));
//                data.add(new Item("Food Data", R.drawable.food,".HostelFoodDataFragment"));
//                data.add(new Item("Medical Data", R.drawable.medical,".HostelMedicalDataFragment"));
//                data.add(new Item("Remarks", R.drawable.admission,".HostelStudRemFragment"));
//                data.add(new Item("Reports", R.drawable.reports,".HostelReportsFragment"));
//
//                break;
//
//            //case "Parent":
//            case "STUD":
//                data.add(new Item("Profile", R.drawable.profile,".PlaySchoolStudentProfileViewFragment"));
//                data.add(new Item("Attendance", R.drawable.attendance,".DailyAttendanceViewParentPlaySchoolFragment"));
//                data.add(new Item("Pickup Information", R.drawable.pickup,".ChildPickupPlaySchoolParentViewFragment"));
////                data.add(new Item("Photo Upload", R.drawable.profile,".PhotoGalleryUploadPlaySchoolFragment"));
//                data.add(new Item("Photo/Video Gallery", R.drawable.photogallery,".PhotoGalleryPlaySchoolFragment"));
////                data.add(new Item("Video Gallery", R.drawable.videogallery,".VideoGalleryPlaySchoolFragment"));
//                data.add(new Item("Fees Payment", R.drawable.feespayment,".FeesPaymentPlaySchoolViewFragment"));
//                data.add(new Item("Home Work/Activity", R.drawable.homework,".ParentHomeWorkFragment"));
//                data.add(new Item("Events", R.drawable.events,".ParentEventCircularsPlayschoolViewFragment"));
//                data.add(new Item("Progress", R.drawable.performance,".IndividualStudentProgressPlaySchoolViewFragment"));
//
//
////                data.add(new Item("Profile", R.drawable.profile,".ParentProfileViewFragment"));
////                data.add(new Item("Daily Attendance", R.drawable.attendance,".ParentDailyAttfragment"));
////                data.add(new Item("Test Performance", R.drawable.performance,".ParentTestPerFragment"));
////                data.add(new Item("Exam Performance", R.drawable.performance,".ParentExamPerFragment"));
////                data.add(new Item("Fees Payment", R.drawable.feespayment,".ParentFeesPaymentFragment"));
////                data.add(new Item("Home Work", R.drawable.homework,".ParentHomeWorkFragment"));
////                data.add(new Item("Leave Request", R.drawable.leaveletter,".ParentLeaveReqFragment"));
////                data.add(new Item("Events", R.drawable.events,".ParentEventFragment"));
////                data.add(new Item("Feed Back", R.drawable.feedback,".ParentFeedBackFragment"));
////                data.add(new Item("Remarks", R.drawable.admission,".ParentRemarksFragment"));
//
//                break;
//
//            //case "System Admin":
//            case "SYSADM":
//                data.add(new Item("Add User", R.drawable.adduser,".AddUserFragment"));
//                data.add(new Item("Modify User",R.drawable.edituser,".ModifyUserFragment"));
//                data.add(new Item("Remove User", R.drawable.relievestaff,".RemoveUserFragment"));
//                data.add(new Item("Reports", R.drawable.reports,".SysAdminReportsFragment"));
//
//                break;
//        }
//        data.add(new Item("Change Password", R.drawable.profile,".ChangePassword"));
//        data.add(new Item("Feedback", R.drawable.attendance,".ParentFeedBackFragment"));
//        return data;
    }
    //end of
}
