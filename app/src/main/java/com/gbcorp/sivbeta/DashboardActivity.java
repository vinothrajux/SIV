package com.gbcorp.sivbeta;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
//import org.eazegraph.lib.R;

/**
 * Created by Vinoth Joshua on 03-Apr-17.
 */

public class DashboardActivity extends AppCompatActivity {

    ArrayList<Item> data = new ArrayList<Item>();
    GridView grid;
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
            case "MGMT":

                data.add(new Item("Student Attendance",R.drawable.attendance,".AdmissionCounsellingFragment"));
                data.add(new Item("Staff Attendance",R.drawable.staff_attendance,".AdmissionCounsellingFragment"));
                data.add(new Item("School Fees Set",R.drawable.accounts1,".AdmissionCounsellingFragment"));
                data.add(new Item("Transport Fees Set",R.drawable.accounts1,".AdmissionCounsellingFragment"));
                data.add(new Item("Daily Fees Report",R.drawable.reports,".AdmissionCounsellingFragment"));
                data.add(new Item("Admission Status",R.drawable.accounts,".AdmissionCounsellingFragment"));
                data.add(new Item("Student Progress",R.drawable.performance,".AdmissionCounsellingFragment"));
                data.add(new Item("Class Progress",R.drawable.performance,".AdmissionCounsellingFragment"));
                data.add(new Item("Parent FeedBack",R.drawable.feedback,".AdmissionCounsellingFragment"));
                data.add(new Item("Reports",R.drawable.reports,".AdmissionCounsellingFragment"));

//                data.add(new Item("Admission Counselling",R.drawable.admission,".AdmissionCounsellingFragment"));
//                data.add(new Item("Admission Status", R.drawable.accounts,".AdmissionStatusFragment"));
//                data.add(new Item("College Fees Setting", R.drawable.accounts1,".CollegeFeesSettingFragment"));
//                data.add(new Item("Transport Fees Setting", R.drawable.accounts1,".TransportFeesSettingFragment"));
//                data.add(new Item("Hostel Fees Setting", R.drawable.accounts1,".HostelFeesSettingFragment"));
//                data.add(new Item("Food Fees Setting", R.drawable.accounts1,".HostelFeesSettingFragment"));
//                data.add(new Item("Staff Attendance", R.drawable.staff_attendance,".StaffAttendanceFragment"));
//                data.add(new Item("Student Attendance", R.drawable.attendance,".StudentAttendanceFragment"));
//                data.add(new Item("Staff Performance", R.drawable.performance,".StaffPerformanceFragment"));
//                data.add(new Item("Department Performance", R.drawable.performance,".DepartmentPerformanceFragment"));
//                data.add(new Item("Salary Calculation", R.drawable.salary,".SalaryCalculationFragment"));
//                data.add(new Item("Staff Requirement", R.drawable.admission,".StaffReqFragment"));
//                data.add(new Item("Locate Staff", R.drawable.locatestaff,"LocateStaffFragment"));
//                data.add(new Item("Admin Removal", R.drawable.adminremoval,".AdminRemovalFragment"));
//                data.add(new Item("Placement Performance", R.drawable.performance,".PlacementPerformFragment"));
//                data.add(new Item("Promote Staff", R.drawable.promotestaff,".PromoteStaffFragment"));
//                data.add(new Item("Relieve Staff", R.drawable.relievestaff,".RelieveStaffFragment"));
//                data.add(new Item("Approvals", R.drawable.approval,".ApprovalsFragment"));
//                data.add(new Item("Events", R.drawable.events,".EventsFragment"));
//                data.add(new Item("Transport Maintenance", R.drawable.transport,".TransportMaintenanceFragment"));
//                data.add(new Item("Hostel", R.drawable.hostel,".HostelManagementFragment"));
//                data.add(new Item("Reports", R.drawable.reports,".ReportsFragment"));
//                data.add(new Item("Sports Performance", R.drawable.sports,".SportsPerformFragment"));
                break;

