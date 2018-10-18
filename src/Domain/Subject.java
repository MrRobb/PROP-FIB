package Domain;

import java.util.Map;


public class Subject {
    public class asignatura {
        private String name;
        private Integer semester;
        private Integer credits;
        private Boolean mandatory;
        private String speciality;
        private Map<String,Integer> weeklyHours;

        public String getName() { return name; }

        public Integer getSemester() {
            return semester;
        }

        public Integer getCredits() {
            return credits;
        }

        public Boolean getMandatory() {
            return mandatory;
        }

        public String getSpeciality() {
            return speciality;
        }

        public Integer getWeeklyHours(String type) {

            return weeklyHours.get(type);
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSemester(Integer semester) {
            this.semester = semester;
        }

        public void setCredits(Integer credits) { this.credits = credits; }

        public void setMandatory(Boolean mandatory) {
            this.mandatory = mandatory;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public void addWeeklyHours(String type, Integer hours) {
            weeklyHours.put(type,hours);
        }
    }
}
