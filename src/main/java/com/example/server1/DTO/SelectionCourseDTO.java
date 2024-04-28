package com.example.server1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionCourseDTO {
    private Integer id;
    private Integer courseID;
    private Integer selectionID;
}