            case "PRPL":
                data.add(new Item("Staff Attendance", R.drawable.staff_attendance,".PrincStaffAttendFragment"));
                data.add(new Item("Student Attendance", R.drawable.attendance,".PrincStudentAttendFragment"));
                data.add(new Item("Staff Performance", R.drawable.performance,".PrincStaffPerfFragment"));
                data.add(new Item("Department Performance", R.drawable.performance,".PrincDeptPerfFragment"));
                data.add(new Item("Staff Workload", R.drawable.workload,".PrincStaffWorkloadFragment"));
                data.add(new Item("Staff Requirement", R.drawable.admission,".PrincStaffReqFragment"));
                data.add(new Item("Locate Staff", R.drawable.locatestaff,".PrincLocateStaffFragment"));
                data.add(new Item("Hostel", R.drawable.hostel,".PrincHostelFragment"));
                data.add(new Item("Placement Performance", R.drawable.performance,".PrincPlacementPerfFragment"));
                data.add(new Item("Sports Performance", R.drawable.performance,".PrincSportPerfFragment"));
                break;

            //case "Office Admin":
            case "OADM":
                data.add(new Item("Admission", R.drawable.admission,".OffAdmissionFragment"));
                data.add(new Item("Student Data", R.drawable.student,".OfficeStudentInfoUpdFragment"));
                data.add(new Item("Staff Data", R.drawable.staff,".OfficeStaffInfoFragment"));
                data.add(new Item("Fees Counter", R.drawable.feespayment,".OffFeeCounterFragment"));
                data.add(new Item("Stores", R.drawable.stores,".OfficeStoresFragment"));
                data.add(new Item("Transport", R.drawable.transport,".OffTransportFragment"));
                data.add(new Item("Hostel", R.drawable.hostel,".OfficeHostelFragment"));
                data.add(new Item("Exam", R.drawable.exam,".OfficeExamFragment"));
                data.add(new Item("e-Governance", R.drawable.admission,".OffEGovernanceFragment"));
                data.add(new Item("Events", R.drawable.events,".OffEventHandlerFragment"));
                data.add(new Item("Circulars", R.drawable.circulars,".OffStenoCircularsFragment"));

                break;

            //case "Office Admission":
            case "OADMSN":
                //data.add(new Item("Application Sale", R.drawable.application,".ApplicationSaleViewFragment"));
                data.add(new Item("Application Sale", R.drawable.application,".ApplicationSaleViewPlaySchoolFragment"));
                data.add(new Item("Admission", R.drawable.admissionent,".AdmissionPlaySchoolViewFragment"));
                data.add(new Item("Cash Counter", R.drawable.feespayment,".FeesPaymentPlaySchoolViewFragment"));
                data.add(new Item("Transport Change", R.drawable.idcard,".TransportChangePlaySchoolViewFragment"));
                data.add(new Item("Events Update", R.drawable.events,".EventsPlaySchoolViewFragment"));
                data.add(new Item("Emergency Message", R.drawable.application,".EmergencyAlarmPlaySchoolFragment"));
                data.add(new Item("Name List", R.drawable.application,".EmergencyAlarmPlaySchoolFragment"));
                data.add(new Item("Staff Bio Data", R.drawable.personalinf,".StaffBioDataViewPlaySchoolFragment"));
                data.add(new Item("Reports", R.drawable.reports,".StaffBioDataViewPlaySchoolFragment"));

                //data.add(new Item("Admission Entry", R.drawable.admissionent,".AdmissionEntryFragment"));
                //data.add(new Item("Follow up", R.drawable.follower,".ApplicationFollowFragment"));
                //data.add(new Item("Reports", R.drawable.reports,".OffAdmissionReportFragment"));
                break;

            //case "Office Fees":
            case "FEES":
                data.add(new Item("Fees Payment",R.drawable.feespayment,".FeesPaymentFragment"));
                data.add(new Item("Fees Payment PS",R.drawable.feespayment,".FeesPaymentPlaySchoolViewFragment"));
                data.add(new Item("Exam Fees", R.drawable.examfees,".ExamFeesFragment"));
                data.add(new Item("Follow up", R.drawable.follow,".FollowupFeesFragment"));
                data.add(new Item("Reports", R.drawable.reports,".ReportFeeCounterFragment"));
                break;

