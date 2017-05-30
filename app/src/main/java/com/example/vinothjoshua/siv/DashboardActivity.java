package com.example.vinothjoshua.siv;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Vinoth Joshua on 03-Apr-17.
 */

public class DashboardActivity extends FragmentActivity {

    ArrayList<Item> data = new ArrayList<Item>();GridView grid;
    String userCategory = "";
    String[] dashboardColors = {
            "#2DA3AD",
            "#EC538D",
            "#77C046",
            "#FC5E13",
            "#EDA11B",
            "#E4444B"
    } ;

    private void fillData()
    {
        switch(userRole){
            case "Management":
                data.add(new Item("Admission Counselling",R.drawable.admission,".AdmissionCounsellingFragment"));
                data.add(new Item("Admission Status", R.drawable.accounts,".AdmissionStatusFragment"));
                data.add(new Item("College Fees Setting", R.drawable.accounts1,".CollegeFeesSettingFragment"));
                data.add(new Item("Transport Fees Setting", R.drawable.accounts1,".TransportFeesSettingFragment"));
                data.add(new Item("Hostel Fees Setting", R.drawable.accounts1,".HostelFeesSettingFragment"));
                data.add(new Item("Staff Attendance", R.drawable.admission,".StaffAttendanceFragment"));
                data.add(new Item("Student Attendance", R.drawable.attendance,".StudentAttendanceFragment"));
                data.add(new Item("Staff Performance", R.drawable.admission,".StaffPerformanceFragment"));
                data.add(new Item("Department Performance", R.drawable.admission,".DepartmentPerformanceFragment"));
                data.add(new Item("Salary Calculation", R.drawable.admission,".SalaryCalculationFragment"));
                data.add(new Item("Staff Requirement", R.drawable.admission,".StaffReqFragment"));
                data.add(new Item("Locate Staff", R.drawable.locatestaff,"LocateStaffFragment"));
                data.add(new Item("Admin Removal", R.drawable.adminremoval,".AdminRemovalFragment"));
                data.add(new Item("Placement Performance", R.drawable.admission,".PlacementPerformFragment"));
                data.add(new Item("Promote Staff", R.drawable.promotestaff,".PromoteStaffFragment"));
                data.add(new Item("Relieve Staff", R.drawable.relievestaff,".RelieveStaffFragment"));
                data.add(new Item("Approvals", R.drawable.admission,".ApprovalsFragment"));
                data.add(new Item("Events", R.drawable.events,".EventsFragment"));
                data.add(new Item("Transport Maintenance", R.drawable.transport,".TransportMaintenanceFragment"));
                data.add(new Item("Hostel", R.drawable.hostel,".HostelManagementFragment"));
                data.add(new Item("Reports", R.drawable.reports,".ReportsFragment"));
                data.add(new Item("Sports Performance", R.drawable.sports,".SportsPerformFragment"));
                break;

            case "Principal":
                data.add(new Item("Staff Attendance", R.drawable.admission,".PrincStaffAttendFragment"));
                data.add(new Item("Student Attendance", R.drawable.attendance,".PrincStudentAttendFragment"));
                data.add(new Item("Staff Performance", R.drawable.admission,".PrincStaffPerfFragment"));
                data.add(new Item("Department Performance", R.drawable.admission,".PrincDeptPerfFragment"));
                data.add(new Item("Staff Workload", R.drawable.admission,".PrincStaffWorkloadFragment"));
                data.add(new Item("Staff Requirement", R.drawable.admission,".PrincStaffReqFragment"));
                data.add(new Item("Locate Staff", R.drawable.locatestaff,".PrincLocateStaffFragment"));
                data.add(new Item("Hostel", R.drawable.hostel,".PrincHostelFragment"));
                data.add(new Item("Placement Performance", R.drawable.admission,".PrincPlacementPerfFragment"));
                data.add(new Item("Sports Performance", R.drawable.admission,".PrincSportPerfFragment"));
                break;

            case "Office Admin":
                data.add(new Item("Admission", R.drawable.admission,".OffAdmissionFragment"));
                data.add(new Item("Student Data", R.drawable.student,".OfficeStudentInfoUpdFragment"));
                data.add(new Item("Staff Data", R.drawable.staff,".OfficeStaffInfoFragment"));
                data.add(new Item("Fees Counter", R.drawable.admission,".OffFeeCounterFragment"));
                data.add(new Item("Stores", R.drawable.stores,".OfficeStoresFragment"));
                data.add(new Item("Transport", R.drawable.transport,".OffTransportFragment"));
                data.add(new Item("Hostel", R.drawable.hostel,".OfficeHostelFragment"));
                data.add(new Item("Exam", R.drawable.exam,".OfficeExamFragment"));
                data.add(new Item("e-Governance", R.drawable.admission,".OffEGovernanceFragment"));
                data.add(new Item("Events", R.drawable.events,".OffEventHandlerFragment"));
                data.add(new Item("Circulars", R.drawable.circulars,".OffStenoCircularsFragment"));

                break;

            case "Office Admission":
                data.add(new Item("Application Sale", R.drawable.application,".ApplicationSaleFragment"));
                data.add(new Item("Admission Entry", R.drawable.admissionent,".AdmissionEntryFragment"));
                data.add(new Item("Follow up", R.drawable.follower,".ApplicationFollowFragment"));
                data.add(new Item("College ID", R.drawable.admission,".CollegeIdCardFragment"));
                data.add(new Item("Bonafide", R.drawable.admission,".BonafideFragment"));
                data.add(new Item("TC", R.drawable.admission,".TcGenerateFragment"));
                data.add(new Item("Name List", R.drawable.admission,".NameListFragment"));
                data.add(new Item("First Aid", R.drawable.admission,".FirstAidEntryFragment"));
                data.add(new Item("Certificate Submission", R.drawable.admission,".CertificateSubmissionFragment"));
                data.add(new Item("Scholarship", R.drawable.admission,".ScholarshipFragment"));
                data.add(new Item("Reports", R.drawable.reports,".OffAdmissionReportFragment"));
                break;

            case "Office Fees":
                data.add(new Item("Fees Payment",R.drawable.admission,".FeesPaymentFragment"));
                data.add(new Item("Exam Fees", R.drawable.admission,".ExamFeesFragment"));
                data.add(new Item("Follow up", R.drawable.admission,".FollowupFeesFragment"));
                data.add(new Item("Reports", R.drawable.admission,".ReportFeeCounterFragment"));
                break;

            case "Office Supdt":
                data.add(new Item("Staff Personal", R.drawable.admission,".StaffPersonalFragment"));
                data.add(new Item("Staff Academic", R.drawable.admission,".StaffAcademicFragment"));
                data.add(new Item("Willing Subjects", R.drawable.admission,".WillingSubjectsFragment"));
                data.add(new Item("Staff Transport", R.drawable.admission,".StaffTransportFragment"));
                data.add(new Item("Staff Attendance", R.drawable.admission,".OffSupdtStaffAttendanceFragment"));
                data.add(new Item("Leave Entry", R.drawable.admission,".StaffLeaveEntryFragment"));
                data.add(new Item("Nominal Entry", R.drawable.admission,".NominalEntryFragment"));
                data.add(new Item("Nominal Entry", R.drawable.admission,".EquivalenceSubjectEntryFragment"));
                data.add(new Item("Result Entry", R.drawable.admission,".ResultEntryFragment"));
                data.add(new Item("Ex-2 Entry", R.drawable.admission,".Ex2EntryFragment"));
                data.add(new Item("E-Gov Spell Entry", R.drawable.admission,".EgovSpellEntryFragment"));
                data.add(new Item("B-Forms", R.drawable.admission,".BformFragment"));
                data.add(new Item("B-Forms", R.drawable.admission,".BformActivity"));
                data.add(new Item("Reports", R.drawable.admission,".OfficeSupdtReportFragment"));
                break;

            case "Office Steno":
                data.add(new Item("Fees Events", R.drawable.admission,".FeesEventsActivity"));
                data.add(new Item("Branch Events", R.drawable.admission,".BranchEventsActivity"));
                data.add(new Item("Exam Events", R.drawable.admission,".ExamEventsActivity"));
                data.add(new Item("Placement Events", R.drawable.admission,".PlacementEventsActivity"));
                data.add(new Item("Sports Events", R.drawable.admission,".SportsEventsActivity"));
                data.add(new Item("NSS Events", R.drawable.admission,".NSSEventsActivity"));
                data.add(new Item("Management Events", R.drawable.admission,".ManagementEventsActivity"));
                data.add(new Item("Fees Circular", R.drawable.admission,".FeesCircularActivity"));
                data.add(new Item("Branch Circular", R.drawable.admission,".BranchCircularActivity"));
                data.add(new Item("Exam Circular", R.drawable.admission,".ExamCircularActivity"));
                data.add(new Item("Placement Circular", R.drawable.admission,".PlacementCircularActivity"));
                data.add(new Item("Sports Circular", R.drawable.admission,".SportsCircularActivity"));
                data.add(new Item("Management Circular", R.drawable.admission,".ManagementCircularActivity"));
                data.add(new Item("Reports", R.drawable.admission,".OfficeStenoReportsActivity"));
                break;

            case "Office Stores":
                data.add(new Item("Stores Vendor", R.drawable.admission,".VendorDetailsFragment"));
                data.add(new Item("Stores Purchase", R.drawable.admission,".PurchaseFragment"));
                data.add(new Item("Stores Sales", R.drawable.admission,".SalesFragment "));
                data.add(new Item("Reports", R.drawable.admission,".OfficeManagerReportsActivity"));

                break;
            case "Office Transport" :
                data.add(new Item("Transport ID", R.drawable.admission,".TransportIdFragment"));
                data.add(new Item("Route Change", R.drawable.admission,".RouteChangeFragment"));
                data.add(new Item("MTC Concession", R.drawable.admission,".MTCConcessionFragment"));
                data.add(new Item("Train Concession", R.drawable.admission,".TrainConcessionFragment"));
                data.add(new Item("Route Verification", R.drawable.admission,".RouteVerificationFragment"));
                data.add(new Item("Fuel Filling", R.drawable.admission,".FuelFillingFragment"));
                data.add(new Item("Transport Insurance", R.drawable.admission,".TransportInsuranceFragment"));
                data.add(new Item("Transport FC", R.drawable.admission,".TransportFCFragment"));
                data.add(new Item("Driver Data", R.drawable.admission,".DriverDataFragment"));
                data.add(new Item("Requirement Request", R.drawable.admission,".TransRequirementReqFragment"));
                data.add(new Item("Reports", R.drawable.admission,".TransReportFragment"));
                break;



            case "Teaching Staff":
                data.add(new Item("Student Attendance", R.drawable.admission,".TeachstaffStudAttEntryFragment"));
                data.add(new Item("Exam Mark Entry", R.drawable.admission,".TSExamMarkEntryFragment"));
                data.add(new Item("View Internal Mark", R.drawable.admission,".TSViewInternalFragment"));
                data.add(new Item("Time Table", R.drawable.admission,".TSTimeTableViewerFragment"));
                data.add(new Item("Home Work Entry", R.drawable.admission,".HomeWOrkEntryFragment"));
                data.add(new Item("Requirements Request", R.drawable.admission,".MaterialRequirementRequestFragment"));
                data.add(new Item("Leave Request", R.drawable.admission,".StaffLeaveRequestFragment"));
                data.add(new Item("Events", R.drawable.admission,".StaffEventsFragment"));
                data.add(new Item("Reports", R.drawable.admission,".StaffReportsFragment"));

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
                data.add(new Item("Member Data", R.drawable.admission,".LibMemberDataFragment"));
                data.add(new Item("Books Data", R.drawable.admission,".LibBooksDataFragment"));
                data.add(new Item("CD Data", R.drawable.admission,".LibCDDataFragment"));
                data.add(new Item("Magazines Data", R.drawable.admission,".MagazinesDataFragment"));
                data.add(new Item("Books Purchase", R.drawable.admission,".BooksPurchaseFragment"));
                data.add(new Item("Books Availability", R.drawable.admission,".BooksAvailFragment"));
                data.add(new Item("Issuing Books", R.drawable.admission,".IssuingBooksFragment"));
                data.add(new Item("Returning Books", R.drawable.admission,".ReturningBooksFragment"));
                data.add(new Item("Library Usage", R.drawable.admission,".LibraryUsageFragment"));
                data.add(new Item("Reports", R.drawable.admission,".LibraryReportsfragment"));

                break;

            case "Hostel":
                data.add(new Item("Student Data", R.drawable.admission,".HostelStudDataFragment"));
                data.add(new Item("Facilities Data", R.drawable.admission,".HostelFacilitiesDataFragment"));
                data.add(new Item("Payment Data", R.drawable.admission,".HostelPayDataFragment"));
                data.add(new Item("Leave Data", R.drawable.admission,".HostelStudLeaveDataFragment"));
                data.add(new Item("Visitor Data", R.drawable.admission,".HostelVisitorDataFragment"));
                data.add(new Item("Food Data", R.drawable.admission,".HostelFoodDataFragment"));
                data.add(new Item("Medical Data", R.drawable.admission,".HostelMedicalDataFragment"));
                data.add(new Item("Remarks", R.drawable.admission,".HostelStudRemFragment"));
                data.add(new Item("Reports", R.drawable.reports,".HostelReportsFragment"));

                break;

            case "Parent":
                data.add(new Item("Profile", R.drawable.admission,".ParentProfileFragment"));
                data.add(new Item("Daily Attendance", R.drawable.admission,".ParentDailyAttfragment"));
                data.add(new Item("Test Performance", R.drawable.admission,".ParentTestPerFragment"));
                data.add(new Item("Exam Performance", R.drawable.admission,".ParentExamPerFragment"));
                data.add(new Item("Fees Payment", R.drawable.admission,".ParentFeesPaymentFragment"));
                data.add(new Item("Home Work", R.drawable.admission,".ParentHomeWorkFragment"));
                data.add(new Item("Leave Request", R.drawable.admission,".ParentLeaveReqFragment"));
                data.add(new Item("Events", R.drawable.events,".ParentEventFragment"));
                data.add(new Item("Feed Back", R.drawable.admission,".ParentFeedBackFragment"));
                data.add(new Item("Remarks", R.drawable.admission,".ParentRemarksFragment"));

                break;

            case "System Admin":
                data.add(new Item("Add User", R.drawable.admission,".AddUserFragment"));
                data.add(new Item("Modify User",R.drawable.admission,".ModifyUserFragment"));
                data.add(new Item("Remove User", R.drawable.admission,".RemoveUserFragment"));
                data.add(new Item("Reports", R.drawable.admission,".SysAdminReportsFragment"));

                break;
        }

    }
    String userRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("userRole")!=null){
            userRole = bundle.getString("userRole");
        }
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
            String selectedDetail="";
            String selectedFragmentName;
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item selectedDashboardTool= new Item();
                selectedDashboardTool=data.get(position);
                //testtext=testitem.getTitle();
                selectedFragmentName = selectedDashboardTool.getFragmentName();

//                Toast.makeText(DashboardActivity.this, "You Clicked at " + selectedFragmentName+", position:", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setClassName("com.example.vinothjoshua.siv", "com.example.vinothjoshua.siv"+nextActivityClassName);
//                startActivity(intent);
                Intent intent = new Intent(DashboardActivity.this,DetailsActivity.class);
                intent.putExtra("selectedFragmentName", selectedFragmentName);
                startActivity(intent);
            }
        });
    }

}
