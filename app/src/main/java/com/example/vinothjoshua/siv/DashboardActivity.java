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

            case "Office Staff":
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

            case "Teaching Staff":
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