            //case "Office Supdt":
            case "OSUPDT":
                data.add(new Item("Staff Personal PS", R.drawable.personalinf,".StaffBioDataViewPlaySchoolFragment"));
                data.add(new Item("Staff Search", R.drawable.personalinf,".StaffSearchFragment"));
                data.add(new Item("Staff Personal", R.drawable.personalinf,".StaffPersonalFragment"));
                data.add(new Item("Student Personal", R.drawable.personalinf,".StudentPersonalInformationFragment"));
                data.add(new Item("College ID", R.drawable.idcard,".CollegeIdCardFragment"));
                data.add(new Item("Bonafide", R.drawable.admission,".BonafideFragment"));
                data.add(new Item("TC", R.drawable.admission,".TcGenerateFragment"));
                data.add(new Item("Name List", R.drawable.admission,".NameListFragment"));
                data.add(new Item("First Aid", R.drawable.firstaid,".FirstAidEntryFragment"));
                data.add(new Item("Certificate Submission", R.drawable.certificate,".CertificateSubmissionViewFragment"));
                data.add(new Item("Scholarship", R.drawable.scholarship,".ScholarshipFragment"));
                data.add(new Item("Staff Academic", R.drawable.academic,".StaffAcademicFragment"));
                data.add(new Item("Willing Subjects", R.drawable.subject,".WillingSubjectsFragment"));
                data.add(new Item("Staff Transport", R.drawable.transport,".StaffTransportFragment"));
                data.add(new Item("Staff Attendance", R.drawable.staff_attendance,".OffSupdtStaffAttendanceFragment"));
                data.add(new Item("Leave Entry", R.drawable.admission,".StaffLeaveEntryFragment"));
                data.add(new Item("Nominal Entry", R.drawable.admission,".NominalEntryFragment"));
                data.add(new Item("Equivalence Subject Entry", R.drawable.admission,".EquivalenceSubjectEntryFragment"));
                data.add(new Item("Result Entry", R.drawable.admission,".ResultEntryFragment"));
                data.add(new Item("Ex-2 Entry", R.drawable.admission,".Ex2EntryFragment"));
                data.add(new Item("E-Gov Spell Entry", R.drawable.admission,".EgovSpellEntryFragment"));
                data.add(new Item("B-Forms", R.drawable.admission,".BformFragment"));
                data.add(new Item("B-Forms", R.drawable.admission,".BformActivity"));
                data.add(new Item("Reports", R.drawable.reports,".OfficeSupdtReportFragment"));
                break;

            //case "Office Steno":
            case "OSTN":
                data.add(new Item("Events PS", R.drawable.fees,".EventsPlaySchoolViewFragment"));
                data.add(new Item("Fees Events", R.drawable.fees,".FeesEventsActivity"));
                data.add(new Item("Branch Events", R.drawable.branches,".BranchEventsActivity"));
                data.add(new Item("Exam Events", R.drawable.exam,".ExamEventsActivity"));
                data.add(new Item("Placement Events", R.drawable.placement,".PlacementEventsActivity"));
                data.add(new Item("Sports Events", R.drawable.sports,".SportsEventsActivity"));
                data.add(new Item("NSS Events", R.drawable.nss,".NSSEventsActivity"));
                data.add(new Item("Management Events", R.drawable.events,".ManagementEventsActivity"));
                data.add(new Item("Fees Circular", R.drawable.circulars,".FeesCircularActivity"));
                data.add(new Item("Branch Circular", R.drawable.circulars,".BranchCircularActivity"));
                data.add(new Item("Exam Circular", R.drawable.circulars,".ExamCircularActivity"));
                data.add(new Item("Placement Circular", R.drawable.circulars,".PlacementCircularActivity"));
                data.add(new Item("Sports Circular", R.drawable.circulars,".SportsCircularActivity"));
                data.add(new Item("Management Circular", R.drawable.circulars,".ManagementCircularActivity"));
                data.add(new Item("Reports", R.drawable.reports,".OfficeStenoReportsActivity"));
                break;

            //case "Office Stores":
            case "OSTO":
                data.add(new Item("Stores Vendor", R.drawable.vendor,".VendorDetailsFragment"));
                data.add(new Item("Stores Purchase", R.drawable.stores,".PurchaseFragment"));
                data.add(new Item("Stores Sales", R.drawable.sale,".SalesFragment "));
                data.add(new Item("Reports", R.drawable.reports,".OfficeManagerReportsActivity"));

                break;
            //case "Office Transport" :
            case "OTRAN" :
                data.add(new Item("Transport ID", R.drawable.idcard,".TransportIdFragment"));
                data.add(new Item("Transport Change PS", R.drawable.idcard,".TransportChangePlaySchoolViewFragment"));
                data.add(new Item("Route Change", R.drawable.admission,".RouteChangeFragment"));
                data.add(new Item("MTC Concession", R.drawable.admission,".MTCConcessionFragment"));
                data.add(new Item("Train Concession", R.drawable.admission,".TrainConcessionFragment"));
                data.add(new Item("Route Verification", R.drawable.admission,".RouteVerificationFragment"));
                data.add(new Item("Fuel Filling", R.drawable.fuel,".FuelFillingFragment"));
                data.add(new Item("Transport Insurance", R.drawable.admission,".TransportInsuranceFragment"));
                data.add(new Item("Transport FC", R.drawable.admission,".TransportFCFragment"));
                data.add(new Item("Driver Data", R.drawable.driver,".DriverDataFragment"));
                data.add(new Item("Requirement Request", R.drawable.requiredkit,".TransRequirementReqFragment"));
                data.add(new Item("Reports", R.drawable.reports,".TransReportFragment"));
                break;



