package com.grainacademy.backend.eduservice.entity.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectVo {
    private String id;
    private String label;
    private List<SubjectVo> children;

    public SubjectVo(String id, String title){
        this.id = id;
        this.label = title;
        this.children = new ArrayList<>();
    }

}
