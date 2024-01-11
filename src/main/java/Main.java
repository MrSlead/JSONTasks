import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import task1.InsuranceCompanies;
import task2.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Task 1
        File file = new File("./src/main/resources/companies.json");
        InsuranceCompanies insuranceCompanyList = objectMapper.readValue(file, InsuranceCompanies.class);
        insuranceCompanyList.getInsuranceCompany().forEach(System.out::println);


        // Task 2
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Mark");
        employee.setSkills(new ArrayList(Arrays.asList("Communication", "Teamwork", "Leadership")));
        String json = objectMapper.writeValueAsString(employee);

        try(FileWriter fileWriter = new FileWriter("./src/main/resources/employee.json", false)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