            //case "Teaching Staff":
            case "TSTAFF":
                data.add(new Item("Home Work Entry PS", R.drawable.homework,".HomeWorkPlaySchoolViewFragment"));
                data.add(new Item("Childs Pickup PS", R.drawable.homework,".ChildPickupViewPlaySchoolFragment"));
                data.add(new Item("Childs Progress PS", R.drawable.homework,".IndividualStudentProgressPlaySchoolViewFragment"));
                data.add(new Item("Attendance Entry", R.drawable.attendance,".IndividualStudentProgressPlaySchoolViewFragment"));
                data.add(new Item("Photo Upload", R.drawable.homework,".PhotoGalleryUploadPlaySchoolFragment"));
                data.add(new Item("Video Upload", R.drawable.homework,".IndividualStudentProgressPlaySchoolViewFragment"));

//                data.add(new Item("Student Attendance", R.drawable.attendance,".TeachstaffStudAttEntryFragment"));
//                data.add(new Item("Exam Mark Entry", R.drawable.admission,".TSExamMarkEntryFragment"));
//                data.add(new Item("View Internal Mark", R.drawable.internal,".TSViewInternalFragment"));
//                data.add(new Item("Time Table", R.drawable.timetable,".TSTimeTableViewerFragment"));
//                data.add(new Item("Home Work Entry", R.drawable.homework,".HomeWOrkEntryFragment"));
//                data.add(new Item("Requirements Request", R.drawable.admission,".MaterialRequirementRequestFragment"));
//                data.add(new Item("Leave Request", R.drawable.leaveletter,".StaffLeaveRequestFragment"));
//                data.add(new Item("Events", R.drawable.events,".StaffEventsFragment"));
//                data.add(new Item("Reports", R.drawable.reports,".StaffReportsFragment"));
//                data.add(new Item("Image Upload", R.drawable.reports,".PhotoGalleryUploadPlaySchoolFragment"));

                break;


            case "HOD":
                data.add(new Item("Student Attendance", R.drawable.attendance,".StudentAttEntryActivity"));
                data.add(new Item("Subject Entry", R.drawable.subject,".SubjectEntryActivity"));
                data.add(new Item("Subject Allocation", R.drawable.subject,".SubjectAllocationActivity"));
                data.add(new Item("Time Table", R.drawable.timetable,".TimeTableViewerActivity"));
                data.add(new Item("Exam Time Table", R.drawable.exam,".ExamTimeTableActivity"));
                data.add(new Item("Exam Mark Entry", R.drawable.exam,".ExamMarkEntryActivity"));
                data.add(new Item("Question Set", R.drawable.question,".QuestionSetActivity"));
                data.add(new Item("Attendance Entry Monitor", R.drawable.monitor,".AttEntryMonitorActivity"));
                data.add(new Item("Student Remarks", R.drawable.admission,".StudentsRemarksActivity"));
                data.add(new Item("Staff Leave Approval", R.drawable.approval,".StaffLeaveApprovalActivity"));
                data.add(new Item("Home Work Entry", R.drawable.homework,".HomeWOrkEntryActivity"));
                data.add(new Item("Requirements Request", R.drawable.admission,".MaterialRequirementRequestActivity"));
                data.add(new Item("Leave Request", R.drawable.leaveletter,".StaffLeaveRequestActivity"));
                data.add(new Item("Events", R.drawable.events,".StaffEventsActivity"));
                data.add(new Item("Reports", R.drawable.reports,".StaffReportsActivity"));
                break;

            //case "Placement officer":
            case "PLO":
                data.add(new Item("Corporates Detail", R.drawable.corporates,".CorporateDetActivity"));
                data.add(new Item("Campus Request", R.drawable.campusreq,".CampusReqActivity"));
                data.add(new Item("Schedules", R.drawable.schedule,".SchedulesActivity"));
                data.add(new Item("Followup", R.drawable.follow,".FollowupActivity"));
                data.add(new Item("Seminars", R.drawable.seminars,".SeminarsActivity"));
                data.add(new Item("Inplant Training", R.drawable.inplant,".InplantActivity"));
                data.add(new Item("Job Links", R.drawable.joblink,".JobLinksActivity"));
                data.add(new Item("Reports", R.drawable.reports,".PlacementReportsActivity"));

