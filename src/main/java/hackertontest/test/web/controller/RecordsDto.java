package hackertontest.test.web.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RecordsDto {

    private long memberId;
    List<Integer> concentratedTime = new ArrayList<Integer>();
    private String timeStamp;
}
