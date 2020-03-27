package example.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class EmployeePostRecord {
    private String name;
    private String salary;
    private String age;

    public static class EmployeePostRecordBuilder {
        private String name = "";
        private String salary = "";
        private String age = "";
    }

}
