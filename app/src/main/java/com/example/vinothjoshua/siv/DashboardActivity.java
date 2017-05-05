package com.example.vinothjoshua.siv;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Vinoth Joshua on 03-Apr-17.
 */

public class DashboardActivity extends FragmentActivity {
    GridView grid;
    String userCategory = "principal";
    String[] dashboardColors = {
            "#2DA3AD",
            "#EC538D",
            "#77C046",
            "#FC5E13",
            "#EDA11B",
            "#E4444B"
    } ;

    ArrayList<Item> data = new ArrayList<Item>();
    private void fillData()
    {
        switch(userCategory){
            case "Management":
                data.add(new Item("Admission Counselling",R.drawable.admission,".AdmissionCounsellingActivity"));
                data.add(new Item("Admission Status", R.drawable.accounts,".AdmissionStatusActivity"));
                data.add(new Item("Fees Setting", R.drawable.accounts1,".FessSetActivity"));
                data.add(new Item("Staff Attendance", R.drawable.admission,".StaffAttendanceActivity"));
                data.add(new Item("Student Attendance", R.drawable.admission,".StudentAttendanceActivity"));
                data.add(new Item("Staff Performance", R.drawable.admission,".StaffPerformanceActivity"));
                data.add(new Item("Department Performance", R.drawable.admission,".DeptPerformanceActivity"));
                data.add(new Item("Salary Calculation", R.drawable.admission,".SalaryCalculationActivity"));
                data.add(new Item("Staff Workload", R.drawable.admission,".StaffWorkLoadActivity"));
                data.add(new Item("Staff Requirement", R.drawable.admission,".StaffReqActivity"));
                data.add(new Item("Locate Staff", R.drawable.admission,".LocateStaffActivity"));
                data.add(new Item("Admin Removal", R.drawable.admission,".AdminRemovalActivity"));
                data.add(new Item("Placement Performance", R.drawable.admission,".PlacementPerformActivity"));
                data.add(new Item("Promote Staff", R.drawable.admission,".PromoteStaffActivity"));
                data.add(new Item("Relieve Staff", R.drawable.admission,".RelieveStaffActivity"));
                data.add(new Item("Approvals", R.drawable.admission,".ApprovalsActivity"));
                data.add(new Item("Events", R.drawable.admission,".EventsActivity"));
                data.add(new Item("Transport Maintenance", R.drawable.admission,".TransportMaintenanceActivity"));
                data.add(new Item("Hostel", R.drawable.admission,".HostelManagementActivity"));
                data.add(new Item("Reports", R.drawable.admission,".ReportsActivity"));
                data.add(new Item("Sports Performance", R.drawable.admission,".SportsPerformActivity"));
                break;

            case "Principal":
                data.add(new Item("Staff Attendance", R.drawable.admission,".StaffAttendanceActivity"));
                data.add(new Item("Student Attendance", R.drawable.admission,".StudentAttendanceActivity"));
                data.add(new Item("Staff Performance", R.drawable.admission,".StaffPerformanceActivity"));
                data.add(new Item("Department Performance", R.drawable.admission,".DeptPerformanceActivity"));
                data.add(new Item("Staff Workload", R.drawable.admission,".StaffWorkLoadActivity"));
                data.add(new Item("Staff Requirement", R.drawable.admission,".StaffReqActivity"));
                data.add(new Item("Locate Staff", R.drawable.admission,".LocateStaffActivity"));
                data.add(new Item("Hostel", R.drawable.admission,".HostelManagementActivity"));
                data.add(new Item("Placement Performance", R.drawable.admission,".PlacementPerformActivity"));
                data.add(new Item("Sports Performance", R.drawable.admission,".SportsPerformActivity"));

                break;

            case "Office Admin":
                data.add(new Item("Admission", R.drawable.admission,".AdmissionActivity"));
                data.add(new Item("Student Data", R.drawable.admission,".OfficeStudentInfoUpdActivity"));
                data.add(new Item("Staff Data", R.drawable.admission,".OfficeStaffInfoActivity"));
                data.add(new Item("Fees Counter", R.drawable.admission,".FeeCounterActivity"));
                data.add(new Item("Stores", R.drawable.admission,".StoresActivity"));
                data.add(new Item("Transport", R.drawable.admission,".TransportActivity"));
                data.add(new Item("Hostel", R.drawable.admission,".HostelActivity"));
                data.add(new Item("Exam", R.drawable.admission,".ExamActivity"));
                data.add(new Item("e-Governance", R.drawable.admission,".EGovernanceActivity"));
                data.add(new Item("Events", R.drawable.admission,".EventHandlerActivity"));
                data.add(new Item("Circulars", R.drawable.admission,".CircularsActivity"));

                break;

            case "Office Admission":
                data.add(new Item("Application Sale", R.drawable.admission,".ApplicationSaleActivity"));
                data.add(new Item("Admission Entry", R.drawable.admission,".AdmissionEntryActivity"));
                data.add(new Item("Follow up", R.drawable.admission,".ApplicationFollowActivity"));
                data.add(new Item("College ID", R.drawable.admission,".CollegeIdCardActivity"));
                data.add(new Item("Bonafide", R.drawable.admission,".BonafideActivity"));
                data.add(new Item("TC", R.drawable.admission,".TcGenerateActivity"));
                data.add(new Item("Name List", R.drawable.admission,".NameListActivity"));
                data.add(new Item("First Aid", R.drawable.admission,".FirstAidEntryActivity"));
                data.add(new Item("Certificate Submission", R.drawable.admission,".CertificateSubmissionActivity"));
                data.add(new Item("Scholarship", R.drawable.admission,".ScholarshipActivity"));
                data.add(new Item("Reports", R.drawable.admission,".OffAdmissionReportActivity"));

                break;

            case "Office Fees":
                data.add(new Item("Fees Payment", R.drawable.admission,".FeesPaymentActivity"));
                data.add(new Item("Exam Fees", R.drawable.admission,".ExamFeesActivity"));
                data.add(new Item("Follow up", R.drawable.admission,".FollowupFeesActivity"));
                data.add(new Item("Reports", R.drawable.admission,".ReportFeeCounterActivity"));

                break;



            case "Teaching Staff":
                data.add(new Item("Student Attendance", R.drawable.admission,".StudentAttEntryActivity"));
                data.add(new Item("Exam Mark Entry", R.drawable.admission,".ExamMarkEntryActivity"));
                data.add(new Item("Time Table", R.drawable.admission,".TimeTableViewerActivity"));
                data.add(new Item("Home Work Entry", R.drawable.admission,".HomeWOrkEntryActivity"));
                data.add(new Item("Requirements Request", R.drawable.admission,".MaterialRequirementRequestActivity"));
                data.add(new Item("Leave Request", R.drawable.admission,".StaffLeaveRequestActivity"));
                data.add(new Item("Events", R.drawable.admission,".StaffEventsActivity"));
                data.add(new Item("Reports", R.drawable.admission,".StaffReportsActivity"));

                break;

            case "HOD":
                data.add(new Item("Student Attendance", R.drawable.admission,".StudentAttEntryActivity"));
                data.add(new Item("Subject Entry", R.drawable.admission,".SubjectEntryActivity"));
                data.add(new Item("Subject Allocation", R.drawable.admission,".SubjectAllocationActivity"));
                data.add(new Item("Time Table", R.drawable.admission,".TimeTableViewerActivity"));
                data.add(new Item("Exam Time Table", R.drawable.admission,".ExamTimeTableActivity"));
                data.add(new Item("Exam Mark Entry", R.drawable.admission,".ExamMarkEntryActivity"));
                data.add(new Item("Question Set", R.drawable.admission,".QuestionSetActivity"));
                data.add(new Item("Attendance Entry Monitor", R.drawable.admission,".AttEntryMonitorActivity"));
                data.add(new Item("Student Remarks", R.drawable.admission,".StudentsRemarksActivity"));
                data.add(new Item("Staff Leave Apporval", R.drawable.admission,".StaffLeaveApprovalActivity"));
                data.add(new Item("Home Work Entry", R.drawable.admission,".HomeWOrkEntryActivity"));
                data.add(new Item("Requirements Request", R.drawable.admission,".MaterialRequirementRequestActivity"));
                data.add(new Item("Leave Request", R.drawable.admission,".StaffLeaveRequestActivity"));
                data.add(new Item("Events", R.drawable.admission,".StaffEventsActivity"));
                data.add(new Item("Reports", R.drawable.admission,".StaffReportsActivity"));

                break;

            case "Placement officer":
                data.add(new Item("Corporates Detail", R.drawable.admission,".CorporateDetActivity"));
                data.add(new Item("Campus Request", R.drawable.admission,".CampusReqActivity"));
                data.add(new Item("Schedules", R.drawable.admission,".SchedulesActivity"));
                data.add(new Item("Followup", R.drawable.admission,".FollowupActivity"));
                data.add(new Item("Seminars", R.drawable.admission,".SeminarsActivity"));
                data.add(new Item("Inplant Training", R.drawable.admission,".InplantActivity"));
                data.add(new Item("Job Links", R.drawable.admission,".JobLinksActivity"));
                data.add(new Item("Reports", R.drawable.admission,".PlacementReportsActivity"));

                break;

            case "Sports":
                data.add(new Item("Athelete Data", R.drawable.admission,".AtheleteDataActivity"));
                data.add(new Item("Tournament Data", R.drawable.admission,".TournamentDataActivity"));
                data.add(new Item("Kits Stock", R.drawable.admission,".KitsStockActivity"));
                data.add(new Item("Kits Requirement", R.drawable.admission,".KitsReqActivity"));
                data.add(new Item("O.D.Form", R.drawable.admission,".ODFormActivity"));
                data.add(new Item("Reports", R.drawable.admission,".SportsReportsActivity"));

                break;

            case "Library":
                data.add(new Item("Member Data", R.drawable.admission,".MemberDataActivity"));
                data.add(new Item("Books Data", R.drawable.admission,".BooksDataActivity"));
                data.add(new Item("CD Data", R.drawable.admission,".CDDataActivity"));
                data.add(new Item("Magazines Data", R.drawable.admission,".MagazinesDataActivity"));
                data.add(new Item("Books Purchase", R.drawable.admission,".BooksPurchaseActivity"));
                data.add(new Item("Books Availability", R.drawable.admission,".BooksAvailActivity"));
                data.add(new Item("Issuing Books", R.drawable.admission,".IssuingBooksActivity"));
                data.add(new Item("Returning Books", R.drawable.admission,".ReturningBooksActivity"));
                data.add(new Item("Library Usage", R.drawable.admission,".LibraryUsageActivity"));
                data.add(new Item("Reports", R.drawable.admission,".LibraryReportsActivity"));

                break;

            case "Hostel":
                data.add(new Item("Student Data", R.drawable.admission,".HostelStudDataActivity"));
                data.add(new Item("Facilities Data", R.drawable.admission,".FacilitiesDataActivity"));
                data.add(new Item("Payment Data", R.drawable.admission,".HostelPayDataActivity"));
                data.add(new Item("Leave Data", R.drawable.admission,".HostelStudLeaveDataActivity"));
                data.add(new Item("Visitor Data", R.drawable.admission,".VisitorDataActivity"));
                data.add(new Item("Food Data", R.drawable.admission,".HostelFoodDataActivity"));
                data.add(new Item("Medical Data", R.drawable.admission,".HostelMedicalDataActivity"));
                data.add(new Item("Remarks", R.drawable.admission,".HostelStudRemActivity"));
                data.add(new Item("Reports", R.drawable.admission,".HostelReportsActivity"));

                break;

            case "Parent":
                data.add(new Item("Profile", R.drawable.admission,".ProfileActivity"));
                data.add(new Item("Daily Attendance", R.drawable.admission,".ParentDailyAttActivity"));
                data.add(new Item("Test Performance", R.drawable.admission,".ParentTestPerActivity"));
                data.add(new Item("Exam Performance", R.drawable.admission,".ParentExamPerActivity"));
                data.add(new Item("Fees Payment", R.drawable.admission,".ParentFeesPaymentActivity"));
                data.add(new Item("Home Work", R.drawable.admission,".ParentHomeWorkActivity"));
                data.add(new Item("Leave Request", R.drawable.admission,".ParentLeaveReqActivity"));
                data.add(new Item("Events", R.drawable.admission,".ParentEventActivity"));
                data.add(new Item("Feed Back", R.drawable.admission,".FeedBackActivity"));
                data.add(new Item("Remarks", R.drawable.admission,".ParentRemarksActivity"));

                break;

            case "System Admin":
                data.add(new Item("Add User", R.drawable.admission,".AddUserActivity"));
                data.add(new Item("Modify User",R.drawable.admission,".ModifyUserActivity"));
                data.add(new Item("Remove User", R.drawable.admission,".RemoveUserActivity"));
                data.add(new Item("Reports", R.drawable.admission,".SysAdminReportsActivity"));

                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        if (savedInstanceState == null) {
//            OfficeDashboardFragment fr = new OfficeDashboardFragment();
////        if(view == findViewById(R.id.button2)) {
////
////            fr = new FragmentTwo();
////
////        }else {
////
////            fr = new FragmentOne();
////
////        }
//
//            FragmentManager fm = getFragmentManager();
//
//            FragmentTransaction fragmentTransaction = fm.beginTransaction();
//
//            fragmentTransaction.add(R.id.dashBoardFragemntContaier, fr);
//
//            fragmentTransaction.commit();
//        }
        fillData();
        DashboardGrid adapter = new DashboardGrid(DashboardActivity.this,R.layout.gridview_dashboardtool_single ,data , dashboardColors);
        grid=(GridView)findViewById(R.id.dashboardTools);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String testtext;
            String nextActivityClassName;
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item testitem= new Item();
                testitem=data.get(position);
                testtext=testitem.getTitle();
                nextActivityClassName = testitem.getNextActivityClassName();

                Toast.makeText(DashboardActivity.this, "You Clicked at " + testtext, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClassName("com.example.vinothjoshua.siv", "com.example.vinothjoshua.siv"+nextActivityClassName);
                startActivity(intent);
            }
        });
    }

}