                break;

            //case "Sports":
            case "SPRT":
                data.add(new Item("Athelete Data", R.drawable.athlete,".AtheleteDataActivity"));
                data.add(new Item("Tournament Data", R.drawable.tournament,".TournamentDataActivity"));
                data.add(new Item("Kits Stock", R.drawable.stockkit,".KitsStockActivity"));
                data.add(new Item("Kits Requirement", R.drawable.requiredkit,".KitsReqActivity"));
                data.add(new Item("O.D.Form", R.drawable.leaveletter,".ODFormActivity"));
                data.add(new Item("Reports", R.drawable.reports,".SportsReportsActivity"));

                break;

            //case "Library":
            case "LIB":
                data.add(new Item("Member Data", R.drawable.profile,".LibMemberDataFragment"));
                data.add(new Item("Books Data", R.drawable.book,".LibBooksDataFragment"));
                data.add(new Item("CD Data", R.drawable.cd,".LibCDDataFragment"));
                data.add(new Item("Magazines Data", R.drawable.magazine,".MagazinesDataFragment"));
                data.add(new Item("Books Purchase", R.drawable.purchase,".BooksPurchaseFragment"));
                data.add(new Item("Books Availability", R.drawable.admission,".BooksAvailFragment"));
                data.add(new Item("Issuing Books", R.drawable.admission,".IssuingBooksFragment"));
                data.add(new Item("Returning Books", R.drawable.returnbook,".ReturningBooksFragment"));
                data.add(new Item("Library Usage", R.drawable.admission,".LibraryUsageFragment"));
                data.add(new Item("Reports", R.drawable.reports,".LibraryReportsfragment"));

                break;

            //case "Hostel":
            case "HOS":
                data.add(new Item("Student Data", R.drawable.student,".HostelStudDataFragment"));
                data.add(new Item("Facilities Data", R.drawable.facility,".HostelFacilitiesDataFragment"));
                data.add(new Item("Payment Data", R.drawable.feespayment,".HostelPayDataFragment"));
                data.add(new Item("Leave Data", R.drawable.leaveletter,".HostelStudLeaveDataFragment"));
                data.add(new Item("Visitor Data", R.drawable.visitor,".HostelVisitorDataFragment"));
                data.add(new Item("Food Data", R.drawable.food,".HostelFoodDataFragment"));
                data.add(new Item("Medical Data", R.drawable.medical,".HostelMedicalDataFragment"));
                data.add(new Item("Remarks", R.drawable.admission,".HostelStudRemFragment"));
                data.add(new Item("Reports", R.drawable.reports,".HostelReportsFragment"));

                break;

            //case "Parent":
            case "STUD":
                data.add(new Item("Profile", R.drawable.profile,".PlaySchoolStudentProfileViewFragment"));
                data.add(new Item("Attendance", R.drawable.attendance,".DailyAttendanceViewParentPlaySchoolFragment"));
                data.add(new Item("Pickup Information", R.drawable.pickup,".ChildPickupPlaySchoolParentViewFragment"));
//                data.add(new Item("Photo Upload", R.drawable.profile,".PhotoGalleryUploadPlaySchoolFragment"));
                data.add(new Item("Photo Gallery", R.drawable.photogallery,".PhotoGalleryPlaySchoolFragment"));
                data.add(new Item("Video Gallery", R.drawable.videogallery,".VideoGalleryPlaySchoolFragment"));
                data.add(new Item("Fees Payment", R.drawable.feespayment,".FeesPaymentPlaySchoolViewFragment"));
                data.add(new Item("Home Work", R.drawable.homework,".ParentHomeWorkFragment"));
                data.add(new Item("Events", R.drawable.events,".ParentEventCircularsPlayschoolViewFragment"));
                data.add(new Item("Progress", R.drawable.performance,".IndividualStudentProgressPlaySchoolViewFragment"));


//                data.add(new Item("Profile", R.drawable.profile,".ParentProfileViewFragment"));
//                data.add(new Item("Daily Attendance", R.drawable.attendance,".ParentDailyAttfragment"));
//                data.add(new Item("Test Performance", R.drawable.performance,".ParentTestPerFragment"));
//                data.add(new Item("Exam Performance", R.drawable.performance,".ParentExamPerFragment"));
//                data.add(new Item("Fees Payment", R.drawable.feespayment,".ParentFeesPaymentFragment"));
//                data.add(new Item("Home Work", R.drawable.homework,".ParentHomeWorkFragment"));
//                data.add(new Item("Leave Request", R.drawable.leaveletter,".ParentLeaveReqFragment"));
//                data.add(new Item("Events", R.drawable.events,".ParentEventFragment"));
//                data.add(new Item("Feed Back", R.drawable.feedback,".ParentFeedBackFragment"));
//                data.add(new Item("Remarks", R.drawable.admission,".ParentRemarksFragment"));

