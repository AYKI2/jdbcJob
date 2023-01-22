package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private Long id;
    private String position;//("Mentor","Management","Instructor")
    private String profession;//("Java","JavaScript")
    private String description;//("Backend developer","Fronted developer")
    private int experience;//(1,2,3........) опыт работы

    public Job(String position, String profession, String description, int experience) {
        this.position = position;
        this.profession = profession;
        this.description = description;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "\nJob: " +
                "\nid = " + id +
                "\nposition = " + position +
                "\nprofession = " + profession +
                "\ndescription = " + description +
                "\nexperience = " + experience  + "\n";
    }
}
