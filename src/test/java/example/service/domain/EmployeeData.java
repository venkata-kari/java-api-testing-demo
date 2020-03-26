package example.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmployeeData {
    private String id;
    private String employee_name;
    private String employee_salary;
    private String employee_age;
    private String profile_image;

    public static class EmployeeDetailsBuilder {
        private String id = "1";
        private String employee_name = "Mr XYZ";
        private String employee_salary = "86000";
        private String employee_age = "39";
        private String profile_image = "";
    }

}