                break;

            //case "System Admin":
            case "SYSADM":
                data.add(new Item("Add User", R.drawable.adduser,".AddUserFragment"));
                data.add(new Item("Modify User",R.drawable.edituser,".ModifyUserFragment"));
                data.add(new Item("Remove User", R.drawable.relievestaff,".RemoveUserFragment"));
                data.add(new Item("Reports", R.drawable.reports,".SysAdminReportsFragment"));

                break;
        }

    }
    String userRole;
    String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    public DashboardActivity() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initViews();
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("userRole")!=null){
            userRole = bundle.getString("userRole");
        }
        fillData();


        //menu = new String[]{"Home","Android","Windows","Linux","Raspberry Pi","WordPress","Videos","Contact Us"};
        Item generateMenuList= new Item();

        ArrayList<String> menulist = new ArrayList<String>();
        for (int i = 0; i < data.size(); i++) {
            generateMenuList=data.get(i);
            menulist.add(generateMenuList.getTitle());
        }
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menulist);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();
//                Bundle args = new Bundle();
//                args.putString("Menu", menu[position]);
//                Fragment detail = new DetailFragment();
//                detail.setArguments(args);
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
                Item generateMenuListMenu= new Item();
                generateMenuListMenu=data.get(position);
                String selectedFragmentName = generateMenuListMenu.getFragmentName();
                String selectedFragmentTitle = generateMenuListMenu.getTitle();
                Intent intent = new Intent(DashboardActivity.this,DetailsActivity.class);
                intent.putExtra("selectedFragmentName", selectedFragmentName);
                intent.putExtra("selectedFragmentTitle", selectedFragmentTitle);
                startActivity(intent);

            }

        });







        DashboardGrid adapter = new DashboardGrid(DashboardActivity.this,R.layout.gridview_dashboardtool_single ,data , dashboardColors);
        grid=(GridView)findViewById(R.id.dashboardTools);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String selectedDetail="";
            String selectedFragmentName;
            String selectedFragmentTitle;
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item selectedDashboardTool= new Item();
                selectedDashboardTool=data.get(position);
                selectedFragmentName = selectedDashboardTool.getFragmentName();
                selectedFragmentTitle = selectedDashboardTool.getTitle();
                Intent intent = new Intent(DashboardActivity.this,DetailsActivity.class);
                intent.putExtra("selectedFragmentName", selectedFragmentName);
                intent.putExtra("selectedFragmentTitle", selectedFragmentTitle);
                startActivity(intent);
            }
        });


        //PieChart chart = (PieChart) findViewById(R.id.chart);
       //commented by me on 18.09.2017
        //mPieChart = (PieChart) findViewById(R.id.piechart);
        //loadData();
        //commented by me on 18.09.2017
    }
    private void initViews(){

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


//        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
//        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout ,  R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        //commented by me on 18.09.2017
        //mPieChart.startAnimation();
        //commented by me on 18.09.2017
    }

//    @Override
//    public void restartAnimation() {
//        mPieChart.startAnimation();
//    }
//
//    @Override
//    public void onReset() {
//
//    }
    //commented by me on 18.09.2017
//    private void loadData() {
//        mPieChart.addPieSlice(new PieModel("Absent", 15, Color.parseColor("#FE6DA8")));
//        mPieChart.addPieSlice(new PieModel("Present", 85, Color.parseColor("#56B7F1")));
//        mPieChart.addPieSlice(new PieModel("Work", 35, Color.parseColor("#CDA67F")));
    //commented by me on 18.09.2017
//        mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));

//        mPieChart.setOnItemFocusChangedListener(new IOnItemFocusChangedListener() {
//            @Override
//            public void onItemFocusChanged(int _Position) {
////                Log.d("PieChart", "Position: " + _Position);
//            }
//        });
    //commented by me on 18.09.2017
  //  }


 //   private PieChart mPieChart;

    //commented by me on 18.09.2017

}
