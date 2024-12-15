import CampApplication.Camps.*;
import CampApplication.Utilities.*;
import CampApplication.users.*;
import java.util.Scanner;

//driver program to test our code
public class driver {
        public static void main(String[] args) {
                //Scanner scanner = new Scanner(System.in);
                Student student = new Student("kahyen","kahyen@gmail.com","SPMS","albc","abcd");
                
                Staff staff = new Staff("lifang","STAFF","SCSE","abc123","ablcd");     
                
                Camp camp = new Camp("yay", null, null, null, null, "lifang", 2, 2, null, null);
                Camp camp2 = new Camp("ok", null, null, null, null, "notlifang", 2, 2, null, null);
                //CampCommittee campcomp= new CampCommittee("name", "email", "faculty", "UserID", "password", camp,student);
                //staff.viewSuggestion(camp);
                //staff.approveSuggestion(camp);
                staff.addCamp(camp);
                staff.addCamp(camp2);
                student.setRegisteredCamp(camp, 0, true);
                camp.addAttendee(student);
                student.registerCampCommittee(camp2);
                // System.out.println(camp.getCampCommittee());
                // student.createEnquiry(camp);
                // student.viewEnquiry(camp);
                // camp.getCampCommittee().get(0).viewEnquiry(camp);
                // camp.getCampCommittee().get(0).replyEnquiry(camp);

                //camp.getCampCommittee().get(0).generateEnquiryReport(student);
                // staff.viewSuggestion(camp);
                // staff.approveSuggestion(camp);
                // staff.generatePerformanceReport(camp);
                //System.out.println(camp.getCampCommittee().get(0).getPoints());
                //CampMenuStudent menucamp = new CampMenuStudent();
                //menucamp(camp,student);
                
                //CampInformation campinfo = new CampInformation(null, null, null, null, "lifang");
                //staff.createCamp();
                //student.printCampDetails(camp);
                staff.generateReport(null, null,"lifang");
                //staff.generateEnquiryReport();
                //System.out.println(staff.getName() + "@@");
                //System.out.println(staff.Camps.get(0).getStaffInCharge() + "!!");
                //student.viewCamp(camp,6);
                //staff.editCamp(staff.Camps.get(0));
                //staff.getOwnCamplist();
                //staff.deleteCamp(staff.Camps.get(0));
                //staff.toggleVisibility(staff.Camps.get(0));
                //student.viewCamp();
}

}